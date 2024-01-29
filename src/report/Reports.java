package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import users.User;
/**
 * Members report that implements ReportGenerator interface
 */
public class Reports implements ReportGenerator{

	/**
	 * @param user the user generating the report
	 * @param campList the list of camps
	 * Generates the IDS of users in each camp in either txt or csv
	 */
	@Override
	public void generate(User user, CampList campList) {
		
		Scanner sc = new Scanner(System.in);
		String filename, filepath;


		
		boolean AttendeeList = false;
		boolean CommitteeList = false;
		boolean genAll = false;

		System.out.println("=====================================");
		System.out.println("[1] Generate Committee  List");
		System.out.println("[2] Generate Attendee  List");
		System.out.println("[3] Generate All");
		System.out.println("=====================================");
		System.out.print("Select an option: ");
        String optionstr = sc.nextLine().trim();
		
        while(!(optionstr.matches("^[1-3]{1}$"))){
        	System.out.println("Enter a valid option:");
        	optionstr = sc.nextLine().trim();
        }
        
        int option = Integer.parseInt(optionstr);
        switch (option){
        	case 1: CommitteeList = true;
        			break;
        	case 2:
					AttendeeList = true;
        			break;
        	case 3: AttendeeList = true;
        			CommitteeList = true;
        			break;
        }

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
	  		      System.out.println("An error occurred.");
	  		      e.printStackTrace();
	  		    }
	        }
	        
			try {
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("=====Members Report=====");
				myWriter.write("\r\n");


				for(int i=0; i<campList.size(); i++) {

					Camp selectedCamp = campList.get(i);
					myWriter.write("\r\n");
					myWriter.write((i+1)+": "+selectedCamp.getName());
					myWriter.write("\r\n");

					if(AttendeeList) {
						ArrayList<String> IdList = selectedCamp.getStudentIdList();
						myWriter.write("List of Students: ");
						myWriter.write("\r\n");
						for(int j=0; j<IdList.size(); j++) {
							myWriter.write((j+1)+": "+ IdList.get(j));
							myWriter.write("\r\n");
						}
					}
					if(CommitteeList) {
						ArrayList<String> IdList = selectedCamp.getCommitteeIdList();
						myWriter.write("List of Committees: ");
						myWriter.write("\r\n");
						for(int j=0; j<IdList.size(); j++) {
							myWriter.write((j+1)+": "+ IdList.get(j));
							myWriter.write("\r\n");
						}
					}
				}
							
				myWriter.close();
				System.out.println("File written Success");
			} catch (IOException e) {
			      e.printStackTrace();
			 }	

	}
}