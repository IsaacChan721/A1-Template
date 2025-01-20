package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();
    private BufferedReader reader;

    private ArrayList<char[]> mazeObject;
    private Navigator navigator;
    private int[] location = new int[2]; //coordinate pair

    private int mazeWidth, mazeHeight;

    public Maze(String file, Navigator navigator){
        mazeObject = new ArrayList();
        this.navigator = navigator;
        
        //Creating a file reader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error(e);
        }

        try {
            char[] lineChars;
            int lineNum = 0;
            String line;
            // creates the maze object from the file
            while ((line = reader.readLine()) != null) {
                lineChars = new char[line.length()];
                for (int idx=0; idx<line.length(); idx++) {
                    lineChars[idx] = line.charAt(idx);
                }
                mazeObject.add(lineChars);
                lineNum++;
            }
            mazeWidth = mazeObject.get(0).length;
            mazeHeight = mazeObject.size();
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        //Searches for the entrance and sets the location there
        location = getEntrance();
    }

    public int[] getEntrance(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[0] == ' ') return new int[]{0, i};
        }
        return null;
    }

    public int[] getExit(){
        for(int i = 0; i < mazeHeight; i++){
            if(mazeObject.get(i)[mazeWidth-1] == ' ') return new int[]{mazeWidth-1, i};
        }
        return null;
    }

    public int[] getLocation(){
        return location;
    }

    public void setLocation(int[] coordinate){
        location = coordinate;
    }

    public void moveNavigatorForward(){
        char facing = navigator.getFacing();
        // System.out.println("Currently facing: " + facing);
        // System.out.println("Current Location: " + location[0] + "," + location[1] + "!");
        // System.out.println("North: " + mazeObject.get(location[0])[location[1]-1] + "!");
        // System.out.println("East: " + mazeObject.get(location[0]+1)[location[1]] + "!");
        // System.out.println("South: " + mazeObject.get(location[0])[location[1]+1] + "!");
        // System.out.println("West: " + mazeObject.get(location[0]-1)[location[1]] + "!");

        if(facing == 'N' && location[1] != 0 && mazeObject.get(location[1]-1)[location[0]] == ' ') location[1] -= 1;
        else if(facing == 'E' && location[0] != mazeWidth-1 && mazeObject.get(location[1])[location[0]+1] == ' ') location[0] += 1;
        else if(facing == 'S' && location[1] != mazeHeight-1 && mazeObject.get(location[1]+1)[location[0]] == ' ') location[1] += 1;
        else if(facing == 'W' && location[0] != 0 && mazeObject.get(location[1])[location[0]-1] == ' ') location[0] -= 1;
        else System.out.println("Nowhere to move to");
    }

    public void printMaze(){
        char facing = navigator.getFacing();
        String line = "";
        for (int y = 0; y < mazeObject.size(); y++) {
            for (int x = 0; x < mazeObject.get(0).length; x++) {
                if(location[0] == x && location[1] == y){
                    if(facing == 'N') line += '^';
                    else if(facing == 'E') line += '>';
                    else if(facing == 'S') line += 'v';
                    else line += '<';
                } else {
                    line += mazeObject.get(y)[x];
                }
            }
            System.out.println(line);
            line = "";
        }
        System.out.println();
    }

    public Navigator getNavigator(){
        return navigator;
    }
}
