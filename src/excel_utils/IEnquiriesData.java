package excel_utils;

import enquiry.EnquiryList;
/**
 * An interface responsible for handling enquiry-related data.
 *
 */

public interface IEnquiriesData {
	/**
	 * A method to save enquiry
	 * @param enquiryList list of all enquiries
	 */

	public void save(EnquiryList enquiryList);
	/**
	 * A method to load enquiry
	 * @param enquiryList list of all enquiries
	 */

	public void load(EnquiryList enquiryList);

}
