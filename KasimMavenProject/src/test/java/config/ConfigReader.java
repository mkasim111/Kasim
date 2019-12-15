package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static void PopulateSettings() throws IOException {
		ConfigReader reader = new ConfigReader();
		reader.ReadProperty();
	}

	private void ReadProperty() throws IOException {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(new File(System.getProperty("user.dir")+
					"\\src\\test\\resources\\configFolder\\Config.properties")));
			Settings.url = prop.getProperty("url");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
