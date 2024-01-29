package excel_utils;

import camps.CampList;

/**
 * The ICampData interface defines methods for handling camp-related data.
 * Classes implementing this interface are expected to provide functionality
 * for reading, writing, and manipulating camp data.
 */

public interface ICampData {
	// method signatures go here
	/**
	 * Save the camp data to a storage medium.
	 *
	 * @param campList The CampList containing the camp data to be saved.
	 */
	public void save(CampList campList);
	/**
	 * Load camp data from a storage medium and populate the provided CampList.
	 *
	 * @param campList The CampList to be populated with the loaded data.
	 */
	public void load(CampList campList);
}
