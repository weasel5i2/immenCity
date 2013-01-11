package net.weasel.immenCity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

//When facing NORTH:  X = X-- and Z = Z--
//When facing EAST:   X = Z++ and Z = X--
//When facing SOUTH:  X = X-- and Z = Z++
//When facing WEST:   X = Z-- and Z = X--

public class SaveStructure 
{
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static void dbgOutput( String message ) { immenCity.dbgOutput(message); }
	
	public static void saveChunkFile( Player player, Location loc, BlockFace dir, String file, Integer x, Integer y, Integer z )
	{
		ArrayList<String> blocks = new ArrayList<String>();
		
		Block block = null;
		
		// Counters..
		double xc, yc, zc;
		double X, Y, Z;

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
						
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );
		
						dbgOutput( "block " + X + "," + Y + "," + Z + " = " 
						+ block.getTypeId() + ":" + block.getData() );
								
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}

			player.sendMessage( "Saving " + blocks.size() + " blocks.." );
			saveChunkData( player, blocks, dir, file, x, y, z );
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
						
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );
		
						dbgOutput( "block " + X + "," + Y + "," + Z + " = " 
						+ block.getTypeId() + ":" + block.getData() );
								
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}

			player.sendMessage( "Saving " + blocks.size() + " blocks.." );
			saveChunkData( player, blocks, dir, file, x, y, z );
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
						
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );
		
						dbgOutput( "block " + X + "," + Y + "," + Z + " = " 
						+ block.getTypeId() + ":" + block.getData() );
								
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );
			saveChunkData( player, blocks, dir, file, x, y, z );
		}
		else
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
						
						block = player.getWorld().getBlockAt( (int)X, (int)Y, (int)Z );
		
						dbgOutput( "block " + X + "," + Y + "," + Z + " = " 
						+ block.getTypeId() + ":" + block.getData() );
								
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );
			saveChunkData( player, blocks, dir, file, x, y, z );
		}
	}

	public static void saveChunkData( Player p, ArrayList<String> data, BlockFace dir, String file, int x, int y, int z )
	{
		int counter = 0;
		String filename = "plugins/immenCity/" + p.getName() + "." + file + ".chunk";
		
		try
	    {
			FileWriter outFile = new FileWriter( filename );
			PrintWriter outP = new PrintWriter( outFile );
			
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
			StringBuilder cDate = new StringBuilder( df.format(now) );

			outP.println( "[BEGIN CHUNKFILE " + immenCity.pluginVersion + "] " + cDate );
			outP.println( "[SIZE " + x + " " + y + " " + z + "]" );
			outP.println( "[OO " + dir.name() + "]" );
			
			for( counter = 0; counter < data.size(); counter++ )
			{
				outP.println( data.get(counter) );
			}
			
			outP.println( "[END CHUNKFILE " + immenCity.pluginVersion + "] " + cDate );
			outP.close();

			p.sendMessage( ChatColor.BLUE + "Your ChunkFile '" + ChatColor.YELLOW 
	    	+ file + ChatColor.BLUE + "' was saved successfully." );
		} 
	    catch (IOException e) 
	    {
	    	p.sendMessage( "There was a problem saving your chunk file." );
			logOutput( "Error writing to ChunkFile " + file + " for " + p.getName() + "!" );
			e.printStackTrace();
		}

	}
}
