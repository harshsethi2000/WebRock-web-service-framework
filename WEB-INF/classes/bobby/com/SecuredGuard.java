package bobby.com;
public class SecuredGuard
{
public void isSecured() throws SecurityException
{
System.out.println("isSecured method is executed");
//here user will code in a manner to check if token in the session exist or not
//if not exist then will throw some exception
//throw new SecurityException("User have to login first");
}
}