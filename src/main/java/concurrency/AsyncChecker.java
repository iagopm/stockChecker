package concurrency;

import java.util.ArrayList;
import java.util.List;

import model.CustomAsyncPage;
import utils.Container;

public class AsyncChecker extends Thread {
	private List<CustomAsyncPage> pages = new ArrayList<>();
	private Container container;
	private CustomAsyncPage latestPageRefreshed;

	public AsyncChecker(List<CustomAsyncPage> pages, Container container) {
		this.pages = pages;
		this.container = container;
		latestPageRefreshed = this.pages.get(0);
	}

	@Override
	public void run() {
		while (true) {
			latestPageRefreshed.latestBody = container.bodyFetcher.getHtml(latestPageRefreshed.url);
			container.printer.printRefreshingPage(latestPageRefreshed);
			container.printer.printBody(latestPageRefreshed.latestBody);
			if (customContains(latestPageRefreshed.word, latestPageRefreshed.latestBody)) {
				container.printer.printRefreshingPageAvailable(latestPageRefreshed);
				latestPageRefreshed.isAvailable = true;
			} else {
				latestPageRefreshed.isAvailable = false;
			}
			container.guiEngine.updateAsync(pages, latestPageRefreshed, latestPageRefreshed.isAvailable);

			latestPageRefreshed = pages.get((pages.indexOf(latestPageRefreshed) + 1 == pages.size()) ? 0
					: pages.indexOf(latestPageRefreshed) + 1);

			try {
				Thread.sleep(container.configuration.getInt("refreshAsyncDefaultTime"));
			} catch (InterruptedException e) {
				container.printer.printException(e);
			}
		}
	}

	private boolean customContains(String word, String body) {
		if (word.contains(container.configuration.get("splitChar"))) {
			String[] words = word.split(container.configuration.get("splitChar"));
			for (String w : words) {
				if (body.contains(w)) {
					return true;
				}
			}
		} else if (body.contains(word)) {
			return true;
		}
		return false;
	}
}
