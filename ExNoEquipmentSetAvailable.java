public class ExNoEquipmentSetAvailable extends Exception{
    
    public ExNoEquipmentSetAvailable(){
        super("There is no available set of this equipment for the command.");
    }
    
    public ExNoEquipmentSetAvailable(String message){
        super(message);
    }
}
