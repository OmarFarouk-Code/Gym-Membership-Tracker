import java.time.LocalDate;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        GymManager myGym = new GymManager();
        myGym.initializeDatabase();

        boolean running = true;
        while (running) 
        {
            System.out.println("\n========= GYM SYSTEM MENU =========");
            System.out.println("1. Add New Member");
            System.out.println("2. Add New Trainer");
            System.out.println("3. Assign Trainer to Member");
            System.out.println("4. View Member Details");
            System.out.println("5. Check Member Remaining Days");
            System.out.println("6. List Overdue Memberships");
            System.out.println("7. Remove Member");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            // Input validation for the menu choice
            int choice = input.nextInt();
            input.nextLine(); 

            while (choice < 1 || choice > 8) 
            {
                System.out.print("Invalid Choice, Enter a Valid Choice: ");
                choice = input.nextInt();
                input.nextLine(); 
            }

            switch (choice) 
            {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = input.nextInt();
                    input.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();

                    System.out.println("Select Plan: 1. MONTHLY, 2. QUARTERLY, 3. ANNUAL");
                    int planChoice = input.nextInt();
                    input.nextLine(); 
                    
                    // Safety check for Enum array bounds
                    if (planChoice >= 1 && planChoice <= 3) 
                    {
                        MembershipPlan plan = MembershipPlan.values()[planChoice - 1];
                        Member newMember = new Member(id, name, email, LocalDate.now(), plan, null);
                        myGym.addMember(newMember);
                        System.out.println("Member added successfully!");
                    } 
                    else 
                    {
                        System.out.println("Invalid Plan Choice. Member not added.");
                    }
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id2 = input.nextInt();
                    input.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name2 = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email2 = input.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = input.nextLine();

                    Trainer trainer = new Trainer(id2, name2, email2, specialization);
                    myGym.addTrainer(trainer);
                    System.out.println("Trainer added successfully!");
                    break;

                case 3:
                    System.out.print("Enter Member ID: ");
                    int mIdAssign = input.nextInt();
                    System.out.print("Enter Trainer ID: ");
                    int tIdAssign = input.nextInt();
                    input.nextLine(); 

                    Member m = myGym.searchMember(mIdAssign);
                    Trainer t = myGym.searchTrainer(tIdAssign);

                    if (m != null && t != null)
                    {
                        myGym.assignTrainer(m, t);
                        System.out.println("Assignment Complete!");
                    } 
                    else 
                    {
                        System.out.println("Error: Member or Trainer not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Member ID: ");
                    int idView = input.nextInt();
                    input.nextLine(); 
                    Member memberView = myGym.searchMember(idView);
                    if (memberView != null) 
                    {
                        memberView.displayInfo();
                    } 
                    else 
                    {
                        System.out.println("Member not found");
                    }
                    break;

                case 5:
                    System.out.print("Enter Member ID: ");
                    int idCheck = input.nextInt();
                    input.nextLine(); 
                    Member memberCheck = myGym.searchMember(idCheck);
                    if (memberCheck != null) 
                    {
                        System.out.println("Days Remaining: " + memberCheck.getRemainingDays());
                    } 
                    else 
                    {
                        System.out.println("Member not found");
                    }
                    break;

                case 6:
                    System.out.println("-------------------------");
                    System.out.println("OVERDUE MEMBERSHIPS");
                    System.out.println("-------------------------");
                    myGym.checkOverdueMemberships();
                    break;

                case 7:
                    System.out.print("Enter Member ID: ");
                    int idRemove = input.nextInt();
                    input.nextLine(); 
                    myGym.removeMember(idRemove); 
                    break;

                case 8:
                    running = false;
                    System.out.println("Exiting System. Goodbye!");
                    break;
            }
        }
        input.close();
    }
    
}