package com.thinking.machines.webrock.annotations;
import java.lang.annotation.*;  
import java.lang.reflect.*; 
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD) 
public @interface ForwardTo{
public String value();
}