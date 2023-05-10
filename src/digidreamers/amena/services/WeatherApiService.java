/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digidreamers.amena.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 *
 * @author Ahlem
 */
public class WeatherApiService {

    public WeatherApiService() {
    }

    public String getWeather() throws Exception {

        URL url = new URL("");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept", "application/json");
        con.setRequestProperty("","");
        InputStream responseStream = con.getInputStream();
        String jsonResponse = new BufferedReader(new InputStreamReader(responseStream))
                .lines().collect(Collectors.joining("\n"));
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Extract the image URL from the API response
        String imageUrl = jsonObject.getString("data");

        return imageUrl;
    }

}
