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

    public Maze(String file){
        String line;
        int lineNum = 0;
        navigator = new Navigator();
        mazeObject = new ArrayList();
        
        //Creating a file reader
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            logger.error(e);
        }

        try {
            char[] lineChars;
            // creates the maze object from the file
            while ((line = reader.readLine()) != null) {
                lineChars = new char[line.length()];
                for (int idx=0; idx<line.length(); idx++) {
                    lineChars[idx] = line.charAt(idx);
                }
                mazeObject.add(lineChars);
                lineNum++;
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        //Searches for the entrance and sets the location there
        for(int i = 0; i<lineNum; i++){
            if(mazeObject.get(i)[0] == ' ') location = new int[]{i, 0};
        }
    }

    public int[] getLocation(){
        return location;
    }

    public void setLocation(int[] coordinate){
        location = coordinate;
    }

    public void moveNavigatorForward(char facing){
        if(facing == 'N' && mazeObject.get(location[0])[location[1]+1] == ' ') location[1] += 1;
        else if(facing == 'E' && mazeObject.get(location[0]+1)[location[1]] == ' ') location[0] += 1;
        else if(facing == 'S' && mazeObject.get(location[0])[location[1]-1] == ' ') location[1] -= 1;
        else if(facing == 'W' && mazeObject.get(location[0]-1)[location[1]] == ' ') location[0] -= 1;
        else System.out.println("No where to move to");
    }

    public void printMaze(){
        String line = "";
        for (char[] mazeObject1 : mazeObject) {
            for (int j = 0; j < mazeObject1.length; j++) {
                line += mazeObject1[j];
            }
            System.out.println(line);
            line = "";
        }
    }
}
