package com.thinking.machines.webrock.pojo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionScope
{
private HttpSession httpSession;
public void setAttribute(HttpSession httpSession)
{
this.httpSession=httpSession;
}
public HttpSession getAttribute()
{
return this.httpSession;
}
}