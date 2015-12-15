import java.util.ArrayList;


public class Volume 
{
	
	private ArrayList<Chapter> chapters;
	
	private String title; 
	
	/**
	 * Creates a new Volume object.
	 */
	public Volume() 
	{
		chapters = new ArrayList<Chapter>();
	}
	
	/**
	 * Gets all the {@link Chapter}s in this volume.
	 * @return the chapters in this volume.
	 */
	public ArrayList<Chapter> getChapters()
	{
		return chapters;
	}
	
	
	/**
	 * Creates a new {@link Chapter}.
	 * @return the new chapter
	 */
	public Chapter newChapter() 
	{
		Chapter chapter = new Chapter();
		chapters.add(chapter);
		return chapter;
	}
	
	/**
	 * The total count of the {@link Chapter chapter}.
	 * @return the total count of the chapters
	 */
	public int getChapterCount() 
	{
		return chapters.size();
	}
	
	/**
	 * Gets a {@link Chapter chapter}.
	 * @param chapterNum the chapter number
	 * @return the chapter
	 */
	public Chapter getChapter(int chapterNum) 
	{
		if(chapterNum < 0) {
			throw new IllegalArgumentException("Chapter numbers should be 0 or more.");
		}
		if(chapterNum > chapters.size()) {
			throw new IllegalArgumentException("Chapter with the given number does not exist.");
		}
		return chapters.get(chapterNum);
	}
	
	/**
	 * Whether the volume has {@link Chapter chapters}.
	 * @return true if the volume has chapters
	 */
	public boolean hasChapters() 
	{
		return !chapters.isEmpty();
	}
	
	/**
	 * Sets the title of this volume.
	 * @param content the title to set for this volume
	 */
	public void setTitle(String content) 
	{
		this.title = content;
	}
	
	/**
	 * Gets the title of this volume.
	 * @return the title of this title
	 */
	public String getTitle() 
	{
		return title; 
	}

}
