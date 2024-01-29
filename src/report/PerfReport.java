package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.Camp;
import camps.CampList;
import users.Staff;
import users.User;
/**
 * Performance report of committee members the implements ReportGenerator interface
 */
public class PerfReport implements ReportGenerator{

	/**
	 * Generates the performance report of committee members in either txt or csv
	 * @param user generating the report
	 * @param campList the list of camps
	 */
	@Override
	public void generate(User user, CampList campList) {
		Staff staff = (Staff) user;
		Scanner sc = new Scanner(System.in);
		String filename, filepath;


	        while(true) {
	        	System.out.println("Enter the file name:");
	        	filename = sc.nextLine().trim();
	            filepath = "src/excel/reports/"+filename+".txt";
	        	try {
	  		      File myObj = new File(filepath);
	  		      if (myObj.createNewFile()) {
	  		        System.out.println("File created: " + myObj.getName());
	  		        break;
	  		      } else {
	  		        System.out.println("File already exists.");
	  		      }
	  		    } catch (IOException e) {
	  		      e.printStackTrace();
	  		    }
	        }
			
			try {
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("=====Performance Report for Camp Committees====\n");
				myWriter.write("\r\n");
				
				ArrayList<String> committeeId =  new ArrayList<String>();
				for(int i=0; i<campList.size(); i++) {
					Camp selectedCamp = campList.get(i);
					
					for (String var : selectedCamp.getCommitteeIdList()) {
						if(!committeeId.contains(var)) {
							committeeId.add(var);
						}		  
					}
				}
				Collections.sort(committeeId);
				
				ArrayList<Integer> committeePoints = new ArrayList<Integer>(); 
				
	            try {
	                // Load the Excel file
	            	String path = "src/excel/student_list.xlsx";
	                Workbook workbook = WorkbookFactory.create(new FileInputStream(path));
	                
	                Sheet sheet = workbook.getSheetAt(0);
	                for(int i=0; i<committeeId.size();i++) {
	                	
		                for (Row row : sheet) {
		                    Cell emailCell = row.getCell(1); 
		                    if (emailCell != null && emailCell.getCellType() == CellType.STRING) {
		                        String email = emailCell.getStringCellValue();
		                        String[] parts = email.split("@");
		                        if (parts.length > 1 && parts[0].equals(committeeId.get(i))) {
		                            
	                                try {
	                                	committeePoints.add((int) row.getCell(4).getNumericCellValue());
	                                } catch (NullPointerException NPE) {
	                                	
	                                	committeePoints.add(0);
	                                }
		                            break; 
		                        }
		                    }
		                }     	
	                }
	                
	                workbook.close();
	            } catch (IOException IOE) {

	            }
	
				
				for(int i=0; i<committeeId.size(); i++) {
					
					myWriter.write((i+1)+": "+committeeId.get(i)+" in charge of ");
					for(int j=0; j<campList.size(); j++) {
						
						if(campList.get(j).getCommitteeIdList().contains(committeeId.get(i))) {
							myWriter.write(campList.get(i).getName()+" ");
						}
					}
					myWriter.write("have "+committeePoints.get(i)+" points");
					myWriter.write("\r\n");
				}
			  
				myWriter.close();
				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
			      e.printStackTrace();
			 }	

	}
}
