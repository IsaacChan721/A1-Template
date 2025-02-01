package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class Main {

    // Logger instance for logging messages
    private static final Logger logger = LogManager.getLogger();

    /*
     * This is the main functionality of the program that takes in a maze file from the user and optionally a path.
     * If the path is provided, the program will return whether the path is deemed successful or failed based on whether
     * the navgator has reached the exit. If the program does not recieve a path, it will instead return a path that is
     * deemed valid to complete the maze.
     */
    
    public static void main(String[] args) {
        // Set the logging level for this class to INFO
        Configurator.setLevel("ca.mcmaster.se2aa4.mazerunner.Main", org.apache.logging.log4j.Level.INFO);

        // Read command-line arguments using FlagReader
        FlagReader flagReader = new FlagReader(args);
        String path = flagReader.getPath();  // Path for pre-defined instructions (if provided)
        String file = flagReader.getFile();  // Maze file name

        // Initialize necessary components
        Navigator navigator = new Navigator(Directions.EAST); // Start navigating facing EAST
        Maze maze = new Maze(file, navigator); // Create Maze instance with the provided file
        Instructions instructions = new Instructions(path, maze); // Initialize instructions handler
        Algorithm algo = new Algorithm(maze); // Initialize algorithm for pathfinding

        if (path == null) { // If no pre-defined path is provided, compute a path using an algorithm
            path = algo.rightHandPath(); // Use right-hand rule algorithm to find a path
            instructions.readInstructions(path); // Parse and store the computed path
            System.out.println("Canonical path: " + path);
            System.out.println("Factorial Path: " + instructions.getFactorial());
        } else { // If a path is provided, validate and execute it
            instructions.readInstructions(path); // Parse instructions
            instructions.excecuteInstruction(); // Execute the instructions

            // Check if the final position matches the maze exit
            if (maze.getLocation()[0] == maze.getExit()[0] && maze.getLocation()[1] == maze.getExit()[1]) {
                System.out.println("Factorial Path: " + instructions.getFactorial());
                System.out.println("Path is successful!"); // The provided path successfully reaches the exit
            } else {
                System.out.println("Path has failed!"); // The provided path does not lead to the exit
            }
        }
    }
}
