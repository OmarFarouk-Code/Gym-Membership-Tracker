import java.util.List;
import java.util.ArrayList;

public class GymManager implements GymOperationsInterface 
{
    private List <Member> allMembers;
    private List <Trainer> allTrainers;

    public GymManager ()
    {
        this.allMembers = new ArrayList<>();
        this.allTrainers = new ArrayList<>();
    }

    public void addTrainer ( Trainer trainer )
    {
        this.allTrainers.add(trainer);
    }

    public void addMember ( Member member )
    {
        this.allMembers.add( member );
    }

    public void removeMember ( int id )
    {
        Member found = searchMember(id);
        if (found != null) 
        {
            allMembers.remove(found); 
            System.out.println("Member " + id + " removed.");
        } 
        else 
        {
            System.out.println("Member ID " + id + " not found.");
        }
    }

    public Member searchMember ( int id )
    {
        for ( Member member : allMembers )
        {
            if ( member.getID() == id )
            {
                return member;
            }
        }
        return null;
    }

    public Trainer searchTrainer ( int id )
    {
        for ( Trainer trainer : allTrainers )
        {
            if ( trainer.getID() == id )
            {
                return trainer;
            }
        }
        return null;
    }

    public void assignTrainer ( Member member , Trainer trainer )
    {
        int memberID = member.getID();
        int trainerID = trainer.getID();
        if ( searchMember(memberID) == null )
        {
            System.out.println("Member Not Found");
            return;
        }
        if ( searchTrainer(trainerID) == null )
        {
            System.out.println("Trainer Not Found");
            return;
        }

        member.setAssignedTrainer(trainer);
        trainer.addClient(member);  
    }

    public void checkOverdueMemberships ()
    {
        int count = 1;
        for ( Member member : allMembers )
        {
            if ( member.getRemainingDays() <= 0 )
            {
                System.out.println ("Member " + (count++));
                System.out.println ("Name " + member.getName() );
                System.out.println ("Previous Plan " + member.getPlan() );
            }
        }
    }
    
}
