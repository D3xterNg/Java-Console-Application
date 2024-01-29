package users;

import campfunctions.CampController;
import excel_utils.ICampData;
import excel_utils.IEnquiriesData;
import excel_utils.ISuggestionData;
import report.ReportGenerator;
import utils.IViewData;
/**
 * The Staff class represents a staff user
 * It extends the User class and includes additional attributes and methods specific to students.
 */
public class Staff extends User {
	
    /**
     * A class to allow staff to make modifications to the camp.
     */
	private CampController modifyCamp;
    
    /**
     * A class to load and save camp's data.
     */
	private ICampData campData;
	
    /**
     * A class to load and save suggestion data.
     */
	private ISuggestionData suggestionData;
	
    /**
     * A class to load and save enquiry data.
     */
	private IEnquiriesData enquiryData;
	
    /**
     * A class to allow the viewing of any data.
     */
	private IViewData dataviewer;

	
    /**
     * A class to allow Camp Committee to generate report.
     */
    private ReportGenerator generator;

	
	/**
	 * Constructor of staff
     * @param name This user's name
     * @param faculty This user's faculty
     * @param email user's email
     * @param entryNumber This user's entry number in the excel sheet provided
	 */
	public Staff(String name, String faculty, String email, int entryNumber) {
        super(name, faculty, email, entryNumber);
        this.userType = 2;
    }
	
	
	
    /**
     * Gets the class to access the data of the camps.
     * @return the class that provides access to camps' data.
     */
	public ICampData getCampDataController() {

		return campData;
	}
	
    /**
     * Gets the class to access the data of the suggestions.
     * @return the class that provides access to suggestions' data.
     */
	public ISuggestionData getSuggestionDataManager() {

		return suggestionData;
	}
	
    /**
     * Gets the class to access the data of the enquiries.
     * @return the class that provides access to enquiries' data.
     */
	public IEnquiriesData getEnquiryDataManager() {

		return enquiryData;
	}
	
    /**
     * Gets class to access the viewing functions.
     * @return the class that provides access to viewing functions. 
     */
	public IViewData getData () {
		return dataviewer;
	}
	
    /**
     * Gets class to access the functions to generate a report (for Camp Committee).
     *  the class that provides access to functions for generating a report (for Camp Committee)
	 * @return the report generated
     */
	public ReportGenerator getReportGenerator() {
		return this.generator;
	}

	/**
	 * Gets the CampController for modifying a camp.
	 * @return The CampController for modifying a camp.
	 */
	public CampController getModifyCamp() {

		return modifyCamp;
	}
	
    /**
     * Sets the class that provides data of the camps.
     * @param campData A class that allows access to the camps' data.
     */
	public void setCampDataController(ICampData campData) {

		this.campData = campData;
	}
	
    /**
     * Sets the class that provides data of the suggestions.
     * @param suggestionData A class that allows access to the suggestions' data.
     */
	public void setSuggestionDataManager(ISuggestionData suggestionData) {

		this.suggestionData = suggestionData;
	}
	
    /**
     * Sets the class that provides data of the enquiries.
     * @param enquiryData A class that allows access to the enquiries' data.
     */
	public void setEnquiryDataManager(IEnquiriesData enquiryData) {

		this.enquiryData = enquiryData;
	}
	
    /**
     * Sets the class that provides the function to view data.
     * @param dataviewer A class that provides the function to view data.
     */
	public void setData (IViewData dataviewer) {
		this.dataviewer = dataviewer;
	}
	
    /**
     * Sets the class that provides the function to generate a report about the camp.
     * @param generator A class that provides the function to generate a report about the camp.
     */
	public void setReportGenerator(ReportGenerator generator) {
		this.generator = generator;
	}
		
    /**
     * Sets the class that provides the function to make modifications to camp information.
     * @param modifyCamp A class that provides the function to make modifications to camp information.
     */
	public void setModifyCamp(CampController modifyCamp) {
		this.modifyCamp = modifyCamp;
	}


}


