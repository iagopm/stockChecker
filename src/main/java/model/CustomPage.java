package model;

public class CustomPage extends BasePage {
	public final String url;
	public final String word;
	public final int refreshTime;

	public CustomPage(String url, String elementWord, int refreshTime) {
		super(url, elementWord, refreshTime);
		this.url = url;
		this.word = elementWord;
		this.refreshTime = refreshTime;
	}

	@Override
	public String toString() {
		return "CustomPage [url=" + url + ", word=" + word + ", refreshTime=" + refreshTime + "]";
	}

	public String toCLI() {
		return "" + url;

	}

}
