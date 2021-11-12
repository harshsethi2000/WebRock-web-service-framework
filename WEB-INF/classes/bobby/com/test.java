package bobby.com;
import java.lang.annotation.*;  
import java.lang.reflect.*;
       
import com.thinking.machines.webrock.annotations.*;
@Path("/test")
@GET 
public class test
{
public static void main(String gg[])
{
test t=new test();
Path p=t.getClass().getAnnotation(Path.class);
System.out.println("value is: "+p.value());  
}
@Path("/methodOne") 
@ForwardTo("/hum")
public void methodOne()
{
System.out.println("Method one");
}
@Path("/methodTwo") public void methodTwo()
{
System.out.println("Method two");
}
@Path("/methodThree") public void methodThree()
{
System.out.println("Method three");
}

}
