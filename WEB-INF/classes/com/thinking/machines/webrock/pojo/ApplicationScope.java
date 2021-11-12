package com.thinking.machines.webrock.pojo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ApplicationScope
{
private ServletContext servletContext;
public void setAttribute(ServletContext servletContext)
{
this.servletContext=servletContext;
}
public ServletContext getAttribute()
{
return this.servletContext;
}
}