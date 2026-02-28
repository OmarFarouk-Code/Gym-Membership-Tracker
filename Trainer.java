import java.util.List;
import java.util.ArrayList;

public class Trainer extends Person
{
    // ANSI Color Codes for UI consistency
    private final String RESET = "\u001B[0m";
    private final String CYAN = "\u001B[36m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BOLD = "\u001B[1m";

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
        System.out.println("\n" + CYAN + BOLD + "------- [ CLIENT LIST: " + getName().toUpperCase() + " ] -------" + RESET);
        
        if (clients.isEmpty()) 
        {
            System.out.println(YELLOW + " This trainer currently has no assigned clients." + RESET);
            return;
        }

        int count = 1;
        // Header for the client list
        System.out.printf(BOLD + "%-4s | %-6s | %-20s%n" + RESET, "No.", "ID", "Client Name");
        System.out.println(CYAN + "------------------------------------------------" + RESET);

        for (Member client : clients) 
        {
            System.out.printf("%-4d | %-6d | %-20s%n", 
                count++, 
                client.getID(), 
                GREEN + client.getName() + RESET);
        }
        System.out.println(CYAN + "------------------------------------------------" + RESET);
    }

    public void addClient ( Member client )
    {
        this.clients.add( client );
        client.setAssignedTrainer(this);
        System.out.println(GREEN + "SUCCESS: " + client.getName() + " is now training with " + this.getName() + RESET);
    }
}