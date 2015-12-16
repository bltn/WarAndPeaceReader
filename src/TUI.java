

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple text-based user interface for a eBook application.
 * 
 * @author S H S Wong
 * @author Ben Lawton
 * @author Denver Fernandes 
 * @version 14/12/2015
 */
public class TUI {
	
	private static final Pattern VOLUME_CHAPTER_PATTERN = Pattern.compile("([0-9]+):([0-9]+)");
	
	private Controller controller; // The controller which this TUI is working with
	
	private Scanner stdIn;


	/**
	 * Constructor
	 * 
	 * @param controller	the controller of this eBook application
	 */
	public TUI(Controller controller) {
		
		this.controller = controller;  // keeps track of which controller this TUI is working with
		
		// Creates a Scanner object for obtaining user input
		stdIn = new Scanner(System.in);
		
		/* Run the application */
		while (true) {
			displayMenu();
			getAndProcessUserOption();
		}
	}
	
	public static void main(String[] args) {
		Book book = new Book();
		BookController controller = new BookController(book);
		TUI tui = new TUI(controller);
	}

	/*
	 * Displays the header of this application and a summary of menu options.
	 */
	private void displayMenu() {
		display(header());
		display(menu());
	}

	/*
	 * Returns a string representation of a brief title for this application as the header.
	 * @return	a header
	 */
	private static String header() {
		return "\neBookApp\n";
	}
	
	/*
	 * Returns a string representation of the user menu.
	 * @return	the user menu
	 */
	private static String menu() {
		StringBuilder sb = new StringBuilder("PLEASE NOTE THAT ALL COMMANDS ARE CASE-SENSITIVE.\n");
		
		sb.append("To display a chapter: Type 'show {volume}:{chapter number}', eg\n");
		sb.append("\t show 3:5\n");
		sb.append("To display the previous chapter: Type 'previous', eg\n");
		sb.append("\t previous\n");
		sb.append("To display the next chapter: Type 'next', eg\n");
		sb.append("\t next\n");
		sb.append("To find out the frequency of a specified word: Type 'count {word}', eg\n");
		sb.append("\t count father-in-law\n");
		sb.append("To find out the context of a specified word: Type 'context {word}', eg\n");
		sb.append("\t context father-in-law\n");
		sb.append("To exit this application: Type 'exit'\n");
		
		return sb.toString();
	}
	
	/*
	 * Obtains an user option and processes it.
	 */
	private void getAndProcessUserOption() {
		String command = stdIn.nextLine().trim();
		if (command.equalsIgnoreCase("exit")) { // Exits the application
			display("Thank you for using eBookApp. Goodbye!");
			System.exit(0);
		}
		
		String[] commandWords = command.split(" ");
		if (commandWords.length > 2) {
			displayError(unrecogniseCommandErrorMsg(command));
			display("Please read the menu for available options.\n");
			menu();
		} 
		else {
			switch (commandWords[0]) {
			case "count" : // display the frequency of a word occurred in the book
				if (commandWords.length == 2) {
					display("The word {" + commandWords[1] + "} occurs " + controller.getTotalOccurrences(commandWords[1]) + " time(s).\n");
				}
				else {
					displayError("No word specified.");
				}
				break;
			case "show" : // Display the specified chapter
				if (commandWords.length == 2) {
					Matcher matcher = VOLUME_CHAPTER_PATTERN.matcher(commandWords[1]);
					if (matcher.matches()) {
						try {
							int volumeNumber = Integer.parseInt(matcher.group(1)); 
							int chapterNumber = Integer.parseInt(matcher.group(2));
							showChapterLookupResult(volumeNumber, chapterNumber, controller.getChapter(volumeNumber, chapterNumber));
						}
						catch (NumberFormatException nfe) {
							display("Invalid volume and/or chapter number: " + commandWords[1]);
						}
						catch (IllegalArgumentException e) {
							display(e.getMessage());
						}
					}
					else {
						display("Cannot recognise command {" + command + "} \t The volume and chapter numbers must be integers.");
					}
				}
				else {
					displayError("No word specified.");
				}
				break;
			case "previous" : // Show the previous chapter
				showChapterLookupResult(controller.getPreviousChapter());
				break;
			case "next" : // Show the next chapter
				showChapterLookupResult(controller.getNextChapter());
				break;
			case "context" : // display the contexts of a word in the book
				if (commandWords.length == 2) {
					showContextResult(commandWords[1], controller.getLinesWithWord(commandWords[1]));
				}
				else {
					displayError("No word specified.");
				}
				break;
			default: 
				displayError(unrecogniseCommandErrorMsg(command));
			}
		}
	}

	/*
	 * Display the result of the word context lookup features.
	 * @param string
	 * @param linesWithWord
	 */
	private void showContextResult(String word, String result) {
		if (result == null) {
			display(word + " does not exist in the book.\n");
		}
		else {
			display(result);
		}
	}
	
	/*
	 * Display the result of the previous/next chapter lookup features.
	 * @param the result
	 */
	private void showChapterLookupResult(String result) {
		if (result == null) {
			display("No further chapters to show.");
		}
		else {
			display(result);
		}
	}
	
	/*
	 * Display the result of the specified chapter lookup features.
	 * 
	 * @param the number of the required volume
	 * @param the number of the required chapter
	 * @param the result
	 */
	private void showChapterLookupResult(int volume, int chapter, String result) {
		if (result == null) {
			display("Chapter " + chapter + " of " + "Volume " + volume + " does not exist in the text.");
		}
		else {
			display(result);
		}
	}
	
	/*
	 * Displays an error message for the user to view.
	 * @param error	the error message to be displayed on the screen
	 */
	private void displayError(String error) {
		System.out.println("ERROR: " + error);
	}
	
	/*
	 * Displays the specified info for the user to view.
	 * @param info	info to be displayed on the screen
	 */
	private void display(String info) {
		System.out.println(info);
	}
	
    /*
     * Returns an error message for an unrecognised command.
     * 
     * @param error the unrecognised command
     * @return      an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: %s.%n", error);
    }
    
}