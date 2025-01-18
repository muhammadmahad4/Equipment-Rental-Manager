import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

	public static void main(String [] args) throws FileNotFoundException, ExUnknownCommand, ExInvalidFormat {	
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = null;
		try{
			inFile = new Scanner(new File(filepathname));
			
			
			String cmdLine1 = inFile.nextLine();
			String[] cmdLine1Parts = cmdLine1.split(" ");
			System.out.println("\n> "+cmdLine1); 
			SystemDate.createTheInstance(cmdLine1Parts[1]);
			
			while (inFile.hasNext()) {
				String cmdLine = inFile.nextLine().trim();
				
				if (cmdLine.equals("")) continue;  

				System.out.println("\n> "+cmdLine);
				
				
				String[] cmdParts = cmdLine.split(" "); 

				
				try{
					if (cmdParts[0].equals("register"))
						{(new CmdRegister()).execute(cmdParts);}
					
					else if (cmdParts[0].equals("listMembers"))
						{(new CmdListMembers()).execute(cmdParts);}

					else if (cmdParts[0].equals("startNewDay"))
						{(new CmdStartNewDay()).execute(cmdParts);}

					else if (cmdParts[0].equals("create"))
						{(new CmdCreate()).execute(cmdParts);}
					
					else if (cmdParts[0].equals("arrive"))
						{(new CmdArrive()).execute(cmdParts);}

					else if (cmdParts[0].equals("listEquipment"))
						{(new CmdListEquipment()).execute(cmdParts);}

					else if (cmdParts[0].equals("borrow"))
						{(new CmdBorrow()).execute(cmdParts);}

					else if (cmdParts[0].equals("request"))
						{(new CmdRequest()).execute(cmdParts);}

					else if (cmdParts[0].equals("listMemberStatus"))
						{(new CmdListMemberStatus()).execute(cmdParts);}

					else if (cmdParts[0].equals("listEquipmentStatus"))
						{(new CmdListEquipmentStatus()).execute(cmdParts);}

					else if (cmdParts[0].equals("undo")){
						RecordedCommand.undoOneCommand();
					}
					else if (cmdParts[0].equals("redo")){
						RecordedCommand.redoOneCommand();
					}
					//if not command matches then you exit the program
					else{
						throw new ExUnknownCommand();
					}
				}

				//all the catch statements, catching all the exceptions.
				catch (ExUnknownCommand u) {
					System.out.println(u.getMessage());
					break; // Exit the loop directly
				}
				catch(ExMemberIDinUse m){
					System.out.println(m.getMessage());
				}	
				catch (ExInsufficientCommands i){
					System.out.println(i.getMessage());
				}
				
				catch(ExInvalidFormat ii){
					System.out.println(ii.getMessage());
				}
				catch (ExNotLater n){
					System.out.println(n.getMessage());
				}
				catch (ExMemberNotFound m){
					System.out.println(m.getMessage());
				}catch(ExEquipmentNotFound e){
					System.out.println(e.getMessage());
				}
				catch(ExEquipmentCodeAlreadyInUse e){
					System.out.println(e.getMessage());
				}
				catch(ExEquipmentCodeNotFound e){
					System.out.println(e.getMessage());
				}
				catch(ExNoEquipmentSetAvailable e){
					System.out.println(e.getMessage());
				}
				catch(ExMemberAlreadyBorrowedThisEquipment m){
					System.out.println(m.getMessage());
				}
				catch(ExPeriodOverLapRequest p){
					System.out.println(p.getMessage());
				}
				catch(ExPeriodOverlapsBorrow p){
					System.out.println(p.getMessage());
				}
				catch(ExPeriodIsLessThan p){
					System.out.println(p.getMessage());
				}
				catch(ExNoOfDaysMustBeInteger n){
					System.out.println(n.getMessage());
				}
			}
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		finally{
			if(inFile !=null){
				inFile.close();
				in.close();
			}
		}	
	}
}
