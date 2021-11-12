package com.thinking.machines.webrock.annotations;
import java.lang.annotation.*;  
import java.lang.reflect.*; 
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD) 
public @interface InjectRequestParameter{
public String value();
}