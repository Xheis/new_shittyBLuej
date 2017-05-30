/* Name: Playlist.java
 * Author: Chris Caelli     Date: 30/05/17
 * Purpose: To hold movies & stats, as well as allowing the user to delete specific movies.
 * Use: It inherites MovieDatabase as it's asically a mini MovieDatabase.
 * Comments: 
 */

public class Playlist extends MovieDatabase
{
    //main variables we'll use are inherited from MovieDatabase.
   
    //stats
    private float totalDuration;
    private int totalSize;
    private String playlistName;
    
    /**
     * Constructor for objects of class Playlist
     */
    public Playlist(String tempName)
    {
        //Made the consturctor call the movie constructor, so it prevents "empty" playlists from exisiting.
        playlistName = tempName;
        movieArray = new Movie[logicalSize];
        //generates our totalDUration & totalSize; Again, this is important to make sure that whenever or whereever we create a Playlist we're making a valid playlist.
        updatePlaylist();
    }

    public String getPlaylistName()
    {
      return playlistName;
    }
 
    //a specific playlist function needed to add a movie to a playlist, and check if there's already a movie by the same name present.
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

  //check our 20GB & 1000minute limits. I took 20GB as 20,000 Bytes because google told me, and it's never wrong... right?
  private boolean isPlaylistFull(Movie newMovie) //initPlaylist()
    {
        int testFileSize = totalSize + newMovie.getFileSize();          //make new total size
        float testDuration = totalDuration + newMovie.getDuration();    //make new total duration

        if ((testFileSize > 20000) || (testDuration > 1000)) //check 'em'
        {
            //test failed. Playlist too big
            return (true);    
        }
        else
        {
            return(false);
        }

    }

    private void updatePlaylist() //previously known as initPlaylist()
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
