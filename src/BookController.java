import java.util.Arrays;


/**
 * Controller class for book objects
 * Handles search and interaction operations 
 * @author Ben Lawton
 * @author Denver Fernandes 
 */
public class BookController implements Controller
{
	
	// Book this controller handles 
	private Book book;
	// Current chapter 
	private int chapterBookmark;
	// Current volume 
	private int volumeBookmark;
	
	/**
	 * Creates a new BookController object.
	 * @param book the book to use
	 */
	public BookController(Book book) 
	{
		this.book = book;
		this.chapterBookmark = 0;
		this.volumeBookmark = 1;
	}
	
	/**
	 * Returns the previous {@link Chapter chapter} or the current {@link Chapter chapter} if there is no previous {@link Chapter chapter}/{@link Volume volume}. 
	 * @return String representation of the previous chapter
	 */
	public String getPreviousChapter() 
	{
		StringBuilder chapter = new StringBuilder(); 
		if (chapterBookmark == 0 && volumeBookmark == 1)
		{
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		else if (chapterBookmark == 0 && volumeBookmark > 1)
		{
			volumeBookmark -= 1;
			chapterBookmark = this.book.getVolume(volumeBookmark).getChapterCount()-1;
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		else 
		{
			chapterBookmark -= 1;
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		return chapter.toString(); 
	}

	/**
	 * Returns the next chapter or the current chapter if there is no next {@link Chapter chapter}/{@link Volume volume}. 
	 * @return String the next {@link Chapter chapter}
	 */
	public String getNextChapter() 
	{
		StringBuilder chapter = new StringBuilder(); 
		if ((volumeBookmark == this.book.getVolumeCount()) && (chapterBookmark == this.book.getVolume(volumeBookmark).getChapterCount()-1))
		{
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		else if (volumeBookmark < this.book.getVolumeCount() && (chapterBookmark == this.book.getVolume(volumeBookmark).getChapterCount()-1))
		{
			volumeBookmark ++;
			chapterBookmark = 0;
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		else
		{
			chapterBookmark++;
			chapter.append(this.book.getVolume(volumeBookmark).getChapter(chapterBookmark).getContent());
		}
		return chapter.toString();
	}

	/**
	 * Gets the chapter for the given {@link Volume volume}.
	 * @param volumeNumber the volume number
	 * @param chapterNumber the chapter number
	 * @return the content of the chapter
	 */
	@Override
	public String getChapter(int volumeNumber, int chapterNumber) 
	{
		Volume volume = book.getVolume(volumeNumber);
		Chapter chapter = volume.getChapter(chapterNumber);
		return chapter.getContent();
	}

	/**
	 * Gets lines containing occurrence of the given word.
	 * @param word the word to search for 
	 * @return lines containing occurrence of the word 
	 */
	@Override
	public String getLinesWithWord(String word) 
	{
		StringBuilder occurrence = new StringBuilder();
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
						String[] phrases = line.split("\\s");
						for (String phrase : phrases)
						{
							if (phrase.contains(word))
							{
								occurrence.append("Line " + Arrays.asList(lines).indexOf(line) + " Chapter " + currentChapter + " Volume " + currentVolume + "\n");
								occurrence.append(line + "\n");
								resultsCount++; 
							}
						} 
					}
				}
				currentChapter++;
			}
			currentChapter = 0;
			currentVolume++;
		}
		occurrence.append(resultsCount + " results found.");
		return occurrence.toString();
	}
	
	/**
	 * Gets the total number of occurrences of the given word.
	 * @param word the word to count the occurrences of 
	 * @return number of occurrences of the given word
	 */
	@Override
	public int getTotalOccurrences(String word) 
	{
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
