
public class Chapter {
	
	private String content;
	
	private String title;
	
	public Chapter() {
		this.content = "";
	}
	
	public String getContent() {
		return content;
	}
	
	public void addContent(String content) {
		StringBuilder builder = new StringBuilder();
		builder.append(this.content)
				.append(content + "\n")
				.append(" ");
		this.content = builder.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
