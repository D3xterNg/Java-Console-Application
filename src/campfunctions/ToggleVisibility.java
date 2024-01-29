package campfunctions;

import java.util.Scanner;

import camps.CampList;
import excel_utils.ICampData;
import excel_utils.CampExcelData;
import users.User;
/**
 * ToggleVisibility class extends CampController for managing the visibility of a camp.
 * This class provides methods to toggle the visibility status of a camp.
 */
public class ToggleVisibility extends CampController {
	/**
	 * Constructor of CampToggler
	 * @param user user information
	 * @param campList list of camps
	 */
	public ToggleVisibility(User user, CampList campList) {
		super(user, campList);
	}
	private ICampData campData;
	//store camp visibility
	private boolean visibility;
	


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
			System.out.println("Camp is not found");
		}
		// If camp is found
		else { 
			//If there are student/committee registered, the camp visibility cannot be edited
			if(campList.get(campIndex).getStudentIdList().size()!=0 || campList.get(campIndex).getCommitteeIdList().size()!=0) {
				System.out.println("The visibility cannot be toggled");
			}
			else {
				String visible;
				System.out.print("Enter new camp visibility (on/off):");
		        do {
		        	visible = sc.nextLine().trim();
		        	if(!(visible.equals("on") || (visible.equals("off"))))
		        		System.out.print("Please enter valid visibility:");
		        }while(!(visible.equals("on") || (visible.equals("off"))));
		        if(visible.equals("on")) visibility = true;
		        else visibility = false;
		        // set the new visibility to the edited camp
		        campList.get(campIndex).setVisibility(visibility);
		        
		        System.out.println("Successfully set the visibility to: " + visible);
			}
			campList.sort();
			campData = new CampExcelData();
			campData.save(campList);	
		}
	}
}
