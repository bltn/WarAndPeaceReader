
public class BookController implements Controller{
	
	private Book book;
	
	public BookController(Book book) {
		this.book = book;
	}
	
	@Override
	public String getPreviousChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNextChapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChapter(int volumeNumber, int chapterNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLinesWithWord(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalOccurrences(String word) {
		// TODO Auto-generated method stub
		return 0;
	}

}
