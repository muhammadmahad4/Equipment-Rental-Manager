public class ExInsufficientCommands extends Exception {
    public ExInsufficientCommands(){
        super("Insufficient command arguments.");
    }
    
    public ExInsufficientCommands(String message){
        super(message);
    }
}
