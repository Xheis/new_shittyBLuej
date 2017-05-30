/* Name: Interface.java
 * Author: Chris Caelli     Date: 4/03/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch as a terminal application and follow the prompts
 * Comments: 
 */

import java.util.*;
import java.text.*;

public class Interface {
    
    /*
     * Private Varaibles for encapsulation
     */
    
    private Playlist playlist1,playlist2;	//2 playlists are delcared in interface as required. Would replace with playlistArray[] as needed
    private MovieDatabase database;    		//A single movie database is used
    private boolean exit;					//exit is used to to exit our program's input while loop
    private int movieCount;					//movieCount is a int used to count the number of movies we have
    private int playlistCount;				//playlistCount is a int used to count the number of playlists we have
    
    private Scanner console = new Scanner(System.in);	//the console will mainly be used


    //our main hook for use to start the program.
	public static void main(String[] args)
	{
		Interface intFace = new Interface();
		intFace.run();
	}

	//we run our main 'command loop' to handle user input.
	//This method should control the flow of the program
    //and have all code for accessing the playlists
    //and movie database
	private void run() 
	{
    
    initInterface();	//initialise all our variables we'll need to start
    

    String answer;  //console answer
    boolean error;  //answer error flag
    error = false;
    do {	//Do loop used to get an acceptable answer

            //Take user input on TIO or GUI?
            System.out.println("Would you like to enter GUI or TIO?");
            answer = console.nextLine();
            if(answer.equalsIgnoreCase("GUI"))
            {
                error = false;	//no errors, so we could leave this do loop if/when we return.
                launchGUI();	//launch our GUI-driven command loop
            }else if(answer.equalsIgnoreCase("TIO"))
            {
                error = false;	//no errors, so we could leave this do loop if/when we return.
                launchTIO();	//launch our TIO-driven command loop
            }else
            {
                error = true;	//Error: Not correct format
                System.out.println("I couldn't understand your answer. Please enter again \n");
            }
        }while (error == true);	//loop on "Would you like to enter GUI or TIO?" until we get a good answer

	}

	//init our variables, which is mainly just calling initialising constructors of our classes
	private void initInterface()
	{
	   playlist1 = new Playlist();
	   playlist2 = new Playlist();
	   database = new MovieDatabase();
	   movieCount = 0;	//tells us we have 0 movies at the present.
	}

	//this method will run our user interface via TIO
	private void launchTIO()
	{
	    System.out.println("|-----------------------------------------------------------");
	    System.out.println("|------------------   Movie Assignment 1  ------------------");
	    System.out.println("|-----------------------------------------------------------");
	    System.out.println("|> Type the following commands to");
	    System.out.println("|> navigate the program:");
	    System.out.println("|>");
	    printCommandsString();
	    
	    //after our opening intro, we're ready to dive in.
	    while (!exit)
	    {
	        processUserInput();	// Until we exit, keep processing user input
	    }
	}

	//this method is made for visability 
	private void printCommandsString()
	{
	    System.out.println("|>> Commands:");
	    System.out.println("|> help");
	    System.out.println("|> new_movie [Title],[Director],[Filesize],[Duration]");
	    System.out.println("|> new_playlist");
	    System.out.println("|> list_movies");
	    System.out.println("|> list_playlists");
	    System.out.println("|> list_playlist [-p playlist]");
	    System.out.println("|> list_by [-d director]");
	    System.out.println("|> delete [-p playlist] or [-m movie] or [-mp [Movie],[Playlist]]");
	    System.out.println("|> exit");
	    System.out.print("|> ");
	}

	private void processUserInput()
	{
	    //Take user input by looping through the uglest nested if-else tree you can imagine. I wanted to do the program like this, but I didn't work out how to make a proper command line parser. 
	                String answer;  //console answer
	                boolean error;  //answer error flag
	                error = false;

	    //start by grabbing a command and checking it
	    do 
	    {                           //get the right answer
	                answer = console.nextLine();
	                if(answer.equalsIgnoreCase("help"))
	                {
	                    System.out.println("|");
	                    printCommandsString();
	                    error = false; break; //overkill, but I feel safer to have both methods of ending the rotuine
	                }else if(answer.startsWith("new_movie"))
	                {
	                    //check if the command is 'sanitised' i.e. new_movieGARBAGE would be caught here
	                    if (answer.length() <= ("new_movie ").length()) {System.out.println("|>> new_movie [Title],[Director],[Filesize],[Duration]"); System.out.println("|");System.out.print("|> ");break;}
	                    /*   get indexs of spaces (S1-S4), split between them
	                     *   0-S1    -   Command. Throw this out
	                     *   S1-S2   -   Title
	                     *   S2-S3   -   Director
	                     *   S3-S4   -   Filesize
	                     *   S4-End  -   Duration
	                     */
	                    
	                    //Initialise our variables for working with the command line
	                    int S1,S2,S3,S4;
	                    String tempName,tempDirector;
	                    int tempFileSize;
	                    float tempDuration;
	                    
	                    //set up a temp. movie variable, for us to pass to the database to store.
	                    Movie tempMovie;
	                    tempMovie = new Movie();
	                    
	                    
	                    //now check we have a valid space for a new movie to go in. This is important to make sure the user can't add more than 4 movies in Assignment 1.
	                    if (movieCount >= 0 && movieCount < database.getNumberOfMovies())
	                    {
	                        //indexOf(int ch, int fromIndex)
	                        S1 = answer.indexOf(' ');
	                        S2 = answer.indexOf(',', S1+1);
	                        S3 = answer.indexOf(',', S2+1);
	                        S4 = answer.indexOf(',', S3+1);
	                     
	                        
	                        tempName = answer.substring(S1+1,S2);
	                        tempDirector = answer.substring(S2+1,S3);
	                        tempFileSize = Integer.parseInt(answer.substring(S3+1,S4));
	                        tempDuration = Float.valueOf(answer.substring(S4+1));
	                        
	                        tempMovie.setName(tempName);
	                        tempMovie.setDirector(tempDirector);
	                        tempMovie.setFileSize(tempFileSize);
	                        tempMovie.setDuration(tempDuration);
	                        
	                       
	                                                
	                        if (database.getMovieNumber(tempName) == -1) //time to check if we have a duplicate movie by name: (Note: An assumption is made that all other meta data could theoretically be the same as a different, unique movie. We also butcher our "getMovieNumber()" method to check)
	                        {
	                            
	                            System.out.println("|>> New Movie Made!");
	                            System.out.println("|> Name: " + tempMovie.getName());
	                            System.out.println("|> Director: " + tempMovie.getDirector());
	                            System.out.println("|> FileSize: " + tempMovie.getFileSize());
	                            System.out.println("|> Duration: " + tempMovie.getDuration());
	                            System.out.println("|> movieCount:" + movieCount);
	                            
	                            
	                            database.setMovie(tempMovie,movieCount);
	                            //null everything, I think we could have a unforseen issues otherwise.
	                            tempName = "";
	                            tempDirector = "";
	                            tempFileSize = -2;
	                            tempDuration = -2;
	                            tempMovie = new Movie();
	                            
	                            movieCount++; //we're 0 indexed for our movies 0-3. 
	                            
	                            System.out.println("|");
	                            System.out.print("|>");
	                            error = false; break;
	                        }
	                        else
	                        {
	                            //oh oh! We've found the same movie.
	                            System.out.println("|>> Unable to add " + tempName + "!");
	                            System.out.println("|> This movie by the same name is already in the database.");
	                            System.out.println("|<<Programmers note: Movie ID not implimented, so movie name must be unique>>");
	                            System.out.println("|> Would you like to overright the existing movie? (y/n)");
	                            System.out.print("|>");
	                            String userAnswer;  //console answer
	                            boolean userError;  //answer error flag
	                            userError = false;

	                            //Now, better check if they'd like to overwrite the data of the movie they just tried to add again
	                            do 
	                            {                           
	                                answer = console.nextLine();
	                                if(answer.equalsIgnoreCase("y"))
	                                {
	                                    System.out.println("|>> Movie Overwritten!");
	                                    System.out.println("|> Name: " + tempMovie.getName());
	                                    System.out.println("|> Director: " + tempMovie.getDirector());
	                                    System.out.println("|> FileSize: " + tempMovie.getFileSize());
	                                    System.out.println("|> Duration: " + tempMovie.getDuration());
	                                    System.out.println("|> movieCount:" + movieCount);
	                            
	                                    database.setMovie(tempMovie,database.getMovieNumber(tempName));
	                                    //null everything, I think we're having a santistion issue
	                                    tempName = "";
	                                    tempDirector = "";
	                                    tempFileSize = -2;
	                                    tempDuration = -2;
	                                    tempMovie = new Movie();
	                                    
	                                    movieCount++; //we're 0 indexed for our movies 0-4. 
	                                    
	                                    System.out.println("|");
	                                    System.out.print("|>");
	                                    error = false; 
	                                    userError = false;
	                                    break;
	                                }
	                                else if(answer.equalsIgnoreCase("n")) //nope, better leave it
	                                {
	                                    System.out.println("|>> new_movie aborted!");
	                                    System.out.println("|");
	                                    System.out.print("|>");
	                                    userError = false;
	                                    break;
	                                }
	                                else
	                                {
	                                    System.out.println("|>> I couldn't understand you. Please enter again!");//try again, answer unclear
	                                    System.out.println("|");
	                                    System.out.print("|>");
	                                    userError = true;
	                                }
	                            } while(userError == true);
	                        }
	                    }else
	                        {
	                            //too many movies!
	                            System.out.print("|>> Too many movies! Please delete some movies to make room");
	                            System.out.println("|");
	                            System.out.print("|>");
	                            break;
	                        }
	                   
	                }else if(answer.startsWith("new_playlist ")) //new_playlist command
	                {
	                    if (answer.length() <= ("new_playlist ").length()) {System.out.println("|>> new_playlist [Movie1],[Movie2],[Movie3]"); System.out.println("|");System.out.print("|> ");break;}
	                    /*   get indexs of spaces (S1-S3), split between them
	                     *   0-S1    -   Throw this out
	                     *   S1-S2   -   Movie1
	                     *   S2-S3   -   Movie2
	                     *   S3-END   -   Movie3
	                     */

	                    int S1,S2,S3;
	                    int tempMovieIndex1,tempMovieIndex2,tempMovieIndex3;	//get some new movie indexes, for future use with arrays
	                    String tempMovie1,tempMovie2,tempMovie3;				//get the names of these movies
	                    
	                    Playlist tempPlaylist;									//new playlist to generate and send to database.java
	                    tempPlaylist = new Playlist();
	                    
	                    if (playlistCount >= 0 && playlistCount < 2)
	                    {
	                        //indexOf(int ch, int fromIndex)
	                        S1 = answer.indexOf(' ');
	                        S2 = answer.indexOf(',', S1+1);
	                        S3 = answer.indexOf(',', S2+1);
	                        
	                        if( (S1 == -1) || (S2 == -1) || (S3 == -1) ) 
	                        {
	                            System.out.println("|>> new_playlist [Movie1],[Movie2],[Movie3]"); System.out.println("|");System.out.print("|> ");break;
	                        }
	                        
	                       
	                        tempMovie1 = answer.substring(S1+1,S2);
	                        tempMovieIndex1 = database.getMovieNumber(tempMovie1);
	                        tempMovie2 = answer.substring(S2+1,S3);
	                        tempMovieIndex2 = database.getMovieNumber(tempMovie2);
	                        tempMovie3 = answer.substring(S3+1);
	                        tempMovieIndex3 = database.getMovieNumber(tempMovie3);

	                            /*
	                             * Author: Chris Caelli Date: 19/04/17
	                             * Purpose:
	                             * Use:
	                             * Comments: Currently the database.getMovie() function will return the first instance of the movie, e.g. chronologically. Playlsits do not retain their indexed value.
	                             */
	                            tempPlaylist.setMovie(database.getMovie(tempMovieIndex1),tempMovieIndex1);
	                            tempPlaylist.setMovie(database.getMovie(tempMovieIndex2),tempMovieIndex2);
	                            tempPlaylist.setMovie(database.getMovie(tempMovieIndex3),tempMovieIndex3);
	                            
	                            switch (playlistCount)
	                            {
	                                case 0:
	                                    playlist1 = tempPlaylist;
	                                case 1:
	                                    playlist2 = tempPlaylist;
	                            }
	                            playlistCount++; //we're 0 indexed for our movies 0-4. 
	                            
	                            System.out.println("|");
	                            System.out.print("|>");
	                            error = false; break;
	                        //}
	                    }
	                    else
	                    {
	                        //too many playlists!
	                        System.out.print("|>> Too many playlists! Please delete some playlists to make room");
	                        System.out.println("|");
	                        System.out.print("|>");
	                        break;
	                    }
	                }else if(answer.equalsIgnoreCase("list_movies"))
	                {
	                    
	                    for (int i = 0; i < database.getNumberOfMovies(); i++)
	                    {
	                        if(database.getMovie(i).getName().equals("")) 
	                        {
	                            System.out.println("|> Empty Space #" + (i+1));
	                            System.out.println("| ");
	                        }
	                        else
	                        {
	                            System.out.println("|>> Movie #" + (i+1));
	                            System.out.println("|> Name: " + database.getMovie(i).getName());
	                            System.out.println("|> Director: " + database.getMovie(i).getDirector());
	                            System.out.println("|> FileSize: " + database.getMovie(i).getFileSize());
	                            System.out.println("|> Duration: " + database.getMovie(i).getDuration());
	                            System.out.println("| ");
	                        }
	                    }
	                    
	                    System.out.print("|> ");
	                  
	                    error = false; break;
	                }else if(answer.startsWith("list_playlists"))
	                {
						//Awful implimentation, but again would like to use a an approach such as playlistArray[i].getPlaylistAsString() to get to do this better
						System.out.println("|>> Playlist1:");
						System.out.println(playlist1.getPlaylistAsString());	//returns a TIO printable string of the playlist
						System.out.println("|");
						System.out.println("|>> Playlist2:");
						System.out.println(playlist2.getPlaylistAsString()); //returns a TIO printable string of the playlist
						System.out.println("|");
						System.out.print("|> ");
	                     
	                }else if(answer.startsWith("list_playlist "))
	                {
	                     if (answer.length() <= ("list_playlist ").length()) {System.out.println("|>> list_playlist [Playlist]"); System.out.println("|");System.out.print("|> ");break;}
	                     
	                    String subAnswer;
	                    subAnswer = answer.substring(("list_playlist ").length()); // this should get just the playlist in question. E.g. playlist1, playlist2.
	                    
	                    System.out.println("|");
	                    if(subAnswer.equals("playlist1"))        //this could be replaced with a For loop checking each playlist for a match.
	                    {
							//success, playlist 1 is found.
							System.out.println("|>> Playlist1 Found!");
							System.out.println(playlist1.getPlaylistAsString());	//returns a TIO printable string of the playlist
							System.out.println("|");
							System.out.print("|> ");
	                    }else if(subAnswer.equals("playlist2")) 
	                    {
	                        //success, playlist 1 is found.
							System.out.println("|>> Playlist2 Found!");
							System.out.println(playlist2.getPlaylistAsString());	//returns a TIO printable string of the playlist
							System.out.println("|");
							System.out.print("|> ");
	                    }
	                    else
	                    {
							System.out.println("|>> Playlist not found! Please check your spelling.");
							System.out.println("|");
							System.out.print("|> ");
	                    }
							error = false; break;

	                }else if(answer.startsWith("list_by "))
	                {
	                    String subAnswer = "";
	                    if (answer.length() <= ("list_by ").length()) {System.out.println("|>> list_by [-d director]"); System.out.println("|");System.out.print("|> ");break;}
							subAnswer = answer.substring(("list_by ").length()); // this should get just the playlist in question. E.g. playlist1, playlist2.
							System.out.println("|");

	                    if(subAnswer.startsWith("-d"))        //this could be replaced with a For loop checking each playlist for a match.
	                    {
							String director;
							if (subAnswer.length() <= ("-d ").length()) {System.out.println("|>> list_by [-d director]"); System.out.println("|");System.out.print("|> ");break;}
							director = subAnswer.substring(("-d ").length());
							//success, we know they're trying to  1 is found.
							System.out.print("|> Searching for director:    ");
							System.out.println(director);
							//success, playlist 1 is found.
							System.out.println(database.getMovieByDirector(director));
							System.out.println("|");
							System.out.print("|>> ");
							error = false; break;
	                    }else if(subAnswer.equals("-x")) 
	                    {
							//success, playlist 1 is found.
							System.out.println("|>> Not implimented");
							//playlist2.Show()
							System.out.println("|");
							System.out.print("|> ");
	                    }
	                    else
	                    {
	                    System.out.println("|>> Playlist not found! Please check your spelling.");
	                    System.out.println("|");
	                    System.out.print("|> ");
	                    }
	                    //("list_playlist")
	                    error = false; break;
	                }else if(answer.startsWith("delete ")) //|> delete [-p playlist] [-m movie] [-mp movie in a playlist]
	                {
	                    if (answer.length() <= ("delete ").length()) {System.out.println("|>> delete [-p playlist] or [-m movie] or [-mp [Movie],[Playlist]]"); System.out.println("|");System.out.print("|> ");break;}
	                    String subAnswer = "";
	                    subAnswer = answer.substring(("delete ").length()); // this should get just the playlist in question. E.g. playlist1, playlist2.
	                    System.out.println("|");
	                    if(subAnswer.startsWith("-p "))        //this could be replaced with a For loop checking each playlist for a match.
	                    {
							String playlist;
							playlist = subAnswer.substring(("-p ").length());
							//success, we know they're trying to  1 is found.
							System.out.print("|> Attempting to delete playlist:    ");
							System.out.println(playlist);
	                       
							if(playlist.equals("playlist1")) 
							{
								playlist1 = new Playlist(); //nulling to make it zero again
								System.out.println("|>" + playlist + " has been deleted");
								System.out.println("|");
								System.out.print("|> ");
							}
							else if(playlist.equals("playlist2")) 
							{
								playlist2 = new Playlist(); //nulling to make it zero again
								System.out.println("|>" + playlist + " has been deleted");
								System.out.println("|");
								System.out.print("|> ");
							}
							else
							{
								System.out.println("|>" + playlist + " not found! Please check your spelling and try again.");
								System.out.println("|");
								System.out.print("|> ");
							}
	                        
	                    }
	                    else if(subAnswer.startsWith("-m "))        //this could be replaced with a For loop checking each playlist for a match.
	                    {
							String movie;
							boolean isFound = false;
							movie = subAnswer.substring(("-m ").length());

							//success, we know they're trying to  1 is found.
							System.out.print("|> Attempting to delete movie:    ");
							System.out.println(movie);
	                       
	                       
	                        if(database.getMovieNumber(movie) != -1)
	                        {
								database.deleteMovie(database.getMovieNumber(movie));

								/* ------------------------------------
								*  This will delete the movie from the playlist, because we hold an instance 
								*  of the movie in the playlist. This is because I couldn't work out how to
								*  have a playlist just have the index of the movie number, without also 
								*  having 'instance issues'. E.g. maybe I change the original movie and 
								*  that changes the pointer to the original movie etc. This probably won't
								*  make it into the next verison. - Chris
								*/
								playlist1.deleteMovie(movie);
								playlist2.deleteMovie(movie); 
								// ------------------------------------
								System.out.println("|>" + movie + " has been deleted");
								isFound = true;
	                        }
	                        else
	                        {
								System.out.println("|> Movie not found");
	                        }
	                       
	                       
	                        if(isFound == false)
	                        {
								System.out.println("|>" + movie + " not found! Please check your spelling and try again.");
	                        }
	                    }
	                    else if(subAnswer.startsWith("-mp "))        //this could be replaced with a For loop checking each playlist for a match.
	                    { 
							//get [Movie],[Playlist]  
							//delete -mp is 11char, so let's remove that from the start
							int S1,S2,S3,S4;
							String tempName, tempPlaylist;
							//indexOf(int ch, int fromIndex)
							S1 = answer.indexOf(' ', 10);
							S2 = answer.indexOf(',', S1+1);
	                        
	                        tempName = answer.substring(S1+1,S2);
	                        tempPlaylist = answer.substring(S2+1);
	                        
	                        switch(tempPlaylist)
	                        {
	                            case "playlist1":
	                                playlist1.deleteMovie(tempName);
	                                System.out.println("|> " + tempName + " deleted from " + tempPlaylist);
	                                break;
	                            case "playlist2":
	                                playlist2.deleteMovie(tempName);
	                                System.out.println("|> " + tempName + " deleted from " + tempPlaylist);
	                                break;
	                            default:
	                                System.out.println("|> Unable to delete" + tempName + " from " + tempPlaylist + ". Please try again.");
	                                System.out.println("|");
	                                System.out.print("|> ");
	                                break;
	                        }
	                    
	                    }else {System.out.println("|> ERROR IN COMMAND");}
	                    
	                    System.out.println("|");
	                    System.out.print("|> ");
	                    error = false; break;

	                }else if(answer.equalsIgnoreCase("exit"))
	                    {
	                        exit = true;
	                        System.out.println("|");
	                        System.out.print("|>> Exiting"); customWait(330); System.out.print("."); customWait(330); System.out.print("."); customWait(330); System.out.print(".");
	                        error = false; break;
	                    }else
	                    {
	                        System.out.println("|");
	                        System.out.println("|>> I couldn't understand your answer. Please enter again \n");
	                        System.out.println("|");
	                        System.out.print("|> ");
	                        //Error: Not correct format
	                        error = false;
	                    }
	    }while (error == true);
	}

	//this method would launch our GUI
	private void launchGUI()
	{
	    //not implimented, but will keep incase I want to impliment this later.
	    System.out.print("\n\nGUI not implimented. Shutting down"); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print(".");
	}

	//a method to make our GUI wait artifically for a set number of miliseconds
	private void customWait(int miliSeconds)
	{
	    //editted excert from https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html
	    try {
	        Thread.sleep(miliSeconds);                 //1000 milliseconds is one second.
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	}

}