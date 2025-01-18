import java.util.*;


public class EquipmentSet implements Comparable<EquipmentSet>{

    //==================================SET DETAILS=======================================================//
    private String label;
    private ArrayList<Request> reservations = new ArrayList<>();
    public ArrayList<Request> getReservations(){return reservations;}

    public EquipmentSet(String l,Boolean a, Boolean b){
        label = l;
        available = a;
        Borrowed = b;
    }

    @Override
    public int compareTo(EquipmentSet another){
        return this.label.compareTo(another.getLabel());

    }
    public String getLabel(){return label;}
    
    //====================================BORROW DETAILS====================================================//
    private Boolean available;
    private Boolean Borrowed;
    private String Borrowedby;
    private Day BorrowStartDate;
    private Day BorrowEndDate;
    
  
    
    public void setBorrow(Day StartDay, Day EndDay, String name){
        Borrowed = true; 
        BorrowStartDate = StartDay;
        BorrowEndDate=EndDay;
        available = false;
        Borrowedby = name;
    }
   
    public String getBorrowedby(){return Borrowedby;}
    public Day getStartDate(){return BorrowStartDate;}
    public Day getEndDate(){return BorrowEndDate;}
    public Boolean isBorrowed(){return Borrowed;}
    public Boolean isAvailable(){return available;}


    public void SetUnBorrow(){
        available = true;
        Borrowed = false;
        Borrowedby=null;
        BorrowEndDate = null;
        BorrowStartDate = null;
    }
    //==================================================================================================================//


    //============================================REQUESTS========================================================//

    public void SetReservation(String Label, Member m, Day Start, Day End){
        reservations.add(new Request(Label, m, Start, End));
    }

    public void SetUnRequest(String EquipmentLabel, String id, Day StartDayy, Day EndDayy){
        for(int i=0; i<reservations.size();i++){
            if(reservations.get(i).getLabel().equals(EquipmentLabel) && reservations.get(i).getStartDay().toString().equals(StartDayy.toString()) && reservations.get(i).getEndDay().toString().equals(EndDayy.toString())){
               reservations.remove(i);
            }
        }
    }
}
