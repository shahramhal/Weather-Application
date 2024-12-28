import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {
    public static void main(String[] args) {
        try {
            // Example API URL
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?city=London";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response to the console to check it
            System.out.println("API Response: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
