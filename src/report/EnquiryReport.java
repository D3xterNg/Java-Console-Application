package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import excel_utils.IEnquiriesData;
import excel_utils.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import users.User;
/**
 * Inquiry report that implements ReportGenerator interface
 */
public class EnquiryReport implements ReportGenerator{
	/**
	 * Generates an inquiry report and writes it in either txt or csv
	 * @param user the user currently logged in
	 * @param campList the list of camps
	 */
	@Override
	public void generate(User user, CampList campList) {
		
		Scanner sc = new Scanner(System.in);
		String filename, filepath;

		IEnquiriesData enquiryData = new EnquiryExcelData();
		EnquiryList enquiryList = new EnquiryList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int count = 0;
		
		enquiryData.load(enquiryList);


        	try {
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
				FileWriter myWriter = new FileWriter(filepath);
	  
				myWriter.write("======Enquiries Report======");
				myWriter.write("\r\n");

				for(int i=0; i<campList.size(); i++) {
					Camp selectedCamp = campList.get(i);
					myWriter.write("\r\n");
					myWriter.write("           "+(i+1)+":"+" Camp Name: "+selectedCamp.getName());
					myWriter.write("\n");
					myWriter.write("===========================================");
					myWriter.write("\r\n");
					count = 0;
					for (int j=0; j<enquiryList.size(); j++) {
						if (selectedCamp.getName().equals(enquiryList.get(j).getName())) {
							Enquiry selectedEnquiry = enquiryList.get(j);
							myWriter.write((count+1)+": "+ selectedEnquiry.getUserID() + " ("+ dateFormat.format(selectedEnquiry.getEnquiryDate()) +"): "+ selectedEnquiry.getEnquiry());
							myWriter.write("\r\n");
							count++;
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