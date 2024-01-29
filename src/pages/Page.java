package pages;
/**
 * Represents an abstract class for pages
 * Subclasses should implement methods from this class.
 */
public abstract class Page {
    /**
     * Previous page visited
     */
    private Page earlierPg;
    /**
     * Constructor of Page
     * @param earlierPg previous page visited
     */
    public Page(Page earlierPg){
        this.earlierPg = earlierPg;
    }
    /**
     * main functionality of the page
     * @return A description of what the method returns.
     */
    public abstract Page startApp();
    /**
     * Gets previous page visited
     * @return previous page visited
     */
    // method implementation goes here
    protected Page getEarlierPg() {
        if (this.earlierPg != null)
            return this.earlierPg;
        // if it's the first page
        else
            return this;
    }
}
