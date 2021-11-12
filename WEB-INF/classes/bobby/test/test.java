package bobby.test;
import java.lang.annotation.*;  
import java.lang.reflect.*;
       
import com.thinking.machines.webrock.annotations.*;
@Path(path="/something") 
class test
{
public static void main(String gg[])
{
test t=new test();
Path p=t.getClass().getAnnotation(Path.class);
System.out.println("value is: "+p.path());  
}
@Path(path="/methodOne") public void methodOne()
{
System.out.println("Method one");
}
@Path(path="/methodTwo") public void methodTwo()
{
System.out.println("Method two");
}
@Path(path="/methodThree") public void methodThree()
{
System.out.println("Method three");
}

}
