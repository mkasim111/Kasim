package config;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static void PopulateSettings() throws IOException {
		ConfigReader reader = new ConfigReader();
		reader.ReadProperty();
	}

	private void ReadProperty() throws IOException {
		Properties prop = new Properties();
		prop.load(getClass().getResourceAsStream("Config.properties"));
		Settings.url = prop.getProperty("url");
	}

}
