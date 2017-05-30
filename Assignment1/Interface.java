/* Name: Interface.java
 * Author: Chris Caelli     Date: 30/05/17
 * Purpose: Acts as interface between our User and all other classes.
 * Use: Launch terminal application and follow the prompts
 * Comments: 
 * You don't have to use it, but I've attached a AutoHotKey.ahk that I was using for benchtesting. 
 * Feel free to make use of it. If using BlueJ, you have to check "Unlimited Buffer" in the Terminal->Options
 */

import java.util.*;                         //general IO
import java.text.*;                         //string classes
import java.io.File;                        //file IO
import java.io.FileNotFoundException;       //File try/catch defintions
import java.io.PrintWriter;                 //file output

public class Interface {
    
    /*
     * Private Varaibles for encapsulation
     */
    
    private Playlist playlistArray[];       //called playlists[] in the assignment sheet. Seems ambigious to have "allMovies" and "playlists", why not "allPlaylists"? I prefered this name.
    private MovieDatabase database;         //A single movie database is used
    private boolean exit;                   //exit is used to to exit our program's input while loop
    private int logicalSize = 0;            //logicalSize is a int used to count the number of playlists we have. Used to be called playlistCount
    
    private Scanner console = new Scanner(System.in);   //the console is used for our general IO

    private String databasePath = "";                   //points to the active database, if loaded/saved.

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

// ------------------------------------- START UP BLOCK ---------------------------------------------

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

    //this method is made for easy recalling of our commands 
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
// ------------------------------------- START UP BLOCK ---------------------------------------------


// ------------------------------------- SANTISE INPUT BLOCK -----------------------------------------   
    //sanatiseNextInt is used in lieu of the regular "console.nextInt", as a way we can capture and handle user input.
    private boolean sanatiseNextInt(int [] convertedAnswer)    //what a beautiful pass-by-reference function which won't work in Java. Welcome to the single element array.
    {
        String answer = console.nextLine();         //get next line
        boolean error = false;                      //did an error happen?

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

    //sanatiseNextIntFromFile is used in lieu of the regular "file.nextLine", as a way we can capture and handle our File I/O.
    private boolean sanatiseNextIntFromFile(String nextLine, int [] convertedAnswer)    //what a beautiful pass-by-reference function which won't work in Java. Welcome to the single element array.
    {
        String answer = nextLine;
        boolean error = false;

            //santise
            try{
                convertedAnswer[0] = Integer.parseInt(answer);
                error = false;
                
            }catch(NumberFormatException ex)
            {
                //Error: Not correct format
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

    private boolean sanatiseNextFloatFromFile(String nextLine, float [] convertedAnswer)
    {
        String answer = nextLine;
        boolean error = false;

            //santise
            try{
                convertedAnswer[0] = Float.parseFloat(answer);
                error = false;
            }catch(NumberFormatException ex)
            {
                //Error: Not correct format
                error = true;
            }
            
            return(error);
    }

// ------------------------------------- SANTISE INPUT BLOCK -----------------------------------------


// ------------------------------------- USER INPUT & MAIN LOOP BLOCK ---------------------------------
    private void processUserInput()
    {
        int menuOption[] = {0}; //Program navigation is via ints. This is the int we'll use. It's a pointer because 
                                //I planned for the santiseNextInt() methods without realising we can't pass-by-reference in java. 
                                //So... single element array hack it is
        //start by grabbing a command and santise till good
        while(sanatiseNextInt(menuOption));
        //switch on our valid int
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
    }
// ------------------------------------- USER INPUT & MAIN LOOP BLOCK ---------------------------------


// ------------------------------------- COMMAND FUNCTIONS BLOCK --------------------------------------

 //a method show the help text
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
        }while(tempName.matches(".*\\w.*") == false);       //keep asking for input till non-blank. Read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        

        do
        {
            //enter Director   
            System.out.println("|"); 
            System.out.println("|>> Enter Name of Director");
            System.out.println("|");
            System.out.print("|> ");    
            tempDirector = console.nextLine();
        }while(tempDirector.matches(".*\\w.*") == false);       //read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        


        //enter FileSize
        System.out.println("|>> Enter FileSize of Movie (e.g. 512 for 512mb)");
        System.out.println("|");
        System.out.print("|> ");
        while (sanatiseNextInt(tempFileSize));   //will repeat input until a valid input is taken

        //enter Duration
        System.out.println("|>> Enter Duration of Movie (e.g. 144.2 for 144.2 minutes))");
        System.out.println("|");
        System.out.print("|> ");
        while (sanatiseNextFloat(tempDuration));   //will repeat input until a valid input is taken

        //add our inputs to the new temp movie.
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
            database.addMovie(tempMovie);   //add movie to database
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
    
    //a method make a new playlist
    private void newPlaylist()
    {
        String tempPlaylistName;
        int intAnswer = 0;

        do
        {
        //enter Playlist Name
        System.out.println("|");
        System.out.println("|>> Enter Name of new Playlist");
        System.out.println("|");
        System.out.print("|> ");
        tempPlaylistName = console.nextLine();
        }while(tempPlaylistName.matches(".*\\w.*") == false);       //read at https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#matches(java.lang.String)
        


        Playlist tempPlaylist = new Playlist(tempPlaylistName); //constructor makes a new playlist with our name.

        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        System.out.println("|>> To select movies, simply type in their number. When you are done, type in -1");
        do
        {
            intAnswer = showNumberedMovies();   //shows our movies as options, and returns the option the user picked
            if(intAnswer==-1)
            {
                break;  //user wanted to quit
            }
            else if (intAnswer > database.getLogicalSize())
            {
                //wrong! choice doesn't go higher than the number of movies we have

            }
            else
            {
                tempPlaylist.addMovieToPlaylist(database.getMovie(intAnswer));
            }
        }while(intAnswer!=-1);
        //add this new playlist now
        addNewPlaylist(tempPlaylist);
        System.out.println("|");
        System.out.print("|> ");
    }

    //sub-method for adding plalist to our playlistArray. For readablity
    private void addNewPlaylist(Playlist tempPlaylist)
    {
        Playlist tempPlaylists[] = new Playlist[logicalSize+1];
        chrisCopy2implimentation(playlistArray,tempPlaylists,tempPlaylist);
        playlistArray = tempPlaylists;

        logicalSize++;
        debugPrint("LogiSize= " + Integer.toString(logicalSize));
    }
    
    //a method show all movies in our database
    private void listMovies()
    {
        System.out.println("|>> Movies in Database:");
        System.out.println("|");
        for (int i = 0; i < database.getLogicalSize(); i++)     //loop movies
        {
            System.out.println("|> " + database.getMovie(i).getName());
        }
        
        System.out.println("|");
        System.out.print("|> ");
    }
    
    //a method show playlists
    private void listPlaylists()
    {
        System.out.println("|>> Playlists in System:");
        for (int i = 0; i < logicalSize; i++)
        {
            System.out.println("|> " + playlistArray[i].getPlaylistName());
        }
        System.out.print("|> ");
    }
    
    //a method show specific playlist's contents
    private void listMoviesInSpecificPlaylist()
    {
        int intAnswer = showNumberedPlaylists();
        if (intAnswer == -1) 
        {
            //no playlists or exited
        }
        else
        {
            System.out.println("|>> Movies in \"" + playlistArray[intAnswer].getPlaylistName() +  "\":");
            System.out.println("|> " + playlistArray[intAnswer].getMoviesAsString());
        }

        System.out.println("|");
        System.out.print("|> ");
    }
    
    //a method show by director
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
    
    //a method delete a movie from a playlist
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

    
    //a method delete a playlist
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
    
    //a method delete a movie
    private void deleteMovie()
    {
        System.out.println("|>> Select a Movie to delete it");
        int selectedMovie = showNumberedMovies();                       //allows user to select movie
        String tempName = database.getMovie(selectedMovie).getName();
        database.deleteMovie(selectedMovie);                            //deletes from database
        for (int i = 0; i < logicalSize; i++)                           //for each playlist...
        {

            debugPrint("Attempting to delete from " + playlistArray[i].getPlaylistName());
            int indexOfMovieInsidePlaylist = playlistArray[i].getMovieNumber(tempName);
            if (indexOfMovieInsidePlaylist != -1) 
            {
                playlistArray[i].deleteMovie(indexOfMovieInsidePlaylist);                //..also delete the movie.   
            }
        }
    }
    
    //a method save Movie Database. Handles more user interaction
    private void saveMovieDatabase()
    {
        //does a path exit?
        if (databasePath.equals("")) 
        {
         //no path exists, please make one
        System.out.println("|>> Saving a new database:");
        System.out.println("|> No path is registered for this Database. Please enter a new path to save the current Movie Database.");
        System.out.println("|> This can either be Absolute (e.g. C:/foo.txt) or Relative (e.g. foo.txt)");
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
    
    //sub-method to save Movie Database to a specific path. handles more specific file interation
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
            }
            outputStream.close();
            System.out.println("|");
            System.out.print("|> ");  
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

    //sub-method to open Movie Database to a specific path. handles more specific file interation
    private void openFromPath(String path)
    {
        //null current database
        Movie.numberOfMovies = 0;
        database.logicalSize = 0;
        logicalSize = 0;

        //success, save the path
        databasePath = path;
        // -------- my implimentation of Lecture 11 --------
        Scanner inputStream = null;
        System.out.println ("Reading file " + path +" now...");
        try
        {
            int i = 0;
            inputStream = new Scanner (new File (path));
            while (inputStream.hasNextLine())
            {

                String tempName,tempDirector,tempFileSize_str,tempDuration_str;
                int tempFileSize[] = {0};
                float tempDuration[] = {0};

                //set up a temp. movie variable, for us to pass to the database to store.
                Movie tempMovie;
                tempMovie = new Movie();
                //enter Name
                tempName = inputStream.nextLine();
                if(tempName.equals(""))
                {
                    //I suspect it was unintentional, but the movieDatabase provided has 3 Carriage Returns at the end.
                    //call it quits, we're done here.
                    break;
                }
                debugPrint(Integer.toString(i) + ": " + tempName); i++;
                //trim name
                tempName = tempName.substring(13);

                if (inputStream.hasNextLine() == false) {debugPrint("NO NEW LINES!");}

                //enter Director
                tempDirector = inputStream.nextLine();
                //trim Director
                tempDirector= tempDirector.substring(10);

                if (inputStream.hasNextLine() == false) {debugPrint("NO NEW LINES!");}

                //enter FileSize
                tempFileSize_str = inputStream.nextLine();
                tempFileSize_str = tempFileSize_str.substring(10);
                sanatiseNextIntFromFile(tempFileSize_str, tempFileSize);

                if (inputStream.hasNextLine() == false) {debugPrint("NO NEW LINES!");}

                //enter Duration
                tempDuration_str = inputStream.nextLine();
                tempDuration_str = tempDuration_str.substring(10);
                sanatiseNextFloatFromFile(tempDuration_str, tempDuration); 

                debugPrint("Opened: " + tempName + " " + tempDirector + " " + Integer.toString(tempFileSize[0]) + " " + Float.toString(tempDuration[0]));
                tempMovie.setName(tempName);
                tempMovie.setDirector(tempDirector);
                tempMovie.setFileSize(tempFileSize[0]);
                tempMovie.setDuration(tempDuration[0]);

                database.addMovie(tempMovie);
                Movie.numberOfMovies++; //static as requested.

                //discard the new carriage line
                inputStream.nextLine();
                }

                System.out.println("|");
                System.out.print("|>");
            
            inputStream.close ();
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
    //a method save Movie Database. Handles more user interaction
    private void openMovieDatabase()
    {


        int intAnswer[] = {-1};
        boolean error = false;
        do
        {
            //alert user this will override their current movieDatabase
            System.out.println("|>> This will repalce any unsaved changes to the current Movie Database. Are you sure you'd like to continue?");
            System.out.println("|> - 1 Yes");
            System.out.println("|> - 2 No");
            System.out.println("|");
            System.out.print("|> ");  
            error = sanatiseNextInt(intAnswer);
        }while(error || (intAnswer[0] > 2) || (intAnswer[0] < 1));

        switch (intAnswer[0])
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
    
     //a method show all movies under a duration given
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
    
     //a method edit any movie in our database
    private void editMovie()
    {
         int selectedMovie;// = showNumberedMovies();
         int editMovieOptions;
        //now that we have a new Playlist, let's look at adding movies before adding it to our playlistArray
        //System.out.println("|>> To add a movie, simply type in their number. When you are done, type in -1");
        do
        {
            System.out.println("|>> Select a Movie to edit it");
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
                        break;
                    }
                    case 2:
                    {
                        String tempDirector = "";
                        System.out.println("|>> Enter a new Director:");
                        System.out.print("|> ");
                        tempDirector = console.nextLine();
                        tempMovie.setDirector(tempDirector);
                        database.setMovie(selectedMovie,tempMovie);
                        break;
                    }
                    case 3:
                    {
                        int tempFileSize = -1;
                        System.out.println("|>> Enter a new FileSize:");
                        System.out.print("|> ");
                        tempFileSize = console.nextInt();
                        tempMovie.setFileSize(tempFileSize);
                        database.setMovie(selectedMovie,tempMovie);
                        break;
                    }
                    case 4:
                    {
                        float tempDuration = -1;
                        System.out.println("|>> Enter a new Duration:");
                        System.out.print("|> ");
                        tempDuration = console.nextFloat();
                        tempMovie.setDuration(tempDuration);
                        database.setMovie(selectedMovie,tempMovie);
                        break;
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

    //sub-method for showing all the options for edditing a movie and taking the user's input for what they choice
    private int editMovieMenu()
    {

        int intAnswer[] = {-1};
        boolean error = false;
        do
        {
            //ask user to navigate by number
            System.out.println("|>> Edit Movie Menu");
            System.out.println("|");        
            System.out.println("|> 1 - Edit Name");
            System.out.println("|> 2 - Edit Director");
            System.out.println("|> 3 - Edit FileSize");
            System.out.println("|> 4 - Edit Duration");
            System.out.println("|> 5 - Exit Menu");
            System.out.println("|");
            System.out.print("|> ");
            error = sanatiseNextInt(intAnswer);
        }while(error || (intAnswer[0] > 5) || (intAnswer[0] < 1));
        return(intAnswer[0]);

    }
    
    //sub-method for showing and handling user input for choosing a playlist
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
        }while(error || (intAnswer[0] > (logicalSize -1)) || (intAnswer[0] < -1));

        return(intAnswer[0]);
    }
    
    //sub-method for showing and handling user input for choosing a movie
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

    //sub-method for showing and handling user input for choosing a movie inside any playlist
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


     //a method edit the playlist by adding more movies to it
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


    //a method exit our program
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


    //chrisCopy2implimentation from Lecture 9. Also found in MovieDatabase.
    private void chrisCopy2implimentation(Playlist array1[], Playlist array2[], Playlist newPlaylist)
    {
      for(int i=0; i< array1.length; i++)
      {
        array2[i] = array1[i];
      }
      array2[array1.length] = newPlaylist;
    }

    //used because BlueJ is an awful, awful program. It apprently loads the entire .java file into memory, makes changes and re-writes the ENTIRE file.
    public void debugPrint(String debugString)
    {
        System.out.println("|DEBUG~$: " + debugString);
    }

}