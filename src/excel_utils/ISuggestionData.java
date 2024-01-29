package excel_utils;

import suggestion.SuggestionList;
/**
 * This interface is related to the reading and writing of suggestions from committee members
 */
public interface ISuggestionData {
	/**
	 * Saves the given list of suggestions to a data source.
	 * @param suggestionList The list of suggestions to be saved.
	 */
	public void save(SuggestionList suggestionList);

	/**
	 * This load method reads the suggestions from committee members to Staff
	 * @param suggestionList list of suggestions
	 */
	public void load(SuggestionList suggestionList);	
}
