package properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectPropertie {

	private static Properties properties = null;

	private static void loadProperties() throws IOException {

		File propertiesFile = new File(
				"./src/main/resources/properties.properties");
		ProjectPropertie.properties = new Properties();

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				propertiesFile));

		properties.load(bis);
		bis.close();
	}

	public static String getProperty(String propertyName) throws IOException {

		if (properties == null) {
			loadProperties();
		}

		if (!properties.containsKey(propertyName)) {
			throw new IOException("Key not found in Properties");
		}

		return properties.getProperty(propertyName);

	}
}