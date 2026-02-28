import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class GymManager implements GymOperationsInterface 
{
    // ANSI Color Constants for the UI
    private final String RESET = "\u001B[0m";
    private final String CYAN = "\u001B[36m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String RED = "\u001B[31m";
    private final String BOLD = "\u001B[1m";

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
            System.out.println(GREEN + "SUCCESS: Member " + id + " removed." + RESET);
        } 
        else 
        {
            System.out.println(RED + "ERROR: Member ID " + id + " not found." + RESET);
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
            System.out.println(RED + "FAILED: Member Not Found" + RESET);
            return;
        }
        if ( searchTrainer(trainerID) == null )
        {
            System.out.println(RED + "FAILED: Trainer Not Found" + RESET);
            return;
        }

        member.setAssignedTrainer(trainer);
        trainer.addClient(member);  
    }

    public void checkOverdueMemberships ()
    {
        System.out.println(RED + BOLD + String.format("%-5s | %-20s | %-15s", "No.", "Name", "Status") + RESET);
        System.out.println(RED + "---------------------------------------------------" + RESET);
        
        int count = 1;
        boolean found = false;
        
        for ( Member member : allMembers )
        {
            if ( member.getRemainingDays() <= 0 )
            {
                found = true;
                System.out.printf("%-5d | %-20s | %-15s%n", 
                    count++, 
                    member.getName(), 
                    RED + "EXPIRED (" + member.getPlan() + ")" + RESET);
            }
        }

        if (!found)
        {
            System.out.println(GREEN + "All memberships are currently active!" + RESET);
        }
    }
    
    public void initializeDatabase() 
    {
        System.out.println(CYAN + "Initializing Titan Gym Database..." + RESET);
        
        // 1. Seed Trainers
        Trainer t1 = new Trainer(501, "Coach Marcus", "marcus@gym.com", "Powerlifting");
        Trainer t2 = new Trainer(502, "Coach Sarah", "sarah@gym.com", "Yoga & Pilates");
        Trainer t3 = new Trainer(503, "Coach David", "david@gym.com", "Cardio & HIIT");
        Trainer t4 = new Trainer(504, "Coach Elena", "elena@gym.com", "Nutrition & Weight Loss");

        this.addTrainer(t1);
        this.addTrainer(t2);
        this.addTrainer(t3);
        this.addTrainer(t4);

        // 2. Seed Members
        Member m1 = new Member(101, "John Wick", "john@continental.com", LocalDate.now(), MembershipPlan.MONTHLY, null);
        Member m2 = new Member(102, "Lara Croft", "lara@tomb.com", LocalDate.now().minusMonths(1), MembershipPlan.ANNUAL, null);
        Member m3 = new Member(103, "Bruce Wayne", "bruce@wayneent.com", LocalDate.now().minusMonths(2), MembershipPlan.QUARTERLY, null);
        Member m4 = new Member(104, "Peter Parker", "peter@dailybugle.com", LocalDate.now().minusMonths(2), MembershipPlan.MONTHLY, null);
        Member m5 = new Member(105, "Tony Stark", "tony@stark.com", LocalDate.now().minusYears(1), MembershipPlan.ANNUAL, null);
        Member m6 = new Member(106, "Wanda Maximoff", "wanda@hex.com", LocalDate.now().minusMonths(5), MembershipPlan.QUARTERLY, null);
        Member m7 = new Member(107, "Steve Rogers", "steve@avengers.com", LocalDate.now().minusDays(3), MembershipPlan.ANNUAL, null);
        Member m8 = new Member(108, "Natasha Romanoff", "nat@widow.com", LocalDate.now().minusDays(10), MembershipPlan.MONTHLY, null);

        this.addMember(m1); this.addMember(m2); this.addMember(m3);
        this.addMember(m4); this.addMember(m5); this.addMember(m6);
        this.addMember(m7); this.addMember(m8);

        // 3. Establish Initial Assignments
        this.assignTrainer(m1, t1);
        this.assignTrainer(m2, t4);
        this.assignTrainer(m3, t3);
        this.assignTrainer(m7, t1);

        System.out.println(GREEN + BOLD + "===============================================" + RESET);
        System.out.println(GREEN + BOLD + "   SYSTEM STATUS: ONLINE" + RESET);
        System.out.println(CYAN + "   Data Loaded: " + YELLOW + "4 Trainers | 8 Members" + RESET);
        System.out.println(CYAN + "   Alerts: " + RED + "3 Overdue Accounts Identified" + RESET);
        System.out.println(GREEN + BOLD + "===============================================" + RESET);
    }
}