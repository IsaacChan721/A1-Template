package ca.mcmaster.se2aa4.mazerunner;

public class Navigator {
    private final char[] directions = {'N', 'E', 'S', 'W'};
    private char facing;

    public Navigator(char facing){
        this.facing = facing;
    }

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

    public char getFacing(){
        return facing;
    }
}
