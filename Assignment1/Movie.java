/* Name: Interface.java
 * Author: Chris Caelli     Date: 30/05/17
 * Purpose: Holds our movie data, constructors and get/sets relevent to making a new movie
 * Use: 
 * Comments:
 */
import java.text.*;

public class Movie
{
    // instance variables - replace the example below with your own
    private String name;
    private String director;
    private int fileSize;
    private float duration;
    static int numberOfMovies;      //static as per assignment2
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
    public Movie(boolean didAnErrorOccur)   //an overload so I can return a sential value movie as a error code.
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
    public boolean getHasErrorOccured()
    {
        return errorOccured;
    }
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
