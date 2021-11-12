package com.thinking.machines.webrock.model;
import com.thinking.machines.webrock.pojo.*;
import java.lang.reflect.*;
import java.util.*;
public class WebRockModel
{
private Map<String,Service> map;
public WebRockModel()
{
this.map=new HashMap<>();
}
public void add(String url,Service service)
{
this.map.put(url,service);
}
public int getSize()
{
return this.map.size();
}
public Map<String,Service> getHashMap()
{
return this.map;
}
}