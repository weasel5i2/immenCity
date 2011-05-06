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
		
		// Wooden door
		//
		if( type == 64 )
		{
			if( newDir == BlockFace.NORTH )
			{
				if( oldDir == BlockFace.EAST )
				{
					if( data == 3 ) retVal = 2;
					if( data == 11 ) retVal = 10;
				}
				
				if( oldDir == BlockFace.WEST )
				{
					if( data == 1 ) retVal = 2;
					if( data == 9 ) retVal = 10;
				}

				if( oldDir == BlockFace.SOUTH )
				{
					if( data == 0 ) retVal = 2;
					if( data == 8 ) retVal = 10;
				}
			}

			if( newDir == BlockFace.EAST )
			{
				if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH  )
				{
					if( data == 0 || data == 1 || data == 2 ) 
						retVal = 3;
					else if( data == 8 || data == 9 || data == 10 )
						retVal = 11;
				}
			}

			if( newDir == BlockFace.SOUTH )
			{
				if( oldDir == BlockFace.EAST || oldDir == BlockFace.WEST )
				{
					if( data == 1 || data == 2 || data == 3 ) 
						retVal = 0;
					else if( data == 9 || data == 10 || data == 11 )
						retVal = 8;
				}
			}

			if( newDir == BlockFace.WEST )
			{
				if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH  )
				{
					if( data == 0 || data == 2 || data == 3 ) 
						retVal = 1;
					else if( data == 8 || data == 10 || data == 11 )
						retVal = 9;
				}
			}
		}
		
		// Standard rails
		//
		if( type == 66 )
		{
			// Straight rails
			//
			if( data == 0 || data == 1 )
			{
				if( newDir == BlockFace.WEST || newDir == BlockFace.EAST )
				{
					if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH )
					{
						if( data == 1 ) retVal = 0;
						if( data == 0 ) retVal = 1;
					}
				}
				
				else if( newDir == BlockFace.NORTH || newDir == BlockFace.SOUTH )
				{
					if( oldDir == BlockFace.EAST || oldDir == BlockFace.WEST )
					{
						if( data == 1 ) retVal = 0;
						if( data == 0 ) retVal = 1;
					}
				}
			}
						
			// Corner rails
			//
			else if( data >= 6 || data <= 9 )
			{
				if( newDir == BlockFace.NORTH )
				{
					if( oldDir == BlockFace.EAST )
					{
						if( data == 6 ) retVal = 9;
						if( data == 7 ) retVal = 6;
						if( data == 8 ) retVal = 7;
						if( data == 9 ) retVal = 8;
					}
					else if( oldDir == BlockFace.SOUTH )
					{
						if( data == 7 ) retVal = 9;
						if( data == 8 ) retVal = 6;
						if( data == 9 ) retVal = 7;
						if( data == 6 ) retVal = 8;
					}
					else if( oldDir == BlockFace.WEST )
					{
						if( data == 8 ) retVal = 9;
						if( data == 9 ) retVal = 6;
						if( data == 6 ) retVal = 7;
						if( data == 7 ) retVal = 8;
					}
				}

				if( newDir == BlockFace.EAST )
				{
					if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH  )
					{
						if( data == 7 ) retVal = 6;
						if( data == 8 ) retVal = 7;
					}
				}
			
				if( newDir == BlockFace.SOUTH )
				{
					if( oldDir == BlockFace.EAST || oldDir == BlockFace.WEST )
					{
						if( data == 7 ) retVal = 7;
						if( data == 8 ) retVal = 8;
					}
				}

				if( newDir == BlockFace.WEST )
				{
					if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH  )
					{
						if( data == 7 ) retVal = 8;
						if( data == 8 ) retVal = 9;
					}
				}
			}
		}
		
		System.out.println( "Block " + type + " old=" + data + ", new=" + retVal );
		
		return retVal;
	}
}