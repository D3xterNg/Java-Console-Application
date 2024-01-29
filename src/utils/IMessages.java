package utils;

import camps.CampList;
import users.User;
/**
 * Interface for submitting enquiries/messages in general.
 */
public interface IMessages {
	/**
	 * Abstract
	 * @param user user
	 * @param campList list of camps
	 */
	public void submit(User user, CampList campList);
}
