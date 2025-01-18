public class ExPeriodIsLessThan extends Exception {
    public ExPeriodIsLessThan(){
        super("The number of days must be at least 1.");
    }
    
    public ExPeriodIsLessThan(String message){
        super(message);
    }
}
