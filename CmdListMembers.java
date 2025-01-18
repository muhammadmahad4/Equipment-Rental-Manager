public class CmdListMembers extends RecordedCommand{
    //We add instance fields in the objects to store the data which will be needed upon undo/redo
	
	@Override
	public void execute(String[] cmdParts)
	{
        Club.getInstance().listClubMembers();
        
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
