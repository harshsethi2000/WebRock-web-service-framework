package bobby.com;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import com.thinking.machines.webrock.pojo.*;       
import com.thinking.machines.webrock.annotations.*;
@Path("/students") 
public class Students
{
@AutoWired(name="xyz") public Students students;
public void setStudents(Students students)
{
this.students=students;
System.out.println("Set student method is called");
}
@AutoWired(name="xyz") public Students teacher;
public void setTeacher(Students teacher)
{
this.teacher=teacher;
System.out.println("Set teacher method is called");
}
@InjectRequestParameter("param1") public int x;
public void setX(int x)
{
this.x=x;
System.out.println("setter method of x is called"+x);
}
public static void main(String gg[])
{
test t=new test();
Path p=t.getClass().getAnnotation(Path.class);
System.out.println("value is: "+p.value());  
}
@Path("/add") public void methodOne()
{
System.out.println("Method one got executed");
}
@Path("/delete") public void methodTwo()
{
System.out.println("Method two");
}
@Path("/edit") public void methodThree()
{
System.out.println("Method three");
}
@Path("/doSomething") 
public void doSomething(@RequestParameter("param1") float x,@RequestParameter("param2") String str)
{
System.out.println("DO something is called with parameter "+x+str);
}

@Path("/doSomething2") 
public void doSomething2(@RequestParameter("param1") float x,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+x+rs);
}
@Path("/doSomething3") 
public void doSomething3(Students students,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+rs);
}
@Path("/doSomething4") 
public void doSomething4(Students students,TestFour testFour,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+rs);
}
@Path("/doSomething5") 
public void doSomething5(Students students,@RequestParameter("param1") float x,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+rs);
}



}
