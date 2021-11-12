package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.model.*;
import java.util.*;
import java.lang.reflect.*;
import java.io.*;
public class TMWebRock extends HttpServlet
{
public boolean forwardedFromPOST=false;
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
/*
System.out.println("TMWebRock got called");
System.out.println(request.getRequestURI());
System.out.println(request.getContextPath());
System.out.println(request.getServletPath());
System.out.println(request.getServletPath().length());
*/
ServletContext servletContext=getServletContext();;
String key;
WebRockModel webRockModel;
Map<String,Service> map;
Service service;
int indexOfMappedURL;
indexOfMappedURL=request.getRequestURI().indexOf(request.getServletPath());
key=request.getRequestURI().substring(indexOfMappedURL+request.getServletPath().length());
//System.out.println(key);
Object obj;
List<AutoWiredField> autoWiredFields;
List<RequestParameterPojo> requestParameters;
List<InjectRequestParameterPojo> injectRequestParameters;
//Access webRockModel from application scope
try
{
webRockModel=(WebRockModel)servletContext.getAttribute("webRockModel");
map=webRockModel.getHashMap();
}catch(Exception e)
{
System.out.println(e.getMessage());
return;
}
System.out.println(map.size());
System.out.println(map.containsKey(key));
try
{
//throw some exception if key not exist 
service=map.get(key);
System.out.println(service.getUrl());
System.out.println(service.getService());
System.out.println(service.getServiceClass());

if(this.forwardedFromPOST==true)
{
//if only get is allowed then throw some exception
if(service.getIsGetAllowed()==true && service.getIsPostAllowed()==false)
{
//only get is allowed
System.out.println("request is of Get type but only post request is allowed hence throw some exception");
}
}
else
{
//not forwarded from post that means only get request is allowed 
if(service.getIsPostAllowed()==true && service.getIsGetAllowed()==false)
{
//request is of Post type but only get request is allowed hence throw some exception
System.out.println("request is of Post type but only get request is allowed hence throw some exception");
}
}
obj=service.getServiceClass().newInstance();
System.out.println(obj);
Class[] parameterClass=new Class[1];
//invoke setter method if injection is applied
if(service.getInjectSessionScope())
{
parameterClass[0]=Class.forName("com.thinking.machines.webrock.pojo.SessionScope");
//execute setter of that class
Method method=service.getServiceClass().getMethod("setSessionScope",parameterClass);
System.out.println("Method :"+method.getName());
SessionScope sessionScope=new SessionScope();
sessionScope.setAttribute(request.getSession());
method.invoke(obj,sessionScope);
System.out.println("Session Scope in servlet "+sessionScope.getAttribute());	
}
if(service.getInjectApplicationScope())
{
parameterClass[0]=Class.forName("com.thinking.machines.webrock.pojo.ApplicationScope");
//execute setter of that class
Method method=service.getServiceClass().getMethod("setApplicationScope",parameterClass);
System.out.println("Method :"+method.getName());
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setAttribute(getServletContext());
method.invoke(obj,applicationScope);
System.out.println("Application Scope in servlet "+applicationScope.getAttribute());	
}
if(service.getInjectRequestScope())
{
parameterClass[0]=Class.forName("com.thinking.machines.webrock.pojo.RequestScope");
//execute setter of that class
Method method=service.getServiceClass().getMethod("setRequestScope",parameterClass);
System.out.println("Method :"+method.getName());
RequestScope requestScope=new RequestScope();
requestScope.setAttribute(request);
method.invoke(obj,requestScope);
System.out.println("Application Scope in servlet "+requestScope.getAttribute());	
}
/*
if(service.getInjectApplicationDirectory())
{
parameterClass[0]=Class.forName("com.thinking.machines.webrock.pojo.ApplicationDirectory");
//execute setter of that class
Method method=service.getServiceClass().getMethod("setApplicationDirectory",parameterClass);
System.out.println("Method :"+method.getName());
ApplicationDirectory applicationDirectory=new ApplicationDirectory();
applicationDirectory.setDirectory(new File());
method.invoke(obj,applicationDirectory);
System.out.println("Application Directory in servlet "+applicationDirectory.getDirectory());	
}
*/
System.out.println("request class is " + service.getServiceClass());

injectRequestParameters=service.getInjectRequestParameters();
for(InjectRequestParameterPojo injectRequestParameter:injectRequestParameters)
{
String name=injectRequestParameter.getName();
String dataType=injectRequestParameter.getDataType().getSimpleName();
String methodName="set"+injectRequestParameter.getVariableName().substring(0,1).toUpperCase()+injectRequestParameter.getVariableName().substring(1,injectRequestParameter.getVariableName().length());
System.out.println("methodName is"+methodName);
Method injectRequestParameterSetterMethod=service.getServiceClass().getMethod(methodName,injectRequestParameter.getDataType());
Object [] injectRequestParameterParam=new Object[1];

if(request.getParameter(name)!=null)
{
if(dataType.equals("String"))
{
injectRequestParameterParam[0]=request.getParameter(name);
}
else if(dataType.equals("int") || dataType.equals("Integer"))
{
//is of intInteger type
try
{
injectRequestParameterParam[0]=Integer.parseInt(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Integer");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("byte") || dataType.equals("Byte"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Byte.parseByte(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Byte");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("short") || dataType.equals("Short"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Short.parseShort(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Short");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("long") || dataType.equals("Long"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Long.parseLong(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Long");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("float") || dataType.equals("Float"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Float.parseFloat(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Float");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("double") || dataType.equals("Double"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Double.parseDouble(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Double");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("boolean") || dataType.equals("Boolean"))
{
//is of int/Integer type
try
{
injectRequestParameterParam[0]=Boolean.parseBoolean(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Boolean");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("char") || dataType.equals("Character"))
{
try
{
injectRequestParameterParam[0]=(request.getParameter(name)).charAt(0);
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Character");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}

}//if ends here

injectRequestParameterSetterMethod.invoke(obj,injectRequestParameterParam);
}//inject parameter loop ends here

//for testing purpose set xyz in application scope
servletContext.setAttribute("xyz",service.getServiceClass().newInstance());
autoWiredFields=service.getAutoWiredFields();
for(AutoWiredField autoWiredField:autoWiredFields)
{
String name=autoWiredField.getName();
String methodName="set"+autoWiredField.getVariableName().substring(0,1).toUpperCase()+autoWiredField.getVariableName().substring(1,autoWiredField.getVariableName().length());
System.out.println("Method name is "+methodName);
Class dataType=autoWiredField.getDataType();
Method autoWiredSetterMethod=service.getServiceClass().getMethod(methodName,autoWiredField.getDataType());
if(request.getAttribute(name)!=null && dataType.isInstance(request.getAttribute(name))==true)
{
//found in request scope and data type of data in request scope and autoWiredFields is same
autoWiredSetterMethod.invoke(obj,request.getAttribute(name));
}
else if(request.getSession().getAttribute(name)!=null && dataType.isInstance(request.getSession().getAttribute(name)))
{
//found in session 
autoWiredSetterMethod.invoke(obj,request.getSession().getAttribute(name));
}
else if(servletContext.getAttribute(autoWiredField.getName())!=null && dataType.isInstance(servletContext.getAttribute(autoWiredField.getName())))
{
System.out.println("ok");
autoWiredSetterMethod.invoke(obj,servletContext.getAttribute(name));
System.out.println("ok");
}
}

requestParameters=service.getRequestParameters();
Object [] param=new Object[requestParameters.size()];
int i=0;
for(RequestParameterPojo requestParameter:requestParameters)
{
String name=requestParameter.getName();
//check if it is present in requestScope
String dataType=requestParameter.getDataType().getSimpleName();

if(request.getParameter(name)!=null)
{
System.out.println("it is "+request.getParameter(name));
System.out.println("data type is"+dataType);
if(dataType.equals("String"))
{
param[i]=request.getParameter(name);
}
else if(dataType.equals("int") || dataType.equals("Integer"))
{
//is of intInteger type
try
{
param[i]=Integer.parseInt(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Integer");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("byte") || dataType.equals("Byte"))
{
//is of int/Integer type
try
{
param[i]=Byte.parseByte(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Byte");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("short") || dataType.equals("Short"))
{
//is of int/Integer type
try
{
param[i]=Short.parseShort(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Short");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("long") || dataType.equals("Long"))
{
//is of int/Integer type
try
{
param[i]=Long.parseLong(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Long");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("float") || dataType.equals("Float"))
{
//is of int/Integer type
try
{
param[i]=Float.parseFloat(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Float");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("double") || dataType.equals("Double"))
{
//is of int/Integer type
try
{
param[i]=Double.parseDouble(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Double");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("boolean") || dataType.equals("Boolean"))
{
//is of int/Integer type
try
{
param[i]=Boolean.parseBoolean(request.getParameter(name));
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Boolean");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
else if(dataType.equals("char") || dataType.equals("Character"))
{
try
{
param[i]=(request.getParameter(name)).charAt(0);
}catch(NumberFormatException numberFormatException)
{
System.out.println("Cannot convert it to Character");
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
return;
}
}
}//if ends here
else
{
//no annotation is applied hence can be of only 4 type 
if(dataType.equals("RequestScope"))
{
RequestScope requestScope=new RequestScope();
requestScope.setAttribute(request);
param[i]=requestScope;
}
else if(dataType.equals("SessionScope"))
{
SessionScope sessionScope=new SessionScope();
sessionScope.setAttribute(request.getSession());
param[i]=sessionScope;
}
else if(dataType.equals("ApplicationScope"))
{
ApplicationScope applicationScope=new ApplicationScope();
applicationScope.setAttribute(getServletContext());
param[i]=applicationScope;
}else if(dataType.equals("ApplicationDirectory"))
{
//ApplicationDirectory applicationDirectory=new ApplicationDirectory();
//have to set something here
//param[i]=applicationDirectory;
}
else
{
//dataType is not of any above type
if(service.getIsJson()==true)
{
//do json programming here
System.out.println("one parameter is json and is of type "+dataType);

}
}
}
i++;
}//for loop ends here
System.out.println("length of parameter is "+param.length);


Method m=service.getService();
m.invoke(obj,param);
}catch(Exception e)
{
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
     
this.forwardedFromPOST=true;
doGet(request,response);
}


}