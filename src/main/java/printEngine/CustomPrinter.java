package printEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.CustomPage;
import utils.Container;

public class CustomPrinter {
	private static final Logger logger = LogManager.getLogger(CustomPrinter.class.getName());
	public Container container;

	public CustomPrinter(Container container) {
		this.container = container;
		printStartingMessage();
	}

	private void logAndSout(String text) {
		logger.info(text);
		System.out.println(text);
	}

	private void sout(String text) {
		logger.info(text);
		System.out.println(text);
	}

	private void logErrorAndSout(String text) {
		logger.error(text);
		System.out.println(text);
	}

	public void printStartingMessage() {
		logAndSout(container.configuration.get("startingMessage"));
	}

	public void printLaunchingThreadsMessage() {
		logAndSout(container.configuration.get("launchingThreadsMessage"));
	}

	public void printFetchingMessage(String url) {
		logAndSout(url);
	}

	public void printBody(String body) {
		sout(body);
	}

	public void printException(Exception e) {
		logErrorAndSout(e.getMessage());
	}

	public void printPage(CustomPage page) {
		logAndSout(page.toString());
	}

	public void printRefreshingPage(CustomPage page) {
		logAndSout(page.toCLI());
	}

	public void printRefreshingPageAvailable(CustomPage page) {
		logAndSout(container.configuration.get("availableMessage") + page.toCLI());

	}
}
