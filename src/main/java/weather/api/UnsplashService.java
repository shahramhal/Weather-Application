package weather.api;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/** Service for fetching city images from Unsplash */
public class UnsplashService {
    private static final String UNSPLASH_BASE_URL = "https://api.unsplash.com/search/photos";
    private  String API_KEY ;
    
    public UnsplashService(){
        this.API_KEY=ConfigReader.getUnspashApiKey();
    }

    public String getCityImageUrl(String cName) throws Exception {
        String encodedCityName = URLEncoder.encode(cName, StandardCharsets.UTF_8.toString());
        String urlString = UNSPLASH_BASE_URL + "?query=" + encodedCityName + "&client_id=" +API_KEY;
        
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new Exception("Error fetching image: " + conn.getResponseMessage());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        if (jsonResponse.getJSONArray("results").length() > 0) {
            return jsonResponse.getJSONArray("results")
                               .getJSONObject(0)
                               .getJSONObject("urls")
                               .getString("regular");
        } else {
            throw new Exception("No image found for city: " + cName);
        }
    }
}
