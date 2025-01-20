package ca.mcmaster.se2aa4.mazerunner;

public class Instructions {
    private String instructions;
    private Maze maze;

    public Instructions(String instructions, Maze maze){
        this.instructions = instructions;
        this.maze = maze;
    }

    public void excecuteInstruction(){
        for(int i = 0; i < instructions.length(); i++){
            if(instructions.charAt(i) == 'F') maze.moveNavigatorForward();
            else if(instructions.charAt(i) == 'R') maze.getNavigator().turnRight();
            else if(instructions.charAt(i) == 'L') maze.getNavigator().turnLeft();
            maze.printMaze();
        }
    }

    public void readInstructions(){
        int multiplier = 1;
        String newInstructions = "";
        
        for(int i = 0; i < instructions.length(); i++){
            char instruction = instructions.charAt(i);
            if(Character.isDigit(instruction)){
                multiplier = Character.getNumericValue(instruction);
            } else if (instruction == 'F') {
                for(int j = 0; j < multiplier; j++){
                    newInstructions += instruction;
                }
                multiplier = 1;
            } else if (instruction == 'R') {
                for(int j = 0; j < multiplier; j++){
                    newInstructions += instruction;
                }
                multiplier = 1;
            } else if (instruction == 'L') {
                for(int j = 0; j < multiplier; j++){
                    newInstructions += instruction;
                }
                multiplier = 1;
            }
        }

        instructions = newInstructions;
    }


}