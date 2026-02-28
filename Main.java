import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    // ANSI Color Constants for a "Pro" look
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GymManager myGym = new GymManager();
        myGym.initializeDatabase();

        System.out.println(CYAN + "==========================================");
        System.out.println("      OMAR'S GYM MANAGEMENT SYSTEM         ");
        System.out.println("==========================================" + RESET);

        boolean running = true;
        while (running) {
            // Main Menu UI
            System.out.println("\n" + PURPLE + ">>> MAIN MENU" + RESET);
            System.out.println(CYAN + "------------------------------------------" + RESET);
            System.out.println(" 1. " + GREEN + "Add New Member" + RESET);
            System.out.println(" 2. " + GREEN + "Add New Trainer" + RESET);
            System.out.println(" 3. " + YELLOW + "Assign Trainer to Member" + RESET);
            System.out.println(" 4. " + YELLOW + "View Member Details" + RESET);
            System.out.println(" 5. " + YELLOW + "Check Member Remaining Days" + RESET);
            System.out.println(" 6. " + RED + "List Overdue Memberships" + RESET);
            System.out.println(" 7. " + RED + "Remove Member" + RESET);
            System.out.println(" 8. " + RESET + "Exit System");
            System.out.println(CYAN + "------------------------------------------" + RESET);
            System.out.print("Select an option [1-8]: ");

            int choice = input.nextInt();
            input.nextLine(); 

            while (choice < 1 || choice > 8) {
                System.out.print(RED + "Invalid Choice! Enter a Valid Choice [1-8]: " + RESET);
                choice = input.nextInt();
                input.nextLine(); 
            }

            System.out.println(); // Space for clarity

            switch (choice) {
                case 1:
                    System.out.println(GREEN + "[ ACTION: ADD NEW MEMBER ]" + RESET);
                    System.out.print("Enter ID: ");
                    int id = input.nextInt();
                    input.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();

                    System.out.println("\nSelect Plan: 1. MONTHLY | 2. QUARTERLY | 3. ANNUAL");
                    System.out.print("Choice: ");
                    int planChoice = input.nextInt();
                    input.nextLine(); 
                    
                    if (planChoice >= 1 && planChoice <= 3) {
                        MembershipPlan plan = MembershipPlan.values()[planChoice - 1];
                        Member newMember = new Member(id, name, email, LocalDate.now(), plan, null);
                        myGym.addMember(newMember);
                        System.out.println(GREEN + "\u2713 Member added successfully!" + RESET);
                    } else {
                        System.out.println(RED + "\u2717 Invalid Plan Choice. Member not added." + RESET);
                    }
                    break;

                case 2:
                    System.out.println(GREEN + "[ ACTION: ADD NEW TRAINER ]" + RESET);
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
                    System.out.println(GREEN + "\u2713 Trainer added successfully!" + RESET);
                    break;

                case 3:
                    System.out.println(YELLOW + "[ ACTION: ASSIGN TRAINER ]" + RESET);
                    System.out.print("Enter Member ID: ");
                    int mIdAssign = input.nextInt();
                    System.out.print("Enter Trainer ID: ");
                    int tIdAssign = input.nextInt();
                    input.nextLine(); 

                    Member m = myGym.searchMember(mIdAssign);
                    Trainer t = myGym.searchTrainer(tIdAssign);

                    if (m != null && t != null) {
                        myGym.assignTrainer(m, t);
                        System.out.println(GREEN + "\u2713 Assignment Complete!" + RESET);
                    } else {
                        System.out.println(RED + "\u2717 Error: Member or Trainer not found." + RESET);
                    }
                    break;

                case 4:
                    System.out.println(YELLOW + "[ ACTION: VIEW MEMBER DETAILS ]" + RESET);
                    System.out.print("Enter Member ID: ");
                    int idView = input.nextInt();
                    input.nextLine(); 
                    Member memberView = myGym.searchMember(idView);
                    if (memberView != null) {
                        System.out.println(CYAN + "------------------------------------------" + RESET);
                        memberView.displayInfo();
                        System.out.println(CYAN + "------------------------------------------" + RESET);
                    } else {
                        System.out.println(RED + "Member not found." + RESET);
                    }
                    break;

                case 5:
                    System.out.println(YELLOW + "[ ACTION: CHECK REMAINING DAYS ]" + RESET);
                    System.out.print("Enter Member ID: ");
                    int idCheck = input.nextInt();
                    input.nextLine(); 
                    Member memberCheck = myGym.searchMember(idCheck);
                    if (memberCheck != null) {
                        long days = memberCheck.getRemainingDays();
                        String color = (days <= 0) ? RED : GREEN;
                        System.out.println("Remaining Days: " + color + days + " days" + RESET);
                    } else {
                        System.out.println(RED + "Member not found." + RESET);
                    }
                    break;

                case 6:
                    System.out.println(RED + "==========================================");
                    System.out.println("          OVERDUE MEMBERSHIPS             ");
                    System.out.println("==========================================" + RESET);
                    myGym.checkOverdueMemberships();
                    System.out.println(RED + "------------------------------------------" + RESET);
                    break;

                case 7:
                    System.out.println(RED + "[ ACTION: REMOVE MEMBER ]" + RESET);
                    System.out.print("Enter Member ID: ");
                    int idRemove = input.nextInt();
                    input.nextLine(); 
                    myGym.removeMember(idRemove); 
                    break;

                case 8:
                    running = false;
                    System.out.println(PURPLE + "Exiting System... Stay Fit! Goodbye!" + RESET);
                    break;
            }
            
            if (running) {
                System.out.println(CYAN + "\n(Press Enter to return to menu)" + RESET);
                input.nextLine();
            }
        }
        input.close();
    }
}