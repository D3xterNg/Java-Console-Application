package utils;

import camps.CampList;
import users.User;
/**
 * Interface for viewing data
 */
public interface IViewData {
	/**
	 * Abstract
	 * @param user user
	 * @param campList list of camps
	 */
	public void Data(User user, CampList campList);
}
