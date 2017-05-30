
/* Name: Playlist.java
 * Author: Chris Caelli     Date: 19/04/17
 * Purpose: To hold exactly 3 movies unless the user deletes specific movies.
 * Use: Basically a mini MovieDatabase. When I started out it was it's own specific design, but in retrospect I would have just inherited the MovieDatabase and all it's functions. Or visa versa, if MovieDatabase required more stuff. 
 * Comments: Will require a rewrite depending on what we're allowed to use for Assignment
 */

public class Playlist extends MovieDatabase
{

    //private Movie movieArray[]; //movie1,movie2,movie3; //we hold 3 seperate instances of the movies from the movie database. This was in lieu of using pointers, due to some technical difficulties
    private float totalDuration;
    private int totalSize;
    private String playlistName;
    //private int logicalSize = 0; //previously called MovieCount 28/05/17  //was static
    
    /**
     * Constructor for objects of class Playlist
     */
    public Playlist(String tempName)
    {
        //Made the consturctor call the movie constructor, so it prevents "empty" playlists from exisiting.
        playlistName = tempName;
        movieArray = new Movie[logicalSize];
        //generates our totalDUration & totalSize; Again, this is important to make sure that whenever or whereever we create a Playlist we're making a valid playlist.
        //initPlaylist();
        updatePlaylist();
    }

    public String getPlaylistName()
    {
      return playlistName;
    }
 

  public boolean addMovieToPlaylist(Movie newMovie)
  {
 
    if (isPlaylistFull(newMovie)) 
    {
        //too full
        return(false);
    }
    else
    {
        if (getMovieNumber(newMovie.getName()) == -1) //time to check if we have a duplicate movie by name: (Note: An assumption is made that all other meta data could theoretically be the same as a different, unique movie. We also butcher our "getMovieNumber()" method to check)
        {
            addMovie(newMovie);//setMovie(logicalSize,newMovie);  //inherited from MovieDatabase
            updatePlaylist();     //update playlist stats
            return(true);
        }
        else
        {
           System.out.println("|>> Movie already exists in Playlist!"); 
           System.out.println("|");
           return(false);
        }
        
    }
  }

  private boolean isPlaylistFull(Movie newMovie) //initPlaylist()
    {
        //createTotalDuration();
        //createTotalSize();
        int testFileSize = totalSize + newMovie.getFileSize();
        float testDuration = totalDuration + newMovie.getDuration();

        if ((testFileSize > 20000) || (testDuration > 1000)) 
        {
            //test failed. Playlist too big
            return (true);    
        }
        else
        {
            return(false);
        }

    }

    private void updatePlaylist() //initPlaylist()
    {
        createTotalDuration();
        createTotalSize();
    }
    
    private void createTotalDuration()
    {
       for (int i = 0; i < logicalSize; i++)
        {
        	totalDuration += movieArray[i].getDuration();
        }    
    }
    public float getTotalDuration()
    {
        return totalDuration;
    }
        
    private void createTotalSize()
    {
               for (int i = 0; i < logicalSize; i++)
        {
        	totalSize += movieArray[i].getFileSize();
        }    
    }
    public float getTotalSize()
    {
        return totalSize;
    }
    
    //-----------------------------------------------------------------------------------------------------------
}
