import java.util.Arrays;
import java.util.ListIterator;


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
		String occurences = "";
		int currentVolume = 1;
		int currentChapter = 0;
		int resultsCount = 0;
		ListIterator<Volume> volumeIterator = book.getVolumeIterator();
		while (volumeIterator.hasNext())
		{
			Volume vol = volumeIterator.next();
			ListIterator<Chapter> chapterIterator = vol.getChapterIterator();
			while (chapterIterator.hasNext())
			{
				String[] lines = chapterIterator.next().getContent().split("\n");
				for (String line : lines)
				{
					if (line.contains(word))
					{
						occurences += ("Line " + Arrays.asList(lines).indexOf(line) + " Chapter " + currentChapter + " Volume " + currentVolume + "\n");
						occurences += (line + "\n");
						resultsCount++;
					}
				}
				currentChapter++;
			}
			currentChapter = 0;
			currentVolume++;
		}
		occurences += resultsCount + " lines with this keyword found."; 
		return occurences;
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
