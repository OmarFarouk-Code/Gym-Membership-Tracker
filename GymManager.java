import java.util.List;
import java.time.LocalDate;
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
                System.out.println ("-----------------------------------");
            }
        }
    }
    
    public void initializeDatabase() 
    {
        // 1. Seed Trainers (Different Specializations)
        Trainer t1 = new Trainer(501, "Coach Marcus", "marcus@gym.com", "Powerlifting");
        Trainer t2 = new Trainer(502, "Coach Sarah", "sarah@gym.com", "Yoga & Pilates");
        Trainer t3 = new Trainer(503, "Coach David", "david@gym.com", "Cardio & HIIT");
        Trainer t4 = new Trainer(504, "Coach Elena", "elena@gym.com", "Nutrition & Weight Loss");

        this.addTrainer(t1);
        this.addTrainer(t2);
        this.addTrainer(t3);
        this.addTrainer(t4);

        // 2. Seed Members (Various Scenarios)
        
        // --- ACTIVE MEMBERS ---
        // Joined today - Monthly
        Member m1 = new Member(101, "John Wick", "john@continental.com", LocalDate.now(), MembershipPlan.MONTHLY, null);
        // Joined 1 month ago - Annual (Lots of time left)
        Member m2 = new Member(102, "Lara Croft", "lara@tomb.com", LocalDate.now().minusMonths(1), MembershipPlan.ANNUAL, null);
        // Joined 2 months ago - Quarterly (1 month left)
        Member m3 = new Member(103, "Bruce Wayne", "bruce@wayneent.com", LocalDate.now().minusMonths(2), MembershipPlan.QUARTERLY, null);

        // --- OVERDUE MEMBERS (To test Case 6) ---
        // Joined 2 months ago - Monthly (Expired 1 month ago)
        Member m4 = new Member(104, "Peter Parker", "peter@dailybugle.com", LocalDate.now().minusMonths(2), MembershipPlan.MONTHLY, null);
        // Joined 1 year ago - Annual (Expired today)
        Member m5 = new Member(105, "Tony Stark", "tony@stark.com", LocalDate.now().minusYears(1), MembershipPlan.ANNUAL, null);
        // Joined 5 months ago - Quarterly (Expired 2 months ago)
        Member m6 = new Member(106, "Wanda Maximoff", "wanda@hex.com", LocalDate.now().minusMonths(5), MembershipPlan.QUARTERLY, null);

        // --- NEW RECRUITS ---
        Member m7 = new Member(107, "Steve Rogers", "steve@avengers.com", LocalDate.now().minusDays(3), MembershipPlan.ANNUAL, null);
        Member m8 = new Member(108, "Natasha Romanoff", "nat@widow.com", LocalDate.now().minusDays(10), MembershipPlan.MONTHLY, null);

        // Add all to list
        this.addMember(m1); this.addMember(m2); this.addMember(m3);
        this.addMember(m4); this.addMember(m5); this.addMember(m6);
        this.addMember(m7); this.addMember(m8);

        // 3. Establish Initial Assignments
        this.assignTrainer(m1, t1); // John Wick training Powerlifting
        this.assignTrainer(m2, t4); // Lara Croft training Nutrition
        this.assignTrainer(m3, t3); // Bruce Wayne training HIIT
        this.assignTrainer(m7, t1); // Steve Rogers training Powerlifting

        System.out.println("===========================================");
        System.out.println("DATABASE LOADED: 4 Trainers | 8 Members");
        System.out.println("Status: 3 Overdue, 5 Active");
        System.out.println("===========================================");
    }
}
