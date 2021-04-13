package model;

public class CustomAsyncPage extends CustomPage {

	public final String url;
	public final String word;
	public final int refreshTime;
	public String latestBody = "";
	public boolean isAvailable = false;

	public CustomAsyncPage(String url, String elementWord, int refreshTime) {
		super(url, elementWord, refreshTime);
		this.word = elementWord;
		this.url = url;
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
