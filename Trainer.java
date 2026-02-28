import java.util.List;
import java.util.ArrayList;

public class Trainer extends Person
{
    private String specialization;
    private List <Member> clients;

    public Trainer ( int id , String name , String email , String specialization )
    {
        super ( id , name , email);
        this.specialization = specialization;
        this.clients = new ArrayList<>();
    }

    public String getSpecialization ()
    {
        return this.specialization;
    }

    public void setSpecialization ( String specialization )
    {
        this.specialization = specialization;
    }

    public void listClients() 
    {
        if (clients.isEmpty()) 
        {
            System.out.println("This trainer currently has no clients.");
            return;
        }
        int count = 1;
        for (Member client : clients) 
        {
            System.out.println("Client " + count++);
            System.out.println("ID   : " + client.getID()); 
            System.out.println("Name : " + client.getName()); 
            System.out.println("--------------------------------");
        }
    }

    public void addClient ( Member client )
    {
        this.clients.add( client );
        client.setAssignedTrainer(this);
        System.out.println(client.getName() + " is now training with " + this.getName());
    }

}
