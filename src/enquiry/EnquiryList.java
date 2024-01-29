package enquiry;

import java.util.ArrayList;
/**
 * Represents list of enquiries
 */
public class EnquiryList {
	//array of Enquiry object
	private ArrayList<Enquiry> EnquiryList;
	
	/**
	 * Constructor of EnquiryList
	 */
	public EnquiryList() {
		this.EnquiryList = new ArrayList<Enquiry>();
	}
	/**
	 * Accessor of EnquiryList
	 * @return list of enquiry
	 */
	public ArrayList<Enquiry> getEnquiryList(){
		return EnquiryList;
	}
	

	
	/**
	 * Get size of EnquiryList
	 * @return size of enquiry list
	 */
	public int size() {
		return EnquiryList.size();
	}
	
	/**
	 * Get Enquiry object at index i
	 * @param i index of list
	 * @return content of index i
	 */
	public Enquiry get(int i) {
		return EnquiryList.get(i);
	}

	/**
	 * Add method
	 * @param Enquiry enquiry details
	 */
	public void add(Enquiry Enquiry) {
		this.EnquiryList.add(Enquiry);
	}

	/**
	 * Set the ith Enquiry object to the new Enquiry object
	 * @param i index of list
	 * @param Enquiry enquiry details
	 */
	public void set(int i, Enquiry Enquiry) {
		EnquiryList.set(i, Enquiry);
	}
	
	/**
	 * Remove the Enquiry object from EnquiryList
	 * @param i index of list
	 */
	public void remove(int i) {
		EnquiryList.remove(i);
	}
	
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Enquiry Enquiry : EnquiryList) {
	        sb.append(Enquiry.getName()).append(", ");
	    }
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : ""; 
	}
}
