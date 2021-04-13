package conf;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class Configuration {
	private ResourceBundle bundle = ResourceBundle.getBundle("application",Locale.getDefault());
	public boolean isGuiEnabled = false;
	public boolean aSyncMode = false;
	public String fetchUrl = "";

	public Configuration() {
		isGuiEnabled = JOptionPane.showInputDialog(get("enableGuiText")).equalsIgnoreCase(get("enableGuiWord")) ? true : false;
		fetchUrl = JOptionPane.showInputDialog(get("urlInputText"));
		aSyncMode = JOptionPane.showInputDialog(get("aSyncModeText")).equalsIgnoreCase(get("enableaSyncWord")) ? true : false;

	}

	public String get(String property) {
		return bundle.getString(property);
	}

	public int getInt(String property) {
		return Integer.parseInt(bundle.getString(property));
	}
}
