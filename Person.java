public class Person 
{
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
        System.out.println(" ID : " + this.id);
        System.out.println(" Name : " + this.name);
        System.out.println(" Email : " + this.email);
    }
}
