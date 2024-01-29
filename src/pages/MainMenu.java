package pages;

import java.util.Scanner;

/**
 * Welcome page that users see after executing the camp program
 */
public class MainMenu extends Page{
	/**
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Type of user student or staff
     */
    private String login;
    /**
     * Constructor for Welcome
     * @param earlierPg get previous page
     */
    public MainMenu(Page earlierPg){
        super(earlierPg);
    }
    /**
     * Main executable for the page
     * @return goes to the login page
     */
    @Override
    public Page startApp(){
        
        // print menu
        System.out.println("=========================================");
        System.out.println("               Login Menu                ");
        System.out.println("=========================================");
        System.out.println("Log in as a:");
        System.out.println("1: Student");
        System.out.println("2: Staff");
        System.out.println("=====================================================================");

        // get user type
        System.out.print("Enter 1 for Student Login, 2 for Staff Login (enter a to exit menu ): ");
        this.login = sc.nextLine().trim();
        System.out.println();

        // if input not blank, loop to ask for valid input
        while (!this.login.equals("1") && !this.login.equals("2")) {
            // exit program if empty input
            if(this.login.equals("a")){
                return new Exit(null);
            }

            System.out.println("Enter a valid user type:");
            this.login = sc.nextLine().trim();
        }
        
        
        return new Login(this, this.login);
        
    }

}
