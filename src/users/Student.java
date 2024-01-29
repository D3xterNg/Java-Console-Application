package users;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import camps.CampList;
import campfunctions.IEnrolls;
import excel_utils.ICampData;
import excel_utils.IEnquiriesData;
import excel_utils.ISuggestionData;
import report.ReportGenerator;
import utils.IViewData;
import utils.IMessages;
/**
 * The Student class represents a student user
 * It extends the User class and includes additional attributes and methods specific to students.
 */
public class Student extends User {
	
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
     * A class to allow students to register for a camp.
     */
	private IEnrolls enrollmentController;
	
    /**
     * A class to allow students to upload enquiries.
     */
	private IMessages Messages;
	
    /**
     * A class to allow Camp Committee to generate report.
     */
    private ReportGenerator generator;
	
    /**
     * Whether the Student is a camp committee member.
     */
	private boolean isCampCommittee;
	
    /**
     * The committee points of the Student.
     */
	private int committeePoints;
	
    /**
     * The camp list that is visible to this Student.
     */
	private CampList studentCampList;
	
	/**
	 * Constructor of student
     * @param name This user's name
     * @param faculty This user's faculty
     * @param email This user's email
     * @param entryNumber This user's entry number in the excel sheet provided
	 * @param committeePoints This user's committee points
	 */
	public Student(String name, String faculty, String email, int entryNumber, int committeePoints) {
        super(name, faculty, email, entryNumber);
        this.userType = 1;
        this.isCampCommittee = false;
        this.studentCampList = new CampList();
        this.committeePoints = committeePoints;
    }
	
    /**
     * Gets the class to access the data of the camps.
     * @return the class that provides access to camps' data.
     */
	public ICampData getCampData() {
		return campData;
	}
	
	
    /**
     * Gets the class to access the data of the suggestions.
     * @return the class that provides access to suggestions' data.
     */
	public ISuggestionData getSuggestionDataController() {
		return suggestionData;
	}
	
    /**
     * Gets the class to access the data of the enquiries.
     * @return the class that provides access to enquiries' data.
     */
	public IEnquiriesData getEnquiryDataController() {
		return enquiryData;
	}
	
    /**
     * Gets class to access the viewing functions.
     * @return the class that provides access to viewing functions. 
     */
	public IViewData getData() {
		return dataviewer;
	}
	
    /**
     * Gets class to access the enrollment functions.
     * @return the class that provides access to enrollment functions.
     */
	public IEnrolls getEnroll() {
		return this.enrollmentController;
	}
	
    /**
     * Gets class to access the functions to upload enquiries.
     * @return the class that provides access to function for uploading of enquiries.
     */
	public IMessages getMessages() {
		return this.Messages;
	}
	
    /**
     * Gets class to access the functions to generate a report (for Camp Committee).
     * @return the class that provides access to functions for generating a report (for Camp Committee)
     */
	public ReportGenerator getReportGenerator() {
		return this.generator;
	}
	
    /**
     * Sets the class that provides data of the camps.
     * @param campData A class that allows access to the camps' data.
     */
	public void setCampData(ICampData campData) {
		this.campData = campData;
	}
	
    /**
     * Sets the class that provides data of the suggestions.
     * @param suggestionData A class that allows access to the suggestions' data.
     */
	public void setSuggestionDataController(ISuggestionData suggestionData) {
		this.suggestionData = suggestionData;
	}
	
    /**
     * Sets the class that provides data of the enquiries.
     * @param enquiryData A class that allows access to the enquiries' data.
     */
	public void setEnquiryDataController(IEnquiriesData enquiryData) {
		this.enquiryData = enquiryData;
	}
	
    /**
     * Sets the class that provides the function to view data.
     * @param dataviewer A class that provides the function to view data.
     */
	public void setData(IViewData dataviewer) {
		this.dataviewer = dataviewer;
	}
	
    /**
     * Sets the class that provides the function to register for a camp.
     * @param enrollmentController A class that provides the function to register for a camp.
     */
	public void setEnroll(IEnrolls enrollmentController) {

		this.enrollmentController = enrollmentController;
	}
	
    /**
     * Sets the class that provides the function to upload enquiries about a camp.
     * @param messages A class that provides the function to upload enquiries about a camp.
     */
	public void setMessages(IMessages messages) {
		this.Messages = messages;
	}
	
    /**
     * Sets the class that provides the function to generate a report about the camp.
     * @param generator	generator object
     */
	public void setReportGenerator(ReportGenerator generator) {
		this.generator = generator;
	}
	
    /**
     * Gets whether the Student is a camp committee or not.
     * @return the status of whether the student is a camp committee member.
     */
	public Boolean getIsCampCommittee() {
		return this.isCampCommittee;
	}
	
    /**
     * Sets the status of whether the student is a camp committee or not.
     * @param isCampCommittee This student's status as either an ordinary attendee or a camp committee member.
     */
	public void setCampCommittee(Boolean isCampCommittee) {
		this.isCampCommittee =  isCampCommittee;
	}
	
    /**
     * Gets the camp list.
     * @return the camp list.
     */
	public CampList getStudentCampList() {
		return this.studentCampList;
	}
	
    /**
     * Sets the status of the student to a mere attendee.
     */
    public void withdrawCommitteeMember() {
		this.isCampCommittee = false;
	}

	/**
	 * Gets committee points
	 * @return this student's camp committee points.
	 */
	public int getCommitteePoints() {
		return committeePoints;
	}
	
	/**
	 * Set committee points
	 * @param committeePoints the new committee points to be set to.
	 */
	public void setCommitteePoints(int committeePoints) {
		this.committeePoints = committeePoints;
    	try {
        	//WRITE TO EXCEL HERE!
        	Workbook workbook = WorkbookFactory.create(new FileInputStream("src/excel/student_list.xlsx"));
        	Sheet sheet = workbook.getSheetAt(0);
        	Row row = sheet.getRow(entryNumber);

        	if (row == null) {
        	    // If the row doesn't exist, create it
        	    row = sheet.createRow(entryNumber);
        	}

        	int columnIndex = 4; // Column 4

        	// Access the cell at the specified column index
        	Cell cell = row.createCell(columnIndex, CellType.STRING); // Create the cell if it doesn't exist

        	// Set the value of the cell with the new password
        	cell.setCellValue(committeePoints);

        	// Now, you can save the changes back to the Excel file
        	try (FileOutputStream fos = new FileOutputStream("src/excel/student_list.xlsx")) {
        	    workbook.write(fos);
        	}

        	// Close the workbook when done
        	workbook.close();
    	} catch (IOException IOE) {
        	System.out.println("No such file");
        }
	}
	
	
}


