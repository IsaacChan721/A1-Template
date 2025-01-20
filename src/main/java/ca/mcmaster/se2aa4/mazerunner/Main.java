package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.setLevel("ca.mcmaster.se2aa4.mazerunner.Main", org.apache.logging.log4j.Level.INFO);
        logger.info("** Starting Maze Runner\n");

        // Checking for files using the -i flag
        String file = "";
        for(int i = 0; i < args.length - 1; i++){
            if(args[i].equals("-i")){
                file = args[i+1];
            }
        }

        // Setup
        Navigator navigator = new Navigator('E');
        Maze maze = new Maze(file, navigator);
        Instructions instructions = new Instructions("FLFRFFLFFFFFFRFFFFRFFLFFRFFLF", maze);

        instructions.excecuteInstruction();

        // try {
        //     System.out.println("**** Reading the maze from file " + file);
        //     BufferedReader reader = new BufferedReader(new FileReader(file));
        //     String line;
        //     while ((line = reader.readLine()) != null) {
        //         for (int idx = 0; idx < line.length(); idx++) {
        //             if (line.charAt(idx) == '#') {
        //                 System.out.print("WALL ");
        //             } else if (line.charAt(idx) == ' ') {
        //                 System.out.print("PASS ");
        //             }
        //         }
        //         System.out.print(System.lineSeparator());
        //     }
        // } catch(Exception e) {
        //     logger.error("/!\\ An error has occured /!\\");
        // }

        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
