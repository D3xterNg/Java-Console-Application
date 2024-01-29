package campfunctions;

import camps.CampList;
import users.User;

/**
 * This interface is implemented by WithdrawCamp and RegisterCamp. An application of DIP,
 * instead of introducing direct dependencies to the implementing classes.
 */
public interface IEnrolls {
	/**
	 * This method mainly updates campList depending on whether student withdraws or registers
	 * @param user user information
	 * @param campList list of all existing camps
	 */
	public void Enroll (User user, CampList campList);

}
