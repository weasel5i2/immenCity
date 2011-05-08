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

public class LoadStructure 
{
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static boolean isOrientedBlockType( int type ) { return immenCity.isOrientedBlockType(type); }
	public static int reorientAllBlockData( BlockFace oldDir, BlockFace newDir, int type, int data ) { return immenCity.reorientAllBlockData(oldDir, newDir, type, data); }

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
		
		// Start coordinates..
		double startx, starty, startz;
		
		// End coordinates..
		double endx, endy, endz;
		
		// Counters..
		double xc, yc, zc;
		
		Integer[] dims = getChunkDimensions( player.getName() + "." + file );
		ArrayList<String> chunk = getChunkData( player.getName() + "." + file );
		oldDir = BlockFace.valueOf(getChunkOrientation( player.getName() + "." + file ) );
		
		x = dims[0];
		y = dims[1];
		z = dims[2];
		
		startx = loc.getX();
		starty = loc.getY();
		startz = loc.getZ();

		endy = starty + y;

		if( dir == BlockFace.NORTH )
		{
			// When facing NORTH, X = Z-- and Z = X--
			startx = loc.getZ();
			starty = loc.getY();
			startz = loc.getX();
			
			endx = startx - x;
			endy = starty + y;
			endz = startz - z;

			for( zc = startz; zc != endz; zc-- )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc-- )
					{
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt((int)zc, (int)yc, (int)xc);
						
						if( isOrientedBlockType( Integer.parseInt(data[0]) ) == true )
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
			{
				int newOrientation = 0;
				
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					
					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					block.setData( (byte)newOrientation );
				}
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					
					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					block.setData( (byte)newOrientation );
				}
			}
		}

		else if( dir == BlockFace.EAST )
		{
			// When facing EAST, X = X++ and Z = Z--
			startx = loc.getX();
			starty = loc.getY();
			startz = loc.getZ();
			
			endx = startx + x;
			endy = starty + y;
			endz = startz - z;

			for( zc = startz; zc != endz; zc-- )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc++ )
					{
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
						
						if( isOrientedBlockType( Integer.parseInt(data[0]) ) == true )
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
			{
				int newOrientation = 0;
				
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
			}
		}

		else if( dir == BlockFace.SOUTH )
		{
			// When facing SOUTH, X = X++ and Z = Z++

			startx = loc.getX();
			starty = loc.getY();
			startz = loc.getZ();
			
			endx = startx + x;
			endy = starty + y;
			endz = startz + z;

			for( xc = startx; xc != endx; xc++ )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( zc = startz; zc != endz; zc++ )
					{
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);

						if( isOrientedBlockType( Integer.parseInt(data[0]) ) == true )
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
			{
				int newOrientation = 0;
				
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
			}
		}

		else if( dir == BlockFace.WEST )
		{
			// When facing WEST, X = X-- and Z = Z++

			startx = loc.getX();
			starty = loc.getY();
			startz = loc.getZ();
			
			endx = startx - x;
			endy = starty + y;
			endz = startz + z;

			for( zc = startz; zc != endz; zc++ )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc-- )
					{
						data = chunk.get(C).split(" ");
						block = player.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);

						if( isOrientedBlockType( Integer.parseInt(data[0]) ) == true )
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
			{
				int newOrientation = 0;
				
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
				for( int ob = 0; ob < orientedBlocks.size(); ob++ )
				{
					block = player.getWorld().getBlockAt(orientedBlocks.get(ob));
					data = obData.get( orientedBlocks.get(ob) ).split( " " );

					newOrientation = reorientAllBlockData( oldDir, dir, Integer.parseInt(data[0]), Integer.parseInt(data[1]) );
					
					block.setTypeId( Integer.parseInt(data[0]) );
					block.setData( (byte)newOrientation );
				}
			}
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
}
