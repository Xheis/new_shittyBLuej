import java.util.*;
import java.io.*;

public class TextFileInputDemo
{
    public static void main (String [] args)throws IOException
    {
        String fileName = "test2";
        System.out.println ("The file " + fileName + "\ncontains the following lines:\n");
        Scanner inputStream = new Scanner (new File (fileName));
        
        while (inputStream.hasNextLine ())
        {
            String line = inputStream.nextLine ();
            System.out.println (line);
        }
        inputStream.close ();
    }
}