public class ExNoOfDaysMustBeInteger extends Exception{
    public ExNoOfDaysMustBeInteger(){
        super("Please provide an integer for the number of days.");
    }
    
    public ExNoOfDaysMustBeInteger(String message){
        super(message);
    }
}
