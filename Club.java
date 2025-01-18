import java.util.ArrayList;
import java.util.Collections; //Provides sorting


public class Club {

    private static Club instance = new Club();

    private Club() { 
        allMembers = new ArrayList<Member>(); 
    }

    public static Club getInstance() { 
        return instance; 
    }
    //=======================================EQUIPMENT FUNCTIONALITIES==============================================================//

    //all equipments contain all the equipments created
    private ArrayList<Equipment> allEquipments= new ArrayList<>();
    public void addEquipment(Equipment e){allEquipments.add(e);}
    public void removeEquipment(Equipment e){allEquipments.remove(e);}

    public Equipment findEquipment(String Eqcode){
        for(int i=0; i<allEquipments.size();i++){
            if(allEquipments.get(i).getEquipmentCode().equals(Eqcode)){
                return allEquipments.get(i);
            }
        }
        return null;
    }

    public void listEquipments() {
        Equipment.list(this.allEquipments);
    }


  
//==================================MEMBER FUNCTIONALITIES====================================================================//

    private ArrayList<Member> allMembers;
    

    public void addMember(Member m) {
        allMembers.add(m);
        Collections.sort(allMembers); // allMembers
    }

    public void removeMember(Member m) {
        allMembers.remove(m);
    }

    public void listClubMembers() {
        Member.list(this.allMembers);
    }

    public Member findMember(String memid){
        for(Member m: allMembers){
            if(m.getID().equals(memid)){
                return m;
            }
        }
        return null;
    }


    //================================ListMemberStatus================================================//

    public void ListMemberStatus(){

        //sorting
        Collections.sort(allMembers);
        Collections.sort(allEquipments);

        //looping through all members
        for(int i=0; i<allMembers.size();i++){
            
            boolean found = false;//for throwing error if no equipment borrowed

            //for space after status of each member
            if(i!=0){
                System.out.println();
            }

            Member currmem = allMembers.get(i);
            System.out.printf("[%s %s]\n",currmem.getID(), currmem.getName());

            
            //looping through all equipments

            for(Equipment currEq: allEquipments){
                
                Collections.sort(currEq.getequipmentset());

                //looping through all equipments sets of each equipment
            
                //sorting through equipment set label
                for(EquipmentSet currEqSet: currEq.getequipmentset()){

                    if(currEqSet.isBorrowed() && currEqSet.getBorrowedby().equals(allMembers.get(i).getID())){
                        found = true;
                        System.out.printf("- borrows %s (%s) for %s to %s\n",currEqSet.getLabel(), currEq.getName(),currEqSet.getStartDate(),currEqSet.getEndDate());
                    }
                }
            }

            for(Equipment currEq: allEquipments){
            
                //sorting through equipment set
                Collections.sort(currEq.getequipmentset());
                
                //looping through all equipments sets of each equipment
                for(EquipmentSet currEqSet: currEq.getequipmentset()){

                    Collections.sort(currEqSet.getReservations());
                    for(Request currRequest: currEqSet.getReservations()){
                        
                        if(currRequest.getRequestedBy().getID().equals(currmem.getID())){
                            found = true;
                            System.out.printf("- requests %s (%s) for %s to %s\n", currRequest.getLabel(), currEq.getName(),currRequest.getStartDay(), currRequest.getEndDay());
                        }
                    }
                }
            }
            if(!found){
                System.out.println("No Record.");
            }

        }
    }
    //==========================================ListEquipmentStatus============================================================//
    public void ListEquipmentStatus(){
        //sorting
        Collections.sort(allEquipments);


        for(int i=0; i<allEquipments.size();i++){

            if(i!=0){
                System.out.println();
            }

            Equipment currEq = allEquipments.get(i);
            System.out.printf("[%s %s]\n", currEq.getEquipmentCode(), currEq.getName());

            

            if(currEq.getequipmentset().size() == 0){;
                System.out.println("  We do not have any sets for this equipment.");
            }
            else{
                Collections.sort(currEq.getequipmentset());

                for(EquipmentSet currEqSet: currEq.getequipmentset()){
                    boolean found = false;
                    System.out.printf("  %s\n", currEqSet.getLabel());
    
                
                    if (currEqSet.isBorrowed()) {
                        found = true;
                        System.out.printf("    Current status: %s %s borrows for %s to %s\n",
                            currEqSet.getBorrowedby(),
                            findMember(currEqSet.getBorrowedby()).getName(),
                            currEqSet.getStartDate(),
                            currEqSet.getEndDate());
                    } 
                    if(found == false){
                        System.out.println("    Current status: Available");
                    }
                    if(currEqSet.getReservations().size() != 0){
                        Collections.sort(currEqSet.getReservations());
                        String ans = "";
                        for (int k=0; k<currEqSet.getReservations().size();k++){
                        
                            ans += currEqSet.getReservations().get(k).toString();
                            if(k != currEqSet.getReservations().size()-1){ 
                                ans += ", ";
                            }
                            else{
                                ans += " ";
                            }
                        }
                        System.out.printf("    Requested period(s): %s\n", ans);
                    }
                    
                }
            }
        }
    }
}
