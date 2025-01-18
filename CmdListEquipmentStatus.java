public class CmdListEquipmentStatus extends RecordedCommand{
    //We add instance fields in the objects to store the data which will be needed upon undo/redo
	
	@Override
	public void execute(String[] cmdParts)
	{
        Club.getInstance().ListEquipmentStatus();
        //addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		//clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.

	}
	
	@Override
	public void undoMe()
	{
		
	}
	
	@Override
	public void redoMe()
	{
		
	}
}
