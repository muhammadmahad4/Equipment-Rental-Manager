public class CmdCreate extends RecordedCommand{
	
	private Equipment equipment;

	@Override
	public void execute(String[] cmdParts) throws ExEquipmentCodeAlreadyInUse,ExInsufficientCommands
	{
		if(cmdParts.length != 3){
			throw new ExInsufficientCommands();
		}
		Club club = Club.getInstance();
		if(club.findEquipment(cmdParts[1]) == null){
			equipment = new Equipment(cmdParts[1],cmdParts[2]);
			Club.getInstance().addEquipment(equipment);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
       	 	System.out.println("Done.");
		}
		else{
			Equipment existingEquipment = club.findEquipment(cmdParts[1]);
			throw new ExEquipmentCodeAlreadyInUse(existingEquipment.getEquipmentCode(),existingEquipment.getName());
		}

	}
	
	@Override
	public void undoMe()
	{
        Club.getInstance().removeEquipment(equipment);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
        Club.getInstance().addEquipment(equipment);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}
