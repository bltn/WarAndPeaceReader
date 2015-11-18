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
		
			BufferedReader br = new BufferedReader(new FileReader(bookName));
			String line;
			while ((line = br.readLine()) != null) {
				Volume currentVolume;
				Chapter currentChapter;
				if (/*Line starts with BOOK [NUMBER]: or [FIRST/SECOND] EPILOGUE:*/) {
					currentVolume = new Volume();
					currentChapter = currentVolume.newChapter();
					volumes.add(currentVolume);
					currentChapter.addContent(line);
				}
				else if (/*Line starts with CHAPTER[ROMAN NUMERAL OTHER THAN ONE]*/) {
					currentChapter = currentVolume.newChapter();
					currentChapter.addContent(line);	
				}
				
				else {
					currentChapter.addContent(line);
				}
			}
	}
}