public class ExEquipmentCodeNotFound extends Exception {

    public ExEquipmentCodeNotFound(){
        super("Missing record for Equipment E9.  Cannot mark this item arrival.");
    }
    public ExEquipmentCodeNotFound(String EqCode){
        super("Missing record for Equipment "+ EqCode+ ".  Cannot mark this item arrival.");
    }
    
}
