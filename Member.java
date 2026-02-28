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
        System.out.println(" ID : " + getID());
        System.out.println(" Name : " + getName());
        System.out.println(" Email : " + getEmail());
        System.out.println(" Enrollment Data : " + this.enrollmentDate);
        System.out.println(" Membership Plan : " + this.plan);

        if ( this.assignedTrainer != null )
        {
            System.out.println(" Assigned Trainer : " + this.assignedTrainer.getName());
        }
        else
        {
            System.out.println(" Assigned Trainer : None ");
        }   
    }

    public long getRemainingDays() 
    {
        LocalDate today = LocalDate.now();
        LocalDate expiryDate = this.enrollmentDate.plusMonths(this.plan.getDurationInMonths());
        return ChronoUnit.DAYS.between(today, expiryDate);
    }

}
