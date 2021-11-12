package bobby.com;
import java.lang.annotation.*;  
import java.lang.reflect.*;
import com.thinking.machines.webrock.pojo.*;       
import com.thinking.machines.webrock.annotations.*;
import javax.servlet.*;
import javax.servlet.http.*;

@Path("/testThree") 
@InjectSessionScope
public class TestThree
{
public SessionScope sessionScope;
public void setSessionScope(SessionScope sessionScope)
{
this.sessionScope=sessionScope;
System.out.println("SetSessionScope method got called"+sessionScope);
System.out.println("SetSessionScope method got called"+sessionScope.getAttribute());
System.out.println("SetSessionScope method got called"+this.sessionScope);

}
@Path("/add") public void methodOne()
{
System.out.println("Method one got executed");
System.out.println("Session Scope "+this.sessionScope.getAttribute());

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
