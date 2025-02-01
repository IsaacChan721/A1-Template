package ca.mcmaster.se2aa4.mazerunner;

public class FlagReader{
    String file;
    String path;

    // flag reader constructor
    public FlagReader(String[] args){
        //checks for flags and their corresponding file or path
        for(int i = 0; i < args.length - 1; i++){
            if(args[i].equals("-i") && args[i+1] != null){
                file = args[i+1];
            } else if (args[i].equals("-p") && args[i+1] != null){
                path = args[i+1];
            }
        }
    }

    // returns the file
    public String getFile(){
        return file;
    }

    // returns the path
    public String getPath(){
        return path;
    }
}