package net.weasel.immenCity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

//When facing NORTH:  X = X-- and Z = Z--
//When facing EAST:   X = Z++ and Z = X--
//When facing SOUTH:  X = X-- and Z = Z++
//When facing WEST:   X = Z-- and Z = X--

public class LoadStructure 
{
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static void dbgOutput( String message ) { immenCity.dbgOutput(message); }

	public static void setOrientedBlocks( Player player, BlockFace dir, BlockFace oldDir, ArrayList<Location> orientedBlocks, HashMap<Location,String> obData )
	{
		String[] data = new String[1];
		Block block = null;
		
		for( int ob = 0; ob < orientedBlocks.size(); ob++ )
		{
			block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
			data = obData.get( orientedBlocks.get(ob) ).split( " " );
			
			block.setTypeId( Integer.parseInt(data[0]) );
			Rotator.rotate( block, Integer.parseInt(data[1]), oldDir, dir );
		}
		for( int ob = 0; ob < orientedBlocks.size(); ob++ )
		{
			block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
			data = obData.get( orientedBlocks.get(ob) ).split( " " );
			
			block.setTypeId( Integer.parseInt(data[0]) );
			Rotator.rotate( block, Integer.parseInt(data[1]), oldDir, dir );
		}
	}
	
	public static void loadChunkFile( Player player, Location loc, BlockFace dir, String file )
	{
		String filename = "plugins/immenCity/" + player.getName() + "." + file + ".chunk";
		File chunkFile = new File(filename);
		BlockFace oldDir = null;

		ArrayList<Location> orientedBlocks = new ArrayList<Location>();
		HashMap<Location,String> obData = new HashMap<Location,String>();
		
		if( chunkFile.exists() == false )
		{
			player.sendMessage( ChatColor.RED + "Error: " + ChatColor.BLUE 
			+ "Unable to find a chunkfile named '" + ChatColor.YELLOW 
			+ file + ChatColor.BLUE + "'." );
			
			player.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
			+ "/chunker list" + ChatColor.BLUE + " to see your chunkfiles." );
			
			return;
		}
		
		String[] data = new String[1];
		Integer C = 0;
		Block block = null;
		
		// Saved chunk dimensions
		double x, y, z;
		
		// Counters..
		double xc, yc, zc;
		double X, Y, Z;
		
		Integer[] dims = getChunkDimensions( player.getName() + "." + file );
		ArrayList<String> chunk = getChunkData( player.getName() + "." + file );
		oldDir = BlockFace.valueOf(getChunkOrientation( player.getName() + "." + file ) );
		
		x = dims[0];
		y = dims[1];
		z = dims[2];
		
		if( dir == BlockFace.NORTH )
		{
			// When facing NORTH:  X = X-- and Z = Z--

			for( zc = 0; zc < z; zc++ )
			{
				for( yc = 0; yc < y; yc++ )
				{
					for( xc = 0; xc < x; xc++ )
					{
						X = loc.getX() - xc;
						Y = loc.getY() + yc;
						Z = loc.getZ() - zc;
						
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );

						if( Rotator.orientedBlocks.containsKey( Integer.parseInt(data[0]) ) )
						{
							orientedBlocks.add( block.getLocation() );
							obData.put( block.getLocation(), chunk.get(C) );
						}
						else
						{
							block.setTypeId( Integer.parseInt(data[0]) );
							block.setData( (byte)Integer.parseInt(data[1]) );
						}
							
						C++;
					}
				}
			}
			
			if( orientedBlocks.size() > 0 ) 
				setOrientedBlocks( player, dir, oldDir, orientedBlocks, obData );
		}

		else if( dir == BlockFace.EAST )
		{
			// When facing EAST:   X = Z++ and Z = X--

			for( zc = 0; zc < z; zc++ )
			{
				for( yc = 0; yc < y; yc++ )
				{
					for( xc = 0; xc < x; xc++ )
					{
						X = loc.getX() + zc;
						Y = loc.getY() + yc;
						Z = loc.getZ() - xc;
						
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );
						
						if( Rotator.orientedBlocks.containsKey( Integer.parseInt(data[0]) ) )
						{
							orientedBlocks.add( block.getLocation() );
							obData.put( block.getLocation(), chunk.get(C) );
						}
						else
						{
							block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
							block.setData( Byte.parseByte( data[1] ) );
						}
						C++;
					}
				}
			}
			
			if( orientedBlocks.size() > 0 ) 
				setOrientedBlocks( player, dir, oldDir, orientedBlocks, obData );
		}

		else if( dir == BlockFace.SOUTH )
		{
			// When facing SOUTH:  X = X-- and Z = Z++

			for( zc = 0; zc < z; zc++ )
			{
				for( yc = 0; yc < y; yc++ )
				{
					for( xc = 0; xc < x; xc++ )
					{
						X = loc.getX() - xc;
						Y = loc.getY() + yc;
						Z = loc.getZ() + zc;
						
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );

						if( Rotator.orientedBlocks.containsKey( Integer.parseInt(data[0]) ) )
						{
							orientedBlocks.add( block.getLocation() );
							obData.put( block.getLocation(), chunk.get(C) );
						}
						else
						{
							block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
							block.setData( Byte.parseByte( data[1] ) );
						}
						C++;
					}
				}
			}

			if( orientedBlocks.size() > 0 ) 
				setOrientedBlocks( player, dir, oldDir, orientedBlocks, obData );
		}

		else if( dir == BlockFace.WEST )
		{
			// When facing WEST:   X = Z-- and Z = X--

			for( zc = 0; zc < z; zc++ )
			{
				for( yc = 0; yc < y; yc++ )
				{
					for( xc = 0; xc < x; xc++ )
					{
						X = loc.getX() - zc;
						Y = loc.getY() + yc;
						Z = loc.getZ() - xc;
						
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );

						if( Rotator.orientedBlocks.containsKey( Integer.parseInt(data[0]) ) )
						{
							orientedBlocks.add( block.getLocation() );
							obData.put( block.getLocation(), chunk.get(C) );
						}
						else
						{
							block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
							block.setData( Byte.parseByte( data[1] ) );
						}
							
						C++;
					}
				}
			}
			
			if( orientedBlocks.size() > 0 ) 
				setOrientedBlocks( player, dir, oldDir, orientedBlocks, obData );
		}
		
		player.sendMessage( "Done!" );
	}

	public static Integer[] getChunkDimensions( String f )
	{
		Integer[] retVal = { 0, 0, 0 };
		String filename = "plugins/immenCity/" + f;
		String line = "";
	
		if( filename.endsWith(".chunk") == false ) filename = filename + ".chunk";
		
		try 
		{
			FileInputStream fstream = new FileInputStream( filename );
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				line = in.readLine().toString();
				
				if( line.startsWith( "[SIZE " ) == true )
				{
					line = line.replace( "[", "" );
					line = line.replace( "]", "" );
					
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
		String filename = "plugins/immenCity/" + f;
		String line = "";
	
		if( filename.endsWith(".chunk") == false ) filename = filename + ".chunk";
		
		try 
		{
			FileInputStream fstream = new FileInputStream( filename );
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				line = in.readLine().toString();
				
				if( line.startsWith( "[OO " ) == true )
				{
					line = line.replace( "[", "" );
					line = line.replace( "]", "" );

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
		String filename = "plugins/immenCity/" + f;
		String line = "";
	
		if( filename.endsWith(".chunk") == false ) filename = filename + ".chunk";
		
		try 
		{
			FileInputStream fstream = new FileInputStream( filename );
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
			
			while(in.ready()) 
			{
				line = in.readLine().toString().trim();
				
				if( line.startsWith("[") == false && line.equals("") == false )
					retVal.add(line);
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
