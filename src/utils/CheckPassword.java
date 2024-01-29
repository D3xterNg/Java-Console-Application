package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * Allows program to authenticate password for a user
 */
public class CheckPassword {

    /**
     * Method to authenticate student
     * 
     * @param entryNumber input keyed by user
     * @param userPass password of student
     * @return whether the student passed authentication
     */
    public static int checkPasswordStudent(int entryNumber, String userPass){
        
        try {
        	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/excel/student_list.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            String password = "";
            
            try {
            	password = sheet.getRow(entryNumber).getCell(3).getStringCellValue();
            } catch (NullPointerException NPE) {
            	password = null;
            }
    		if (password == null) {
            	if (userPass.equals("password")) {
            		return 2;
            	} else {
            		return 0;
            	}
            } else {
            	if (userPass.equals(password)) {
            		return 1;
            	} else {
            		return 0;
            	}
            }
        } catch (IOException IOE) {
        	System.out.println("No such file");
        	return -1;
        }
    }

    /**
     * Method to authenticate staff
     *
	 *@param entryNumber input keyed by user
	 * @param userPass password of student
     * @return whether the staff passed authentication
     */
    public static int checkPasswordStaff(int entryNumber, String userPass){
        
        try {
        	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/excel/staff_list.xlsx"));
            Sheet sheet = workbook.getSheetAt(0);
            String password = "";
            
            try {
            	password = sheet.getRow(entryNumber).getCell(3).getStringCellValue();
            } catch (NullPointerException NPE) {
            	password = null;
            }
    		if (password == null) {
            	if (userPass.equals("password")) {
            		return 2;
            	} else {
            		return 0;
            	}
            } else {
            	if (userPass.equals(password)) {
            		return 1;
            	} else {
            		return 0;
            	}
            }
        } catch (IOException IOE) {
        	System.out.println("No such file");
        	return -1;
        }
  
    }

}
