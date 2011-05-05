package net.weasel.Chunker;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class ChunkerLoad 
{
	public static void logOutput( String message ) { Chunker.logOutput(message); }

	public static void loadChunkFile( Player player, Location loc, BlockFace dir, String file )
	{
		String filename = "plugins/Chunker/" + player.getName() + "." + file + ".chunk";
		File chunkFile = new File(filename);
		
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
		
		Integer[] dims = Chunker.getChunkDimensions( player.getName() + "." + file );
		ArrayList<String> chunk = Chunker.getChunkData( player.getName() + "." + file );
		
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

						block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						block.setData( Byte.parseByte( data[1] ) );
						C++;
					}
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
						block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						block.setData( Byte.parseByte( data[1] ) );
						C++;
					}
				}
			}
			
		}

		else if( dir == BlockFace.SOUTH )
		{
			// When facing SOUTH, X = X++ and Z = Z++

			startx = loc.getZ();
			starty = loc.getY();
			startz = loc.getX();
			
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
						block = player.getWorld().getBlockAt((int)zc, (int)yc, (int)xc);
						block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						block.setData( Byte.parseByte( data[1] ) );
						C++;
					}
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
						block.setType( Material.getMaterial( Integer.parseInt(data[0]) ) );
						block.setData( Byte.parseByte( data[1] ) );
						C++;
					}
				}
			}
			

		}
		
		player.sendMessage( "Done!" );
	}
}
