package utils;

import java.io.IOException;


import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class BodyFetcher {
	private Container container;

	public BodyFetcher(Container container) {
		this.container = container;
	}

	public String getHtml(String url) {
		Response response = null;
		try {
			response = Jsoup.connect(url).userAgent(container.configuration.get("browser")).timeout(container.configuration.getInt("browserTimeout")).ignoreHttpErrors(true).execute();
			return response.body();
		} catch (IOException e) {
			container.printer.printException(e);
		}
		return null;
	}

}
