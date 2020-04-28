
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterInfo {

	public static String getTweets(String user) throws TwitterException {
		ConfigurationBuilder cf = new ConfigurationBuilder();
		StringBuilder tweets = new StringBuilder();
		cf.setDebugEnabled(true).setOAuthConsumerKey("wOoSUXOcaYpEMwTrYfQWoxEPC")
				.setOAuthConsumerSecret("kLH7m757afcicKxtuBY7qt8keIwhC3SlIc3TKtdUZwTU6ipPc9")
				.setOAuthAccessToken("930277116876414977-rqTcamEZu94b9j7MaFULVtzA6i3jHT6")
				.setOAuthAccessTokenSecret("ImpMBFiGRTENqCLtIx61Ltxx5vOOdBXVSHr2vuQfcEXpo");

		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();

		try {
			List<Status> statuses;
			statuses = twitter.getUserTimeline(user);
			System.out.println("Showing @" + user + "'s user timeline.");

			for (Status status : statuses) {
				String tweet = "@" + status.getUser().getScreenName() + " - " + status.getText() + "\n";
				tweets.append(tweet);
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
		return tweets.toString();
	}

	public static boolean isTwitterRequest(String str) {
		String handlePresent = getTwitterHandle(str);
		if (handlePresent.charAt(0) == '@') {
			return true;
		} else {
			return false;
		}
	}

	public static String getTwitterHandle(String str) {
		String handle = "";
		String[] token = str.split("[\\s+@]");
		int tkposition = 0;

		/*
		 * System.out.println(token.length); for (String tt : token) {
		 * System.out.println(tt); }
		 */

		for (String tt : token) {
			if (tt.length() == 0) {
				handle = "@" + token[tkposition + 1];
				break;
			}

			tkposition++;
		}

		if (token.length == tkposition)
			handle = "empty";

		System.out.println(handle);
		return handle;
	}

	public static void main(String[] args) {
		String str = "@BarackObama";
		try {
			System.out.println(getTweets(getTwitterHandle(str)));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
