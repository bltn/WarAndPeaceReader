
public class BookController implements Controller{
	
	/**
	 * The {@link Book} this {@link Controller} should handle. 
	 */
	private Book book;
	
	private int chapterBookmark;
	
	private int volumeBookmark;
	
	public BookController(Book book) {
		this.book = book;
		this.chapterBookmark = 0;
		this.volumeBookmark = 1;
	}
	
	@Override
	public String getPreviousChapter() {
		String chapter; 
		if (chapterBookmark == 0 && volumeBookmark == 1)
		{
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		else if (chapterBookmark == 0 && volumeBookmark > 1)
		{
			volumeBookmark -= 1;
			chapterBookmark = this.book.getVolume(volumeBookmark).getChapterCount()-1;
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		else 
		{
			chapterBookmark -= 1;
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		return chapter; 
	}

	@Override
	public String getNextChapter() {
		String chapter; 
		if ((volumeBookmark == this.book.getVolumeCount()) && (chapterBookmark == this.book.getVolume(volumeBookmark).getChapterCount()-1))
		{
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		else if (volumeBookmark < this.book.getVolumeCount() && (chapterBookmark == this.book.getVolume(volumeBookmark).getChapterCount()-1))
		{
			volumeBookmark ++;
			chapterBookmark = 0;
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		else
		{
			chapterBookmark++;
			chapter = this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent();
		}
		return chapter;
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
		return null;
	}

	@Override
	public int getTotalOccurrences(String word) {
		int occurences = 0;
		String results[] = this.book.getAllContent().split("\\s");
		for (String result : results)
		{
			if (result.equals(word))
			{
				occurences++;
			}
		}
		return occurences;
	}

}
