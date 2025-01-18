//functionality:
//1) you simply find the equipment in "allEquipments" array in club using "findEquipment" method
//2) then you apply "addEquipmentSet" function of "Equipment" class on the found Equipment

public class CmdArrive extends RecordedCommand{
    
    //instance fields
	private Equipment equipment;
 
	
	@Override
	public void execute(String[] cmdParts) throws ExEquipmentCodeNotFound, ExInsufficientCommands
	{
		
		Club club = Club.getInstance();

		//checking all the exceptions
		//=======================================================================================================//
		if(cmdParts.length != 2){
			throw new ExInsufficientCommands();
		}
		equipment = club.findEquipment(cmdParts[1]);
		if (equipment==null){
			throw new ExEquipmentCodeNotFound(cmdParts[1]);
		}

		//run if no exception thrown
		
		//=======================================================================================================//
		else{
			equipment.AddEquipmentSet();
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
		}
		
	}
	
	@Override
	public void undoMe()
	{
		equipment.RemoveEquipmentSet();
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
		equipment.AddEquipmentSet();
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
