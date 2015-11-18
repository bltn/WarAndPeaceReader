
public class BookController implements Controller{
	
	/**
	 * The {@link Book} this {@link Controller} should handle. 
	 */
	private Book book;
	
	public BookController(Book book) {
		this.book = book;
	}
	
	@Override
	public String getPreviousChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNextChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the chapter for the given volume.
	 * @param volumeNumber the volume number
	 * @param chapterNumber the chapter number
	 * @return the content of the chapter
	 */
	@Override
	public String getChapter(int volumeNumber, int chapterNumber) {
		//TODO: Add error handling for non existent volumes and/or chapters 
		Volume volume = book.getVolume(volumeNumber);
		Chapter chapter = volume.getChapter(chapterNumber);
		return chapter.getContent();
	}

	@Override
	public String getLinesWithWord(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalOccurrences(String word) {
		// TODO Auto-generated method stub
		return 0;
	}

}
