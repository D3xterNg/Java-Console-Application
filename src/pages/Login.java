package pages;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;

import users.Staff;
import users.Student;
import utils.CheckPassword;

/**
 * The login class for students and staff to login
 */
public class Login extends Page {
	/**
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Type of user student or staff
     */
    private String userType;
    /**
     * Row number where user ID is found
     */
    private int entryNumber;
    /**
     * User ID of user
     */
    private String userID;
    /**
     * Password of the user ID input
     */
    private String userPass;
    /**
     * Integer indicating whether user credentials matches those in the excel sheet
     */
    private int userFound;
    
   /**
    * User name from excel sheet
    */
    private static String name;
    /**
     * user faculty from excel sheet
     */
    private static String faculty;
    /**
     * user email address from excel sheet
     */
    private static String email;
    /**
     * User committee points for camp committee members
     */
    private int committeePoints;

    
    /**
     * Constructor for Login class
     * @param earlierPg to go back
     * @param userType user type 1 for student 2 for staff
     */
    public Login(Page earlierPg, String userType){
        super(earlierPg);
        this.userType = userType;
    }

   /**
    * Main executable for login page
    * @return Page for student or staff depending on user type
    */
    public Page startApp(){


        do{
        	//Reinitialise userFound variable
        	userFound = 0;
        	if (userType.equals("1")) {
                System.out.println("========================================================================");
        		System.out.println("Student Login");
        	} else {
                System.out.println("=========================================");
        			System.out.println("Staff Login");
        	}
            // get userID
            System.out.print("Enter UserID:");
            this.userID = sc.nextLine().trim().toUpperCase();

            // get userPass
            System.out.print("Enter password:");
            this.userPass = sc.nextLine();
            System.out.println("=========================================");
            // Finding student in the Excel file
            try {
                // Load the Excel file
            	String filepath = userType.equals("1") ? "src/excel/student_list.xlsx" : "src/excel/staff_list.xlsx";
                Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath));
                // The data is in the first sheet (index 0)
                Sheet sheet = workbook.getSheetAt(0);
                // Iterate through rows to find the user
                entryNumber = 0;
                for (Row row : sheet) {
                    Cell emailCell = row.getCell(1); // Assuming email is in the first column
                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
                        email = emailCell.getStringCellValue();
                        String[] parts = email.split("@");
                        if (parts.length > 1 && parts[0].equals(userID)) {
                            // User found
                            name = row.getCell(0).getStringCellValue(); // Name in the first column
                            faculty = row.getCell(2).getStringCellValue();
                            if (userType.equals("1")) {
                                try {
                                	committeePoints = (int) row.getCell(4).getNumericCellValue();
                                } catch (NullPointerException NPE) {
                                	//student is not a committee
                                	committeePoints = 0;
                                }
                            }
                            // Faculty in the third column
                            userFound = 1;
                            break; // Exit the loop when the user is found
                        }
                    }
                    entryNumber += 1;
                }

                // Close the workbook
                workbook.close();


//        do{
//        	//Reinitialize userFound variable
//        	userFound = 0;
//            // get userID
//            System.out.print("Enter UserID:");
//            this.userID = sc.nextLine().trim().toUpperCase();
//
//            // get userPass
//            System.out.print("Enter password:");
//            this.userPass = sc.nextLine();
//
//            // Finding staff in the Excel file
//            try(FileInputStream fileInputStream = new FileInputStream("C:\\CAMs-App\\src\\main\\student_list.xlsx")) {
//                // Load the Excel file
//                Workbook workbook = WorkbookFactory.create(fileInputStream);
//                // The data is in the first sheet (index 0)
//                Sheet sheet = workbook.getSheetAt(0);
//                // Iterate through rows to find the user
//                entryNumber = 0;
//                for (Row row : sheet) {
//                    Cell emailCell = row.getCell(1); // Assuming email is in the first column
//                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
//                        email = emailCell.getStringCellValue();
//                        String[] parts = email.split("@");
//                        if (parts.length > 1 && parts[0].equals(userID)) {
//                            // User found
//                            name = row.getCell(0).getStringCellValue(); // Name in the first column
//                            faculty = row.getCell(2).getStringCellValue();
//                            // Faculty in the third column
//                            userFound = 1;
//                            break; // Exit the loop when the user is found
//                        }
//                    }
//                    entryNumber += 1;
//                }
//
//                // Close the workbook
//                workbook.close();
//



                // If user is not found
                if (userFound == 0) {
                	System.out.println("User not found.");
                	continue;
                } else {
                	//If user is found
                	int checkPassword;
                	if (userType.equals("1")) {
                    	checkPassword = CheckPassword.checkPasswordStudent(entryNumber, userPass);
        				if (checkPassword == 2) {
                    		System.out.println("User login succussful");
                            System.out.println("Student : " + name + " (" + faculty + ")");
                            System.out.println("First time Login, please change password");
                            Student student = new Student(name, faculty, email, entryNumber, committeePoints);
                            return new SetPassword(this, student.getEntryNumber(), "1", true);
                        } else if (checkPassword == 1) {
                    		System.out.println("User login succussful");
                            System.out.println("Student : " + name + " (" + faculty + ")");
                            Student student = new Student(name, faculty, email, entryNumber, committeePoints);
                            return new StudentMenu(this, student);
                        } else if (checkPassword == 0) {
                    		System.out.println("Wrong password.");
                        } else {
                    		System.out.println("Error, check input file");
                        }
                	} else {
                    	checkPassword = CheckPassword.checkPasswordStaff(entryNumber, userPass);
        				if (checkPassword == 2) {
                    		System.out.println("User login successful");
                            System.out.println("Staff : " + name + " (" + faculty + ")");
                            System.out.println("First time Login, please change password");
                            Staff staff = new Staff(name, faculty, email, entryNumber);
                            return new SetPassword(this, staff.getEntryNumber(), "2", true);
                        } else if (checkPassword == 1) {
                    		System.out.println("User login successful");
                            System.out.println("Staff : " + name + " (" + faculty + ")");
                            Staff staff = new Staff(name, faculty, email, entryNumber);
                            return new StaffMenu(this, staff);
                        } else if (checkPassword == 0) {
                    		System.out.println("Wrong password.");
                        } else {
                    		System.out.println("Error, check input file");
                        }
                	}
                }


            } catch (IOException IOE) {
            	System.out.println("No such file");
            }

            // Redirect to staff main
            
            if(!(this.userPass.isBlank() || this.userID.isBlank())){
                System.out.println("Re-attempting login.");
            }

        } while((this.userPass.isBlank() || this.userID.isBlank()));
        return this.getEarlierPg();
    }
}

//                // If user is not found
//                if (userFound == 0) {
//                	System.out.println("User not found.");
//                	continue;
//                } else {
//                	//If user is found
//                	int checkPassword = CheckPassword.checkPasswordStaff(entryNumber, userPass);
//    				if (checkPassword == 1) {
//                		System.out.println("User login succussful");
//                        System.out.println("Staff : " + name + " (" + faculty + ")");
//                        Staff staff = new Staff(name, faculty, email, entryNumber);
//                        return new StaffMain(this, staff);
//                    } else if (checkPassword == 0) {
//                		System.out.println("Wrong password.");
//                    } else {
//                		System.out.println("Error, check input file");
//                    }
//                }
//
//
//            } catch (IOException IOE) {
//            	System.out.println("No such file");
//            }
//
//            // Redirect to staff main
//
//            if(!(this.userPass.isBlank() || this.userID.isBlank())){
//                System.out.println("Re-attempting login.");
//            }
//
//        } while(!(this.userPass.isBlank() || this.userID.isBlank()));
//        return this.getPreviousPage();
//    }