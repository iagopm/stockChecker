package concurrency;

import model.CustomPage;
import utils.Container;

public class Checker extends Thread {
	private final CustomPage page;
	private Container container;
	private boolean isAvailable = false;

	public Checker(CustomPage page, Container container) {
		this.page = page;
		this.container = container;
	}

	private String latestBody = "";

	@Override
	public void run() {
		while (true) {
			latestBody = container.bodyFetcher.getHtml(page.url);
			container.printer.printRefreshingPage(page);
			container.printer.printBody(latestBody);
			if (customContains(page.word, latestBody)) {
				container.printer.printRefreshingPageAvailable(page);
				isAvailable = true;
			} else {
				isAvailable = false;
			}
			container.guiEngine.update(page, isAvailable);
			try {
				Thread.sleep(page.refreshTime);
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
