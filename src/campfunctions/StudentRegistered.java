package campfunctions;

import java.util.ArrayList;
import java.util.Scanner;

import camps.CampList;
import users.Staff;
import users.User;
import utils.IViewData;
/**
 * StudentRegistered class implements IViewData for viewing information about students registered in camps.
 * This class provides methods to retrieve and display data about students who have registered for camps.
 */
public class StudentRegistered implements IViewData {

	@Override
	public void Data(User user, CampList campList) {
		Scanner sc = new Scanner(System.in);
		boolean campFound = false;
		int campIndex = 0;
		// if user is a staff
		if(user instanceof Staff) {
			System.out.print("Enter the camp name in which you would like to see the attendees: ");
			String campName = sc.nextLine().trim();
			for (int i=0; i<campList.size();i++) {
				if (campList.get(i).getName().toUpperCase().equals(campName.toUpperCase())) {
					campFound = true;
					break;
				}
				campIndex++;
			}
			if (!campFound) {
				System.out.println("Camp not found.");
				return;
			}
			ArrayList <String> studentIdList = campList.get(campIndex).getStudentIdList();
			ArrayList <String> committeeIdList = campList.get(campIndex).getCommitteeIdList();
			System.out.println("\n");
			System.out.println("===========================================================");
			System.out.println("                   List of Students: ");
			for(int i=0; i<studentIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("===========================================================");
				System.out.println( i+1 + ": "+studentIdList.get(i));
			}
			System.out.println("===========================================================");
			System.out.println("                List of Camp Committee: ");
			for(int i=0; i<committeeIdList.size(); i++) {
				//if visibility is on and student faculty and camp userGroup are the same
				System.out.println("===========================================================");
				System.out.println(i+1 + ": "+committeeIdList.get(i));
			}
			System.out.println("===========================================================");
			System.out.println();
		}
		// if user is a campCommittee member
		else{
			String userID = user.getUserID().toUpperCase();
			for (int i=0; i<campList.size();i++) {
				if (campList.get(i).getCommitteeIdList().contains(userID)) {
					campFound = true;
					break;
				}
				campIndex++;
			}
			if (!campFound) {
				System.out.println("You are not a camp committee of any camps.");
				return;
			}
			ArrayList <String> studentIdList = campList.get(campIndex).getStudentIdList();
			ArrayList <String> committeeIdList = campList.get(campIndex).getCommitteeIdList();
			System.out.println("\n");
			System.out.println("=============================================================");
			System.out.println("                  List of Students: ");
			for(int i=0; i<studentIdList.size(); i++) {
				//if visibility is on + student faculty + camp userGroup are the same
				System.out.println("===========================================================");
				System.out.println( i+1 + ": "+studentIdList.get(i));
			}
			System.out.println("===========================================================");
			System.out.println("                List of Camp Committee: ");
			for(int i=0; i<committeeIdList.size(); i++) {
				//if visibility is on + student faculty + camp userGroup are the same
				System.out.println("===========================================================");
				System.out.println(i+1 + ": "+committeeIdList.get(i));
			}
			System.out.println("==========================================================\n");

		}	
	}
	
}
