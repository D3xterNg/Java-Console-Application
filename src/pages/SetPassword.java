package pages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utils.CheckPassword;

/**
 * Page to set password of user
 */
public class SetPassword extends Page{
	/**
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Type of user
     */
    private String userType;
    /**
     * User ID of user
     */
    private int entryNumber;
    /**
     * Boolean indicating whether it is the initial login of user
     */
    private boolean initialLogin;
    /**
     * Constructor for SetPassword
     * @param earlierPg get previous pg
     * @param entryNumber get user entry
     * @param userType get user type
     * @param initialLogin first time login
     */

    public SetPassword(Page earlierPg, int entryNumber, String userType, boolean initialLogin){
        super(earlierPg);
        this.userType = userType;
        this.entryNumber = entryNumber;
        this.initialLogin = initialLogin;
    }
    /**
     * main functionality of the page
     * @return page
     */
    @Override
    public Page startApp(){
        String filepath = userType.equals("1") ? "src/excel/student_list.xlsx" : "src/excel/staff_list.xlsx";
        String currentPass;
        int flag = 0;

        // verify current password
        while(true){
        	System.out.println("Enter your current password ");
            currentPass = sc.nextLine();
            // if empty input return to previous page
            if(currentPass.isBlank()){
                return this.getEarlierPg();
            }

            // check if current password matches
            flag = userType.equals("1")
                        ? CheckPassword.checkPasswordStudent(this.entryNumber, currentPass)
                        : CheckPassword.checkPasswordStaff(this.entryNumber, currentPass);
            if(flag == 1 || flag == 2){
                break;
            }
            else{
                System.out.printf("Invalid password. Try again.");
            }
        }

        String newPass1, newPass2;
        boolean match = false;

        do{
            System.out.println("Enter your new password");
            newPass1 = sc.nextLine();
            System.out.println("Re-enter your new password");
            newPass2 = sc.nextLine();

            // check if both password match
            match = newPass1.equals(newPass2);
            if(!newPass2.isBlank() && !newPass2.isBlank()){
                if(!match){
                    System.out.println("The passwords you entered did not match");
                }
                // set password
                else{
                	try {
                    	//WRITE TO EXCEL HERE!
                    	Workbook workbook = WorkbookFactory.create(new FileInputStream(filepath));
                    	Sheet sheet = workbook.getSheetAt(0);
                    	Row row = sheet.getRow(entryNumber);

                    	if (row == null) {
                    	    // If the row doesn't exist, create it
                    	    row = sheet.createRow(entryNumber);
                    	}

                    	int columnIndex = 3; // Column 4

                    	// Access the cell at the specified column index
                    	Cell cell = row.createCell(columnIndex, CellType.STRING); // Create the cell if it doesn't exist

                    	// Set the value of the cell with the new password
                    	cell.setCellValue(newPass1);

                    	// Now, you can save the changes back to the Excel file
                    	try (FileOutputStream fos = new FileOutputStream(filepath)) {
                    	    workbook.write(fos);
                    	}

                    	// Close the workbook when done
                    	workbook.close();
                    	System.out.println("Password changed successfully. You are required to re-login. \n");
                    	if (initialLogin == true) {
                    		return this.getEarlierPg();
                    	} else {
                    		//Force logout
                            return this.getEarlierPg().getEarlierPg();
                    	}
                	} catch (IOException IOE) {
                    	System.out.println("No such file");
                    	return this.getEarlierPg();
                    }

                }
            }

        } while(!(newPass1.isBlank() || newPass2.isBlank()));
        return this.getEarlierPg();


    }
}
