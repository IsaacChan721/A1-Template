package ca.mcmaster.se2aa4.mazerunner;

public class Navigator {
    private final char[] directions = {'N', 'E', 'S', 'W'};
    private char facing = 'E';

    public void turnLeft(){
        for(int i=0; i<directions.length; i++){
            if(directions[i] == facing){
                facing = directions[(i-1+directions.length)%directions.length];
                return;
            }
        }
    }

    public void turnRight(){
        for(int i=0; i<directions.length; i++){
            if(directions[i] == facing){
                facing = directions[(i+1)%directions.length];
                return;
            }
        }
    }

    public boolean checkForward(char[] paths){
        if(facing == 'N') return paths[0] == ' ';
        else if (facing == 'E') return paths[1] == ' ';
        else if (facing == 'S') return paths[2] == ' ';
        else return paths[3] == ' ';
    }
}