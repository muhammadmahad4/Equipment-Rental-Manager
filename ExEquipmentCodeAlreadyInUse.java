public class ExEquipmentCodeAlreadyInUse extends Exception{

    public ExEquipmentCodeAlreadyInUse(){
        super("Equipment code already in use");
    }
    public ExEquipmentCodeAlreadyInUse(String EqCode, String MemName){
        super("Equipment code already in use: " +EqCode + " "+ MemName);
    }
    
}
