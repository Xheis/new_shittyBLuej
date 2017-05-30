/* Name: Interface.java
 * Author: Chris Caelli     Date: 4/03/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch as a terminal application and follow the prompts
 * Comments: 
 */

import java.util.*;
import java.text.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;          

public class Interface {
    
    /*
     * Private Varaibles for encapsulation
     */
    
    private Playlist playlistArray[];   //called playlists[] in the assignment sheet. Seems ambigious to have "allMovies" and "playlists", why not "allPlaylists"? I prefered this name.
    private MovieDatabase database;         //A single movie database is used
    private boolean exit;                   //exit is used to to exit our program's input while loop
    private int logicalSize = 0;              //logicalSize is a int used to count the number of playlists we have. Used to be called playlistCount
    
    private Scanner console = new Scanner(System.in);   //the console will mainly be used

    private String databasePath = "";

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
        //initInterface();    //initialise all our variables we'll need to start
        launchTIO();    //launch our TIO-driven command loop
    }

    //init our variables, which is mainly just calling initialising constructors of our classes. Could be a constructor, but I'll leave it as is.
    //private void initInterface()
    public Interface()
    {
       playlistArray = new Playlist[logicalSize];
       database = new MovieDatabase();
    }

    //this method will run our user interface via TIO
    private void launchTIO()
    {
        System.out.println("|-----------------------------------------------------------");
        System.out.println("|------------------   Movie Assignment 2  ------------------");
        System.out.println("|-----------------------------------------------------------");
        System.out.println("|> Type the following commands to");
        System.out.println("|> navigate the program:");
        System.out.println("|>");
        printCommandsString();
        
        //after our opening intro, we're ready to dive in.
        while (!exit)
        {
            processUserInput(); // Until we exit, keep processing user input
        }
    }

    //this method is made for visability 
    private void printCommandsString()
    {
        System.out.println("|>> Commands:");
        System.out.println("|> 1  - Help");
        System.out.println("|> 2  - New Movie");
        System.out.println("|> 3  - New Playlist");
        System.out.println("|> 4  - List Movies");
        System.out.println("|> 5  - List Playlists");
        System.out.println("|> 6  - List Movies in Specific Playlist");
        System.out.println("|> 7  - List Movies By Director");
        System.out.println("|> 8  - Delete From Playlist");
        System.out.println("|> 9  - Delete Playlist");
        System.out.println("|> 10 - Delete Movie");
        System.out.println("|> 11 - Save Movie Database");
        System.out.println("|> 12 - Open Movie Database");
        System.out.println("|> 13 - List Movies Under Specific Runtime");
        System.out.println("|> 14 - Edit Movie");
        System.out.println("|> 15 - Edit Playlist");
        System.out.println("|> 16 - Exit");
        System.out.print("|> ");
    }

    
    private boolean sanatiseNextInt(int [] convertedAnswer)    //what a beautiful pass-by-reference function which won't work in Java. Welcome to the single element array.
    {
        String answer = console.nextLine();
        boolean error = false;

            //santise
            try{
                convertedAnswer[0] = Integer.parseInt(answer);
                error = false;
                
            }catch(NumberFormatException ex)
            {
                //Error: Not correct format
                System.out.println("|>> I couldn't understand your answer. Please enter again \n");
                System.out.println("|");
                System.out.print("|> ");
                error = true;
            }

            return(error);
    }

    private boolean sanatiseNextFloat(float [] convertedAnswer)
    {
        String answer = console.nextLine();
        boolean error = false;

            //santise
            try{
                convertedAnswer[0] = Float.parseFloat(answer);
                error = false;
            }catch(NumberFormatException ex)
            {
                //Error: Not correct format
                System.out.println("|>> I couldn't understand your answer. Please enter again \n");
                System.out.println("|");
                System.out.print("|> ");
                error = true;
            }
            
            return(error);
    }


    private void processUserInput()
    {
        //Take user input by looping through the uglest nested if-else tree you can imagine. I wanted to do the program like this, but I didn't work out how to make a proper command line parser. 
                    //String answer;          //Answer as a string
                    int menuOption[] = {0};//convertedAnswer;    //Answer converted to int. Allows us to check number range, sanatise etc.
                    //boolean error;  //answer error flag
                    //error = false;

        //start by grabbing a command and checking it
       // do 
        //{                           //get the right answer
                    //answer = console.nextLine();
                    //if (sanatiseNextInt(menuOption))
                    //{
                    //    continue; //an error has occured, skip this loop iteration and start again.
                    //}
                    while(sanatiseNextInt(menuOption));
                    //santise
                    /*try{
                        convertedAnswer = Integer.parseInt(answer);
                        error = false;
                    }catch(NumberFormatException ex)
                    {
                        //Error: Not correct format
                        System.out.println("|");
                        System.out.println("|>> I couldn't understand your answer. Please enter again \n");
                        System.out.println("|");
                        System.out.print("|> ");
                        error = true;
                        continue; //skips this do loop operation
                    } */

                    switch(menuOption[0])
                    {
                        case 1: help();break; 

                        case 2: newMovie();break; 

                        case 3: newPlaylist();break; 

                        case 4: listMovies();break; 

                        case 5: listPlaylists();break; 

                        case 6: listMoviesInSpecificPlaylist();break; 

                        case 7: listByMovieDirector();break; 

                        case 8: deleteFromPlaylist();break; 

                        case 9: deletePlaylist();break; 

                        case 10: deleteMovie();break; 

                        case 11: saveMovieDatabase();break; 

                        case 12: openMovieDatabase();break; 

                        case 13: listMoviesUnderSpecificRuntime();break; 

                        case 14: editMovie();break; 

                        case 15: editPlaylist();break; 

                        case 16: exit();break; 

                        default: 
                            //Error: Not recognised command
                            System.out.println("|");
                            System.out.println("|>> Not a recognised command. Please enter again");
                            System.out.println("|");
                            System.out.print("|> ");
                            //error = true;
                    }
        //}while (error == true);
    }

 //a method show help text
    private void help()
    {
        System.out.println("|");
        printCommandsString();
    }
    

    //a method we call to create a new movie
    private void newMovie()
    {
        //variables we'll fill, and use to create a new instance of a movie
        String tempName,tempDirector;
        int tempFileSize[] = {0};
        float tempDuration[] = {0};

        //set up a temp. movie variable, for us to pass to the database to store.
        Movie tempMovie;
        tempMovie = new Movie();

        do
        {
        	//enter Name
        	System.out.println("|");
	        System.out.println("|>> Enter Name of Movie");
	        System.out.println("|");
	        System.out.print("|> ");
			tempName = console.nextLine();
        }while(tempName.matches(".*\\w.*") == false);		//read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        

        do
        {
		    //enter Director   
		    System.out.println("|"); 
		    System.out.println("|>> Enter Name of Director");
		    System.out.println("|");
		    System.out.print("|> ");    
		    tempDirector = console.nextLine();
        }while(tempDirector.matches(".*\\w.*") == false);		//read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        


        //enter FileSize
        System.out.println("|>> Enter FileSize of Movie (e.g. 512 for 512mb)");
        System.out.println("|");
        System.out.print("|> ");
        //tempFileSize = console.nextInt();
        while (sanatiseNextInt(tempFileSize));   //will repeat input until a valid input is taken

        //enter Duration
        System.out.println("|>> Enter Duration of Movie (e.g. 144.2 for 144.2 minutes))");
        System.out.println("|");
        System.out.print("|> ");
        //tempDuration = console.nextFloat();
        while (sanatiseNextFloat(tempDuration));   //will repeat input until a valid input is taken

        tempMovie.setName(tempName);
        tempMovie.setDirector(tempDirector);
        tempMovie.setFileSize(tempFileSize[0]);
        tempMovie.setDuration(tempDuration[0]);

        if (database.getMovieNumber(tempName) == -1) //time to check if we have a duplicate movie by name: (Note: An assumption is made that all other meta data could theoretically be the same as a different, unique movie. We also butcher our "getMovieNumber()" method to check)
        {
            System.out.println("|>> New Movie Made!");
            System.out.println("|> Movie title: " + tempMovie.getName());
            System.out.println("|> Director: " + tempMovie.getDirector());
            System.out.println("|> FileSize: " + tempMovie.getFileSize());
            System.out.println("|> Duration: " + tempMovie.getDuration());
            database.addMovie(tempMovie);
            Movie.numberOfMovies++; //static as requested.

            System.out.println("|");
            System.out.print("|>");
        }
        else
        {
           System.out.println("|>> This movie already exists!"); 
           System.out.println("|");
           System.out.print("|>");
        }
    }
    
    //a method show help text
    private void newPlaylist()
    {
        String tempPlaylistName;
        int intAnswer = 0;
        

        do
        {
		//enter Name
        System.out.println("|");
        System.out.println("|>> Enter Name of new Playlist");
        System.out.println("|");
        System.out.print("|> ");
        tempPlaylistName = console.nextLine();
        }while(tempPlaylistName.matches(".*\\w.*") == false);		//read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        


        Playlist tempPlaylist = new Playlist(tempPlaylistName);
        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        System.out.println("|>> To select movies, simply type in their number. When you are done, type in -1");
        do
        {
            intAnswer = showNumberedMovies();
            if(intAnswer==-1)
            {
                break;
            }
            else if (intAnswer > database.getLogicalSize())
            {
            	//wrong choice

            }
            else
        	{
        		tempPlaylist.addMovieToPlaylist(database.getMovie(intAnswer));
        	}
    }while(intAnswer!=-1);
    
        addNewPlaylist(tempPlaylist);
        System.out.println("|");
        System.out.print("|> ");
    }

    private void addNewPlaylist(Playlist tempPlaylist)
    {
        Playlist tempPlaylists[] = new Playlist[logicalSize+1];
        chrisCopy2implimentation(playlistArray,tempPlaylists,tempPlaylist);
        playlistArray = tempPlaylists;

        logicalSize++;
        debugPrint("LogiSize= " + Integer.toString(logicalSize));
    }
    
    //a method show help text
    private void listMovies()
    {
        System.out.println("|>> Movies in Database:");
        System.out.println("|");
        for (int i = 0; i < database.getLogicalSize(); i++) 
        {
            System.out.println("|> " + database.getMovie(i).getName());
        }
        System.out.print("|> ");
    }
    
    //a method show help text
    private void listPlaylists()
    {
        System.out.println("|>> Playlists in System:");
        for (int i = 0; i < logicalSize; i++)
        {
            System.out.println("|> " + playlistArray[i].getPlaylistName());
        }
        System.out.print("|> ");
    }
    
    //a method show help text
    private void listMoviesInSpecificPlaylist()
    {
        int intAnswer = showNumberedPlaylists();
        System.out.println("|>> Movies in \"" + playlistArray[intAnswer].getPlaylistName() +  "\":");
        System.out.println("|> " + playlistArray[intAnswer].getMoviesAsString());
    }
    
    //a method show help text
    private void listByMovieDirector()
    {
        String tempDirector, stringToPrint;
        //enter Director    
        System.out.println("|>> Enter Name of Director");
        System.out.println("|");
        System.out.print("|> ");    
        tempDirector = console.nextLine();

        stringToPrint = database.getMovieByDirector(tempDirector);
        System.out.println(stringToPrint); 
        System.out.println("| "); 
        System.out.print("|> "); 
    }
    
    //a method show help text
    private void deleteFromPlaylist()
    {
         //select a playlist
         System.out.println("|>> Select a Playlist");
         int selectedPlaylist = showNumberedPlaylists();
         int selectedMovie = -1;
         //tempPlaylist.addMovieToPlaylist(database.getMovie(intAnswer));
        
        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        System.out.println("|>> To delete a movie, simply type in their number.");
        do
        {
            selectedMovie = showNumberedMoviesInPlaylist(selectedPlaylist);
            if(selectedMovie==-1)
            {
                break;
            }
            else
            {
                playlistArray[selectedPlaylist].deleteMovie(selectedMovie);
            }
            
        }while(selectedMovie!=-1);


        System.out.println("|");
        System.out.print("|> ");
    }

    
    //a method show help text
    private void deletePlaylist()
    {
        System.out.println("|>> Select a Playlist to delete it");
        int selectedPlaylist = showNumberedPlaylists();
        String tempNameOfPlaylist = playlistArray[selectedPlaylist].getPlaylistName();
        Playlist tempPlaylistArray[] = new Playlist[logicalSize-1];
        for (int i = 0; i< tempPlaylistArray.length; i++) 
        {
            //copy 1 to 1 until we get to selectedPlaylist, then copy 1+1 to 1
            if (i < selectedPlaylist) 
            {
                tempPlaylistArray[i] = playlistArray[i];
            }
            else if (i == selectedPlaylist)
            {
                //Do nothing, but I'm leaving this nested if to indicate we're deliberatelty doing nothing
            }   
            else if (i > selectedPlaylist)
            {
                tempPlaylistArray[i] = playlistArray[i+1];
            }
        }
        playlistArray = tempPlaylistArray;
        logicalSize--;

        debugPrint("LogiSize= " + Integer.toString(logicalSize));
        System.out.println("|> " + tempNameOfPlaylist + " deleted."); 
        System.out.println("|");
        System.out.print("|> ");  
    }
    
    //a method show help text
    private void deleteMovie()
    {
        System.out.println("|>> Select a Movie to delete it");
        int selectedMovie = showNumberedMovies();                       //allows user to select movie
        database.deleteMovie(selectedMovie);                            //deletes from database
        for (int i = 0; i < logicalSize; i++)                           //for each playlist...
        {
            playlistArray[i].deleteMovie(selectedMovie);                //..also delete the movie.   
        }
    }
    
    //a method show help text
    private void saveMovieDatabase()
    {
        //does a path exit?
        if (databasePath.equals("")) 
        {
         //no path exists, please make one
        System.out.println("|>> Saving a new database:");
        System.out.println("|> No path is registered for this Database. Please enter a new path to save the current Movie Database.");
        System.out.print("|>");
        String answerPath = "";
        answerPath = console.nextLine();
        databasePath = answerPath;
        //save to path
        saveToPath(databasePath);
        }

        else
        {
            //save to path
            saveToPath(databasePath);
        }
    }
    
    private void saveToPath(String path)
    {
        // -------- my implimentation of Lecture 11 --------
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter (path);
            int numberOfMovies = database.getLogicalSize();
            for (int i = 0; i < numberOfMovies; i++) 
            {
                outputStream.println("Movie title: "    + database.getMovie(i).getName());
                outputStream.println("Director: "       + database.getMovie(i).getDirector());
                outputStream.println("fileSize: "       + database.getMovie(i).getFileSize());
                outputStream.println("duration: "       + database.getMovie(i).getDuration());
                outputStream.println("");
            }
            outputStream.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("|>> Error opening the file " + path);
            System.out.println ("|> Returning to main menu ");
            System.out.println("|");
            System.out.print("|> ");  
        }
        // -----------------------------------------------
    }

    private void openFromPath(String path)
    {

        //success, save the path
        databasePath = path;
        // -------- my implimentation of Lecture 11 --------
        Scanner inputStream = null;
        System.out.println ("The file " + path +"\ncontains the following lines:\n");
        try
        {
            inputStream = new Scanner (new File (path));
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("|>> Error opening the file " + path);
            System.out.println ("|> Returning to main menu ");
            System.out.println("|");
            System.out.print("|> ");  
        }
        while (inputStream.hasNextLine ())
        {
            String line = inputStream.nextLine ();
            System.out.println (line);
        }
        inputStream.close ();
        // -----------------------------------------------
    }
     //a method show help text
    private void openMovieDatabase()
    {
        //alert user this will override their current movieDatabase
        System.out.println("|>> This will repalce any unsaved changes to the current Movie Database. Are you sure you'd like to continue?");
        System.out.println("|> - 1 Yes");
        System.out.println("|> - 2 No");
        System.out.println("|");
        System.out.print("|> ");  
        int answer = console.nextInt();
        switch (answer)
        {
            case 1: 
            {
                //openDatabase
                System.out.println("|>> Opening a new database:");
                System.out.println("|> Please enter a path of a new Movie Database to open.");
                System.out.print("|>");
                String answerPath = "";
                answerPath = console.nextLine();
                openFromPath(answerPath);
            }
            case 2: 
            {
                //break
                break;
            }
            default:
            {
                //Not a command option
            }
        }
    }
    
     //a method show help text
    private void listMoviesUnderSpecificRuntime()
    {
        float tempRuntime[] = {0};
        String stringToPrint ="";
        //enter Director    
        System.out.println("|>> Enter Runtime in Minutes (float)");
        System.out.println("|");
        System.out.print("|> ");    
        
        while (sanatiseNextFloat(tempRuntime));   //will repeat input until a valid input is taken

        stringToPrint = database.getMovieByRuntime(tempRuntime[0]);
        System.out.println(stringToPrint); 
        System.out.println("| "); 
        System.out.print("|> "); 
    }
    
     //a method show help text
    private void editMovie()
    {
        System.out.println("|>> Select a Movie to edit it");
         int selectedMovie;// = showNumberedMovies();
         int editMovieOptions;
        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        //System.out.println("|>> To add a movie, simply type in their number. When you are done, type in -1");
        do
        {
            selectedMovie = showNumberedMovies();
            if(selectedMovie==-1)
            {
                break;
            }
            do
            {
                Movie tempMovie = database.getMovie(selectedMovie);
                editMovieOptions = editMovieMenu();
                switch(editMovieOptions)
                {
                    case 1:
                    {
                        String tempName = "";
                        System.out.println("|>> Enter a new Name:");
                        System.out.print("|> ");
                        tempName = console.nextLine();
                        tempMovie.setName(tempName);
                        database.setMovie(selectedMovie,tempMovie);
                    }
                    case 2:
                    {
                        String tempDirector = "";
                        System.out.println("|>> Enter a new Director:");
                        System.out.print("|> ");
                        tempDirector = console.nextLine();
                        tempMovie.setDirector(tempDirector);
                        database.setMovie(selectedMovie,tempMovie);
                    }
                    case 3:
                    {
                        int tempFileSize = -1;
                        System.out.println("|>> Enter a new FileSize:");
                        System.out.print("|> ");
                        tempFileSize = console.nextInt();
                        tempMovie.setFileSize(tempFileSize);
                        database.setMovie(selectedMovie,tempMovie);
                    }
                    case 4:
                    {
                        float tempDuration = -1;
                        System.out.println("|>> Enter a new Duration:");
                        System.out.print("|> ");
                        tempDuration = console.nextFloat();
                        tempMovie.setDuration(tempDuration);
                        database.setMovie(selectedMovie,tempMovie);
                    }
                    case 5:

                    default:
                }
            }while(editMovieOptions!=5 && editMovieOptions!=-1);
            //playlistArray[selectedPlaylist].addMovieToPlaylist(database.getMovie(intAnswer));
        }while(selectedMovie!=-1);


        System.out.println("|");
        System.out.print("|> ");
    }

    private int editMovieMenu()
    {
        System.out.println("|>> Edit Movie Menu");
        System.out.println("|");        
        System.out.println("|> 1 - Edit Name");
        System.out.println("|> 2 - Edit Director");
        System.out.println("|> 3 - Edit FileSize");
        System.out.println("|> 4 - Edit Duration");
        System.out.println("|> 5 - Exit Menu");
        System.out.println("|");
        System.out.print("|> ");
        int answer = -1;
        answer = console.nextInt();
        return(answer);
    }
    
    private int showNumberedPlaylists()
    {
        int intAnswer[] = {-1};
        boolean error = false;
        System.out.println("|>> Showing all Playlists");
        System.out.println("|");
        //loop all playlists, show them numbered;
        for (int i = 0; i < logicalSize; i++) 
        {
            if (i < 10) 
            {
                System.out.println("|> " + Integer.toString(i) + "  - " + playlistArray[i].getPlaylistName());  
            }
            else
            {
                System.out.println("|> " + Integer.toString(i) + " - " + playlistArray[i].getPlaylistName());   
            }
        }

        do
        {
			//ask user to add by number
			System.out.println("| ");
			System.out.println("|> Please choose a playlist between 0 - " + Integer.toString(logicalSize-1) + ". Enter -1 to leave this menu.");
			System.out.print("|> ");
        	error = sanatiseNextInt(intAnswer);
        }while(error || (intAnswer[0] > (logicalSize -1)) || (intAnswer[0] < -1));//intAnswer = console.nextInt();

        return(intAnswer[0]);
    }
    
    private int showNumberedMovies()
    {
        int intAnswer[] = {-1};
        boolean error = false;
        System.out.println("|>> Showing all Movies");
        System.out.println("|");
        //loop all playlists, show them numbered;
        for (int i = 0; i < database.getLogicalSize(); i++) 
        {
            if (i < 10) 
            {
                System.out.println("|> " + Integer.toString(i) + "   - " + database.getMovie(i).getName()); 
            }
            else if (i < 100) 
            {
                System.out.println("|> " + Integer.toString(i) + "  - " + database.getMovie(i).getName());  
            }
            else
            {
                System.out.println("|> " + Integer.toString(i) + " - " + database.getMovie(i).getName());   
            }
        }

        do
        {
			//ask user to add by number
			System.out.println("| ");
			System.out.println("|> Please choose a movie between 0 - " + Integer.toString(database.getLogicalSize()-1) + ". Enter -1 to leave this menu.");
			System.out.print("|> ");
        	error = sanatiseNextInt(intAnswer);
        }while(error || (intAnswer[0] > (database.getLogicalSize()-1)) || (intAnswer[0] < -1));//intAnswer = console.nextInt();
        return(intAnswer[0]);
    }

    private int showNumberedMoviesInPlaylist(int playlistIndex)
    {
        int intAnswer[] = {-1};
        boolean error = false;
        System.out.println("|>> Showing all Movies in " + playlistArray[playlistIndex].getPlaylistName());
        System.out.println("|");
        //loop all playlists, show them numbered;
        for (int i = 0; i < playlistArray[playlistIndex].getLogicalSize(); i++) 
        {
            if (i < 10) 
            {
                System.out.println("|> " + Integer.toString(i) + "   - " + playlistArray[playlistIndex].getMovie(i).getName()); 
            }
            else if (i < 100) 
            {
                System.out.println("|> " + Integer.toString(i) + "  - " + playlistArray[playlistIndex].getMovie(i).getName());  
            }
            else
            {
                System.out.println("|> " + Integer.toString(i) + " - " + playlistArray[playlistIndex].getMovie(i).getName());   
            }
        }

        do
        {
            //ask user to add by number
            System.out.println("| ");
            System.out.println("|> Please choose a movie between 0 - " + Integer.toString(playlistArray[playlistIndex].getLogicalSize()-1) + ". Enter -1 to leave this menu.");
            System.out.print("|> ");
            error = sanatiseNextInt(intAnswer);
        }while(error || (intAnswer[0] > (playlistArray[playlistIndex].getLogicalSize()-1)) || (intAnswer[0] < -1));//intAnswer = console.nextInt();
        return(intAnswer[0]);
    }


     //a method show help text
    private void editPlaylist()
    {
        //select a playlist
         System.out.println("|>> Select a Playlist to edit it");
         int selectedPlaylist = showNumberedPlaylists();
         int selectedMovie = -1;
         //tempPlaylist.addMovieToPlaylist(database.getMovie(intAnswer));
        
        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        System.out.println("|>> To add a movie, simply type in their number. When you are done, type in -1");
        do
        {
            selectedMovie = showNumberedMovies();
            if(selectedMovie==-1)
            {
                break;
            }
            if(playlistArray[selectedPlaylist].addMovieToPlaylist(database.getMovie(selectedMovie)) == false)
            {
                //an error has occured! Notify user.
                System.out.println("|> Playlist full! Please delete movies from this playlist if you'd like to add more. ");
                System.out.println("|");
                System.out.print("|> ");

            }
        }while(selectedMovie!=-1);


        System.out.println("|");
        System.out.print("|> ");
    }


    //a method show help text
    private void exit()
    {
        exit = true;
        System.out.println("|");
        System.out.print("|>> Exiting"); customWait(330); System.out.print("."); customWait(330); System.out.print("."); customWait(330); System.out.print(".");
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


    //chrisCopy2implimentation from Lecture 9
    private void chrisCopy2implimentation(Playlist array1[], Playlist array2[], Playlist newPlaylist)
    {
      for(int i=0; i< array1.length; i++)
      {
        array2[i] = array1[i];
      }
      array2[array1.length] = newPlaylist;
    }

    public void debugPrint(String debugString)
    {
        System.out.println("|DEBUG~$: " + debugString);
    }

}