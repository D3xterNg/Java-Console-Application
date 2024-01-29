package suggestion;


import java.util.Date;

/**
 * Class handles suggestions submitted by camp committee members
 */
public class Suggestion {
	/**
	 * ID of suggestion
	 */
	private String suggestionID;
	/**
	 * ID of user that gave the suggestion
	 */
	private String userID;
	/**
	 * Name of camp
	 */
	private String name;
	/**
	 * Suggestion
	 */
	private String suggestion;
	/**
	 * Date of suggestion
	 */
	private Date suggestionDate;
	/**
	 * Whether suggestion has been processed
	 */
	private boolean processedStatus;
	/**
	 * Reply to suggestion
	 */
	private String approvedOrDeclined;


	
	
	/**
	 * Constructor of Camp
	 * @param suggestionID ID of suggestion
	 * @param userID ID of user making the suggestion
	 * @param name Name of student
	 * @param suggestion suggestion being made
	 * @param suggestionDate date of suggestion
	 * @param processedStatus status of enquiry: Processed or Pending
	 * @param approvedOrDeclined status of suggestion: Approved or Declined.
	 *
	 */
	public Suggestion(String suggestionID, String userID, String name, String suggestion, Date suggestionDate, boolean processedStatus, String approvedOrDeclined) {
		this.suggestionID = suggestionID;
		this.userID = userID;
		this.name = name;
		this.suggestion = suggestion;
		this.suggestionDate = suggestionDate;
		this.processedStatus = processedStatus;
		this.approvedOrDeclined = approvedOrDeclined;
	}
	
	
	/**
	 * Gets suggestionID
	 * @return String
	 */
	public String getSuggestionID() {
		return this.suggestionID;
	}
	/**
	 * Gets userID
	 * @return String
	 */
	public String getUserID() {
		return this.userID;
	}
	/**
	 * Gets camp's name
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets suggestion
	 * @return suggestion.
	 */
	public String getSuggestion() {
		return this.suggestion;
	}
	/**
	 * Gets suggestion date
	 * @return date of suggestion.
	 */
	public Date getSuggestionDate() {
		return this.suggestionDate;
	}
	/**
	 * Gets processed status
	 * @return status of suggestion.
	 */
	public boolean getProcessedStatus() {
		return this.processedStatus;
	}
	/**
	 * Gets approvedOrDeclined
	 * @return result of suggestion.
	 */
	public String getApprovedOrDeclined() {
		return this.approvedOrDeclined;
	}

	

	/**
	 * Sets suggestionID
 	 * @param suggestionID ID of suggestion
	 */
	public void setSuggestionID(String suggestionID) {
		this.suggestionID = suggestionID;
	}
	/**
	 * Sets userID
	 * @param userID ID of user
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * Sets camp's name
	 * @param name name of user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * sets suggestion
	 * @param suggestion suggestion from user
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	/**
	 * sets suggestion date
	 * @param suggestionDate take in suggestion date
	 */
	public void setSuggestionDate(Date suggestionDate) {
		this.suggestionDate = suggestionDate;
	}
	/**
	 * sets processed status
	 * @param processedStatus  status of suggestion
	 */
	public void setProcessedStatus(boolean processedStatus) {
		this.processedStatus = processedStatus;
	}
	/**
	 * sets approvedOrDeclined
	 * @param approvedOrDeclined  check if its approved or declined
	 */
	public void setApprovedOrDeclined(String approvedOrDeclined) {
		this.approvedOrDeclined = approvedOrDeclined;
	}

}
