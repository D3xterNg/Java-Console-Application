package camps;

import java.util.Date;
/**
 * Class contains majority of the information of the camp
 *
 */
public class CampInfo {
	//Name of camp
	private String name;
	//Dates of camp
	private Date startDate;
	private Date endDate;
	//Registration closing date of camp
	private Date registrationClosingDate;
	//School where the camp is open for
	private String userGroup;
	//Location of camp
	private String location;
	//Total Slots
	private int totalSlots;
	//Slots for camp committee (max10)
	private int campCommitteeSlots;
	//Description of camp
	private String description;
	//Staff in charge
	private String staffId;
	
	/**
	 * Constructor of CampInfo
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
	 */
	public CampInfo(String name, Date startDate, Date endDate, Date registrationClosingDate, String userGroup, String location, int totalSlots, int campCommitteeSlots, String description, String staffId) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registrationClosingDate = registrationClosingDate;
		this.userGroup = userGroup;
		this.location = location;
		this.totalSlots = totalSlots;
		this.campCommitteeSlots = campCommitteeSlots;
		this.description = description;
		this.staffId = staffId;
	}
	/**
	 * Gets camp's name
	 * @return camp's name.
	 */
	public String getName() {

		return this.name;
	}
	/**
	 * Gets camp's start date
	 * @return  start date of camp.
	 */
	public Date getStartDate() {

		return this.startDate;
	}
	/**
	 * Gets camp's end date
	 * @return end date
	 */
	public Date getEndDate() {

		return this.endDate;
	}
	/**
	 * Gets camp's registration closing date
	 * @return registration closing date of camp.
	 */
	public Date getRegistrationClosingDate() {

		return this.registrationClosingDate;
	}
	/**
	 * Gets camp's user group
	 * @return user group for camp
	 */
	public String getUserGroup() {

		return this.userGroup;
	}
	/**
	 * Gets camp's location
	 * @return location of camp.
	 */
	public String getLocation() {

		return this.location;
	}
	/**
	 * Gets camp committee slot
	 * @return number of camp committee slots in camp.
	 */
	public int getCampCommitteeSlots() {

		return this.campCommitteeSlots;
	}
	/**
	 * Gets camp's total slots
	 * @return totalslots of camp
	 */
	public int getTotalSlots() {

		return this.totalSlots;
	}

	/**
	 * Gets camp's description
	 * @return description of camp
	 */
	public String getDescription() {

		return this.description;
	}
	/** 
	 * Get camp's staff in charge
	 * @return StaffID for Staff in charge of camp.
	 */
	public String getStaffId() {

		return this.staffId;
	}
	
	/**
	 * Sets camp's name
	 * @param newName new camp name.
	 */
	public void setName(String newName) {

		this.name = newName;
	}
	/**
	 * Sets camp's date
	 * @param newDate Start date of camp.
	 */
	public void setStartDate(Date newDate) {

		this.startDate = newDate;
	}
	/**
	 * Sets camp's date
	 * @param newDate End date of camp.
	 */
	public void setEndDate(Date newDate) {

		this.endDate = newDate;
	}
	/**
	 * Sets camp's registration closing date
	 * @param newRegistrationClosingDate Registration closing date for camp.
	 */
	public void setRegistrationClosingDate(Date newRegistrationClosingDate) {
		this.registrationClosingDate = newRegistrationClosingDate;
	}
	/**
	 * Sets camp's user group
	 * @param newUserGroup Camp user group.
	 */
	public void setUserGroup(String newUserGroup) {

		this.userGroup = newUserGroup;
	}

	/**
	 * Sets camp's location
	 * @param newLocation new location of camp.
	 */
	public void setLocation(String newLocation) {

		this.location = newLocation;
	}
	/**
	 * Sets camp's total slots
	 * @param newTotalSlots total slots in camp.
	 */
	public void setTotalSlots(int newTotalSlots) {

		this.totalSlots = newTotalSlots;
	}
	/**
	 * Sets camp's description
	 * @param newDescription description of camp.
	 */
	public void setDescription(String newDescription) {

		this.description = newDescription;
	}
	/**
	 * Sets camp committee slot
	 * @param newCampCommitteeSlots new camp committee slots.
	 */
	public void setCampCommitteeSlots(int newCampCommitteeSlots) {

		this.campCommitteeSlots = newCampCommitteeSlots;
	}
}
