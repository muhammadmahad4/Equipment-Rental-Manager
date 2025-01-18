public class SystemDate extends Day{

    private static SystemDate instance;

    private SystemDate(String sDay) throws ExInvalidFormat{
        super(sDay);
    }

    public static SystemDate getInstance() {return instance;}

    public static void deleteInstance() {instance = null;};

    public static void createTheInstance(String sDay) throws ExInvalidFormat{
        if (instance==null) //make sure only one instance can be created (Singleton)
            instance = new SystemDate(sDay);
        else
            System.out.println("Cannot create one more system date instance.");
    }

    
}
