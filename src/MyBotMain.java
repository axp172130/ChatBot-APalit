import org.jibble.pircbot.*;
public class MyBotMain 
{
public static void main(String[] args) throws Exception
{
	Bot bot = new Bot();
	
	bot.setVerbose(true);
	bot.connect("irc.freenode.net");
	bot.joinChannel("#sNaPcHbot");
	

}
}
