package bobby;
import com.thinking.machines.webrock.annotations.*;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import java.util.*;
import java.lang.reflect.Method;

public class eg1
{

public static void main(String gg[])
{
try 
{
// returns the Class object for this class

Class myClass = test.class;           
Annotation[] a=myClass.getAnnotations();
System.out.println(a.length );
for(Annotation an: a)
{
System.out.println(an.toString());
}
Method[] methods=myClass.getMethods();

for(Method m:methods)
{
Annotation[] ann=m.getAnnotations();
for(Annotation an: ann)
{
System.out.println(an.toString());
}

}

}
catch (Exception e)
{
System.out.println(e);
}
}
}