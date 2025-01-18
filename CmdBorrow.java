public class CmdBorrow extends RecordedCommand{

    //====================================INSTANCE FIELDS=================================================//
	private Member member;
    private Equipment equipment;
    Day StartDate;
    Day EndDate;
    String MemberID;
    String EquipmentCode;
    String EquipmentSetGiven; 
    int NoOfDays;

    //===============================================================================================//

    //main execute function
	@Override
	public void execute(String[] cmdParts) throws ExNoOfDaysMustBeInteger,ExPeriodIsLessThan,ExPeriodOverlapsBorrow,ExInsufficientCommands, ExMemberNotFound,ExEquipmentNotFound,ExNoEquipmentSetAvailable, ExMemberAlreadyBorrowedThisEquipment, ExInvalidFormat
	{
        //===========================CHECKING EXCEPTIONS=============================================//
        if(cmdParts.length < 3){
            throw new ExInsufficientCommands();
        }

        if(cmdParts.length == 3 || cmdParts.length == 4){
            
            MemberID = cmdParts[1]; //member which borrows
            EquipmentCode = cmdParts[2];//equiment borrowed

            Club club = Club.getInstance();
            member = club.findMember(MemberID);
            equipment = club.findEquipment(EquipmentCode);

            if(member == null){
                throw new ExMemberNotFound();
            }
            else if(equipment == null){
                throw new ExEquipmentNotFound();
            }
            else{
                if(cmdParts.length == 3){
                    EndDate = SystemDate.getInstance().IncrementDay(7);
                }
                else{
                    try{
                        NoOfDays = Integer.parseInt(cmdParts[3]);
                        if(NoOfDays < 1){
                            throw new ExPeriodIsLessThan();
                        }
                    }catch(NumberFormatException e){
                        throw new ExNoOfDaysMustBeInteger();
                    }
                    EndDate = SystemDate.getInstance().IncrementDay(NoOfDays);
                }

            
                StartDate = SystemDate.getInstance();
                EquipmentSetGiven = equipment.BorrowEquipmentSet(member.clone(), StartDate.clone(), EndDate.clone());
                System.out.printf("%s %s borrows %s (%s) for %s to %s\n", MemberID,member.getName(),EquipmentSetGiven,equipment.getName(),StartDate.toString(), EndDate.toString());
                member.Borrow(); //INCREMENTS THE NUMBER OF BORROWED BY MEMBER
                addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
                clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
                System.out.println("Done.");
                
            }
        }

	}
	
	@Override
	public void undoMe()
	{
        member.UnBorrow(); //DECREMENTS NUMBER OF BORROWED BY MEMBER
        equipment.UnBorrowEquipmentSet(EquipmentSetGiven);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe() throws ExNoEquipmentSetAvailable,ExMemberAlreadyBorrowedThisEquipment,ExInvalidFormat,ExPeriodOverlapsBorrow
	{

        equipment.BorrowEquipmentSet(member.clone(), new Day(StartDate.toString()), new Day(EndDate.toString()));
        addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
        member.Borrow();
     
	}
}