package com.thinking.machines.webrock;
import java.lang.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import java.util.*;
import java.lang.reflect.Method;
import com.thinking.machines.webrock.annotations.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
public class WebRockStartup extends HttpServlet
{
/*
1.web.xml se param name nd value extract krna
2. jo value hai let's say bobby so jis packages ka naam bobby se start hota hai 
   a. usme check krna annotation laga hai ki nahi
   b. agar laga hai toh method me path annotation hai ky
   c. agar han toh WebRockModel ka object banake name->completeUrl value->Service class k object
3.WebRockModel k object ko application scope me set krdena hai
*/
/*
what i want-
1. all the packages name
2. list all the package which starts with prefix
3. get class name from each packages
4. check if annotation is applied on that class or not
5. if applied check annotation is applied on method or not
6. if applies then create object of WebRockModel name->completeURL value->Object of Service Class
7. put object of WebRockModel in application scope
*/
//init method will get executed on startUp
public void init()
{
String mainDirectoryPath= "c:\\tomcat9\\webapps\\webrock\\WEB-INF\\classes";
ServletConfig servletConfig=getServletConfig(); 
String servicePackagePrefix=servletConfig.getInitParameter("SERVICE_PACKAGE_PREFIX");
//System.out.println(servicePackagePrefix);
//to get all the className along with packages
Vector<String> className=scanAllTheClasses(mainDirectoryPath,servicePackagePrefix);
//run all the methods which have @OnStartup annotation applied
runMethodsOnStartup(className);
//to get object of webRockModel
WebRockModel webRockModel=populateHashMap(className);
//set object of webRockModel in Application Scope
ServletContext servletContext =getServletContext();
servletContext.setAttribute("webRockModel",webRockModel);
//getSize() method will return size of hashMap
System.out.println("WebRockModel "+((WebRockModel)servletContext.getAttribute("webRockModel")).getSize());
}



private void runMethodsOnStartup(Vector<String> className)
{
try
{
List<Service> list=new ArrayList<>();
for(String clname:className)
{
int lastIndex=clname.lastIndexOf(".class");
int firstIndex=0;
String name=clname.substring(firstIndex,lastIndex);
System.out.println("Substring is "+name);
Class myClass = Class.forName(name);           
Class onStartupAnnotation=Class.forName("com.thinking.machines.webrock.annotations.OnStartup");
Method[] methods=myClass.getMethods();
for(Method method:methods)
{
if(method.getAnnotation(onStartupAnnotation)!=null)
{
if(method.getParameterCount()==0 && method.getReturnType().getName().equals("void"))
{
int priority=((OnStartup)method.getAnnotation(onStartupAnnotation)).priority();
Service service=new Service();
service.setRunOnStartup(true);
service.setPriority(priority);
service.setService(method);
service.setServiceClass(myClass);
if(priority>0)list.add(service);
}
else System.out.println("Wrong annotation applied method should have 0 paramater and void return type ");
}

}//method loop ends here
}//class loop ends here

//sort the arrayList based on priority
Collections.sort(list,new Comparator<Service>(){
public int compare(Service a,Service b)
{
return a.getPriority()-b.getPriority();
}
});
//execute all the methods in the list
for(Service service:list)
{
System.out.println(service.getPriority());
Class name=service.getServiceClass();
Method method=service.getService();
method.invoke(name.newInstance());
}
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}//function runMethodsOnStartup ends here


//webRockModel will return WebRockModel object
private WebRockModel populateHashMap(Vector<String> className)
{
WebRockModel webRockModel=new WebRockModel();
try 
{
//for(int i=0;i<className.size();i++)System.out.println(className.get(i));
Class pathAnnotation=Class.forName("com.thinking.machines.webrock.annotations.Path");
Class GETAnnotation=Class.forName("com.thinking.machines.webrock.annotations.GET");
Class POSTAnnotation=Class.forName("com.thinking.machines.webrock.annotations.POST");
Class forwardToAnnotation=Class.forName("com.thinking.machines.webrock.annotations.ForwardTo");
Class injectApplicationDirectoryAnnotation=Class.forName("com.thinking.machines.webrock.annotations.InjectApplicationDirectory");
Class injectApplicationScopeAnnotation=Class.forName("com.thinking.machines.webrock.annotations.InjectApplicationScope");
Class injectRequestScopeAnnotation=Class.forName("com.thinking.machines.webrock.annotations.InjectRequestScope");
Class injectSessionScopeAnnotation=Class.forName("com.thinking.machines.webrock.annotations.InjectSessionScope");
Class autoWiredAnnotation=Class.forName("com.thinking.machines.webrock.annotations.AutoWired");
Class requestParameterAnnotation=Class.forName("com.thinking.machines.webrock.annotations.RequestParameter");
Class injectRequestParameterAnnotation=Class.forName("com.thinking.machines.webrock.annotations.InjectRequestParameter");

for(int i=0;i<className.size();i++)
{
List<AutoWiredField> autoWiredList=new ArrayList<AutoWiredField>();
List<InjectRequestParameterPojo> injectRequestParameters=new ArrayList<InjectRequestParameterPojo>(); 
String classPathAnnotationValue="";
// returns the Class object for this class
int lastIndex=className.get(i).lastIndexOf(".class");
int firstIndex=0;
String name=className.get(i).substring(firstIndex,lastIndex);
System.out.println("Substring is "+name);
Class myClass = Class.forName(name);           
boolean isGetAllowed=false;
boolean isPostAllowed=false;
boolean injectSessionScope=false;
boolean injectApplicationDirectory=false;
boolean injectApplicationScope=false;
boolean injectRequestScope=false;
if(myClass.getAnnotation(pathAnnotation)!=null)
{
classPathAnnotationValue=((Path)myClass.getAnnotation(pathAnnotation)).value();
System.out.println(classPathAnnotationValue);
//path annotation is applied to an class
if(myClass.getAnnotation(GETAnnotation)!=null)isGetAllowed=true;
if(myClass.getAnnotation(POSTAnnotation)!=null)isPostAllowed=true;
if(myClass.getAnnotation(injectSessionScopeAnnotation)!=null)injectSessionScope=true;
if(myClass.getAnnotation(injectApplicationScopeAnnotation)!=null)injectApplicationScope=true;
if(myClass.getAnnotation(injectRequestScopeAnnotation)!=null)injectRequestScope=true;
if(myClass.getAnnotation(injectApplicationDirectoryAnnotation)!=null)injectApplicationDirectory=true;
}else continue;

if(myClass.getAnnotation(pathAnnotation)!=null)
{
Field[] fields=myClass.getDeclaredFields();
for(Field field:fields)
{
if(field.getAnnotation(autoWiredAnnotation)!=null)
{
//create AutoWiredField object and add it to the autoWiredList
AutoWiredField autoWiredField=new AutoWiredField();
autoWiredField.setName(((AutoWired)field.getAnnotation(autoWiredAnnotation)).name());
autoWiredField.setVariableName(field.getName());
autoWiredField.setDataType(field.getType());
autoWiredList.add(autoWiredField);
}
if(field.getAnnotation(injectRequestParameterAnnotation)!=null)
{
InjectRequestParameterPojo injectRequestParameterPojo=new InjectRequestParameterPojo();
injectRequestParameterPojo.setName(((InjectRequestParameter)field.getAnnotation(injectRequestParameterAnnotation)).value());
injectRequestParameterPojo.setVariableName(field.getName());
injectRequestParameterPojo.setDataType(field.getType());
injectRequestParameters.add(injectRequestParameterPojo);
}
}
System.out.println("Size of autoWiredList is "+autoWiredList.size());
System.out.println("Size of inejctRequestParameter is "+injectRequestParameters.size());

}

Method[] methods=myClass.getMethods();
for(Method m:methods)
{
String methodPathAnnotationValue="";
if(m.getAnnotation(pathAnnotation)!=null)
{
//path annotation is applied
methodPathAnnotationValue=((Path)m.getAnnotation(pathAnnotation)).value();
System.out.println(methodPathAnnotationValue);

//check if annotation is applied to method parameters or not
List<RequestParameterPojo> requestParameterList=new ArrayList<RequestParameterPojo>();

Parameter[] parameters=m.getParameters();
int requestParameterAnnotationCount=0;
int allowedParamCount=0;
int isJsonCount=0;
for(Parameter p:parameters)
{
//check if RequestParameter annotation is applied to parameter or not
RequestParameterPojo requestParameterPojo;
if(p.getAnnotation(requestParameterAnnotation)!=null)
{
requestParameterPojo=new RequestParameterPojo();
requestParameterPojo.setName(((RequestParameter)p.getAnnotation(requestParameterAnnotation)).value());
requestParameterPojo.setDataType(p.getType());
requestParameterList.add(requestParameterPojo);
requestParameterAnnotationCount++;
}
else
{
//check if the parameter is of any type SessionScope,RequestScope,ApplicationDirectory,ApplicationScope
String paramName=p.getType().getSimpleName();
if(paramName.equals("ApplicationScope") || paramName.equals("RequestScope") || paramName.equals("SessionScope") || paramName.equals("ApplicationDirectory"))
{
allowedParamCount++;
requestParameterPojo=new RequestParameterPojo();
requestParameterPojo.setDataType(p.getType());
requestParameterList.add(requestParameterPojo);
}
else if( p.getType().isPrimitive()==false && paramName.equals("String")==false)
{
//not string not primitive
isJsonCount++;
requestParameterPojo=new RequestParameterPojo();
requestParameterPojo.setDataType(p.getType());
requestParameterList.add(requestParameterPojo);
}
//System.out.println("iS primitive"+p.isPrimitive());
}//else ends here
}//for Parameter loop ends here

System.out.println("Size of request parameter list is "+requestParameterList.size());

Service service=new Service();
service.setUrl(classPathAnnotationValue+methodPathAnnotationValue);
service.setService(m);
service.setServiceClass(myClass);
service.setAutoWiredFields(autoWiredList);
service.setInjectRequestParameters(injectRequestParameters);
int n=m.getParameterCount();
service.setRequestParameters(requestParameterList);
if(isJsonCount==1)service.setIsJson(true);
System.out.println("Is Json"+service.getIsJson());
if(m.getAnnotation(forwardToAnnotation)!=null)
{
String forwardTo=((ForwardTo)m.getAnnotation(forwardToAnnotation)).value();
service.setForwardTo(forwardTo);
System.out.println(forwardTo);
}
if(isGetAllowed)service.setIsGetAllowed(true);
if(isPostAllowed)service.setIsPostAllowed(true);
if(isGetAllowed==false && isPostAllowed==false)
{
//@GET or @POST not specified to an class
if(m.getAnnotation(POSTAnnotation)!=null)service.setIsPostAllowed(true);
if(m.getAnnotation(GETAnnotation)!=null)service.setIsGetAllowed(true);
if(m.getAnnotation(GETAnnotation)==null && m.getAnnotation(POSTAnnotation)==null)
{
//nothing is mentioned in method too so 
service.setIsGetAllowed(true);
service.setIsPostAllowed(true);
}
}
service.setInjectSessionScope(injectSessionScope);
service.setInjectApplicationScope(injectApplicationScope);
service.setInjectRequestScope(injectRequestScope);
service.setInjectApplicationDirectory(injectApplicationDirectory);
System.out.println("Class Name "+service.getServiceClass()+"Class Method"+service.getService()+"Get Request : "+service.getIsGetAllowed());
System.out.println("Class NAme "+service.getServiceClass()+"Class Method"+service.getService()+"Post Request : "+service.getIsPostAllowed());
System.out.println("Json count"+isJsonCount+"allowedParamCount"+allowedParamCount+"Request ann count "+requestParameterAnnotationCount);
if((isJsonCount==1 && allowedParamCount==n-1) || (isJsonCount==0 && allowedParamCount==n-requestParameterAnnotationCount))
{
webRockModel.add(classPathAnnotationValue+methodPathAnnotationValue,service);
}
else
{
System.out.println("Method has invalid parameters");
}
}
}//for methods ends here
}//Iteration of className ends here
}
catch (Exception e)
{
System.out.println(e.getMessage());
}
return webRockModel;
}//populateHashMap method ends here


//scanAllTheClasses function scans and returns all the classes which lie in servicePackagePrefix
private Vector<String> scanAllTheClasses(String mainDirectoryPath,String servicePackagePrefix)
{
Stack<File> s=new Stack<>();
Vector<File> v=new Vector<File>();
File file=new File(mainDirectoryPath);
s.push(file);
int z=0;
while(!s.empty())
{
File tmpF=s.pop();
if(tmpF.isFile())
{
v.add(tmpF);
}
else if(tmpF.isDirectory())
{
File[] f=tmpF.listFiles();
for(File fpp:f)
{
s.push(fpp);
}
}
}
Vector<String> className=new Vector<>();
for(int i=0;i<v.size();i++)
{
if(v.get(i).getName().contains(".class"))
{
String g[]=v.get(i).getAbsolutePath().split("classes.");
if(g[1].contains(servicePackagePrefix))className.add(g[1].replaceAll("\\\\","."));
} 
}//for loops ends here
return className;
}//scanAllTheClasses function ends here

}