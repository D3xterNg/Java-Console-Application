package campfunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import excel_utils.ICampData;
import excel_utils.CampExcelData;
import users.Student;
import users.User;
/**
 * WithdrawCamp class implements the IEnrolls interface for handling camp withdrawals.
 * This class provides methods to handle the withdrawal of students from a camp.
 */
public class WithdrawCamp implements IEnrolls {
	// class implementation goes here
	
	private ICampData campData;

	//@Override
	public  void Enroll(User user, CampList campList) {
		
		Student student = (Student) user;
		System.out.println("Enter the name of the camp to withdraw from ");
		Scanner sc = new Scanner(System.in);
		String campName = sc.nextLine().trim();
		
		//get campIndex in campList
		int campIndex = SearchCamp.searchCamp(campName, campList);
		//selected camp object
		Camp selectedCamp;
		
		//check if camp is found
		if(campIndex==-1) {
			System.out.println("Camp not found");
		}
		else {
			//assign selected camp to the searchedcamp
			selectedCamp = campList.get(campIndex);
			if (selectedCamp.getCommitteeIdList().contains(student.getUserID())) {
				System.out.println("You are not allowed to withdraw from this camp as you are part of the camp committee.");
			} else if (!selectedCamp.getStudentIdList().contains(student.getUserID())) {
				System.out.println("You are not registered for this camp");
			} else {
				//Edit the studentidList
				ArrayList<String> newStudentIdList = selectedCamp.getStudentIdList();
				//remove the student Id
				newStudentIdList.remove(student.getUserID());
				
				//Edit the withdrawidList
				ArrayList<String> newWithdrawIdList = selectedCamp.getWithdrawIdList();
				//add new student id
				newWithdrawIdList.add(student.getUserID());
				Collections.sort(newWithdrawIdList);
			
				//save to data base
		        campData = new CampExcelData();
				campData.save(campList);
		        System.out.println("Successfully withdraw from the camp.");
			}
		}	
	}	
}
