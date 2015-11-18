import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
	
	private String bookName = "Data/ebook.txt";
	
	private List<Volume> volumes;
		
	public Book() {
		volumes = new LinkedList<Volume>();
		setContent();
	}
	
	public Volume getVolume(int volumeNum) {
		return volumes.get(volumeNum-1);
	}
	
	//TODO: Figure out how to do the line pattern matching 
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
			Volume currentVolume;
			Chapter currentChapter;
			while ((line = br.readLine()) != null) {

				chapterMatcher.reset(line);
				epilogueMatcher.reset(line);
				volumeMatcher.reset(line);
				if (epilogueMatcher.find() || volumeMatcher.find()) {
					currentVolume = new Volume();
					currentChapter = currentVolume.newChapter();
					volumes.add(currentVolume);
					currentChapter.addContent(line);
				}
				else if (chapterMatcher.matches()) {
					currentChapter = currentVolume.newChapter();
					currentChapter.addContent(line);	
				}
				
				else {
					currentChapter.addContent(line);
				}
			}
	}
}