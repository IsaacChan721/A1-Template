package ca.mcmaster.se2aa4.mazerunner;

// This class allows the user to navigate throguh the maze using the provided instructions
public class Navigator {
    private final Directions[] directions = Directions.values();
    private Directions facing;

    // Navigator constructor
    public Navigator(Directions facing){
        this.facing = facing;
    }

    // method that turns the navigator left
    public void turnLeft(){
        for(int i=0; i<Directions.values().length; i++){
            if(directions[i] == facing){
                facing = directions[(i-1+directions.length)%directions.length];
                return;
            }
        }
    }

    // method that turns the navigator right
    public void turnRight(){
        for(int i=0; i<directions.length; i++){
            if(directions[i] == facing){
                facing = directions[(i+1)%directions.length];
                return;
            }
        }
    }

    // method that returns which direction the navigator is facing
    public Directions getFacing(){
        return facing;
    }
}
