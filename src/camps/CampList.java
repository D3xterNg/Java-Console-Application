package camps;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The CampList class represents a list of camps.
 * It provides methods to manage and retrieve information about camps.
 */
public class CampList {
	/**
	 * Represents a list of camps.
	 * This class provides methods for managing a list of camps.
	 */
	private ArrayList<Camp> campList;

	/**
	 * Constructor of CampList
	 */
	public CampList() {

		this.campList = new ArrayList<Camp>();
   	}
	/**
	 * Accessor of CampList
	 * @return ArrayList describing camp
	 */
	public ArrayList<Camp> getCampList(){

		return campList;
	}



	/**
	 * Get size of campList
	 * @return size of camp.
	 */
	public int size() {

		return campList.size();
	}

	/**
	 * Get camp object at index i
	 * @param i index in camp list.
	 * @return index i in camp list.
	 */
	public Camp get(int i) {

		return campList.get(i);
	}

	/**
	 * Set the ith camp object to the new camp object
	 * @param i index in camp list.
	 * @param camp Camp to be accessed.
	 */
	public void set(int i, Camp camp) {

		campList.set(i, camp);
	}
	/**
	 * Add method for camp
	 * @param camp name of camp
	 */
	public void add(Camp camp) {

		this.campList.add(camp);
	}
	/**
	 * Removes a camp from the list at the specified index.
	 * @param i The index of the camp to be removed.
	 *
	 */
	public void remove(int i) {

		campList.remove(i);
	}
	/**
	 * Add comma to camp names
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Camp camp : campList) {
			sb.append(camp.getName()).append(", ");
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
	}
	/**
	 * Sort the campList by camp's name
	 */
	public void sort() {

		Collections.sort(campList, Camp.CampNameComparator);
	}

}
