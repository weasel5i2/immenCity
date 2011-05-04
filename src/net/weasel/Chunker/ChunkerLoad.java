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
		double xm, zm;
		
		Integer[] dims = Chunker.getChunkDimensions( p.getName() + "." + f );
		ArrayList<String> chunk = Chunker.getChunkData( p.getName() + "." + f );
		
		x = dims[0];
		y = dims[1];
		z = dims[2];
		
		startx = l.getX();
		starty = l.getY();
		startz = l.getZ();

		endy = starty + y;

		if( d == BlockFace.NORTH )
		{
			// When facing NORTH, X = Z-- and Z = X--
			xm = -1;
			zm = -1;
				
			endx = startz - x;
			endz = startx - z;

			for( xc = startz; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( zc = startx; zc != endz; zc += zm )
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
			// When facing EAST, X = X++ and Z = Z--
			xm = 1;
			zm = -1;
				
			endx = startx + x;
			endz = startz - z;

			for( xc = startx; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc++ )
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

		if( d == BlockFace.SOUTH )
		{
			// When facing SOUTH, X = Z++ and Z = X++
			xm = 1;
			zm = 1;
				
			endx = startz + x;
			endz = startx + z;

			for( xc = startz; xc != endz; xc += xm )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( zc = startx; zc != endx; zc += zm )
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
			// When facing WEST, X = X-- and Z = Z++
			xm = -1;
			zm = 1;
				
			endx = startx - x;
			endz = startz + z;

			for( xc = startx; xc != endx; xc += xm )
			{
				for( yc = starty; yc != endy; yc++ )
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
}
