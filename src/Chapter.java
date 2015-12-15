 

public class Chapter 
{
	// Text contained in the chapter 
	private String content;
	// Chapter's title, numbered in Roman numerals 
	private String title;
	
	/**
	 * Creates a new chapter.
	 */
	public Chapter() 
	{
		this.content = "";
	}
	
	/**
	 * Gets the content for this chapter.
	 * @return the content
	 */
	public String getContent() 
	{
		return content;
	}
	
	/**
	 * Adds content to this chapter.
	 * @param content the content
	 */
	public void addContent(String content) 
	{
		StringBuilder builder = new StringBuilder();
		builder.append(this.content)
				.append(content + "\n")
				.append(" ");
		this.content = builder.toString();
	}

	/**
	 * Gets the title of this chapter.
	 * @return the title
	 */
	public String getTitle() 
	{
		return title;
	}
	
	/**
	 * Sets the title of this chapter.
	 * @param title the title to set
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}

}
