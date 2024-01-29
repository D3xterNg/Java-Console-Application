package campfunctions;

import java.text.SimpleDateFormat;

import camps.CampList;
import users.Student;
import users.User;
import utils.IViewData;

/**
 * This class implements the functionality for viewing data of registered camp students.
 * It implements the IViewData interface to define the required behavior.
 */
public class ViewRegisteredCampStudent implements IViewData {
	// class implementation goes here
	@Override
	public void Data(User user, CampList campList) {
		
		Student student = (Student) user;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		int count=1, remainingSlots=0, remainingCommSlots=0;
		System.out.println("\n");
		System.out.println("============================================================");
		System.out.println("                     Registered list of Camps: ");
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getStudentIdList().contains(user.getUserID()) || campList.get(i).getCommitteeIdList().contains(user.getUserID())){

				//show the camp and remaining slots
				remainingSlots = campList.get(i).getTotalSlots()-campList.get(i).getStudentIdList().size();
				remainingCommSlots = campList.get(i).getCampCommitteeSlots() - campList.get(i).getCommitteeIdList().size();
				System.out.println("=========================================================");
				System.out.println(count + ": Camp Name: "+campList.get(i).getName()
						+ "\nDate: " + dateFormat.format(campList.get(i).getStartDate()) + " to " + dateFormat.format(campList.get(i).getEndDate())
						+ "\nRegistration Deadline: " + dateFormat.format(campList.get(i).getRegistrationClosingDate())
						+ "\nTotal Slots Remaining (Attendee):"+ remainingSlots + " (Committee Slots): " + remainingCommSlots
						+ "\nLocation: " + campList.get(i).getLocation()
						+ "\nDescription: " + campList.get(i).getDescription()
						+ "\nStaffInCharge: " + campList.get(i).getStaffId());
				count++;
			}

		}
		System.out.println("============================================================\n");

	}
		
}
