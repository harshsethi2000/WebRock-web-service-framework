package bobby;
import java.lang.annotation.*;  
import java.lang.reflect.*;
     
import com.thinking.machines.webrock.annotations.*;
@Path("/xyz") 
public class test
{
public static void main(String gg[])
{
test t=new test();
Path p=t.getClass().getAnnotation(Path.class);
System.out.println("value is: "+p.value());  
}
@Path("/methodOne")
@POST
public void methodOne()
{
System.out.println("Method one");
}
@Path("/methodTwo") 
@GET
public void methodTwo()
{
System.out.println("Method two");
}
@Path("/methodThree") public void methodThree()
{
System.out.println("Method three");
}
@OnStartup(priority=3) public void methodSix()
{
System.out.println("Method Six run on startup");
}
@OnStartup(priority=4) public void methodSeven()
{
System.out.println("Method Seven run on startup");
}
@OnStartup(priority=1) public void methodFour()
{
System.out.println("Method Four run on startup");
}
@OnStartup(priority=2) public void methodFive()
{
System.out.println("Method Five run on startup");
}


}
