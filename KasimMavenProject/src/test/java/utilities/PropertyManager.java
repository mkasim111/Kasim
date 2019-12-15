package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
	private static PropertyManager instance;
	private static final Object lock = new Object();
	private static String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\configFolder\\Config.properties";

	private static String url;
	private static String implicitTime;

	// Create a Singleton instance. We need only one instance of Property Manager.
	public static PropertyManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new PropertyManager();
				instance.loadData();
			}
		}
		return instance;

	}

	// Get all configuration data and assign to related fields.

	private void loadData() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(propertyFilePath));
		} catch (IOException e) {
			System.out.println("Configuration properties file cannot be found");
		}

		// Get properties from configuration.properties
		url = prop.getProperty("url");
		implicitTime = prop.getProperty("implicitTime");

	}

	public String getUrl() {
		return url;

	}

	public String getImplicitTime() {
		return implicitTime;

	}

}
