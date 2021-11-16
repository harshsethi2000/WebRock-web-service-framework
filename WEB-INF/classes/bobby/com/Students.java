package bobby.com;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import com.thinking.machines.webrock.pojo.*;       
import com.thinking.machines.webrock.annotations.*;
@Path("/students") 
@POST
@SecuredAccess(checkPost="bobby.com.SecuredGuard",guard="isSecured")
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
@SecuredAccess(checkPost="something.sxy",guard="sjs")
@Path("/doSomething") 
public void doSomething(@RequestParameter("param1") float x,@RequestParameter("param2") String str)
{
System.out.println("DO something is called with parameter "+x+str);
}



@Path("/doSomething3") 
public void doSomething3(RequestScope rs,Student students,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something3 is called with parameter "+students);
}
@Path("/doSomething4") 
public void doSomething4(Student students,TestFour testFour,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+students);
}
@Path("/doSomething5") 
public void doSomething5(Student students,@RequestParameter("param1") float x,RequestScope rs,SessionScope ss,ApplicationScope as)
{
System.out.println("DO something is called with parameter "+students);
}



}
