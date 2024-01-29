package suggestion_enquriy_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import camps.CampList;
import excel_utils.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import users.Student;
import users.User;
import utils.IMessages;
/**
 * This class provides functionality to load enquiries.
 * It implements the IMessages interface for enquiries.
 */
public class LoadEnquiries implements IMessages {

	/**
	 * This method is for the student to submit an enquiry message
	 * @param user user
	 * @param campList list of camps
	 */
	@Override
	public void submit(User user, CampList campList) {
		EnquiryList enquiryList = new EnquiryList();;
		String enquiryID, userID, campName, enquiryQuestion, replyTo;
		boolean processedStatus;
		Date enquiryDate;
		int enquiryOrReply;
		
		// if user is not a student
		if(!(user instanceof Student)) {
			System.out.println("You do not have access to submit enquiries.");
		}
		// if user is a student
		else{
			Scanner sc = new Scanner(System.in);
			Student student = (Student) user;
			// get all camp information
	        // get campName
	        System.out.print("Camp you are enquiring about: ");
	        campName = sc.nextLine().trim();
	        userID = user.getUserID().trim().toUpperCase();
	        
	        boolean campFound = false;
	        
			for(int i=0; i<campList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				if(campList.get(i).getName().toUpperCase().equals(campName.toUpperCase()) && campList.get(i).getVisibility().equals(true)
					&& (campList.get(i).getUserGroup().equals(user.getFaculty()) || campList.get(i).getUserGroup().equals("ALL"))) {
                	campFound = true;
                	student.setEnquiryDataController(new EnquiryExcelData());
                	student.getEnquiryDataController().load(enquiryList);

			// new enquiry initialisation
            		// generate a random enquiry ID
            		UUID uuid = UUID.randomUUID();
            		enquiryID = uuid.toString();
            		// get userID
            		userID = user.getUserID().toUpperCase();
            		// set processed status as false
            		processedStatus = false;
            		// set bit - 0 for enquiry, 1 for a reply to the enquiry
            		enquiryOrReply = 0;
            		// first set the default reply to NIL
            		replyTo = "NIL";
        	        // Date the enquiry
        	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        	        enquiryDate = new Date();
        	        // get enquiry message
        	        System.out.print("Type your enquiry message here: ");
        	        enquiryQuestion = sc.nextLine();
        	        enquiryList.add(new Enquiry(enquiryID, userID, campName, enquiryQuestion, enquiryDate, processedStatus, enquiryOrReply, replyTo));
        	        student.getEnquiryDataController().save(enquiryList);
        	        System.out.println("Success. Enquiry posted.");
                    break;
				}
			}
            // if the camp being enquired cannot be found:
            if (!campFound) {
            	System.out.println("Invalid camp.");
            }
		} 
		
	}
}
