package model;

public class BasePage {
	public final String url;
	public final String word;
	public final int refreshTime;

	public BasePage(String url, String elementWord, int refreshTime) {
		this.url = url;
		this.word = elementWord;
		this.refreshTime = refreshTime;
	}

}
