public class ExMemberAlreadyBorrowedThisEquipment extends Exception{

    public ExMemberAlreadyBorrowedThisEquipment(){
        super("The member is currently borrowing a set of this equipment. He/she cannot borrow one more at the same time.");
    }

    
    public ExMemberAlreadyBorrowedThisEquipment(String message){
        super(message);
    }
}
