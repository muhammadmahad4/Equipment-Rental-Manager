public class CmdRegister extends RecordedCommand{
    //We add instance fields in the objects to store the data which will be needed upon undo/redo
	private Member member;

	@Override
	public void execute(String[] cmdParts) throws ExMemberIDinUse,ExInsufficientCommands
	{
		if(cmdParts.length != 3){
			throw new ExInsufficientCommands();
		}
		Club club = Club.getInstance();
		if (club.findMember(cmdParts[1]) == null){
			member = new Member(cmdParts[1],cmdParts[2]);
        	Club.getInstance().addMember(member);
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
        	System.out.println("Done.");
		}
		else{
			Member existingMember = club.findMember(cmdParts[1]);
			throw new ExMemberIDinUse(existingMember.getID(), existingMember.getName());
		}

	}
	
	@Override
	public void undoMe()
	{
        Club.getInstance().removeMember(member);
		addRedoCommand(this); //<====== upon undo, we should keep a copy in the redo list (addRedoCommand is implemented in RecordedCommand.java)
	}
	
	@Override
	public void redoMe()
	{
        Club.getInstance().addMember(member);
		addUndoCommand(this); //<====== upon redo, we should keep a copy in the undo list
	}
}