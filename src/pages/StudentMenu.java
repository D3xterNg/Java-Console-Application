package pages;

import java.util.Scanner;

import camps.CampList;
import campfunctions.RegisterCamp;
import campfunctions.ViewCampStudent;
import campfunctions.ViewRegisteredCampStudent;
import campfunctions.WithdrawCamp;
import excel_utils.CampExcelData;
import suggestion_enquriy_utils.LoadEnquiries;
import suggestion_enquriy_utils.ViewStudentEnquiries;
import users.Student;
import users.User;
/**
 * Menu for student users
 */
public class StudentMenu extends Page{
	/**
	 * Scanner object for user input
	 */
    Scanner sc = new Scanner(System.in);
    /**
     * Student that logged in
     */
    private Student student;
    /**
     * List of camps
     */
    private CampList campList;
   
    /**
     * Constructor of StudentMain
     * @param earlierPg go to previous page
     * @param student get the student info
     *
     */
    public StudentMenu(Page earlierPg, Student student){
        super(earlierPg);
        this.student = student;
    }

    /**
     * Main executable of the page
     * Menu for student to choose their options
     * @return Page of student choice
     */
    public Page startApp(){
    	//load campList
    	campList = new CampList();
    	student.setCampData(new CampExcelData());
    	student.getCampData().load(campList);
    	student.setCampCommittee(StudentMenu.getCommitteeStatus(student, campList));
    	
    	//print UI
        System.out.println("============================================================");
        System.out.println("                      Student Menu                          ");
        System.out.println("============================================================");
        System.out.println("1: Reset your password                                     ");
        System.out.println("2: View All Camps                                          ");
        System.out.println("3: Register for a Camp                                     ");
        System.out.println("4: View Registered Camp                                    ");
        System.out.println("5: Submit Enquiries regarding a Camp                       ");
        System.out.println("6: View/Edit/Delete Enquiries                              ");
        System.out.println("7: Withdraw from Camp                                      ");
        System.out.println("8: Enter Camp Committee Menu (Only for Committee Members)  ");
        System.out.println("9: Log-out                                                 ");
        System.out.println("============================================================");

        // get option
        System.out.print("Enter your option: ");
        String optionstr = sc.nextLine();
        System.out.println();

        // loop to ask for valid input
        while(!(optionstr.matches("^[1-9]{1}$"))){

            System.out.println("Enter a valid option:");
            optionstr = sc.nextLine();
        }

        int option = Integer.parseInt(optionstr);

        switch (option){

            // SetPassword Page
            case 1:
                return new SetPassword(this, this.student.getEntryNumber(), "1", false);
            case 2: 
            	student.setData(new ViewCampStudent());
            	student.getData().Data(student, campList);
            	break;
            case 3:
            	student.setEnroll(new RegisterCamp());
                student.getEnroll().Enroll(student, campList);
            	break;
            case 4://viewcamp
                student.setData(new ViewRegisteredCampStudent());
                student.getData().Data(student, campList);

            	break;
            case 5: //submitenq
                student.setMessages(new LoadEnquiries());
                student.getMessages().submit(student, campList);

            	break;
            case 6://view enq
                student.setData(new ViewStudentEnquiries());
                student.getData().Data(student, campList);
            	break;    
            case 7: //withdraw camp
            	student.setEnroll(new WithdrawCamp());
            	student.getEnroll().Enroll(student, campList);
            	break;
            case 8:
            	if(student.getIsCampCommittee()==true) return new CampCommitteeMenu(this, this.student);
            	else{
            		System.out.println("You are not a camp committee!");
            		break;
            	}
            case 9:
            	return this.getEarlierPg().getEarlierPg();
        }

        return this;
    }
    
    private static boolean getCommitteeStatus(User user, CampList campList) {
    	
    	String studentId = user.getUserID();
    	
    	for (int i = 0; i < campList.size(); i++) {
			//check if the student is in the camp committee
			if (campList.get(i).getCommitteeIdList().contains(studentId))
				return true;		
		}
    	return false;
    }
}
