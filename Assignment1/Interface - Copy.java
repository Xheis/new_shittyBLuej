/*
 *  Seng1110 - Assignment 1
 *  Date:   4/03/17
 *  Author: Chris Caelli
 */

import java.util.*;
import java.text.*;

public class Interface {
    
    /*
     * Private Varaibles for encalpulation //spelling
     */
    private Playlist playlist1,playlist2;
    private MovieDatabase database;
    private Movie tempMovie;
    private boolean exit;
    
    //public, incase we need to call it from interface's children
    public Scanner console = new Scanner(System.in);
private void run() 
{
    //This method should control the flow of the program
    //and have all code for accessing the playlists
    //and movie database
    
    //would you like to enter TIO or GUI?
        String answer;	//console answer
      	boolean error;	//answer error flag
    	error = false;
    	do {							//get the right answer
    	    	//Take user input
    	    	//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    			System.out.println("Would you like to enter GUI or TIO?");
    			answer = console.nextLine();
    	    	if(answer.equals("GUI"))
    			{
    				error = false;
    				launchGUI();
    			}else if(answer.equals("TIO"))
    			{
    				error = false;
    				launchTIO();
    			}else
    			{
    				//Error: Not correct format
    				error = true;
    				System.out.println("I couldn't understand your answer. Please enter again \n");
    			}
    		}while (error == true);

}

private void launchTIO()
{
    System.out.println("|-----------------------------------------------------------");
    System.out.println("|------------------   Movie Assignment 1  ------------------");
    System.out.println("|-----------------------------------------------------------");
    System.out.println("|> Type the following commands to");
    System.out.println("|> navigate the program:");
    System.out.println("|>");
    System.out.println("|>> Commands:");
    System.out.println("|> help");
    System.out.println("|> new_movie [Title],[Director],[Filesize],[Duration]");
    System.out.println("|> new_movie_from_file [1-100]");
    System.out.println("|> new_playlist");
    System.out.println("|> list_movies");
    System.out.println("|> list_playlists");
    System.out.println("|> list_playlist [-p playlist]");
    System.out.println("|> list_by [-d director]");
    System.out.println("|> delete [-p playlist] [-m movie] [-mp movie in a playlist]");
    System.out.println("|> exit");
    System.out.print("|> ");
    
    while (!exit)
    {
        processUserInput();
    }
}


private void processUserInput()
{
    //Take user input
    	    	String answer;	//console answer
              	boolean error;	//answer error flag
            	error = false;
    do 
    {							//get the right answer
    	    	answer = console.nextLine();
    	    	if(answer.equalsIgnoreCase("help"))
    			{
    			    System.out.println("|");
			        System.out.println("|>> Commands:");
                    System.out.println("|> help");
                    System.out.println("|> new_movie [Title],[Director],[Filesize],[Duration]");
                    System.out.println("|> new_movie_from_file [1-100]");
                    System.out.println("|> new_playlist");
                    System.out.println("|> list_movies");
                    System.out.println("|> list_playlists");
                    System.out.println("|> list_playlist [playlist]");
                    System.out.println("|> list_by [-d director]");
                    System.out.println("|> delete [-p playlist] [-m movie] [-mp movie in a playlist]");
                    System.out.println("|> exit");
                    System.out.print("|> ");
    				error = false; break; //overkill, but I feel safer to have both methods of ending the rotuine
    			}else if(answer.startsWith("new_movie"))
    			{
    			    if (answer.length() <= ("new_movie ").length()) {System.out.println("|>> new_movie [Title],[Director],[Filesize],[Duration]"); System.out.println("|");System.out.print("|> ");break;}
    			    /*   get indexs of spaces (S1-S4), split between them
    			     *   0-S1    -   Command. Throw this out
    			     *   S1-S2   -   Title
    			     *   S2-S3   -   Director
    			     *   S3-S4   -   Filesize
    			     *   S4-End  -   Duration
    			     */
    			    int S1,S2,S3,S4;
    			    String tempName,tempDirector, t1,t2;
    			    int tempFileSize;
    			    float tempDuration;
    			    
    			    //indexOf(int ch, int fromIndex)
    			    S1 = answer.indexOf(' ');
    			    S2 = answer.indexOf(',', S1+1);
    			    S3 = answer.indexOf(',', S2+1);
    			    S4 = answer.indexOf(',', S3+1);
    			    
    			    
    			    tempName= answer.substring(S1,S2);
    			    tempDirector =answer.substring(S2,S3);
    			    t1 = answer.substring(S3,S4);
    			    t2 = answer.substring(S4);
    			    /*
    			    tempMovie.setName(answer.substring(S1,S2));
    			    tempMovie.setDirector(answer.substring(S2,S3));
    			    tempMovie.setFileSize(Integer.parseInt(answer.substring(S3,S4)));
    			    tempMovie.setDuration(Float.valueOf(answer.substring(S4)));
    			    */
    			   System.out.println("|>> New Movie Made:");
    			    System.out.println("|> Name: " + tempName);
    			    System.out.println("|> Director: " + tempDirector);
    			    System.out.println("|> FileSize: " + t1);
    			    System.out.println("|> Duration: " + t2);
    			   /*
    			    * 
    			    *    System.out.println("|>> New Movie Made:");
    			    System.out.println("|> Name: " + tempMovie.getName());
    			    System.out.println("|> Director: " + tempMovie.getDirector());
    			    System.out.println("|> FileSize: " + tempMovie.getFileSize());
    			    System.out.println("|> Duration: " + tempMovie.getDuration());
    			    */
    			    
    			    
    			    database.setMovie(tempMovie,1);
    			    
    				System.out.println("|");
    				System.out.print("|>> Add");
    			    error = false; break;
    			}else if(answer.equalsIgnoreCase("new_movie_from_file"))
    			{
    				System.out.println("|");
    				System.out.print("|>> Add");
    			    error = false; break;
    			}else if(answer.equalsIgnoreCase("new_playlist"))
    			{
    				System.out.println("|");
    				System.out.print("|>> ");
    			    error = false; break;
    			}else if(answer.equalsIgnoreCase("list_movies"))
    			{
    				System.out.println("|");
    				System.out.print("|>> ");
    			    error = false; break;
    			}else if(answer.startsWith("list_playlist"))
    			{
    			    String subAnswer;
    			    subAnswer = answer.substring(("list_playlist ").length()); // this should get just the playlist in question. E.g. playlist1, playlist2.
    				
    			    System.out.println("|");
    			    if(subAnswer.equals("playlist1"))        //this could be replaced with a For loop checking each playlist for a match.
    				{
    				   //success, playlist 1 is found.
    				   System.out.println("|>> Playlist1 Found!");
    				   playlist1.getPlaylistAsString();
    				   System.out.println("|");
    				   System.out.print("|> ");
    				}else if(subAnswer.equals("playlist2")) 
    				{
    				    //success, playlist 1 is found.
    				   System.out.println("|>> Playlist2 Found!");
    				   playlist2.getPlaylistAsString();
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
    			}else if(answer.startsWith("list_by"))
    			{
    			    String subAnswer;
    			    if (answer.length() <= ("list_by ").length()) {System.out.println("|>> list_by [-d director]"); System.out.println("|");System.out.print("|> ");break;}
    			    subAnswer = answer.substring(("list_by ").length()); // this should get just the playlist in question. E.g. playlist1, playlist2.
    				
    			    System.out.println("|");
    			    if(subAnswer.startsWith("-d"))        //this could be replaced with a For loop checking each playlist for a match.
    				{
    				   String director;
    				   if (subAnswer.length() <= ("-d ").length()) {System.out.println("|>> list_by [-d director]"); System.out.println("|");System.out.print("|> ");break;}
    				   director = subAnswer.substring(("-d ").length());
    				   //success, we know they're trying to  1 is found.
    				   System.out.println("|>> Director request found found!");
    				   System.out.println(director);
    				   //playlist1.Show()
    				   System.out.println("|");
    				   System.out.print("|> ");
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
    			}else if(answer.startsWith("delete"))
    			{
    				System.out.println("|");
    				System.out.print("|>> ");
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
    				//Error: Not correct format
    				error = false;
    			}
    }while (error == true);
}

private void launchGUI()
{
    //not implimented
    System.out.print("\n\nGUI not implimented. Shutting down"); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print("."); customWait(1000); System.out.print(".");
}

private void customWait(int miliSeconds)
{
    //editted excert from https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html
    try {
        Thread.sleep(miliSeconds);                 //1000 milliseconds is one second.
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
}
public static void main(String[] args){
 Interface intFace = new Interface();
intFace.run();
}
}