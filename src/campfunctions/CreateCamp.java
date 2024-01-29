package campfunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import excel_utils.ICampData;
import excel_utils.CampExcelData;
import users.User;
/**
 * A class representing the functionality to create a new camp.
 * Extends CampController to inherit common camp-related operations.
 */

public class CreateCamp extends CampController {
	/**
	 * Constructor of CampCreator
	 */
	private ICampData campData;

    /**
     * Constructs a new CreateCamp instance with the specified user and campList.
     *
     * @param user     The user associated with the CreateCamp instance.
     * @param campList The campList associated with the CreateCamp instance.
     */
	public CreateCamp(User user, CampList campList) {
		super(user, campList);
	}

	// STORE CAMP INFORMATION
    /**
     * The name of the camp.
     */
	private String name;
    /**
     * The user group for this class.
     */
	private String userGroup;
    /**
     * The location of the camp.
     */
	private String location;
    /**
     * Total slots available in the camp.
     */
	private int totalSlots;
    /**
     * Total slots available for camp committee members in camp.
     */
	private int campCommitteeSlots; // maximum 10 ONLY
    /**
     * Description of camp.
     */
	private String description;
    /**
     * StaffId of in-charge of camp
     */
	private String staffId;
    /**
     * Visibility of camp.
     */
    private boolean visibility;
    /**
     * Lists of students in camp.
     */
	private static ArrayList<String> studentIdList;
    /**
     * Lists of committee members in camp.
     */
	private static ArrayList<String> committeeIdList;
    /**
     * List of students who withdrew from camp.
     */
	private static ArrayList<String> withdrawIdList;
	
	@Override
	public void modifyCampInfo() {
		
		Scanner sc = new Scanner(System.in);
        //get campName
        System.out.print("Enter camp name:");
        name = sc.nextLine().trim();
        
        //get camp dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        String datesstr;
        
        //get start date
        while (startDate == null) {
            System.out.println("Enter camp start date (DD/MM/YYYY):");
            datesstr = sc.nextLine().trim();

            try {
                startDate = dateFormat.parse(datesstr);

                // Date valid and is parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. DD/MM/YYYY format.");
            }
            if(startDate.before(new Date())) {
            	System.out.println("Start date should come after current date");
            	startDate = null;
            }   
        }
        //get end date
        Date endDate = null;
        while (endDate == null) {
            System.out.println("Enter camp end date (DD/MM/YYYY):");
            datesstr = sc.nextLine().trim();

            try {
                endDate = dateFormat.parse(datesstr);

                //Date valid and is parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. DD/MM/YYYY format.");
            }
            if(endDate.before(startDate)) {
            	System.out.println("End date should come after start date");
            	endDate = null;
            }   
        }
        //get camp registration closing dates
        String regCloseDateStr;
        Date registrationClosingDate = null;

        while (registrationClosingDate == null) {
            System.out.println("Enter camp registration closing date (DD/MM/YYYY):");
            regCloseDateStr = sc.nextLine().trim();

            try {
                registrationClosingDate = dateFormat.parse(regCloseDateStr);

                // Date is valid and parsed successfully
            } catch (java.text.ParseException e) {
                System.out.println("Invalid date format. DD/MM/YYYY format.");
            }
        }
        //get user group 
        System.out.print("Enter user group this camp is open to:");
        userGroup = sc.nextLine().trim().toUpperCase();
        
        //get location
        System.out.print("Enter camp location:");
        location = sc.nextLine().trim();
        
        //get camp committee slots
        System.out.print("Enter camp committee slots:");
        String slotsstr = sc.nextLine().trim();
        // loop to ask for valid input
        while(!(slotsstr.matches("^[1-9]|10$"))){
            System.out.println("Enter a valid slots (The maximum slot is 10): ");
            slotsstr = sc.nextLine().trim();
        }
        campCommitteeSlots = Integer.parseInt(slotsstr);
        
        //get total slots
        System.out.print("Enter camp total slots:");
        String totalslotsstr = sc.nextLine().trim();
        // loop to ask for valid input
        // receive maximum of 100
        while(!(totalslotsstr.matches("\\b(?:[1-9]|[1-9][0-9]|100)\\b")) || (Integer.parseInt(totalslotsstr)<=campCommitteeSlots)){
        	if(Integer.parseInt(totalslotsstr)<=campCommitteeSlots)
        		System.out.println("Enter a valid slots (The total slots must be more than committee slots):");
        	else if(!(totalslotsstr.matches("\\b(?:[1-9]|[1-9][0-9]|100)\\b"))) 
        		System.out.println("Enter a valid slots (The maximum total slots is 100):");
            totalslotsstr = sc.nextLine().trim();
        }
        totalSlots = Integer.parseInt(totalslotsstr);
       
        //get camp description
        System.out.print("Enter camp description:");
        description = sc.nextLine().trim();
        
        //get id of staff in charge
        staffId = this.user.getUserID();
        
        //get camp visibility
        String visible;
        System.out.print("Enter new camp visibility (on/off):");
        do {
        	visible = sc.nextLine().trim();
        	if(!(visible.equals("on") || (visible.equals("off"))))
        		System.out.print("Please enter valid visibility:");
        }while(!(visible.equals("on") || (visible.equals("off"))));
        if(visible.equals("on")) visibility = true;
        else visibility = false;
        
        //empty initial student and committee list
        studentIdList = new ArrayList<String>();
        committeeIdList = new ArrayList<String>();
        withdrawIdList = new ArrayList<String>();
        
        //Instantiate new camp object and add to campList
        campList.add(new Camp(name, startDate, endDate, registrationClosingDate, userGroup, location, totalSlots, 
        		campCommitteeSlots, description, staffId, visibility, studentIdList, committeeIdList, withdrawIdList));
        campList.sort();
        campData = new CampExcelData();
		campData.save(campList);
        System.out.println("Successfully create camp.");
	}

}
