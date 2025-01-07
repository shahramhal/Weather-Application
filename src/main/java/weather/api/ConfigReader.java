package weather.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class to read configuration from a properties file.
 */
public class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";

    /**
     * Reads the API key from the configuration file.
     *
     * @return The API key, or null if not found or an error occurs.
     */
    public static String getApiKey() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Configuration file not found in resources: " + CONFIG_FILE);
                return null;
            }
            properties.load(input);
            return properties.getProperty("WEATHER_API_KEY");
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + CONFIG_FILE);
            e.printStackTrace();
        }
        return null; // Return null if an error occurs
    }
    public static String getUnspashApiKey() {
        Properties properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Configuration file not found in resources: " + CONFIG_FILE);
                return null;
            }
            properties.load(input);
            return properties.getProperty("Unsplash_API_KEY");
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + CONFIG_FILE);
            e.printStackTrace();
        }
        return null; // Return null if an error occurs
    }
}
