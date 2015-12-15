import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
	
	// relative path for the text file containing the book content 
	private String bookName = "Data/ebook.txt";
	// all volumes in the book
	private ArrayList<Volume> volumes;
		
	/**
	 * Create book object and initialise its fields. 
	 */
	public Book() {
		volumes = new ArrayList<Volume>();
		setContent();
	}
	
	/**
	 * Gets the {@link Volume volumes} in this book.
	 * @return the volumes in this book.
	 */
	public ArrayList<Volume> getVolumes()
	{
		return volumes;
	}
	
	/**
	 * Returns the specified {@link Volume volume}.
	 * @param volumeNum number of the required volume 
	 * @return the requested volume
	 */
	public Volume getVolume(int volumeNum) {
		if(volumeNum <= 0) {
			throw new IllegalArgumentException("Volume numbers should be 1 or more.");
		}
		if(volumeNum > getVolumeCount()) {
			throw new IllegalArgumentException("Volume " + volumeNum + " does not exist. Volumes 1-17 are available. Try one of those.");
		}
		return volumes.get(volumeNum-1);
	}
	
	/**
	 * The total count of the {@link Volume volumes}.
	 * @return number of {@link Volume} objects in the book
	 */
	public int getVolumeCount()
	{
		return volumes.size();
	}
	
	/**
	 * Gets all of the content in this book.
	 * @return the content of this book
	 */
	public String getAllContent()
	{
		StringBuilder allContent = new StringBuilder();
		for (Volume v : volumes)
		{
			for (Chapter c : v.getChapters())
			{
				allContent.append(c.getContent());
			}
		}
		return allContent.toString();
	}
	
	/**
	 * Populates all of this book's {@link Volume volumes} and their individual chapters with content from the text file. 
	 */
	private void setContent() {
		Pattern chapterExp = Pattern.compile("(CHAPTER) (.{0,7})");
		Matcher chapterMatcher = chapterExp.matcher("");
		Pattern epilogueExp = Pattern.compile("(EPILOGUE)");
		Matcher epilogueMatcher = epilogueExp.matcher("");
		Pattern volumeExp = Pattern.compile("(BOOK)");
		Matcher volumeMatcher = volumeExp.matcher("");
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(bookName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line;
		Volume currentVolume = null;
		Chapter currentChapter = null;
		try {
			while ((line = br.readLine()) != null) {
				chapterMatcher.reset(line);
				epilogueMatcher.reset(line);
				volumeMatcher.reset(line);
				if (epilogueMatcher.find() || volumeMatcher.find()) {
					currentVolume = new Volume();
					currentChapter = currentVolume.newChapter();
					volumes.add(currentVolume);
					currentVolume.setTitle(line);
					currentChapter.addContent(line);
				}
				else if (chapterMatcher.find()) {
					currentChapter = currentVolume.newChapter();
					currentChapter.setTitle(line);
					currentChapter.addContent(line);
				}
			
				else {
					currentChapter.addContent(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}