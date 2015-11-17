import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class Book {
	
	private String bookName = "ebook.txt";
	
	private List<Volume> volumes;
		
	public Book() throws FileNotFoundException, IOException {
		volumes = new LinkedList<Volume>();
		setContent();
	}
	
	public Volume getVolume(int volumeNum) {
		return volumes.get(volumeNum-1);
	}
	
	private void setContent() throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(bookName))) {
			String line;
			while ((line = br.readLine()) != null) {
				/**
				 * IF the line starts with BOOK [NUMBER]: or [FIRST/SECOND] EPILOGUE:
				 * create a new volume & chapter object that the content is to be added to + add the line to it.
				 * ELSE IF the line starts with CHAPTER[ROMAN NUMERAL]:, create a new chapter object and add line to it.
				 * ELSE (line is just a normal line), add the line to the current chapter object being used. 
				 */
			}
		}
	}
}