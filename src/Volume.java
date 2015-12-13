import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


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
	
	public int getChapterCount() {
		return chapters.size();
	}
	
	public ListIterator<Chapter> getChapterIterator()
	{
		return chapters.listIterator();
	}
	
	public Chapter getChapter(int chapterNum) {
		return chapters.get(chapterNum);
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
