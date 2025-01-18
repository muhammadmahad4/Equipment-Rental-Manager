public class CmdStartNewDay extends RecordedCommand{

	//=========================================INSTANCE FIELDS=======================================//
    private String oldDayString;
    private String newDayString;
	private Day NewDay;
	private Day oldDay;
	
	@Override
	public void execute(String[] cmdParts) throws ExInvalidFormat, ExNotLater,ExInsufficientCommands
	{
		

		if(cmdParts.length != 2){
			throw new ExInsufficientCommands();
		}

        newDayString = cmdParts[1];
		NewDay = new Day(newDayString);
        oldDayString = SystemDate.getInstance().toString();
		oldDay = SystemDate.getInstance();

		
		if(NewDay.valid() == false){
			throw new ExInvalidFormat();
		}
		else if(NewDay.compareTo(oldDay) <= 0){
			throw new ExNotLater(oldDay);
		}
		else{
			
			SystemDate.getInstance().set(newDayString);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			System.out.println("Done.");
			
		
			
		}

	}
	
	@Override
	public void undoMe()
	{
		try{
        	SystemDate.getInstance().set(oldDayString);
			addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
		}catch(ExInvalidFormat i){
			i.getMessage();
		}
	}
	
	@Override
	public void redoMe() throws ExInvalidFormat
	{

		SystemDate.getInstance().set(NewDay.toString());
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
		
	}
}
