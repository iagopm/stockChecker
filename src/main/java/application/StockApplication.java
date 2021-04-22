package application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import concurrency.AsyncChecker;
import concurrency.Checker;
import model.CustomAsyncPage;
import model.CustomPage;
import utils.Caster;
import utils.Container;

public class StockApplication implements Application {
	private Container container;
	private ExecutorService service = Executors.newCachedThreadPool();

	public StockApplication(Container container) {
		this.container = container;
	}

	@Override
	public boolean run() {
		if (isReady()) {
			start();
		}
		return false;
	}

	private void start() {
		container.printer.printLaunchingThreadsMessage();
		if (!container.configuration.aSyncMode) {
			container.fetcher.pages.forEach(p -> service.execute(new Checker(Caster.castToCustom(p), container)));
		} else {
			service.execute(new AsyncChecker(Caster.castToAsync(container.fetcher.pages), container));
		}
		if (container.configuration.isGuiEnabled) {
			container.guiEngine.show();
		}
	}

	private boolean isReady() {
		if (container.fetcher.pages.isEmpty()) {
			return false;
		}
		return true;
	}

}
