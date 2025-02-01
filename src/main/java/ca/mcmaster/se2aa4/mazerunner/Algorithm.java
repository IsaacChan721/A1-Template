package ca.mcmaster.se2aa4.mazerunner;

public class Algorithm {
    private Maze maze;

    // Algorithm Constructor
    public Algorithm (Maze maze){
        this.maze = maze;
    }

    /* 
    * An algorithm that follows the right wall of the maze until the navigator reaches the exit
    * This returns the string that the navigator follows based on the navigation it finds
    */
    public String rightHandPath(){
        String path = "";
        Navigator navigator = maze.getNavigator();

        // while the location of the navigaotr does not equal the location of the exit
        while(maze.getLocation()[0] != maze.getExit()[0] || maze.getLocation()[1] != maze.getExit()[1]){
            navigator.turnRight();
            // if the right hand does not see a wall
            if(maze.moveNavigatorForward()){
                path += "RF";
            } else {
                navigator.turnLeft();
                // if forward is free to move to
                if(maze.moveNavigatorForward()){
                    path += "F";
                } else {
                    navigator.turnLeft();
                    // if left is free to move to
                    if(maze.moveNavigatorForward()){
                        path += "LF";
                    // if there is nowhere to move to but backwards
                    } else {
                        navigator.turnLeft();
                        maze.moveNavigatorForward();
                        path += "LLF";
                    }
                }
            }
            //maze.printMaze();
        }
        return path;
    }
}
