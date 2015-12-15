 

public class Chapter {
	
	private String content;
	
	private String title;
	
	/**
	 * Creates a new chapter.
	 */
	public Chapter() {
		this.content = "";
	}
	
	/**
	 * Gets the content for this chapter.
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Adds content to this chapter.
	 * @param content the content
	 */
	public void addContent(String content) {
		StringBuilder builder = new StringBuilder();
		builder.append(this.content)
				.append(content + "\n")
				.append(" ");
		this.content = builder.toString();
	}
	
	/*public String getKeyWordOccurences(String word)
	{
		String occurences;
		String[] lines = this.content.split("\n");
		for (String line : lines)
		{
			if (line.contains(word))
			{
				int lineNumber = Arrays.asList(lines).indexOf(line) + 1;
				occurences = occurences + "Line " + lineNumber +
			}
		}
		return null;
	}*/

	/**
	 * Gets the title of this chapter.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of this chapter.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
