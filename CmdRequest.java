public class CmdRequest extends RecordedCommand{

    private Member member;
    private Equipment equipment;
    Day StartDate;
    Day EndDate;
    String MemberID;
    String EquipmentCode;
    String GivenEquipmentSet; 
    int NoOfDays;

	@Override
	public void execute(String[] cmdParts) throws ExInvalidFormat, ExInsufficientCommands, ExInvalidFormat,ExMemberNotFound,ExEquipmentNotFound, ExPeriodOverLapRequest,ExNoEquipmentSetAvailable,ExPeriodIsLessThan,ExNoOfDaysMustBeInteger
	{
        if(cmdParts.length != 5){
            throw new ExInsufficientCommands();
        }
        else{
                MemberID = cmdParts[1];
                EquipmentCode = cmdParts[2];
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
                
                    StartDate = new Day(cmdParts[3]);

                    try{
                        NoOfDays = Integer.parseInt(cmdParts[4]);
                        if(NoOfDays < 1){
                            throw new ExPeriodIsLessThan();
                        }
                    }catch(NumberFormatException e){
                        throw new ExNoOfDaysMustBeInteger();
                    }

                    EndDate = StartDate.IncrementDay(NoOfDays);

                    GivenEquipmentSet = equipment.RequestEquipmentSet(member.clone(), StartDate.clone(), EndDate.clone());
                    member.Request();
                    

                    System.out.printf("%s %s requests %s (%s) for %s to %s\n",
                                        MemberID,member.getName(),GivenEquipmentSet,equipment.getName(),StartDate.toString(), EndDate.toString()
                                    );
                    addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
                    clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
                    System.out.println("Done.");

                
                }
            }



	}
	


	@Override
	public void undoMe()
	{
        member.UnRequest(); //DECREMENTS NUMBER OF BORROWED BY MEMBER
        equipment.UnRequestEquipmentSet(member.clone(),GivenEquipmentSet, StartDate, EndDate);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
    
    }
	
	@Override
	public void redoMe() throws ExNoEquipmentSetAvailable,ExPeriodOverLapRequest,ExInvalidFormat
	{

        equipment.RequestEquipmentSet(member.clone(), StartDate.clone(), EndDate.clone());
        member.Request();
        addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
       
	}
}