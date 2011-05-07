package net.weasel.immenCity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.bukkit.entity.Player;

public class WebTransfer 
{
	public static String targetURL = "http://immencity.weasel.net/transfer.php";
	
	public static String arrayToString(String[] a, String separator) { return immenCity.arrayToString(a, separator); }

	public static void sendFileToURL( Player p, String file, String[] blockData )
	{
		try 
		{
		    // Construct data
			//
			// The script expects the following variables to be filled:
			//
			// f = function -- in this case, "upload"
			// p = player name
			// n = file name
			// d = data (joined into a string with ":" and urlencoded)
			//
		    String data = URLEncoder.encode("f", "UTF-8") + "=" 
		    + URLEncoder.encode("upload", "UTF-8");

		    data += URLEncoder.encode("p", "UTF-8") + "=" 
		    + URLEncoder.encode(p.getName(), "UTF-8");
		    
		    data += "&" + URLEncoder.encode("n", "UTF-8") + "=" 
		    + URLEncoder.encode(file, "UTF-8");
		    
		    data += "&" + URLEncoder.encode("d", "UTF-8") + "=" 
		    + URLEncoder.encode(arrayToString(blockData,":"), "UTF-8");

		    // Send data
		    URL url = new URL( targetURL );
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    
		    String line = "";
		    
		    while ((line = rd.readLine()) != null)
		    {
		        if( line.contains( "TRANSFER OK" ) )
		        {
		        	
		        }
		    }
		    wr.close();
		    rd.close();
		} 
		catch (Exception e) 
		{
			// TODO: create an error log entry..
		}
	}
}
