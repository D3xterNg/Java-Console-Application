package report;


import camps.CampList;
import users.User;
/**
 * Interface for reports
 */
public interface ReportGenerator {
	/**
	 * Abstract
	 * @param user user
	 * @param campList list of camp
	 */
	public void generate(User user, CampList campList);

}
