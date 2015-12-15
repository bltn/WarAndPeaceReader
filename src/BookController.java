import java.util.Arrays;


public class BookController implements Controller{
	
	// Book this controller handles 
	private Book book;
	// Current chapter 
	private int chapterBookmark;
	// Current volume 
	private int volumeBookmark;
	
	public BookController(Book book) {
		this.book = book;
		this.chapterBookmark = 0;
		this.volumeBookmark = 1;
	}
	
	/**
	 * Returns the previous chapter or the current chapter if there is no previous chapter/volume 
	 * @return String representation of the previous chapter
	 */
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

	/**
	 * Returns the next chapter or the current chapter if there is no next chapter/volume 
	 * @return String representation of the next chapter
	 */
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

	/**
	 * Gets lines containing occurrence of the given word
	 * @param the word to search for 
	 * @return lines containing occurrence of the word 
	 */
	@Override
	public String getLinesWithWord(String word) {
		String occurrence = "";
		int currentVolume = 1;
		int currentChapter = 0;
		int resultsCount = 0;
		
		for (Volume volume : book.getVolumes())
		{
			for (Chapter chapter : volume.getChapters())
			{
				String[] lines = chapter.getContent().split("\n");
				for (String line : lines)
				{
					if (line.contains(word))
					{
						occurrence += ("Line " + Arrays.asList(lines).indexOf(line) + " Chapter " + currentChapter + " Volume " + currentVolume + "\n");
						occurrence += (line + "\n");
						resultsCount++; 
					}
				}
				currentChapter++;
			}
			currentChapter = 0;
			currentVolume++;
		}
		occurrence += resultsCount + " lines with this keyword found.";
		return occurrence;
	}
	
	/**
	 * Gets the total number of occurrences of the given word
	 * @param the word to count the occurrences of 
	 * @return number of occurrences
	 */
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
