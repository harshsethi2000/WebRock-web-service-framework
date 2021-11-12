package com.thinking.machines.webrock.pojo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestScope
{
private HttpServletRequest httpServletRequest;
public void setAttribute(HttpServletRequest httpServletRequest)
{
this.httpServletRequest=httpServletRequest;
}
public HttpServletRequest getAttribute()
{
return this.httpServletRequest;
}
}