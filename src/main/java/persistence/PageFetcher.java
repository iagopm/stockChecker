package persistence;

import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import utils.Container;
import model.BasePage;

public class PageFetcher {
	private Container container;
	public List<BasePage> pages;

	public PageFetcher(Container container) {
		this.container = container;
		container.printer.printFetchingMessage(container.configuration.fetchUrl);
		pages = processRaw(container.configuration.fetchUrl);
	}

	private List<BasePage> processRaw(String url) {
		List<BasePage> pagesToReturn = new ArrayList<BasePage>();
		String body = container.bodyFetcher.getHtml(url);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(body)));
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("page");
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					String elementUrl = element.getElementsByTagName("url").item(0).getTextContent();
					String elementWord = element.getElementsByTagName("word").item(0).getTextContent();
					int elementRefreshTime = element.getElementsByTagName("time").item(0).getTextContent().equals("")
							? container.configuration.getInt("refreshDefaultTime")
							: Integer.parseInt(element.getElementsByTagName("time").item(0).getTextContent());
					pagesToReturn.add(new BasePage(elementUrl, elementWord, elementRefreshTime));
				}
			}
			pagesToReturn.forEach(p -> container.printer.printPage(p));
		} catch (Exception e) {
			container.printer.printException(e);
		}
		return pagesToReturn;
	}

}
