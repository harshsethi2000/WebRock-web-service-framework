package bobby.com;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import com.thinking.machines.webrock.pojo.*;       
import com.thinking.machines.webrock.annotations.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bobby.com.*;
@Path("/testFour") 
@InjectApplicationScope
public class TestFour
{
public ApplicationScope applicationScope;
@AutoWired(name="xyz") public Students students;
public void setStudents(Students students)
{
this.students=students;
}
public void setApplicationScope(ApplicationScope applicationScope)
{
this.applicationScope=applicationScope;
System.out.println("SetApplicationScope method got called"+applicationScope);
System.out.println("SetApplicationScope method got called"+applicationScope.getAttribute());
System.out.println("SetApplicationScope method got called"+this.applicationScope);

}
@Path("/add") public void methodOne()
{
System.out.println("Method one got executed");
System.out.println("Session Scope "+this.applicationScope.getAttribute());

}
@Path("/delete") public void methodTwo()
{
System.out.println("Method two");
}
@Path("/edit") public void methodThree()
{
System.out.println("Method three");
}

}
