package main;

import pages.*;


/**
 * main
 */
public class MainApp{
	//Main driver to start entire program
	/**
	 * 
	 * @param args main
	 */
    public static void main(String[] args) {

        Page currentPage = new MainMenu(null);
        do {
            currentPage = currentPage.startApp();
        } while (!(currentPage instanceof Exit));

    }
} 
