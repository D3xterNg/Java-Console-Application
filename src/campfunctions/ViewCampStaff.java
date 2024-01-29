package campfunctions;

import java.text.SimpleDateFormat;

import camps.CampList;
import users.Staff;
import users.User;
import utils.IViewData;
/**
 * ViewCampStudent class implements the IViewData interface for handling camp data.
 * This class provides methods to view Staff Camp.
 */
public class ViewCampStaff implements IViewData {

	@Override
	public void Data(User user, CampList campList) {
		Staff staff = (Staff) user;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		int count=1, remainingSlots=0, remainingCommSlots=0;
		System.out.println("\n");
		System.out.println("===========================================================");
		System.out.println("                     List of Camps: ");
		for(int i=0; i<campList.size(); i++) {
			//show the camp and remaining slots
			remainingSlots = campList.get(i).getTotalSlots()-campList.get(i).getStudentIdList().size();
			remainingCommSlots = campList.get(i).getCampCommitteeSlots() - campList.get(i).getCommitteeIdList().size();
			System.out.println("===========================================================");
			System.out.println(count + ": Camp Name: "+campList.get(i).getName()
					+ "\nDate: " + dateFormat.format(campList.get(i).getStartDate()) + " to " + dateFormat.format(campList.get(i).getEndDate())
					+ "\nRegistration Deadline: " + dateFormat.format(campList.get(i).getRegistrationClosingDate())
					+ "\nTotal Slots Remaining (Attendee):"+ remainingSlots + " (Committee Slots): " + remainingCommSlots
					+ "\nLocation: " + campList.get(i).getLocation()
					+ "\nDescription: " + campList.get(i).getDescription()
					+ "\nStaffInCharge: " + campList.get(i).getStaffId());
			count++;
		}
		System.out.println("====================================================================\n");

	}
}
