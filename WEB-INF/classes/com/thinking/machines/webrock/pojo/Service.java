package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
import java.util.*;
public class Service
{
private String url;
private Method service;
private Class serviceClass;
private boolean isGetAllowed;
private boolean isPostAllowed;
private String forwardTo;
private boolean runOnStartup;
private int priority;
private boolean injectSessionScope;
private boolean injectApplicationScope;
private boolean injectRequestScope;
private boolean injectApplicationDirectory;
private List<AutoWiredField> autoWiredFields;
private List<RequestParameterPojo> requestParameters;
private boolean isJson;
private List<InjectRequestParameterPojo> injectRequestParameters;
private boolean isSecured;
private String checkPost;
private String guard;
public Service()
{
this.url="";
this.service=null;
this.serviceClass=null;
this.isGetAllowed=false;
this.isPostAllowed=false;
this.forwardTo="";
this.runOnStartup=false;
this.priority=0;
this.autoWiredFields=null;
this.requestParameters=null;
this.injectSessionScope=false;
this.injectRequestScope=false;
this.injectApplicationScope=false;
this.injectApplicationDirectory=false;
this.isJson=false;
this.isSecured=false;
this.guard="";
this.checkPost="";
}
public void setUrl(String url)
{
this.url=url;
}
public String getUrl()
{
return this.url;
}
public void setService(Method service)
{
this.service=service;
}
public Method getService()
{
return this.service;
}
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public Class getServiceClass()
{
return this.serviceClass;
}
public void setIsGetAllowed(boolean isGetAllowed)
{
this.isGetAllowed=isGetAllowed;
}
public boolean getIsGetAllowed()
{
return this.isGetAllowed;
}
public void setIsPostAllowed(boolean isPostAllowed)
{
this.isPostAllowed=isPostAllowed;
}
public boolean getIsPostAllowed()
{
return this.isPostAllowed;
}
public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}
public String getForwardTo()
{
return this.forwardTo;
}
public void setRunOnStartup(boolean runOnStartup)
{
this.runOnStartup=runOnStartup;
}
public boolean getRunOnStartup()
{
return this.runOnStartup;
}
public void setPriority(int priority)
{
this.priority=priority;
}
public int getPriority()
{
return this.priority;
}

public void setInjectApplicationDirectory(boolean injectApplicationDirectory)
{
this.injectApplicationDirectory=injectApplicationDirectory;
}
public boolean getInjectApplicationDirectory()
{
return this.injectApplicationDirectory;
}
public void setInjectSessionScope(boolean injectSessionScope)
{
this.injectSessionScope=injectSessionScope;
}
public boolean getInjectSessionScope()
{
return this.injectSessionScope;
}
public void setInjectRequestScope(boolean injectRequestScope)
{
this.injectRequestScope=injectRequestScope;
}
public boolean getInjectRequestScope()
{
return this.injectRequestScope;
}


public void setInjectApplicationScope(boolean injectApplicationScope)
{
this.injectApplicationScope=injectApplicationScope;
}
public boolean getInjectApplicationScope()
{
return this.injectApplicationScope;
}
public void setAutoWiredFields(List<AutoWiredField> autoWiredFields)
{
this.autoWiredFields=autoWiredFields;
}
public List<AutoWiredField> getAutoWiredFields()
{
return this.autoWiredFields;
}
public void setRequestParameters(List<RequestParameterPojo> requestParameters)
{
this.requestParameters=requestParameters;
}
public List<RequestParameterPojo> getRequestParameters()
{
return this.requestParameters;
}
public void setIsJson(boolean isJson)
{
this.isJson=isJson;
}
public boolean getIsJson()
{
return this.isJson;
}
public void setInjectRequestParameters(List<InjectRequestParameterPojo> injectRequestParameters)
{
this.injectRequestParameters=injectRequestParameters;
}
public List<InjectRequestParameterPojo> getInjectRequestParameters()
{
return this.injectRequestParameters;
}
public void setIsSecured(boolean isSecured)
{
this.isSecured=isSecured;
}
public boolean getIsSecured()
{
return this.isSecured;
}
public void setCheckPost(String checkPost)
{
this.checkPost=checkPost;
}
public String getCheckPost()
{
return this.checkPost;
}
public void setGuard(String guard)
{
this.guard=guard;
}
public String getGuard()
{
return this.guard;
}

}