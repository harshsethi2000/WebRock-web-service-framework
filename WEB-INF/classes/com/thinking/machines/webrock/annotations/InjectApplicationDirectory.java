package com.thinking.machines.webrock.annotations;
import java.lang.annotation.*;  
import java.lang.reflect.*; 
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.TYPE) 
public @interface InjectApplicationDirectory{

}