package properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to manage the properties.
 * 
 * @author Johannes
 *
 */
public class ProjectPropertie {

	/**
	 * Properties from the project
	 */
	private static Properties properties = null;

	/**
	 * Load the properties from the location
	 * "./src/main/resources/properties.properties"
	 * 
	 * @throws IOException
	 *             If the file couln't be found or loaded
	 */
	private static void loadProperties() throws IOException {

		File propertiesFile = new File(
				"./src/main/resources/properties.properties");
		ProjectPropertie.properties = new Properties();

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				propertiesFile));

		properties.load(bis);
		bis.close();
	}

	/**
	 * Get the properties value of the given key. Load the Properties if it
	 * isn't yet.
	 * 
	 * @param propertyName
	 *            key from the property value
	 * @return the value of the property
	 * @throws IOException
	 *             If the file couln't be found or loaded and also is the key
	 *             isn't found
	 */
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