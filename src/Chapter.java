import java.util.Arrays;
import java.util.List; 

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
