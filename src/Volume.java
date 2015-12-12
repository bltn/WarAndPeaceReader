import java.util.LinkedList;
import java.util.List;


public class Volume {
	
	private List<Chapter> chapters;
	
	private String title; 
	
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
	
	public boolean hasChapters() 
	{
		return !chapters.isEmpty();
	}
	
	public void setTitle(String content) {
		this.title = content;
	}
	
	public String getTitle() {
		return title; 
	}

}
