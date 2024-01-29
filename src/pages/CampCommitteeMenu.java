package pages;

import java.util.Scanner;

import camps.CampList;
import campfunctions.ViewCampCommittee;
import campfunctions.StudentRegistered;
import excel_utils.CampExcelData;
import suggestion_enquriy_utils.ViewComEnquiries;
import users.Student;
import report.Reports;
import suggestion_enquriy_utils.LoadSuggestion;
import suggestion_enquriy_utils.ViewStudentSuggestion;
/**
 * The class for the Camp Committee Menu that Camp Committee Members can access
 */
public class CampCommitteeMenu extends Page {
	/**
	 * Scanner object for taking input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * The student accessing CampCommitteeMain
     */
    Student student;
    /**
     * List of available camps
     */
    CampList campList;
    /**
     * Constructor of CampCommitteeMain
     * @param earlierPg the previous page
     * @param student the student accessing CampCommitteeMain
     */
    public CampCommitteeMenu(Page earlierPg, Student student){
        super(earlierPg);
        this.student = student;
    }

	/**
	 * Displays the camp committee menu
	 * Provides multiple options for camp committee members
	 * @return Page to the choosen option based on user input
	 */
    public Page startApp(){
    	
    	
    	campList = new CampList();
    	student.setCampData(new CampExcelData());
    	student.getCampData().load(campList);

        System.out.println("============================================================");
        System.out.println("                   Camp Committee Menu                      ");
        System.out.println("============================================================");;
        System.out.println("1: View Camps Registered                                 ");
        System.out.println("2: View List of Students in a Camp                       ");
        System.out.println("3: Submit suggestions to camp detail                      ");
        System.out.println("4: View/Edit/Delete Pending Suggestions                  ");
        System.out.println("5: View Enquiries from Attendees                         ");
        System.out.println("6: Generate Report for Camp                              ");
        System.out.println("7: Student Menu                                  ");
        System.out.println("============================================================");

        
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine().trim();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-7]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine().trim();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            case 1:
            	student.setData(new ViewCampCommittee());
            	student.getData().Data(student, campList);
            	break;
            case 2:
            	student.setData(new StudentRegistered());
            	student.getData().Data(student, campList);
            	break;
            case 3:
            	student.setMessages(new LoadSuggestion());
            	student.getMessages().submit(student, campList);
            	break;
            case 4:
            	student.setData(new ViewStudentSuggestion());
            	student.getData().Data(student, campList);
            	break;
            case 5:
            	student.setData(new ViewComEnquiries());
            	student.getData().Data(student, campList);
            	break;
            case 6:
        		student.setReportGenerator(new Reports());
        		student.getReportGenerator().generate(student, campList);
        		break;
            case 7:
            	return this.getEarlierPg();
        }

        return this;
    }
}
