package pages;

import java.util.Scanner;

import camps.CampList;
import campfunctions.*;
import excel_utils.CampExcelData;
import suggestion_enquriy_utils.ViewStaffEnquiries;
import users.Staff;
import report.EnquiryReport;
import report.Reports;
import report.PerfReport;
import suggestion_enquriy_utils.ViewStaffSuggestion;

/**
 * Menu for staff users
 */
public class StaffMenu extends Page{
	/** 
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Staff that logged in
     */
    private Staff staff;
    /**
     * List of camps
     */
    private CampList campList;
    
    /**
     * Constructor of StaffMain
     * @param earlierPg gets previous page
     * @param staff gets staff info
     */
    public StaffMenu(Page earlierPg, Staff staff){
        super(earlierPg);
        this.staff = staff;
    }    
    /**
     * Main executable of StaffMain
     * Menu for staff to choose their options
     * @return Page of staff choice
     */
    public Page startApp(){
    	//load campList and firstly create bare camplist
    	campList = new CampList();
    	staff.setCampDataController(new CampExcelData());
    	staff.getCampDataController().load(campList);
    	
    	//print UI
        System.out.println("============================================================");
        System.out.println("                        Staff Menu                          ");
        System.out.println("============================================================");
        System.out.println("1: Reset your password                                     ");
        System.out.println("2: Create Camp                                             ");
        System.out.println("3: Edit Camp                                               ");
        System.out.println("4: Delete Camp                                             ");
        System.out.println("5: Toggle Visibility of Camp                               ");
        System.out.println("6: View All Camps                                          ");
        System.out.println("7: View My Camps                                           ");
        System.out.println("8: View List of Students in a Camp                         ");
        System.out.println("9: View Enquiries from Students                            ");
        System.out.println("10: View/Approve Suggestions to Changes to Camp Details    ");
        System.out.println("11: Generate Report for Camp                               ");
        System.out.println("12: Generate Performance Report of Camp Committee Members  ");
        System.out.println("13: Generate Enquiry Report                                ");
        System.out.println("14: Log-out                                                ");
        System.out.println("============================================================");


        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for a valid input
        while (!(optionstr.matches("^(1[0-4]|[1-9])$"))) {
            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){
            case 1:
                return new SetPassword(this, this.staff.getEntryNumber(), "2", false);
            case 2:
            	staff.setModifyCamp(new CreateCamp(staff, campList));
            	staff.getModifyCamp().modifyCampInfo();
            	break;
            case 3:
            	staff.setModifyCamp(new EditCamp(staff, campList));
            	staff.getModifyCamp().modifyCampInfo();
            	break;
            case 4:
            	staff.setModifyCamp(new DeleteCamp(staff, campList));
            	staff.getModifyCamp().modifyCampInfo();
            	break;
            case 5:
            	staff.setModifyCamp(new ToggleVisibility(staff, campList));
            	staff.getModifyCamp().modifyCampInfo();
            	break;
            case 6:
            	staff.setData(new ViewCampStaff());
            	staff.getData().Data(staff, campList);
            	break;
            case 7:
            	staff.setData(new StaffCreatedCamp());
            	staff.getData().Data(staff, campList);
            	break;
            case 8:
            	staff.setData(new StudentRegistered());
            	staff.getData().Data(staff, campList);
            	break;
            case 9:
            	staff.setData(new ViewStaffEnquiries());
            	staff.getData().Data(staff, campList);
            	break;    
            case 10:
            	staff.setData(new ViewStaffSuggestion());
            	staff.getData().Data(staff, campList);
            	break;
            case 11: 
            	staff.setReportGenerator(new Reports());
            	staff.getReportGenerator().generate(staff, campList);
            	break;
            case 12:
            	staff.setReportGenerator(new PerfReport());
            	staff.getReportGenerator().generate(staff, campList);
            	break;  
            case 13:
            	staff.setReportGenerator(new EnquiryReport());
            	staff.getReportGenerator().generate(staff, campList);
            	break;
            case 14:
            	return this.getEarlierPg().getEarlierPg();
        }
        return this;
    }
}

