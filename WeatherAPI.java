import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherAPI {
    public static String getTemperatureAtLocation(String location) throws Exception{


        String apiKey = "YOUR_API_KEY";
        String apiUrl = "YOUR_API_PROVIDER=" + location +"YOUR_API_KEY"  + "&appid=" + apiKey;
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
        throw new Exception("Couldnt find a value for specific location:" + location);
    }
}

