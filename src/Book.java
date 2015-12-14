import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
	
	// relative path for the text file containing the book content 
	private String bookName = "Data/ebook.txt";
	// all volumes in the book
	private ArrayList<Volume> volumes;
		
	/**
	 * Create book object and initialise its fields 
	 */
	public Book() {
		volumes = new ArrayList<Volume>();
		setContent();
	}
	
	public ArrayList<Volume> getVolumes()
	{
		return volumes;
	}
	
	/**
	 * Returns the specified volume 
	 * @param volumeNum Number of the required volume 
	 * @return The requested volume
	 */
	public Volume getVolume(int volumeNum) {
		return volumes.get(volumeNum-1);
	}
	
	/**
	 * @return number of Volume objects in the book
	 */
	public int getVolumeCount()
	{
		return volumes.size();
	}
	
	public String getAllContent()
	{
		String allContent = null;
		for (Volume v : volumes)
		{
			for (Chapter c : v.getChapters())
			{
				allContent += c.getContent();
			}
		}
		return allContent;
	}
	
	/**
	 * Populates all of the book's volumes and their individual chapters with content from the text file 
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