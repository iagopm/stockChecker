package utils;

import conf.Configuration;
import gui.GuiEngine;
import persistence.PageFetcher;
import printEngine.CustomPrinter;

public class Container {
	public Configuration configuration = new Configuration();
	public CustomPrinter printer = new CustomPrinter(this);
	public BodyFetcher bodyFetcher=new BodyFetcher(this);
	public PageFetcher fetcher=new PageFetcher(this);
	public GuiEngine guiEngine=new GuiEngine(this);
}
