public class ExMemberIDinUse extends Exception {

    public ExMemberIDinUse(String ID, String Name){
        super("Member ID already in use: "+ ID + " " + Name);
    }
    public ExMemberIDinUse(){
        super("Member ID in use.");
    }
}

