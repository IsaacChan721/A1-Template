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

    public String readInstructions(){
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
        return instructions;
    }

    public String instructionToFactorial(){
        String factorial = "";
        int counter = 1;
        char currChar = instructions.charAt(0);
        for(int i = 1; i < instructions.length(); i++){
            char newChar = instructions.charAt(i);
            if(currChar == newChar){
                counter++;
                if(i == instructions.length() - 1) factorial += counter + "" + currChar;
            } else if (counter == 1){
                factorial += currChar + " ";
                currChar = newChar;
                if(i == instructions.length() - 1) factorial += newChar;
            } else {    
                factorial += counter + "" + currChar + " ";
                currChar = newChar;
                counter = 1;
            }
        }
        return factorial;
    }
}