  
import java.io.File;
  
public class GFG {
    static void RecursivePrint(File[] arr, int index, int level)
    {
        // terminate condition
        if (index == arr.length)
            return;
  
        
  
        // for files
        if (arr[index].isFile())
           {
            if(arr[index].getName().contains(".class"))
            {
            
            //System.out.println(arr[index].getName());
            String g[]=arr[index].getAbsolutePath().split("classes.");
            
            if(g[1].contains("bobby")) 
              {
                
                System.out.println(g[1]+" File Name "+arr[index].getName());
                System.out.println(g[1].replaceAll("\\\\","."));

              }
            } 
           }       
        // for sub-directories
        else if (arr[index].isDirectory()) {
            
  
            // recursion for sub-directories
            RecursivePrint(arr[index].listFiles(), 0,
                           level + 1);
        }
  
        // recursion for main directory
        RecursivePrint(arr, ++index, level);
    }
  
    // Driver Method
    public static void main(String[] args)
    {
        // Provide full path for directory(change
        // accordingly)
        String maindirpath
            = "c:\\tomcat9\\webapps\\webrock\\WEB-INF\\classes";
  
        // File object
        File maindir = new File(maindirpath);
  
        if (maindir.exists() && maindir.isDirectory()) {
              
              // array for files and sub-directories
            // of directory pointed by maindir
            File arr[] = maindir.listFiles();
  
            System.out.println(
                "**********************************************");
            System.out.println(
                "Files from main directory : " + maindir);
            System.out.println(
                "**********************************************");
  
            // Calling recursive method
            RecursivePrint(arr, 0, 0);
        }
    }
}