package net.weasel.Chunker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Chunker extends JavaPlugin
{
	public static String pluginName = "";
	public static String pluginVersion = "";
	public static HashMap<Player,Location> playerBlocks = null;
	public static HashMap<Player,String> playerParams = null;
	public static HashMap<Player,BlockFace> playerFacing = null;
	
	@Override
	public void onDisable() 
	{
		logOutput( getDescription().getName() + " v" + getDescription().getVersion() + " disabled." );
	}

	@Override
	public void onEnable() 
	{
		pluginName = getDescription().getName();
		pluginVersion = getDescription().getVersion();
		
		playerBlocks = new HashMap<Player,Location>();
		playerParams = new HashMap<Player,String>();
		playerFacing = new HashMap<Player,BlockFace>();
		
		File checkDir = new File( "plugins/Chunker" );
		
		if( checkDir.exists() == false )
		{
			checkDir.mkdirs();
			logOutput( "Created new ChunkFile directory." );
		}
		
		getCommand( "chunker" ).setExecutor( new Command(this) );

		logOutput( getDescription().getName() + " v" + getDescription().getVersion() + " enabled." );
	}

	public static void logOutput( String message )
	{
		System.out.println( "[" + pluginName + "] " + message );
	}

	public static BlockFace getPlayerDirection( Player player )
	{
		BlockFace retVal = null;
		float yaw = player.getLocation().getYaw();
		
		if( yaw < 0 ) yaw += 360;
		if( yaw > 360 ) yaw -= 360;
		
		if( yaw <= 25 )
			retVal = BlockFace.WEST;
		else if( yaw <= 115 )
			retVal = BlockFace.NORTH;
		else if( yaw <= 205 )
			retVal = BlockFace.EAST;
		else
			retVal = BlockFace.SOUTH;

		return retVal;
	}

	public static void saveChunkFile( Player p, Location l, BlockFace d, String f, Integer x, Integer y, Integer z )
	{
		Block b = null;
		String file = "plugins/Chunker/" + p.getName() + "." + f + ".chunk";
		
		// Start coordinates..
		double startx, starty, startz;
		
		// End coordinates..
		double endx, endy, endz;
		
		// Counters..
		double xc, yc, zc;
		
		// Modifiers..
		double xm, ym, zm;
				
		startx = l.getX();
		starty = l.getY();
		startz = l.getZ();
		
		if( d == BlockFace.NORTH )
		{
			xm = -1;
			ym = 1;
			zm = -1;
			
			endx = startx - x;
			endy = starty + y;
			endz = startz - z;
		}
		else if( d == BlockFace.EAST )
		{
			xm = 1;
			ym = 1;
			zm = -1;
			
			endx = startx + x;
			endy = starty + y;
			endz = startz - z;
		}
		else if( d == BlockFace.SOUTH )
		{
			xm = 1;
			ym = 1;
			zm = 1;
			
			endx = startx + x;
			endy = starty + y;
			endz = startz + z;
		}
		else
		{
			xm = -1;
			ym = 1;
			zm = 1;
			
			endx = startx - x;
			endy = starty + y;
			endz = startz + z;
		}
		
    	try 
    	{
			FileWriter outFile = new FileWriter( file );
			PrintWriter outP = new PrintWriter( outFile );
			
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
			StringBuilder cDate = new StringBuilder( df.format(now) );

			outP.println( "[BEGIN CHUNKFILE " + pluginVersion + "] " + cDate );
			outP.println( "SIZE " + x + " " + y + " " + z );
			
			for( xc = startx; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc += ym )
				{
					for( zc = startz; zc != endz; zc += zm )
					{
						b = p.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
						outP.println( b.getTypeId() + " " + b.getData() );
					}
				}
			}
			
			outP.println( "[END CHUNKFILE " + pluginVersion + "] " + cDate );
			outP.close();
			
	    	p.sendMessage( ChatColor.BLUE + "Your ChunkFile '" + ChatColor.YELLOW 
	    	+ f + ChatColor.BLUE + "' was saved successfully." );
		} 
	    catch (IOException e) 
	    {
	    	p.sendMessage( "There was a problem saving your chunk file." );
			logOutput( "Error writing to ChunkFile " + file + " for " + p.getName() + "!" );
			e.printStackTrace();
		}
	}
	
	public static void loadChunkFile( Player p, Location l, BlockFace d, String f )
	{
		String filename = "plugins/Chunker/" + p.getName() + "." + f + ".chunk";
		File chunkFile = new File(filename);
		
		if( chunkFile.exists() == false )
		{
			p.sendMessage( ChatColor.RED + "Error: " + ChatColor.BLUE 
			+ "Unable to find a chunkfile named '" + ChatColor.YELLOW 
			+ f + ChatColor.BLUE + "'." );
			
			p.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
			+ "/chunker list" + ChatColor.BLUE + " to see your chunkfiles." );
			
			return;
		}
		
		String[] data = new String[1];
		Integer C = 0;
		Block b = null;
		
		// Saved chunk dimensions
		double x, y, z;
		
		// Start coordinates..
		double startx, starty, startz;
		
		// End coordinates..
		double endx, endy, endz;
		
		// Counters..
		double xc, yc, zc;
		
		// Modifiers..
		double xm, ym, zm;
		
		Integer[] dims = getChunkDimensions( p.getName() + "." + f );
		ArrayList<String> chunk = getChunkData( p.getName() + "." + f );
		
		x = dims[0];
		y = dims[1];
		z = dims[2];
		
		startx = l.getX();
		starty = l.getY();
		startz = l.getZ();
		
		if( d == BlockFace.NORTH )
		{
			xm = -1;
			ym = 1;
			zm = -1;
			
			endx = startx - x;
			endy = starty + y;
			endz = startz - z;
			
			for( zc = startz; zc != endz; zc += zm )
			{
				for( yc = starty; yc != endy; yc += ym )
				{
					for( xc = startx; xc != endx; xc += xm )
					{
						data = chunk.get(C).split(" ");
						b = p.getWorld().getBlockAt( (int)xc, (int)yc, (int)zc );
						
						b.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						b.setData( Byte.parseByte( data[1] ) );
	
						C++;
					}
				}
			}
		}		
		
		else if( d == BlockFace.EAST )
		{
			xm = 1;
			ym = 1;
			zm = -1;
			
			endx = startx + x;
			endy = starty + y;
			endz = startz - z;
			
			for( xc = startx; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc += ym )
				{
					for( zc = startz; zc != endz; zc += zm )
					{
						data = chunk.get(C).split(" ");
						b = p.getWorld().getBlockAt( (int)xc, (int)yc, (int)zc );
						
						b.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						b.setData( Byte.parseByte( data[1] ) );
	
						C++;
					}
				}
			}
		}		

		else if( d == BlockFace.SOUTH )
		{
			xm = 1;
			ym = 1;
			zm = 1;
			
			endx = startx + x;
			endy = starty + y;
			endz = startz + z;
			
			for( zc = startz; zc != endz; zc += zm )
			{
				for( yc = starty; yc != endy; yc += ym )
				{
					for( xc = startx; xc != endx; xc += xm )
					{
						data = chunk.get(C).split(" ");
						b = p.getWorld().getBlockAt( (int)xc, (int)yc, (int)zc );
						
						b.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						b.setData( Byte.parseByte( data[1] ) );
	
						C++;
					}
				}
			}
		}		
		
		else if( d == BlockFace.WEST )
		{
			xm = -1;
			ym = 1;
			zm = 1;
			
			endx = startx - x;
			endy = starty + y;
			endz = startz + z;
			
			for( xc = startx; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc += ym )
				{
					for( zc = startz; zc != endz; zc += zm )
					{
						data = chunk.get(C).split(" ");
						b = p.getWorld().getBlockAt( (int)xc, (int)yc, (int)zc );
						
						b.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						b.setData( Byte.parseByte( data[1] ) );
	
						C++;
					}
				}
			}
		}		

		p.sendMessage( "Done!" );
	}
	
	public static Integer[] getChunkDimensions( String f )
	{
		Integer[] retVal = { 0, 0, 0 };
		String filename = "plugins/Chunker/" + f;
		String line = "";
	
		if( filename.endsWith(".chunk") == false ) filename = filename + ".chunk";
		
		try 
		{
			FileInputStream fstream = new FileInputStream( filename );
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				line = in.readLine().toString();
				
				if( line.startsWith( "SIZE " ) == true )
				{
					String temp[] = line.substring( 5 ).split( " " );
					
					if( temp.length == 3 )
					{
						retVal[0] = Integer.parseInt(temp[0]);
						retVal[1] = Integer.parseInt(temp[1]);
						retVal[2] = Integer.parseInt(temp[2]);
					}
				}
			}
			
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return( retVal );
	}

	public static ArrayList<String> getChunkData( String f )
	{
		ArrayList<String> retVal = new ArrayList<String>();
		String filename = "plugins/Chunker/" + f;
		String line = "";
	
		if( filename.endsWith(".chunk") == false ) filename = filename + ".chunk";
		
		try 
		{
			FileInputStream fstream = new FileInputStream( filename );
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				line = in.readLine().toString().trim();
				
				if( line.startsWith("SIZE ") == false 
					&& line.startsWith("[") == false 
					&& line.equals("") == false )
				{
					retVal.add(line);
				}
			}
			
			in.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return( retVal );
	}
}