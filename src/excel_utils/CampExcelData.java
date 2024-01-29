package excel_utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.Camp;
import camps.CampList;
import users.Staff;
/**
 * A class responsible for handling camp-related data in Excel format.
 * Implements the ICampData interface.
 */
public class CampExcelData implements ICampData {
	
	//camplist object
	private CampList campList;
	//store a camp information
	private String name;
	//Dates of camp
	private Date startDate;
	private Date endDate;
	//Registration closing date of camp
	private Date registrationClosingDate;
	//School where the camp is open for
	private String userGroup;
	//Location of camp
	private String location;
	//Total Slots
	private int totalSlots;
	//Slots for camp committee (max10)
	private int campCommitteeSlots;
	//Description of camp
	private String description;
	//Staff in charge
	private Staff staff; 
	//Id staff in charge
	private String staffId;
	//Visibility of the camp
	private Boolean visibility;
	//list of registered student
	private ArrayList<String> studentIdList;
	//list of registered committee members
	private ArrayList<String> committeeIdList;
	//list of student who previously withdraw from the camp
	private ArrayList<String> withdrawIdList;
		
	@Override
	public void load(CampList campList) {
		// initialize campList
		try {
			//Load the Excel file

			Workbook workbook = WorkbookFactory.create(new FileInputStream("src/excel/camp_list.xlsx"));
			// The data is in the first sheet (index 0)
			Sheet sheet = workbook.getSheetAt(0);

			//map the column name to index
	        Map<String, Integer> headers = new HashMap<>();
	        //Get the header from file and put in map with Index value
	        for (Cell cell : sheet.getRow(0)) {
	        headers.put(cell.getStringCellValue(), cell.getColumnIndex());
	        }
			
	        // Load excel data into campList
	        int count=0;
			for (Row row : sheet) {
				if(count==0) {
					count++;
					continue;
				}


				name = row.getCell(headers.get("name")).getStringCellValue();
				startDate = row.getCell(headers.get("startDate")).getDateCellValue();
				endDate = row.getCell(headers.get("endDate")).getDateCellValue();
				registrationClosingDate = row.getCell(headers.get("registrationClosingDate")).getDateCellValue();
				userGroup = row.getCell(headers.get("userGroup")).getStringCellValue(); 
				location = row.getCell(headers.get("location")).getStringCellValue(); 
				totalSlots = (int)row.getCell(headers.get("totalSlots")).getNumericCellValue(); 
				campCommitteeSlots = (int)row.getCell(headers.get("campCommitteeSlots")).getNumericCellValue(); 
				description = row.getCell(headers.get("description")).getStringCellValue(); 
				staffId = row.getCell(headers.get("staffId")).getStringCellValue(); 
				visibility = row.getCell(headers.get("visibility")).getBooleanCellValue(); 
				
				
                String delimiter = ", "; // Specify the delimiter used in the combinedString
				// Get the combined string from the cell (Attendee)
                String combinedString;
                // Split the combined string into an ArrayList of strings
                String[] stringArray;
                studentIdList = new ArrayList<String>();
	            if(row.getCell(headers.get("studentIdList"))!=null) {
	            	combinedString = row.getCell(headers.get("studentIdList")).getStringCellValue();
	            	stringArray = combinedString.split(delimiter);
					for (String item : stringArray) {
					//Screen out whitespace input
					if(!(item != null && item.trim().isEmpty()))
						studentIdList.add(item);
					}
                }

                // Get the combined string from the cell (Committee)
                committeeIdList = new ArrayList<String>();
                if(row.getCell(headers.get("committeeIdList"))!=null) {
                	 combinedString = row.getCell(headers.get("committeeIdList")).getStringCellValue();
                     // Split the combined string into an ArrayList of strings
                     stringArray = combinedString.split(delimiter);
                     
                     for (String item : stringArray) {
                     	//Screen out whitespace input
                     	if(!(item != null && item.trim().isEmpty()))
                     		committeeIdList.add(item);
                     }
                }
                
                // Get the combined string from the cell (Committee)
                withdrawIdList = new ArrayList<String>();
                if(row.getCell(headers.get("withdrawIdList"))!=null) {
                	combinedString = row.getCell(headers.get("withdrawIdList")).getStringCellValue();
                	// Split the combined string into an ArrayList of strings
                	stringArray = combinedString.split(delimiter);
                	
                	for (String item : stringArray) {
                    	//Screen out whitespace input
                    	if(!(item != null && item.trim().isEmpty()))
                    		withdrawIdList.add(item);
                    }
                }
                
				campList.add(new Camp(name, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, 
						campCommitteeSlots, description, staffId, visibility, studentIdList, committeeIdList, withdrawIdList));
			    count++;
			}
			
			
			// Now, you can save the changes back to the Excel file
	    	try (FileOutputStream fos = new FileOutputStream("src/excel/camp_list.xlsx")) {
	    	    workbook.write(fos);
	    	}
	   
	        // Close the workbook
	        workbook.close();
	
	    } catch (IOException IOE) {
	    	System.out.println("No such file");
	    }
	}

	@Override
	public void save(CampList campList) {
		//store the camp information in the last row
        try {

            Workbook workbook = WorkbookFactory.create(new FileInputStream("src/excel/camp_list.xlsx"));

            Sheet sheet = workbook.getSheetAt(0);
            

            Map<String, Integer> headers = new HashMap<>();
            //Get the header from file and put in map with Index value
            for (Cell cell : sheet.getRow(0)) {
            headers.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            

            for(int i=1; i<= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }

            ArrayList<Row> row  = new ArrayList<Row>();

            for(int i=1; i<campList.size()+1;i++) {
            	row.add(sheet.createRow(i));
            	// Access the cell at the specified column index
	        	Cell cell0 = row.get(i-1).createCell(headers.get("name"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the name
	        	cell0.setCellValue(campList.get(i-1).getName());
	        	
	            // Access the cell at the specified column index
	        	Cell cell1 = row.get(i-1).createCell(headers.get("startDate"), CellType.NUMERIC); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the dates
	        	cell1.setCellValue(campList.get(i-1).getStartDate());
	        	
	            // Access the cell at the specified column index
	        	Cell cell2 = row.get(i-1).createCell(headers.get("endDate"), CellType.NUMERIC); // Create the cell if it doesn't exist
	        	// Set the value of the cell with the dates
	        	cell2.setCellValue(campList.get(i-1).getEndDate());
	        	
	            // Access the cell at the specified column index
	        	Cell cell3 = row.get(i-1).createCell(headers.get("registrationClosingDate"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with registrationClosingDate
	        	cell3.setCellValue(campList.get(i-1).getRegistrationClosingDate());
	        	
	            // Access the cell at the specified column index
	        	Cell cell4 = row.get(i-1).createCell(headers.get("userGroup"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with userGroup
	        	cell4.setCellValue(campList.get(i-1).getUserGroup());
	        	
	            // Access the cell at the specified column index
	        	Cell cell5 = row.get(i-1).createCell(headers.get("location"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with location
	        	cell5.setCellValue(campList.get(i-1).getLocation());
	        	
	            // Access the cell at the specified column index
	        	Cell cell6 = row.get(i-1).createCell(headers.get("totalSlots"), CellType.NUMERIC); // Create the cell if it doesn't exist
	        	// Set the value of the cell with totalSlots
	        	cell6.setCellValue(campList.get(i-1).getTotalSlots());
	        	
	            // Access the cell at the specified column index
	        	Cell cell7 = row.get(i-1).createCell(headers.get("campCommitteeSlots"), CellType.NUMERIC); // Create the cell if it doesn't exist
	        	// Set the value of the cell with campCommitteeSlots
	        	cell7.setCellValue(campList.get(i-1).getCampCommitteeSlots());
	        	
	            // Access the cell at the specified column index
	        	Cell cell8 = row.get(i-1).createCell(headers.get("description"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with description
	        	cell8.setCellValue(campList.get(i-1).getDescription());
	        	
	            // Access the cell at the specified column index
	        	Cell cell9 = row.get(i-1).createCell(headers.get("staffId"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with staffId
	        	cell9.setCellValue(campList.get(i-1).getStaffId());
	        	
	        	Cell cell10 = row.get(i-1).createCell(headers.get("visibility"), CellType.BOOLEAN); // Create the cell if it doesn't exist
	        	// Set the value of the cell with visibility
	        	cell10.setCellValue(campList.get(i-1).getVisibility());
	        	
	        	String combinedString = String.join(", ", campList.get(i-1).getStudentIdList());
	        	Cell cell11 = row.get(i-1).createCell(headers.get("studentIdList"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with studentIdList
	        	cell11.setCellValue(combinedString);
	        	
	        	combinedString = String.join(", ", campList.get(i-1).getCommitteeIdList());
	        	Cell cell12 = row.get(i-1).createCell(headers.get("committeeIdList"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with studentIdList
	        	cell12.setCellValue(combinedString);
	        	
	        	combinedString = String.join(", ", campList.get(i-1).getWithdrawIdList());
	        	Cell cell13 = row.get(i-1).createCell(headers.get("withdrawIdList"), CellType.STRING); // Create the cell if it doesn't exist
	        	// Set the value of the cell with studentIdList
	        	cell13.setCellValue(combinedString);
            	
            }   

        	try (FileOutputStream fos = new FileOutputStream("src/excel/camp_list.xlsx")) {
        	    workbook.write(fos);
        	}      

            workbook.close();
            
        }catch (IOException IOE) {
        	System.out.println("No such file");
        	}
	}
}
