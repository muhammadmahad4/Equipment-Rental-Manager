import java.util.ArrayList;

public class Member implements Comparable<Member>, Cloneable {
    
    private String id;
    private String name;
    private Day joinDate;
    private int Borrowed=0; //stores number of equipments borrowed
    private int Requested=0; //stores number of equipments requested


    //constructor
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
    }

    //clone method used in cmdborrow and cmdrequest
    @Override
	public Member clone(){ 
		Member copy=null;
		try{
			copy = (Member) super.clone();
			copy.id = this.getID();
			copy.name = this.getName();
			copy.joinDate = this.joinDate;
            copy.Borrowed = this.Borrowed;
            copy.Requested = this.Requested;
		}
		catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
		return copy;
	}


    public void Borrow(){Borrowed+=1;} //increments the number of borrowed items
    public void UnBorrow(){Borrowed -= 1;}//decrements 
    public void Request(){Requested++;}
    public void UnRequest(){Requested--;}
    
    public static void list(ArrayList<Member> allMembers) {
        System.out.printf("%-5s%-9s%11s%11s%13s\n", "ID", "Name", "Join Date ", "#Borrowed", "#Requested");//Learn: "-" means left-aligned
        for (Member m: allMembers) {
        System.out.printf("%-5s%-9s%11s%7d%13d\n", m.id, m.name, m.joinDate.toString(), m.Borrowed, m.Requested);
        }
    }
    
    //getter functions
    public String getID(){return id;}
    public String getName(){return name;}

    //campareto used for comparing while listing
    @Override
    public int compareTo(Member another) {
        return this.id.compareTo(another.id);
    }
}
