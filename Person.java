public class Person 
{
    // ANSI Color Constants for consistent UI
    private final String RESET = "\u001B[0m";
    private final String CYAN = "\u001B[36m";
    private final String GREEN = "\u001B[32m";
    private final String BOLD = "\u001B[1m";

    private int id;
    private String name;
    private String email;

    public Person ( int id , String name , String email )
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getID ()
    {
        return this.id;
    }

    public void setID ( int id )
    {
        this.id = id;
    }

    public String getName ()
    {
        return this.name;
    }

    public void setName ( String name )
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return this.email;
    }

    public void setEmail ( String email )
    {
        this.email = email;
    }

    public void displayInfo ()
    {
        // Using formatted labels with colors
        System.out.println(CYAN + BOLD + "------- [ BASIC INFO ] -------" + RESET);
        System.out.printf("%-10s : %d%n", BOLD + "ID" + RESET, this.id);
        System.out.printf("%-10s : %s%n", BOLD + "Name" + RESET, GREEN + this.name + RESET);
        System.out.printf("%-10s : %s%n", BOLD + "Email" + RESET, this.email);
    }
}