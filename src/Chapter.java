
public class Chapter {
	
	private String content;
	
	public Chapter() {
		this.content = "";
	}
	
	public String getContent() {
		return content;
	}
	
	public void addContent(String content) {
		StringBuilder builder = new StringBuilder();
		builder.append(this.content)
				.append(content)
				.append(" ");
		this.content = builder.toString();
	}

}
