
/**
 * Interface Controller acts as the controller of this eBook application. 
 * This controller specifies the core functionality of this application.
 * 
 * @author S H S Wong
 * @version 29/10/2015 
 */
public interface Controller 
{
	
	/**
	 * Returns the text in the previous chapter.
	 * @return	the result as a string; or null if the current chapter is the first chapter of the book.
	 */	
	String getPreviousChapter();
	
	/**
	 * Returns the text in the next chapter.
	 * @return	the result as a string; or null if the current chapter is the last chapter of the book.
	 */
	String getNextChapter();
	
	/**
	 * Returns the text in the specified chapter.
	 * 
	 * @param volumeNumber	the number of the required volume
	 * @param chapterNumber	the number of the required chapter
	 * @return	the result as a string; or null if the specified chapter does not exist
	 */
	String getChapter(int volumeNumber, int chapterNumber);
	
	/**
	 * Returns the lines of text in which the specified word can be found.
	 * 
	 * @param word	the word to be found
	 * @return	the result as a string; or null if the specified word does not exist
	 */
	String getLinesWithWord(String word);
	
	/**
	 * Returns the total number of times a specified word occurs in the text.
	 * 
	 * @param word	the word to be found
	 * @return	the results an an integer
	 */
	int getTotalOccurrences(String word);
}

