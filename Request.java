public class Request implements Comparable<Request>{
    Member RequestedBy;
    Day StartDay;
    Day EndDay;
    String Label;


    //constructor
    public Request(String Label, Member m, Day Start, Day End){
        this.Label = Label;
        RequestedBy = m;
        StartDay = Start;
        EndDay = End;

    }

    //compareto function used for comparing while listing
    @Override
    public int compareTo(Request another){
        return this.StartDay.compareTo(another.getStartDay());

    }

    @Override
    public String toString(){
        String string = StartDay.toString() + " to " + EndDay.toString();
        return string;
    }

    //getter methods
    public Member getRequestedBy (){return RequestedBy;}
    public String getLabel(){return Label;}
    public Day getStartDay(){return StartDay;}
    public Day getEndDay(){return EndDay;}


}
