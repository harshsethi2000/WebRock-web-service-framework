package com.thinking.machines.webrock.pojo;
public class RequestParameterPojo
{
private Class dataType;
private String name;
public void setDataType(Class dataType)
{
this.dataType=dataType;
}
public void setName(String name)
{
this.name=name;
}
public Class getDataType()
{
return this.dataType;
}
public String getName()
{
return this.name;
}
}