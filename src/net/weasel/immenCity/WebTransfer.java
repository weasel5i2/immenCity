package net.weasel.immenCity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WebTransfer 
{
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
		    URL url = new URL( immenCity.onlineRepoURL + "/transfer.php" );
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
		        if( line.contains( "000 OK" ) )
		        {
		        	String[] response = line.split( " " );
		        	String manageURL = immenCity.onlineRepoURL + "/b/?" + response[2]; 
		        	p.sendMessage( ChatColor.BLUE + "Your chunkfile '" + ChatColor.YELLOW 
		        	+ file + ChatColor.BLUE + "' was saved successfully." );
		        	p.sendMessage( ChatColor.BLUE + "You can manage it at " + ChatColor.YELLOW 
				    + manageURL + ChatColor.BLUE + "." );
		        }
		        else if( line.contains( "002 UNABLE TO SAVE CHUNKFILE" ) )
		        {
		        	// Uh oh..
		        }
		        else if( line.contains( "003 FILE EXISTS " ) )
		        {
		        	// Uh oh..
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
