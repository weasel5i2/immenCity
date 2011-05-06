package net.weasel.Chunker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Location;
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

	public static String getChunkOrientation( String f )
	{
		String retVal = "";
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
				
				if( line.startsWith( "OO " ) == true )
				{
					retVal = line.substring( 3 );
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
					&& line.startsWith("OO ") == false 
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

	public static boolean isOrientedBlockType( int type )
	{
		boolean retVal = false;

		// Wooden door
		//
		if( type == 64 ) retVal = true;
		
		// Standard rails
		//
		if( type == 66 ) retVal = true;
		
		return retVal;
	}
	
	public static int reorientBlockData( BlockFace oldDir, BlockFace newDir, int type, int data )
	{
		int retVal = data;
		
		// Door
		//
		if( type == 64 || type == 71 )
		{
			retVal = Door.reorientBlockData( oldDir, newDir, type, data );
		}
		
		// Rails
		//
		if( type == 66 )
		{
			retVal = Rail.reorientBlockData( oldDir , newDir, type, data );
		}
		
		// Torches
		if( type == 50 )
		{
			retVal = Torch.reorientBlockData( oldDir , newDir, type, data );
		}
		
		System.out.println( "Block " + type + " old=" + data + ", new=" + retVal );
		
		return retVal;
	}
}