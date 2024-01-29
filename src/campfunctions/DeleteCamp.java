package campfunctions;

import java.util.Scanner;

import camps.CampList;
import excel_utils.ICampData;
import excel_utils.CampExcelData;
import users.User;
/**
 * A class representing the functionality to delete a camp.
 * Extends CampController to inherit common camp-related operations.
 */
public class DeleteCamp extends CampController {
	
	private ICampData campData;
	/**
	 * Constructor of CampDeletor
	 * @param user userid from user
	 * @param campList list of camps
	 */
	public DeleteCamp(User user, CampList campList) {

		super(user, campList);
	}
	
	@Override
	public void modifyCampInfo() {
		Scanner sc = new Scanner(System.in);
		// Input the edited camp
		System.out.print("Enter camp name:");
		String searchedName = sc.nextLine().trim();
		//search for the camp name
		int campIndex = SearchCamp.searchCamp(searchedName, campList);
		// If camp is not found
		if(campIndex==-1) {
			System.out.println("Camp not found");
		}
		// If camp is found
		else { 
			if(campList.get(campIndex).getStudentIdList().size()!=0 || campList.get(campIndex).getCommitteeIdList().size()!=0) {
				System.out.println("Unable to delete. Students still registered to camp");
			} else {
				campList.remove(campIndex);
				campData = new CampExcelData();
				campData.save(campList);
				System.out.println("Successfully delete the camp");
			}
		}
		campList.sort();
		campData = new CampExcelData();
		campData.save(campList);
	}
}
