package campfunctions;

import camps.CampList;
/**
 * Class for searching camps in a CampList.
 */
public class SearchCamp {
	/**
	 * Searches for a camp by name in the given CampList.
	 *
	 * @param name camp name
	 * @param campList list of camps
	 * @return The index of the camp in the list if found
	 */
	public static int searchCamp(String name, CampList campList) {

		int campIndex=-1;
		for(int i=0; i<campList.size(); i++) {
			if(campList.get(i).getName().equals(name)) {
				campIndex = i;
				break;
			}
		}
		return campIndex;
	}

}
