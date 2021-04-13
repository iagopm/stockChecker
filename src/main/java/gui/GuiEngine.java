package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import model.CustomAsyncPage;
import model.CustomPage;
import utils.Container;

public class GuiEngine {
	private Container container;
	private JTable jTable;
	private String[][] classData;
	String column[] = { "URL", "AVAILABLE", "LASTUPDATED" };

	public GuiEngine(Container container) {
		this.container = container;
	}

	public void show() {
		JFrame frame = new JFrame();
		String data[][] = populateBidiArray();
		jTable = new JTable(data, column) {
			private static final long serialVersionUID = 6450926375596816984L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				Object value = getModel().getValueAt(row, col);
				if (value.equals("false")) {
					comp.setBackground(Color.red);
				} else if (value.equals("true")) {
					comp.setBackground(Color.green);
				} else {
					comp.setBackground(Color.white);
				}
				return comp;
			}
		};
		jTable.setBounds(30, 40, 200, 300);
		JScrollPane scrollPane = new JScrollPane(jTable);
		frame.add(scrollPane);
		frame.setSize(container.configuration.getInt("defaultWidth"), container.configuration.getInt("defaultHeight"));
		frame.setVisible(true);
	}

	private String[][] populateBidiArray() {
		String[][] array = new String[container.fetcher.pages.size()][3];
		for (int i = 0; i < container.fetcher.pages.size(); i++) {
			array[i][0] = container.fetcher.pages.get(i).url;
			array[i][1] = "";
			array[i][2] = new Date().toString();
		}
		classData = array;
		return array;
	}

	public void update(CustomPage page, boolean isAvailable) {
		int indexOf = container.fetcher.pages.indexOf(page);
		classData[indexOf][1] = "" + isAvailable;
		classData[indexOf][2] = new Date().toString();
		jTable.repaint();
	}

	public void updateAsync(List<CustomAsyncPage> pages, CustomAsyncPage latestPageRefreshed, boolean isAvailable) {
		int indexOf = pages.indexOf(latestPageRefreshed);
		classData[indexOf][1] = "" + latestPageRefreshed.isAvailable;
		classData[indexOf][2] = new Date().toString();
		jTable.repaint();

	}
}
