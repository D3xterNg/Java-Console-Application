package pages;
/**
 * Represents a page that handles the exit functionality of the application.
 * This class extends the abstract class Page.
 */
public class Exit extends Page{
    /**
     * Constructor for Exit
     * @param earlierPg the previous page
     */
    public Exit(Page earlierPg){
        super(earlierPg);
    }
    /**
    * Main executable for this page
    * @return null
    */
    @Override
    public Page startApp(){
        return null;
    }
}
