package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* 
This class is used to create the maze, allow the navigator to move in it
and has many methods that allow access to different features of the maze
*/ 
public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private BufferedReader reader;

    private ArrayList<MazeFeatures[]> mazeObject;
    private Navigator navigator;
    private int[] location = new int[2]; //coordinate pair

    private int mazeWidth, mazeHeight;

    /*
     * Maze constructor that takes in a file and uses it as the maze by reading the lines of the file,
     * '#' represents the walls in the file and ' ' represents the spaces the navigator can move around.
     */
    public Maze(String file, Navigator navigator){
        mazeObject = new ArrayList();
        this.navigator = navigator;
        
        //Creating a file reader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException | NullPointerException e) {
            logger.error("File Error: " + e.getMessage(), e);
            System.out.println("Exiting program!");
            System.exit(1);
        }

        try {
            MazeFeatures[] lineChars;
            int lineNum = 0;
            String line;
            // creates the maze object from the file
            while ((line = reader.readLine()) != null) {
                lineChars = new MazeFeatures[line.length()];
                for (int idx=0; idx<line.length(); idx++) {
                    if(line.charAt(idx) == '#') lineChars[idx] = MazeFeatures.WALL;
                    else lineChars[idx] = MazeFeatures.SPACE;
                }
                mazeObject.add(lineChars);
                lineNum++;
            }
            mazeWidth = mazeObject.get(0).length;
            mazeHeight = mazeObject.size();
        } catch (IOException e) {
            logger.error("Error reading the file: " + e.getMessage(), e);
            System.out.println("Exiting program!");
            System.exit(1);
        } catch (NullPointerException e) {
            logger.error("Unexpected null reference encountered: " + e.getMessage(), e);
            System.out.println("Exiting program!");
            System.exit(1);
        } catch (IllegalStateException e) {
            logger.error("Invalid state: " + e.getMessage(), e);
            System.out.println("Exiting program!");
            System.exit(1);
        }

        //Searches for the entrance and sets the location there
        location = getEntrance();
    }

    // returns the coordinates of the entrance of the maze
    public int[] getEntrance(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[0] == MazeFeatures.SPACE) return new int[]{0, i};
        }
        return null;
    }

    // returns the coordinates of the exit of the maze
    public int[] getExit(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[mazeWidth-1] == MazeFeatures.SPACE) return new int[]{mazeWidth-1, i};
        }
        return null;
    }

    // returns the location of the navigator
    public int[] getLocation(){
        return location;
    }

    // sets the location of the navigator to a specified location
    public void setLocation(int[] coordinate){
        location = coordinate;
    }

    /* This method makes many checks depending on which direction the navigator is facing and if there is space in front
     * and returns a boolean determinines if moving was successful
     */
    public boolean moveNavigatorForward(){
        Directions facing = navigator.getFacing();

        if(facing == Directions.NORTH && location[1] != 0 && mazeObject.get(location[1]-1)[location[0]] == MazeFeatures.SPACE) location[1] -= 1;
        else if(facing == Directions.EAST && location[0] != mazeWidth-1 && mazeObject.get(location[1])[location[0]+1] == MazeFeatures.SPACE) location[0] += 1;
        else if(facing == Directions.SOUTH && location[1] != mazeHeight-1 && mazeObject.get(location[1]+1)[location[0]] == MazeFeatures.SPACE) location[1] += 1;
        else if(facing == Directions.WEST && location[0] != 0 && mazeObject.get(location[1])[location[0]-1] == MazeFeatures.SPACE) location[0] -= 1;
        else return false;
        return true;
    }

    // for troubleshooting purposes only
    // prints out the maze to visualize the movement of the navigator
    public void printMaze(){
        Directions facing = navigator.getFacing();
        String line = "";
        for (int y = 0; y < mazeObject.size(); y++) {
            for (int x = 0; x < mazeObject.get(0).length; x++) {
                if(location[0] == x && location[1] == y){
                    if(facing == Directions.NORTH) line += '^';
                    else if(facing == Directions.EAST) line += '>';
                    else if(facing == Directions.SOUTH) line += 'v';
                    else if(facing == Directions.WEST) line += '<';
                } else {
                    if(mazeObject.get(y)[x] == MazeFeatures.SPACE) line += ' ';
                    else line += '#';
                    //line += mazeObject.get(y)[x] + "\t";
                }
            }
            System.out.println(line);
            line = "";
        }
        System.out.println();
    }

    // returns the navigator in the maze
    public Navigator getNavigator(){
        return navigator;
    }
}
