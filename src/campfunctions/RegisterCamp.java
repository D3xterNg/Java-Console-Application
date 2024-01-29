package campfunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import camps.Camp;
import camps.CampList;
import excel_utils.ICampData;
import excel_utils.CampExcelData;
import users.Student;
import users.User;
/**
 * The RegisterCamp class represents a functionality to register for camps
 * It implements the IEnrolls interface and includes methods to enter camp informations.
 */
public class RegisterCamp implements IEnrolls {
	/**
	 * Represents the data source for camps.
	 */
	public ICampData campData;

	@Override
	public  void Enroll (User user, CampList campList) {
		//Upcast user to student
		Student student = (Student) user;
		//Get the camp name
		System.out.println("Enter Camp Name ");
		Scanner sc = new Scanner(System.in);
		String campName = sc.nextLine().trim();
		
		//get campIndex in campList
		int campIndex = SearchCamp.searchCamp(campName, campList);
		//selected camp object
		Camp selectedCamp;
		//check whether the camp's data clash with other or not
		boolean dateClash = false;
		
		//check if camp is found
		if(campIndex==-1) {
			System.out.println("Camp not found");
		}
		else {
			//assign selected camp to the searchedcamp
			selectedCamp = campList.get(campIndex);

            // Check if there is any clash in the dates of camps
			for (int i = 0; i < campList.size(); i++) {
				//Not check if it is the same camp
				if (campList.get(i).getName().equals(selectedCamp.getName())) continue;
				//Not check if the student is not in the camp
				if (!(campList.get(i).getStudentIdList().contains(student.getUserID()))) continue;
				//check if there is a date clashes
				if (selectedCamp.getStartDate().before(campList.get(i).getEndDate()) && selectedCamp.getEndDate().after(campList.get(i).getStartDate())) {
					dateClash = true;
					break;
				}
			}
			
			 // Check the conditions which user is not allowed to register the camp     
		    if (selectedCamp.getWithdrawIdList().contains(student.getUserID())) 
            	System.out.println("You've withdrawn from this camp before ");
            
            else if (!(selectedCamp.getUserGroup().equals(student.getFaculty())) && !(selectedCamp.getUserGroup().equals("ALL")))
            	System.out.println("You are not from this faculty.");
            
            else if (selectedCamp.getRegistrationClosingDate().before(new Date())) 
            	System.out.println("Registration has closed.");
			
			else if (dateClash)
				System.out.println("Clash with another camp.");
            
			else {
				System.out.println("Register as:\n 1: camp attendee \n 2: camp committee member ");
	    		int choice = sc.nextInt();
	    		
	    		if (choice == 1) {
	    			//check if the user is already registered
	    			if (selectedCamp.getStudentIdList().contains(student.getUserID())) 
	                	System.out.println("You are already registered for this camp as an attendee");
	    			else {
	    				int remainingSlots = selectedCamp.getTotalSlots() - selectedCamp.getCampCommitteeSlots() - selectedCamp.getStudentIdList().size();
		    			
		    			if(remainingSlots<=0) {
		    				System.out.println("No more camp slots");
		    			}
		    			else {
		    				//add student to the studentIdList
		    				ArrayList<String> newStudentIdList = selectedCamp.getStudentIdList();
		    				newStudentIdList.add(student.getUserID());
		    				//sort the Id list before storing
		    				Collections.sort(newStudentIdList);
		    				
		    				//save to data base
		    	            campData = new CampExcelData();
		    	    		campData.save(campList);
		    	            System.out.println("Successfully register the camp as a camp attendee.");
		    			}
	    			}
	    		}
	    		// Registered as a campcommittee
	    		else {
	    			if (student.getIsCampCommittee()) {
	    				System.out.println("Student is already part of a camp committee.");
	    			}
	    			else{
	    				int remainingSlots = selectedCamp.getCampCommitteeSlots() - selectedCamp.getCommitteeIdList().size();
	    				
	    				if(remainingSlots<=0) {
	    					System.out.println("Camp is full.");
	    				}
	    				else {
	    					//add student to the CommitteeId list
	    					ArrayList<String> newCommitteeIdList = selectedCamp.getCommitteeIdList();
	        				newCommitteeIdList.add(student.getUserID());
	        				//sort the Id list before storing
	        				Collections.sort(newCommitteeIdList);
	        				
	        				//save to data base
	        	            campData = new CampExcelData();
	        	    		campData.save(campList);
	        	            System.out.println("Successfully register the camp as a camp committee.");
	    				}
	    			}
	    		}
			} 
		}
		
	}

}
