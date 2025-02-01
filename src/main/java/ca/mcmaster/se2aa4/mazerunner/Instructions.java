package ca.mcmaster.se2aa4.mazerunner;

public class Instructions {
    private String instructions;
    private Maze maze;

    // Instructions contructor 
    public Instructions(String instructions, Maze maze){
        this.instructions = instructions;
        this.maze = maze;
    }

    // A method that communicates with the maze to move the navigator based on the instructions
    public void excecuteInstruction(){
        //maze.printMaze();
        // iterates through the instructions and executing the commands
        for(int i = 0; i < instructions.length(); i++){
            if(instructions.charAt(i) == 'F') maze.moveNavigatorForward();
            else if(instructions.charAt(i) == 'R') maze.getNavigator().turnRight();
            else if(instructions.charAt(i) == 'L') maze.getNavigator().turnLeft();
            //maze.printMaze();
        }
    }

    // A method used to convert any set of instructions into canonical form and sets the instructions to it
    public void readInstructions(String path){
        int multiplier = 1;
        String newInstructions = "";
        boolean prevNumber = false;
        
        // iterates through the path
        for(int i = 0; i < path.length(); i++){
            char instruction = path.charAt(i);
            // checks if the read instruction is a digit
            if(Character.isDigit(instruction)){
                if(prevNumber){ // if the previous instruction character was already a number, add onto the multiplier
                    multiplier = multiplier * 10 + Character.getNumericValue(instruction);
                } else { // change the multiplier to the digit value
                    multiplier = Character.getNumericValue(instruction);
                    prevNumber = true;
                }
            // If it matches any of the character inputs then do the corresponding navigator movements
            } else {
                if (instruction == 'F' || instruction == 'R' || instruction == 'L') { // moving forward, turning right, or turning left
                    for(int j = 0; j < multiplier; j++){
                        newInstructions += instruction;
                    }
                }
                multiplier = 1;
                prevNumber = false;
            }
        }

        //reassigns instructions into canonical form
        this.instructions = newInstructions;
    }

    // converts the canonical to factorial form then returns it
    public String getFactorial(){
        String factorial = "";
        int counter = 1;
        char currChar = instructions.charAt(0);

        // iterates through the instructions, checking for digits or letters
        for(int i = 1; i < instructions.length(); i++){
            char newChar = instructions.charAt(i);
            // if the same letter occurs multiple times
            if(currChar == newChar){
                counter++;
                // accounts for the last character
                if(i == instructions.length() - 1) factorial += counter + "" + currChar;
            // if there is only one instruction of the same letter
            } else if (counter == 1){
                factorial += currChar + " ";
                currChar = newChar;
                // accounts for the last character
                if(i == instructions.length() - 1) factorial += newChar;
            } else {    
                factorial += counter + "" + currChar + " ";
                currChar = newChar;
                counter = 1;
            }
        }
        return factorial;
    }

    // a method that returns the canonical instructions/path
    public String getCanonical(){
        return instructions;
    }
}