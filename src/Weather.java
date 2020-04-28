


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class Weather {

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		return map;
	}

	public static String GetWeather(String LOCATION) throws IOException {
		String API_KEY = "c20cbd5e57e543f86e53be25d0a7be08";

		String urlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + LOCATION + "&appid=" + API_KEY
				+ "&units=imperial";

		StringBuilder result = new StringBuilder();
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		System.out.println(result);

		Map<String, Object> respMap = jsonToMap(result.toString());
		Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());

		System.out.println("Current Temperature: " + mainMap.get("temp"));
		System.out.println("Minimum Temperature: " + mainMap.get("temp_min"));
		System.out.println(" Maximum Temperature: " + mainMap.get("temp_max"));
		System.out.println("Name: "+ respMap.get("name"));
		String weather = " The weather at " + respMap.get("name") + ", " + LOCATION + " is going to be " + mainMap.get("temp") + " with a high of "
				+ mainMap.get("temp_max") + " and a low of " + mainMap.get("temp_min");

		return weather;
	}


	public static String GetZipCode(String str) {
		String zip = "";
		String[] token = str.split("[ ?]");
		for (String ss : token) {
			if (ss.matches("\\d{5}")) {
				zip = ss;
			}
		}
		return zip;

	}
	public static boolean isWeatherRequest(String str)
	{
		String zipPresent= GetZipCode(str);
		if(zipPresent.matches("\\d{5}") ==true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static void main(String[] args) {
		String str = "How is the weather in 75022?";
		System.out.println(GetZipCode(str));

		try {
			String weather = GetWeather(GetZipCode(str));

			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

