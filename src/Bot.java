
import java.io.IOException;


import org. jibble.pircbot.*;
public class Bot extends PircBot
{
	public Bot()
	{
		this.setName("MusiCNerD23362018");
	}
	public void onMessage(String channel, String sender, String login, String hostname, String message)
	{
			String botResponse = "";
			try
			{
				if(Weather.isWeatherRequest(message))
				{
					botResponse= Weather.GetWeather(Weather.GetZipCode(message));
				}
				else if(TwitterInfo.isTwitterRequest(message))
				{
					botResponse=TwitterInfo.getTweets(TwitterInfo.getTwitterHandle(message));
				}
				else
				{
					botResponse = "Please ask a weather question or a Twitter question.";
				}
			}catch(Exception e)
			{
				e.printStackTrace();
		}
			sendMessage(channel, sender + botResponse);
		
	}


}
