package suggestion_enquriy_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import camps.CampList;
import excel_utils.SuggestionExcel;
import users.Student;
import users.User;
import suggestion.Suggestion;
import suggestion.SuggestionList;
import utils.IMessages;
/**
 * This class provides functionality to load suggestions.
 * It implements the IMessages interface for suggestions.
 */
public class LoadSuggestion implements IMessages {
	/**
	 * Submit a suggestion
	 * @param user - user submitting suggestion
	 * @param campList - maintained list of all camps
	 */
	public void submit(User user, CampList campList) {
		SuggestionList suggestionList = new SuggestionList();
		String suggestionID, userID, campName, Question, approvedOrDeclined;
		boolean processedStatus;
		Date suggestionDate;
		
		// if user is not a student
		if(!(user instanceof Student)) {
			System.out.println("You do not have access to submit suggestions.");
		}
		// if user is a student
		else{
			Scanner sc = new Scanner(System.in);
			Student student = (Student) user;
	        userID = user.getUserID().trim().toUpperCase();
			// do a check of whether student is a committee member
			boolean campFound = false;
			int campIndex = 0;
			for (int i=0; i<campList.size(); i++) {
				if (campList.get(i).getCommitteeIdList().contains(userID)) {
					campFound = true;
					break;
				}
				campIndex++;
			}
			// if there is no camp that is found, then student has not registered as committee
			if (!campFound) {
				System.out.println("You have not registered as a committee member for any camp.");
				sc.close();
				return;
			}
			
			campName = campList.get(campIndex).getName();

			student.setSuggestionDataController(new SuggestionExcel());
    		student.getSuggestionDataController().load(suggestionList);
    		
    		// generate a random suggestion ID
    		UUID uuid = UUID.randomUUID();
    		suggestionID = uuid.toString();
    		userID = user.getUserID().toUpperCase();
    		processedStatus = false;
    		// set the default reply/remarks to the suggestion
    		approvedOrDeclined = "PENDING";
	        // set the date where the suggestion was created
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        suggestionDate = new Date();
	        // get the suggestion message
	        System.out.print("Type your suggestion message here: ");
	        Question = sc.nextLine();
	        suggestionList.add(new Suggestion(suggestionID, userID, campName, Question, suggestionDate, processedStatus, approvedOrDeclined));
	        student.getSuggestionDataController().save(suggestionList);
	        System.out.println("Success. Suggestion posted successfully.");
	        student.setCommitteePoints(student.getCommitteePoints() + 1);
		} 
	}
}
