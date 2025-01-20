package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.setLevel("ca.mcmaster.se2aa4.mazerunner.Main", org.apache.logging.log4j.Level.INFO);
        logger.info("** Starting Maze Runner\n");

        // Setup
        FlagReader flagReader = new FlagReader(args);
        Navigator navigator = new Navigator('E');
        Maze maze = new Maze(flagReader.getFile(), navigator);
        Instructions instructions = new Instructions(flagReader.getPath(), maze);

        System.out.println(instructions.instructionToFactorial());

        // if(flagReader.getPath() != null){
        //     instructions.excecuteInstruction();
        //     if(maze.getLocation()[0] == maze.getExit()[0] && maze.getLocation()[1] == maze.getExit()[1]) System.out.println("SUCCESS");
        //     else System.out.println("FAIL");
        // } else {
        //     //goober dash
        // }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}


// to do:
// test the canonical and factored form, for both input and output
// test other files
