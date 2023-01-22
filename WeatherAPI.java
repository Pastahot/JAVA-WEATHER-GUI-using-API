import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherAPI {
    public static String getTemperatureAtLocation(String location) throws Exception{


        // Using the Weatherbit API to retrieve the weather for the specified location
        String apiKey = "77c30523f7a24c9283858b1a95629f2b";
        String apiUrl = "https://api.weatherbit.io/v2.0/current?&city=" + location +"&key=77c30523f7a24c9283858b1a95629f2b&include=minutely"  + "&appid=" + apiKey;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Reading response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
               // System.out.println(line);

                    // JSON string containing the "app_temp" field
                    String jsonString = line;

                    // Trying to match the value after the "app_temp" field
                    String temperatureAPI = "\"app_temp\":(-?\\d+(?:\\.\\d+)?)";

                    // Create a Pattern object
                    Pattern pattern = Pattern.compile(temperatureAPI);

                    // Create a Matcher object
                    Matcher matcher = pattern.matcher(jsonString);

                    // Find the first occurrence of the pattern
                    if (matcher.find()) {
                        // Get the value after the "app_temp" field
                        String value = matcher.group(1);

                        // Print the value
                        System.out.println(value);
                        reader.close();
                        return value;
                    }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        throw new Exception("Nepodarilo sa zistit hodnotu pre lokaciu:" + location);
    }
}

