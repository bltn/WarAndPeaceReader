import java.util.LinkedList;
import java.util.List;


public class Book {
	
	private List<Volume> volumes;
	
	private String content;
	
	public Book() {
		volumes = new LinkedList<Volume>();
		content = null;
	}
	
	public Volume getVolume(int volumeNum) {
		return volumes.get(volumeNum-1);
	}
}
