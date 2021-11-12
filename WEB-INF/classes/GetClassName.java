import java.util.*;
import java.io.*;
public class GetClassName
{
public static void main(String gg[])
{
//provide complete path for directory(to be changed accordingly)
String mainDir="c:\\GFG\\example";
//File object
File file=new File(mainDir);
Stack<File> s=new Stack<>();
s.push(file);
//initially stack is not empty
System.out.println("Content of Directory "+mainDir+" is");
while(!s.empty())
{
File tmpF=s.pop();
if(tmpF.isFile())
{
System.out.println(tmpF.getName());
}//if ends here
else if(tmpF.isDirectory())
{
File[] f=tmpF.listFiles();
for(File fpp:f)
{
s.push(fpp);
}
}//else if ends here
}
}//main function ends here
}//class ends here