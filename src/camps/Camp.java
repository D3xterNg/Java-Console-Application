package camps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
/**
 * Represents a camp entity.
 * This class extends CampInfo and provides additional functionality specific to camps.
 */
public class Camp extends CampInfo{
	private ArrayList<String> studentIdList;
	private ArrayList<String> committeeIdList;
	private ArrayList<String> withdrawIdList;
	private boolean visibility;
	/**
	 * Constructor of Camp
	 * @param name Description of the camp name parameter.
	 * @param startDate; Description of the startDate of camp parameter.
	 * @param endDate; Description of the end date of camp.
	 * @param registrationClosingDate Description of registration closing date for camp.
	 * @param userGroup Description of the faculty parameter.
	 * @param location Description of the location  parameter.
	 * @param totalSlots Description of total number of slots
	 * @param campCommitteeSlots Description of the camp committee slots parameter.
	 * @param description Description of the description parameter.
	 * @param staffId Description of the staffID parameter.
	 * @param visibility Description of the visibility parameter.
	 * @param studentIdList Description of student list of camp.
	 * @param committeeIdList Description of camp committee list of camp.
	 * @param withdrawIdList Description of students withdrawn from camp.
	 */
	public Camp(String name, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffId
			, Boolean visibility, ArrayList<String> studentIdList, ArrayList<String> committeeIdList, ArrayList<String> withdrawIdList) {
		super(name, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, campCommitteeSlots, description, staffId);
		this.visibility = visibility;
		this.studentIdList = studentIdList;
		this.committeeIdList = committeeIdList;
		this.withdrawIdList = withdrawIdList;
	}



	/**
	 * Get the list of committee members.
	 *
	 * @return ArrayList containing committee members.
	 */
	public ArrayList<String> getCommitteeIdList(){

		return this.committeeIdList;
	}


	/**
	 * Get camp visibility
	 * @return visibility of the camp.
	 */
	public Boolean getVisibility() {

		return this.visibility;
	}

	/**
	 * Set camp visibility
	 * @param newVisibility Description of new visibility of camp.
	 */
	public void setVisibility(Boolean newVisibility) {

		this.visibility = newVisibility;
	}
	/**
	 * Gets student list in the camp
	 * @return ArrayList containing students on camp.
	 */
	public ArrayList<String> getStudentIdList(){

		return this.studentIdList;
	}

	/**
	 * Gets student list in the camp
	 * @return ArrayList containing students withdrawn from camp.
	 */
	public ArrayList<String> getWithdrawIdList(){

		return this.withdrawIdList;
	}
	/**
	 * A comparator for comparing camps based on their names.
	 * This comparator is used for sorting camps by name.
	 */
	// Comparing camp names. Assume that names should be distinct. 
    public static Comparator<Camp> CampNameComparator = new Comparator<Camp>() {
 
        // Comparing name, but first set to Upper case for proper comparison
        public int compare(Camp camp1, Camp camp2) {
            String campName1 = camp1.getName().toUpperCase();
            String campName2 = camp2.getName().toUpperCase();
 
            // returns value depending on whether there is a match.
            return campName1.compareTo(campName2);
        }
    };


}
