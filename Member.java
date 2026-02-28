import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Member extends Person
{
    private LocalDate enrollmentDate;
    private MembershipPlan plan;
    private Trainer assignedTrainer;
    
    public Member ( int id , String name , String email , LocalDate enrollmenDate ,  MembershipPlan plan , Trainer assignedTrainer )
    {
        super(id , name , email);
        this.plan = plan;
        this.enrollmentDate = enrollmenDate;
        this.assignedTrainer = assignedTrainer;
    }
    
    public LocalDate getEnrollmentDate() 
    {
        return enrollmentDate;
    }

    public MembershipPlan getPlan() 
    {
        return this.plan;
    }

    public void setPlan(MembershipPlan plan) 
    {
        if (plan == null) 
        {
            throw new IllegalArgumentException("Plan cannot be null");
        }
        this.plan = plan;
    }

    public Trainer getAssignedTrainer() 
    {
        return assignedTrainer;
    }

    public void setAssignedTrainer(Trainer assignedTrainer) 
    {
        this.assignedTrainer = assignedTrainer;
    }

    public void displayInfo ()
    {
        // Colors used from Main class context
        String CYAN = "\u001B[36m";
        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BOLD = "\u001B[1m";

        System.out.println(CYAN + BOLD + "------- [ MEMBER PROFILE ] -------" + RESET);
        System.out.println(BOLD + " ID      : " + RESET + getID());
        System.out.println(BOLD + " Name    : " + RESET + GREEN + getName() + RESET);
        System.out.println(BOLD + " Email   : " + RESET + getEmail());
        System.out.println(BOLD + " Joined  : " + RESET + this.enrollmentDate);
        System.out.println(BOLD + " Plan    : " + RESET + YELLOW + this.plan + RESET);

        if ( this.assignedTrainer != null )
        {
            System.out.println(BOLD + " Trainer : " + RESET + GREEN + this.assignedTrainer.getName() + RESET);
        }
        else
        {
            System.out.println(BOLD + " Trainer : " + RESET + "None");
        } 
        
        // Calculate days to show status color
        long days = getRemainingDays();
        String statusColor = (days <= 0) ? "\u001B[31m" : GREEN; // Red if expired, else Green
        
        System.out.println(BOLD + " Status  : " + RESET + statusColor + days + " days remaining" + RESET);
        System.out.println(CYAN + "----------------------------------" + RESET);
    }

    public long getRemainingDays() 
    {
        LocalDate today = LocalDate.now();
        LocalDate expiryDate = this.enrollmentDate.plusMonths(this.plan.getDurationInMonths());
        return ChronoUnit.DAYS.between(today, expiryDate);
    }

}
