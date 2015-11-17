import java.util.LinkedList;
import java.util.List;


public class Volume {
	
	private List<Chapter> chapters;
	
	private String content; 
	
	public Volume() {
		chapters = new LinkedList<Chapter>();
	}
	
	public Chapter newChapter() {
		Chapter chapter = new Chapter();
		chapters.add(chapter);
		return chapter;
	}
	
	public Chapter getChapter(int chapterNum) {
		return chapters.get(chapterNum-1);
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content; 
	}

}
