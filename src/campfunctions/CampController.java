package campfunctions;

import camps.CampList;
import users.User;
/**
 * An abstract class representing a controller for camp-related operations.
 * Subclasses should provide concrete implementations for specific functionalities.
 */
public abstract class CampController {
	/**
	 * The User associated with the controller.
	 */
	protected User user;
	/**
	 * The CampList associated with the controller.
	 */
	protected CampList campList;
	/**
	 * Constructs a new CampController with the given user and campList.
	 *
	 * @param user     The user associated with the controller.
	 * @param campList The campList associated with the controller.
	 */
	public CampController(User user, CampList campList){
		this.campList = campList;
		this.user = user;
	}
	/**
	 * Abstract method to modify camp information.
	 * Subclasses should provide concrete implementations.
	 */
	public abstract void modifyCampInfo();
	
}
