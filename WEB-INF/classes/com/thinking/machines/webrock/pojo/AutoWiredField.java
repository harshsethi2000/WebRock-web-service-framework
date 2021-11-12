package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
import java.util.*;
public class AutoWiredField
{
private String name;
private String variableName;
private Class dataType;
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setVariableName(String variableName)
{
this.variableName=variableName;
}
public String getVariableName()
{
return this.variableName;
}
public void setDataType(Class dataType)
{
this.dataType=dataType;
}
public Class getDataType()
{
return this.dataType;
}
}