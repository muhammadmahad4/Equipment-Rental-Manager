import java.util.*;

public class Equipment implements Comparable<Equipment>{

    //==================================INSTANCE FIELDS====================================================//

    private String name; //stores the name of equipment
    private ArrayList<EquipmentSet> Equipmentset = new ArrayList<>(); //stores all the equipmentsets of each equipment
    private String EquipmentCode; //stores the equipment code

    //constructor
    public Equipment(String c, String n){
        EquipmentCode = c;
        name = n;
    }

    @Override
    public int compareTo(Equipment another){
        return this.EquipmentCode.compareTo(another.getEquipmentCode());

    }

    //======================================getter functions=================================================//
    public String getName(){return name;}
    public String getEquipmentCode(){return EquipmentCode;}
    public ArrayList<EquipmentSet> getequipmentset(){return Equipmentset;}


    //========================ARRIVAL=========================================================//

    //you use this method in cmdarrive. it is used to add set in the Equipmentset Array. 
    //it instantiates a new Equipmentset object with the correct label and set it to available
    public void AddEquipmentSet(){
        int idx = Equipmentset.size()+1;
        String label = this.EquipmentCode + "_" +idx;
        Equipmentset.add(new EquipmentSet(label,true,false));
    }

    //you use for the undo of arrival of an equipmentset 
    public void RemoveEquipmentSet(){
        int lastidx = Equipmentset.size()-1;
        Equipmentset.remove(lastidx);
    }
    //==============================================================================================//

    //============================BORROW===========================================================//

    //Basically this functions helps member borrow and equipment set but first checking if it is avalibale
    //and then gives that equipment set and changes the equipment set values;
    //RETURNS THE EQUIPMENT SET GIVEN TO THE MEMBER

    public String BorrowEquipmentSet (Member m, Day StartDate, Day EndDate) throws ExNoEquipmentSetAvailable, ExMemberAlreadyBorrowedThisEquipment,ExPeriodOverlapsBorrow {

        //checks if member already borrowed equipment set of same equipment
        for(EquipmentSet e: Equipmentset){

            if(e.getBorrowedby() == m.getID() && Day.doRangesOverlap(StartDate, EndDate, e.getStartDate(), e.getEndDate())){
                throw new ExMemberAlreadyBorrowedThisEquipment();
            }

            //check if there is an overlap in borrow and request
            if(e.getReservations().size() !=0){

            
                for(int j=0; j<e.getReservations().size();j++){
                    //IF SAME MEMBER PREVIOUSLY REQUESTED AND OVERLAPS
                    if(e.getReservations().get(j).getRequestedBy().getID().equals(m.getID())  &&  Day.doRangesOverlap(e.getReservations().get(j).getStartDay(), e.getReservations().get(j).getEndDay(), StartDate, EndDate)){
                        throw new ExPeriodOverlapsBorrow();
                    }
                }
            }
        }

        //if available then simply give it
        for(EquipmentSet e: Equipmentset){

            if(e.isAvailable()){
                if(e.getReservations().size() !=0){
                    for(int j=0; j<e.getReservations().size();j++){
                        if(!Day.doRangesOverlap(e.getReservations().get(j).getStartDay(), e.getReservations().get(j).getEndDay(), StartDate, EndDate)){
                            e.setBorrow(StartDate, EndDate, m.getID());
                            return e.getLabel();
                        }
                    }
                }
                else{
                    e.setBorrow(StartDate, EndDate, m.getID());
                    return e.getLabel();
                }
                
            }
        }
        throw new ExNoEquipmentSetAvailable();
    }

    //basically this function helps in undo of borrow. it finds the respective equipment set using its name
    //and the call the setunborrow function of equipment set
    public void UnBorrowEquipmentSet(String EquipmentSett){
        for(int i=0;i<Equipmentset.size();i++){
            if(Equipmentset.get(i).getLabel() != null && Equipmentset.get(i).getLabel().equals(EquipmentSett)){
                Equipmentset.get(i).SetUnBorrow();
            }
        }
    }
   

   


    
    //=======================================================================================//

    
    //==========================LISTING=====================================================//

    public static String listset(Equipment e) {
        String ans = "(Borrowed set(s): ";
        boolean found = false;
    
        for (int i = 0; i < e.getequipmentset().size(); i++) {
            if (e.getequipmentset().get(i).isBorrowed()) {
                if (found) {
                    ans += ", "; // Add a comma before subsequent borrowed sets
                } else {
                    found = true; // Mark that we've found at least one borrowed set
                }
                ans += (e.getequipmentset().get(i).getLabel());
                ans += ("(");
                ans += (e.getequipmentset().get(i).getBorrowedby());
                ans += (")");
            }
        }
    
        ans += (")");
        if (found){return ans;}
        else{return "";}
        }

    public static void list(ArrayList<Equipment> allEquipments) {
        // Adjusted the width for the Name column
        System.out.printf("%-5s%-18s%11s\n", "Code", "Name", "#Sets"); // Increased width for Name
        for (Equipment e: allEquipments) {
            System.out.printf("%-5s%-18s%9d   %11s\n", e.EquipmentCode, e.name, e.Equipmentset.size(), listset(e));
        }
    }

    //========================================================================================================//



    //==================================REQUESTING EQUIPMENT=================================================//
    public String RequestEquipmentSet(Member m, Day startDay, Day endDay) throws ExInvalidFormat, ExNoEquipmentSetAvailable,ExPeriodOverLapRequest{
        
        for(EquipmentSet e: Equipmentset){
            String mid = m.getID();
            boolean overlap = false;

            //if borrowed and overlap
            if(e.isBorrowed() &&  Day.doRangesOverlap(e.getStartDate(), e.getEndDate(), startDay, endDay)){
                //if borrowed by same member
                if(e.getBorrowedby() == mid){
                    throw new ExPeriodOverLapRequest();
                }
                overlap = true;
            }

            for(Request r: e.getReservations()){
                
                //if requested by same member previously
                if(mid.equals(r.getRequestedBy().getID())){
                    //if ranges overlap
                    if(Day.doRangesOverlap(r.getStartDay(), r.getEndDay(), startDay, endDay)){
                        throw new ExPeriodOverLapRequest();
                    }
                }
                else{
                    //if overlap of date occurs then simply break of loop
                    overlap = (Day.doRangesOverlap(r.getStartDay(), r.getEndDay(), startDay, endDay));

                    if(overlap){break;}
                }
            }

            if(!overlap){
                e.SetReservation(e.getLabel(), m, startDay, endDay);
                return e.getLabel();
            }
            continue;
        }
        throw new ExNoEquipmentSetAvailable();
    }
     
    public void UnRequestEquipmentSet(Member m, String EquipmentSet, Day StartDayy, Day EndDayy){
        for(EquipmentSet e: Equipmentset){
            if(e.getLabel()!=null  && e.getLabel().equals(EquipmentSet)){
                e.SetUnRequest(e.getLabel(), m.getID(), StartDayy, EndDayy);
            }
        }
    }
}

