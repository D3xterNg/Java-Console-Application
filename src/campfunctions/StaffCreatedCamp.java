package campfunctions;

import java.text.SimpleDateFormat;

import camps.CampList;
import users.Staff;
import users.User;
import utils.IViewData;
/**
 * The StaffCreatedCamp class represents a functionality to view camps created by staff
 * It implements the IViewData interface and includes methods to retrieve and display information about the camps.
 */
public class StaffCreatedCamp implements IViewData {

	@Override
	public void Data(User user, CampList campList) {
		
		Staff staff = (Staff) user;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		int count=1, remainingSlots=0, remainingCommSlots=0;
		System.out.println("\n");
		System.out.println("=====================================================");
		System.out.println("                     List of Camps: ");
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getStaffId().contains(user.getUserID())) {
				//show the camp and remaining slots
				remainingSlots = campList.get(i).getTotalSlots() - campList.get(i).getStudentIdList().size();
				remainingCommSlots = campList.get(i).getCampCommitteeSlots() - campList.get(i).getCommitteeIdList().size();
				System.out.println("=====================================================");
				System.out.println(count + ": " + campList.get(i).getName()
						+ "\nDate: " + dateFormat.format(campList.get(i).getStartDate()) + " to " + dateFormat.format(campList.get(i).getEndDate())
						+ "\nRegistration Deadline: " + dateFormat.format(campList.get(i).getRegistrationClosingDate())
						+ "\nTotal Slots Remaining (Attendee) | (Committee Slots): " + remainingSlots + " | " + remainingCommSlots
						+ "\nLocation: " + campList.get(i).getLocation()
						+ "\nDescription: " + campList.get(i).getDescription()
						+ "\nStaffInCharge: " + campList.get(i).getStaffId());
				count++;
			}
			System.out.println("=====================================================\n");
		}
	}
}
