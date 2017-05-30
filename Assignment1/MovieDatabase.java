/* Name: Interface.java
 * Author: Chris Caelli     Date: 30/05/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch as a terminal application and follow the prompts
 * Comments: 
 */

import java.util.*;
import java.text.*;

public class MovieDatabase
{
  //protected allows scope into the children of MovieDatabase i.e. Playlist.java
  protected Movie movieArray[]; //called allMovies in the assignment sheet.
  protected int logicalSize = 0; //previously called MovieCount 28/05/17  //was static

    /*
    * Constructor for objects of class Playlist
    */
    public MovieDatabase()
    {
        movieArray = new Movie[logicalSize]; //Java can have [0], but it's a no item. We have to init it with "1" to start. 
    } 
    
    //This function is used for public access
    public int getLogicalSize()
    {
        return logicalSize;
    }
    

    //chrisCopy2implimentation from Lecture 9
    //Takes the 'references' of 2arrays, where array1 is [x]elements, and array2 is [x+1]elements, and the +1 movie is also attached. 
    private void chrisCopy2implimentation(Movie array1[], Movie array2[], Movie newMovie)
    {
      for(int i=0; i< logicalSize; i++)
      {
        array2[i] = array1[i]; //copy sequentially
      }
      array2[logicalSize] = newMovie; //add the new on at the end.
    }

    //Methods for getting/setting our private movies in the MovieDatabase
    //------------------------------------------------------------------------------------------
   //addMovie adds a new movie, and handles the dynamic nature our our movieArray.
   public void addMovie(Movie newMovie)
   {
      //adds to array
      Movie tempMovies[] = new Movie[logicalSize+1];  //make new array
      chrisCopy2implimentation(movieArray,tempMovies,newMovie); //copy into it
      movieArray = tempMovies;  //make the new one our movieArray
      System.out.println("|> " + newMovie.getName() + " added!"); 
      logicalSize++;
    }   
    
    //gets movies if the request is valid. Otherwise, returns an error.
   public Movie getMovie(int movieNumber)
   {
      if (movieNumber > logicalSize -1)
      {
        //error! Not possible
        Movie debug = new Movie(true);
        return debug;
      }
      else
      {
        return movieArray[movieNumber];
      }
   }

   public void setMovie(int index, Movie tempMovie)
   {
      movieArray[index] = tempMovie;
   }
    
    //used to get the movie index from a movie name string (used as our UID in this situation)
    public int getMovieNumber(String movieName)
   {
     for (int i = 0; i < getLogicalSize(); i++)
     {
         if (getMovie(i).getName().equalsIgnoreCase(movieName))
         {
             return i;
         }
     }
     return -1; //error code. -1 indicates an error e.g. movie not found.
   }  
    
 

  //method to search our movies by director name
  public String getMovieByDirector(String movieDirector)
  {
  //catch non-ints
  String stringToPrint = "";
  for (int i = 0; i < getLogicalSize(); i++) 
  {

    if (movieArray[i].getDirector().equalsIgnoreCase(movieDirector))
    {
        stringToPrint += "|> " + movieArray[i].getName() + "\n";
    }

  }
  //no director, moving on
  if (stringToPrint.equals("")) {stringToPrint = "|>> Director not found.";}
   else 
   {
       String tempstring = stringToPrint;
       stringToPrint = "|>> Director found! \n" + movieDirector + " has directed these movies: \n" + tempstring;
   }
  return stringToPrint;
  }

  //method to search our 4 movies by director name
  public String getMovieByRuntime(float tempRuntime)
  {
  String stringToPrint = "";
     for (int i = 0; i < getLogicalSize(); i++) 
     {

          if (movieArray[i].getDuration() <= tempRuntime)
          {
              stringToPrint += "|> Movie title: " + movieArray[i].getName() +  " - Duration: " + Float.toString(movieArray[i].getDuration()) + "\n";
          }

      }
    return stringToPrint;
  }

  //used to pull our list of movies at a string for outputing. This was made before I realised I could have movieDatabase also access the console.
  public String getMoviesAsString()
  {
      String stringToPrint = "|";
      for (int i = 0; i < logicalSize; i++) 
      {
        stringToPrint += "\n|> " + movieArray[i].getName();
      }
      stringToPrint += "\n|>";
      return stringToPrint;
  }

  //method to delete a movie, and maintain garbage collection
  public void deleteMovie(int selectedMovie) 
  {
    //check if the movie even exists in the database
    if(getMovie(selectedMovie).getHasErrorOccured())
    {
      //error occured, movie doesn't exit
      debugPrint("Movie Not Found!");
    }
    else
    {
      String tempNameOfMovie = movieArray[selectedMovie].getName();
      Movie tempMovieArray[] = new Movie[logicalSize-1];
      for (int i = 0; i< tempMovieArray.length; i++) 
      {
          //copy 1 to 1 until we get to selectedPlaylist, then copy 1+1 to 1
          if (i < selectedMovie) 
          {
              tempMovieArray[i] = movieArray[i];

          }
          else if (i == selectedMovie)
          {
              //Do nothing, but I'm leaving this nested if to indicate we're deliberatelty doing nothing
              debugPrint("Movie deleted at index " + Integer.toString(i));
              tempMovieArray[i] = movieArray[i+1];
          }   
          else if (i > selectedMovie)
          {
              tempMovieArray[i] = movieArray[i+1];
          }
      }
      movieArray = tempMovieArray;
      logicalSize--;
       debugPrint("LogiSize= " + Integer.toString(logicalSize));
      System.out.println("|> " + tempNameOfMovie + " deleted."); 
      System.out.println("|");
      System.out.print("|> ");     
    }            
  }


  //used because BlueJ is an awful, awful program. It apprently loads the entire .java file into memory, makes changes and re-writes the ENTIRE file.
  public void debugPrint(String debugString)
  {
      System.out.println("|DEBUG~$: " + debugString);
  }
       
}


