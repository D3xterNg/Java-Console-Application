package enquiry;

import java.util.ArrayList;
import java.util.Date;

import users.Staff;
import users.Student;
import users.User;
/**
 * represents a enquiry informations
 */
public class Enquiry {
	// ID number of enquiry
	private String enquiryID;
	// UserID that posted enquiry
	private String userID;
	// Name of camp
	private String name;
	// Enquiry
	private String enquiry;
	// Dates of enquiry
	private Date enquiryDate;
	// Whether an enquiry has been processed
	private boolean processedStatus;
	// enquiry or reply (0 - enquiry, 1 - reply)
	private int enquiryOrReply;
	// reply to which enquiry ID
	private String replyTo;

	/**
	 * Constructor of Camp
	 *
	 * @param name user name
	 * @param enquiry enquiry from user
	 * @param enquiryID index of enquiry
	 * @param userID id of user
	 * @param enquiryDate date of enquiry
	 * @param processedStatus current status of enquiry
	 * @param enquiryOrReply reply or to read enquiry
	 * @param replyTo reply to enquiry
	 */
	public Enquiry(String enquiryID, String userID, String name, String enquiry, Date enquiryDate,
				   boolean processedStatus, int enquiryOrReply, String replyTo) {
		this.enquiryID = enquiryID;
		this.userID = userID;
		this.name = name;
		this.enquiry = enquiry;
		this.enquiryDate = enquiryDate;
		this.processedStatus = processedStatus;
		this.enquiryOrReply = enquiryOrReply;
		this.replyTo = replyTo;
	}

	/**
	 * Gets enquiryID
	 *
	 * @return id of enquiry
	 */
	public String getEnquiryID() {
		return this.enquiryID;
	}

	/**
	 * Gets userID
	 *
	 * @return id of user
	 */
	public String getUserID() {
		return this.userID;
	}

	/**
	 * Gets camp's name
	 *
	 * @return camp name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets enquiry
	 *
	 * @return enquiry details
	 */
	public String getEnquiry() {
		return this.enquiry;
	}

	/**
	 * Gets enquiry date
	 *
	 * @return date of enquiry
	 */
	public Date getEnquiryDate() {
		return this.enquiryDate;
	}

	/**
	 * Gets processed status
	 *
	 * @return status of enquiry
	 */
	public boolean getStatus() {
		return this.processedStatus;
	}

	/**
	 * Gets enquiryOrReply
	 *
	 * @return enquiry or replied enquiry
	 */
	public int getEnquiryOrReply() {
		return this.enquiryOrReply;
	}

	/**
	 * Gets replyTo
	 *
	 * @return reply to enquiry.
	 */
	public String getReply() {
		return this.replyTo;
	}



	/**
	 * Sets userID
	 * @param userID user id
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * Sets camp's name
	 * @param name camp name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * sets enquiry
	 * @param enquiry enquiry from user
	 */
	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	/**
	 * sets enquiry date
	 * @param enquiryDate date of enquiry
	 */
	public void setEnquiryDate(Date enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	/**
	 * sets processed status
	 *  @param processedStatus process status
	 */
	public void setStatus(boolean processedStatus) {
		this.processedStatus = processedStatus;
	}

	/**
	 * sets enquiryOrReply
	 * @param enquiryOrReply to enquire or reply
	 */
	public void setEnquiryOrReply(int enquiryOrReply) {
		this.enquiryOrReply = enquiryOrReply;
	}

	/**
	 * sets replyTo
	 * @param replyTo to reply to
	 */
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

}
