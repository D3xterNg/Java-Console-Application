package suggestion_enquriy_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import camps.CampList;
import excel_utils.EnquiryExcelData;
import enquiry.Enquiry;
import enquiry.EnquiryList;
import users.Student;
import users.User;
import utils.IViewData;
/**
 * ViewStudentEnquiries class implements the IviewData interface for handling camp data.
 * This class provides methods to view student enquiries.
 */
public class ViewStudentEnquiries implements IViewData {

	/**
	 * This method pulls up the enquiries the student has made. He/she can see the status,
	 * response if there is one, and delete his/her enquiry
	 * @param user user
	 * @param campList list of camps
	 */
	@Override
	public void Data(User user, CampList campList) {

		//initialize everything
		EnquiryList enquiryList = new EnquiryList();
		String enquiryID, userID, campName, enquiryQuestion, replyTo;
		boolean processedStatus;
		Date enquiryDate;
		int enquiryOrReply;
		Student student  = (Student) user;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		userID = student.getUserID().trim().toUpperCase();
		student.setEnquiryDataController(new EnquiryExcelData());
		student.getEnquiryDataController().load(enquiryList);
		Scanner sc = new Scanner(System.in);

		//View as a normal attendee
		System.out.println("\n");
		System.out.println("===============================================================");
		System.out.println("                List of Enquiries Posted: ");
		int count = 1;
		for (int i=0; i<enquiryList.size(); i++) {
			if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
				System.out.println("===============================================================");
				System.out.println(count + ": Camp Name: "+enquiryList.get(i).getName() + " | Date: "
						+ dateFormat.format(enquiryList.get(i).getEnquiryDate())
						+ " | Status: " + (enquiryList.get(i).getStatus() ? "Processed" : "Pending"));
				count++;
			}
		}
		System.out.println("===============================================================");

		while (true) {
			System.out.println("1: View Enquiry");
			System.out.println("2: Edit Enquiry");
			System.out.println("3: Delete Enquiry");
			System.out.println("4: Back");
			System.out.print("Select an option: ");
			String optionstr = sc.nextLine().trim();
			System.out.println();
			// get what the user wants to do
			while(!(optionstr.matches("^[1-4]{1}$"))){
				System.out.println("Enter a valid option:");
				optionstr = sc.nextLine().trim();
			}


			int option = Integer.parseInt(optionstr);
			if (option == 4) break;
			switch (option){
				case 1:
					System.out.println("\n");
					System.out.println("===============================================================");
					System.out.println("                List of Enquiries Posted: ");
					count = 1;
					for (int i=0; i<enquiryList.size(); i++) {
						if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
							System.out.println("===============================================================");
							System.out.println(count + ": Camp Name: "+enquiryList.get(i).getName() + " | Date: "
									+ dateFormat.format(enquiryList.get(i).getEnquiryDate())
									+ " | Status: " + (enquiryList.get(i).getStatus() ? "Processed" : "Pending"));
							count++;
						}
					}
					System.out.println("===============================================================");
					System.out.print("Select your enquiry:   ");
					try {
						int enquiryNumber = sc.nextInt();
						sc.nextLine();
						System.out.println();
						if (enquiryNumber > count-1) {
							System.out.println("No Enquiry Found");
							System.out.println();
							continue;
						}
						int tracker1=0, tracker2=0;
						//match the enquiryNumber to the index of the actual enquiry in enquiryList
						for (int i=0; i<enquiryList.size(); i++) {
							if (tracker2 == enquiryNumber) break;
							if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
								tracker2++;
							}
							tracker1++;
						}
						Enquiry enquiry = enquiryList.get(tracker1-1);
						//print all content related to enquiry
						System.out.println("===============================================================");
						System.out.println("Camp Name: "+enquiry.getName() + " | Date: "
								+ dateFormat.format(enquiry.getEnquiryDate())
								+ " | Status: " + (enquiry.getStatus() ? "Processed" : "Pending"));
						System.out.println("===============================================================");
						for(int i=0; i<enquiryList.size();i++) {
							if (enquiryList.get(i).getEnquiryID().equals(enquiry.getEnquiryID()) || enquiryList.get(i).getReply().equals(enquiry.getEnquiryID())) {
								System.out.println(enquiryList.get(i).getUserID() + " (" + dateFormat.format(enquiryList.get(i).getEnquiryDate()) + "): "
										+ enquiryList.get(i).getEnquiry());
							}
						}
						System.out.println("===============================================================");
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					}
					break;
				case 2:
					System.out.println("\n");
					System.out.println("===============================================================");
					System.out.println("                List of Enquiries Posted: ");
					count = 1;
					for (int i=0; i<enquiryList.size(); i++) {
						if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
							System.out.println("===============================================================");
							System.out.println(  count + ": Camp Name: "+enquiryList.get(i).getName() + " | Date: "
									+ dateFormat.format(enquiryList.get(i).getEnquiryDate())
									+ " | Status: " + (enquiryList.get(i).getStatus() ? "Processed" : "Pending"));
							count++;
						}
					}
					System.out.println("===============================================================");


					System.out.print("Select your enquiry:   ");
					try {
						int enquiryNumber = sc.nextInt();
						sc.nextLine();
						System.out.println();
						if (enquiryNumber > count-1) {
							System.out.println("No Enquiry Found");
							System.out.println();
							continue;
						}
						int tracker1=0, tracker2=0;
						//match the enquiryNumber to the index of the actual enquiry in enquiryList
						for (int i=0; i<enquiryList.size(); i++) {
							if (tracker2 == enquiryNumber) break;
							if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
								tracker2++;
							}
							tracker1++;
						}
						Enquiry enquiry = enquiryList.get(tracker1-1);
						if (enquiry.getStatus()) {
							System.out.println("This enquiry has been processed already, you can no longer edit it.");
							break;
						}
						System.out.print("Enter your new enquiry: ");
						String newEnquiry = sc.nextLine();
						enquiry.setEnquiry(newEnquiry);
						student.getEnquiryDataController().save(enquiryList);
						System.out.println("Your enquiry has been successfully updated.");
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. ");
					}
					break;
				case 3:
					System.out.println("\n");
					System.out.println("===============================================================");
					System.out.println("                List of Enquiries Posted: ");
					count = 1;
					for (int i=0; i<enquiryList.size(); i++) {
						if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
							System.out.println("===============================================================");
							System.out.println( count + ": Camp Name: "+enquiryList.get(i).getName() + " | Date: "
									+ dateFormat.format(enquiryList.get(i).getEnquiryDate())
									+ " | Status: " + (enquiryList.get(i).getStatus() ? "Processed" : "Pending"));
							count++;
						}
					}
					System.out.println("===============================================================");
					System.out.print("Select your enquiry:   ");
					try {
						int enquiryNumber = sc.nextInt();
						sc.nextLine();
						System.out.println();
						if (enquiryNumber > count-1) {
							System.out.println("No Enquiry Found");
							System.out.println();
							continue;
						}
						int tracker1=0, tracker2=0;
						//match the enquiryNumber to the index of the actual enquiry in enquiryList
						for (int i=0; i<enquiryList.size(); i++) {
							if (tracker2 == enquiryNumber) break;
							if (enquiryList.get(i).getUserID().equals(userID) && (enquiryList.get(i).getEnquiryOrReply() == 0)) {
								tracker2++;
							}
							tracker1++;
						}
						Enquiry enquiry = enquiryList.get(tracker1-1);
						if (enquiry.getStatus()) {
							System.out.println("This enquiry has been processed already, you can no longer delete it.");
							break;
						}
						enquiryList.remove(tracker1-1);
						student.getEnquiryDataController().save(enquiryList);
						System.out.println("Your enquiry has been deleted successfully.");
					} catch (InputMismatchException e) {
						System.out.println("Invalid input");
					}
					break;
			}
			System.out.println();
		}

	}
	//end of the method
}