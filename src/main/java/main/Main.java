package main;

import application.StockApplication;
import utils.Container;

public class Main {

	public static void main(String[] args) {
		Container container = new Container();
		StockApplication stockApplication = new StockApplication(container);
		stockApplication.run();
	}
}
