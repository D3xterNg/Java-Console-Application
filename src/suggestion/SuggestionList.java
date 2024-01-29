package suggestion;

import java.util.ArrayList;

/**
 * List of suggestions
 */
public class SuggestionList {
	//array of Suggestion object
	private ArrayList<Suggestion> SuggestionList;
	
	/**
	 * Constructor of SuggestionList
	 */
	public SuggestionList() {
		this.SuggestionList = new ArrayList<Suggestion>();
	}
	/**
	 * Accessor of SuggestionList
	 * @return Suggestion List
	 */
	public ArrayList<Suggestion> getSuggestionList(){
		return SuggestionList;
	}
	
	/**
	 * Add method
	 * @param Suggestion take in suggestion
	 */
	public void add(Suggestion Suggestion) {
		this.SuggestionList.add(Suggestion);
	}
	
	/**
	 * @return size of SuggestionList
	 */
	public int size() {
		return SuggestionList.size();
	}
	
	/**
	 * Get Suggestion object at index i
	 * @param i index of suggestion
	 * @return Suggestion i of SuggestionList
	 */
	public Suggestion get(int i) {
		return SuggestionList.get(i);
	}
	
	/**
	 * Set the ith Suggestion object to the new Suggestion object
	 * @param i index of list
	 * @param Suggestion The suggestion to be added to the list.
	 *  */
	public void set(int i, Suggestion Suggestion) {
		SuggestionList.set(i, Suggestion);
	}
	
	/**
	 * Remove the Suggestion object from SuggestionList
	 * @param i take in index of suggestion object
	 */
	public void remove(int i) {
		SuggestionList.remove(i);
	}
	
	/**
	 * Converts to string
	 * @return Suggestion List as String
	 */
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Suggestion Suggestion : SuggestionList) {
	        sb.append(Suggestion.getName()).append(", ");
	    }
	    return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : ""; 
	}
}
