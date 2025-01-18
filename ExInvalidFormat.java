public class ExInvalidFormat extends Exception{
    public ExInvalidFormat(){
        super("Invalid date.");
    }
    
    public ExInvalidFormat(String message){
        super(message);
    }
}
