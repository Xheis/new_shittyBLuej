/* Name: Interface.java
 * Author: Chris Caelli     Date: 4/03/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch as a terminal application and follow the prompts
 * Comments: 
 */

import java.util.*;
import java.text.*;

public class MovieDatabase
{
   //define our 4 movies
    //private Movie movie1,movie2,movie3,movie4;
  protected Movie movieArray[]; //called allMovies in the assignment sheet.
  protected int logicalSize = 0; //previously called MovieCount 28/05/17  //was static

    /*
    * Constructor for objects of class Playlist
    */
    public MovieDatabase()
    {
        movieArray = new Movie[logicalSize]; //Java can have [0], but it's a no item. We have to init it with "1" to start. 
    } 
    
    //This function is used in lieu of reading a future array length
    public int getLogicalSize()
    {
        return logicalSize;
    }
    
    
    public void debugPrint(String debugString)
    {
        System.out.println("|DEBUG~$: " + debugString);
    }

    
    //chrisCopy2implimentation from Lecture 9
    private void chrisCopy2implimentation(Movie array1[], Movie array2[], Movie newMovie)
    {
      //Movie[] returnArray = new Movie[array1.length+1];
      //debugPrint("Array1Length: " + Integer.toString(array1.length));
      for(int i=0; i< array1.length; i++)
      {
        array2[i] = array1[i];
        //debugPrint("Loop count: " + Integer.toString(i) + " Name:" + array2[i].getName()+ " Name2:" + array1[i].getName());
      }
      array2[array1.length] = newMovie;
      //debugPrint("Loop count END Name:" + array2[array1.length].getName());
    }

    //Methods for getting/setting our private movies in the MovieDatabase
    //------------------------------------------------------------------------------------------
   public void addMovie(Movie newMovie)//,int movieNumber)
   {
      //adds to array
      Movie tempMovies[] = new Movie[logicalSize+1];
      chrisCopy2implimentation(movieArray,tempMovies,newMovie);
      movieArray = tempMovies;
      System.out.println("|> " + newMovie.getName() + " added!");
      logicalSize++;
    }   
    
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
    
 

    //method to search our 4 movies by director name
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
                stringToPrint += "|> " + movieArray[i].getName() +  " - " + Float.toString(movieArray[i].getDuration()) + "\n";
            }

        }
      return stringToPrint;
   }

   
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

  //method to delete
  public void deleteMovie(int selectedMovie) 
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
            }   
            else if (i > selectedMovie)
            {
                tempMovieArray[i] = movieArray[i+1];
            }
        }
        tempMovieArray = movieArray;
        System.out.println("|> " + tempNameOfMovie + " deleted."); 
        System.out.println("|");
        System.out.print("|> ");          
  }
       
}


