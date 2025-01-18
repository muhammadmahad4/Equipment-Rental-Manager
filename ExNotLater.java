public class ExNotLater extends Exception{
    public ExNotLater(){
        super("Invalid new day.  The new day has to be later than the current date 6-Jan-2024.");
    }
    public ExNotLater(Day old){
        super("Invalid new day.  The new day has to be later than the current date " + old.toString() + ".");
    }
}
