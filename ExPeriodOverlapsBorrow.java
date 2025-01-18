public class ExPeriodOverlapsBorrow  extends Exception{
    public ExPeriodOverlapsBorrow(){
        super("The period overlaps with a current period that the member requests the equipment.");
    }
    
    public ExPeriodOverlapsBorrow(String message){
        super(message);
    }
}
