public class ExPeriodOverLapRequest extends Exception {
    public ExPeriodOverLapRequest(){
        super("The period overlaps with a current period that the member borrows / requests the equipment.");
    }
    
    public ExPeriodOverLapRequest(String message){
        super(message);
    }
}
