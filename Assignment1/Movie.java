/* Name: Interface.java
 * Author: Chris Caelli     Date: 4/03/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch as a terminal application and follow the prompts
 * Comments: Would add and use a unique identifier to all movies so they could be distincted when names/directors/filesize or durations are the same.
 */
import java.text.*;

public class Movie
{
    // instance variables - replace the example below with your own
    private String name;
    private String director;
    private int fileSize;
    private float duration;
    static int numberOfMovies;
    private boolean errorOccured;
    //private short ID; //Not used at the present

    /**
     * Constructor for objects of class Movie
     */
    public Movie()
    {
        // initialise instance variables
        name = "";
        director = "";
        fileSize = 0;
        duration = 0;
    }
    public Movie(boolean didAnErrorOccur)
    {
        //yes.
        errorOccured = true;
    }
    public Movie(String tempName, String tempDirector, int tempFileSize, float tempDuration)
    { 
        // initialise instance variables
        name = tempName;
        director = tempDirector;
        fileSize = tempFileSize;
        duration = tempDuration;
    }


    //GetSet Methods 
    //-----------------------------------------------------------------------------
    public String getName()
    {

        return name;
    }
    public void setName(String newName)
    {
        name = newName;
    }    
    
    public String getDirector()
    {
        
        return director;
    }
    public void setDirector(String newDirector)
    {
        director = newDirector;
    }
    
    public int getFileSize()
    {
        
        return fileSize;
    }
    public void setFileSize(int newFileSize)
    {
        fileSize = newFileSize;
    }
        
    public float getDuration()
    {
        
        return duration;
    }
    public void setDuration(float newDuration)
    {
        duration = newDuration;
    }
    //-----------------------------------------------------------------------------
}
