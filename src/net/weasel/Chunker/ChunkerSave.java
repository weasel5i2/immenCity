package net.weasel.Chunker;

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

public class ChunkerSave 
{
	public static void logOutput( String message ) { Chunker.logOutput(message); }
	
	public static void saveChunkFile( Player player, Location loc, BlockFace dir, String file, Integer x, Integer y, Integer z )
	{
		ArrayList<String> blocks = new ArrayList<String>();
		
		Block block = null;
		
		// Counters..
		double xc, yc, zc;
		
		// Start coordinates..
		double startx, starty, startz;
		
		// End coordinates..
		double endx, endy, endz;

		if( dir == BlockFace.NORTH )
		{
			// When facing NORTH, X = Z-- and Z = X--

			startx = loc.getZ();
			starty = loc.getY();
			startz = loc.getX();
			
			endx = startx - x;
			endy = starty + y;
			endz = startz - z;

			logOutput( "startX: " + startx );
			logOutput( "startY: " + starty );
			logOutput( "startZ: " + startz );
			logOutput( "endX: " + endx );
			logOutput( "endY: " + endy );
			logOutput( "endZ: " + endz );

			for( zc = startz; zc != endz; zc-- )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc-- )
					{
						block = player.getWorld().getBlockAt((int)zc, (int)yc, (int)xc);

						logOutput( "block " + xc + "," + yc + "," + zc + " = " 
						+ block.getTypeId() + ":" + block.getData() );
						
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );

			saveChunkData( player, blocks, file, x, y, z );
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

			logOutput( "startX: " + startx );
			logOutput( "startY: " + starty );
			logOutput( "startZ: " + startz );
			logOutput( "endX: " + endx );
			logOutput( "endY: " + endy );
			logOutput( "endZ: " + endz );

			for( zc = startz; zc != endz; zc-- )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc++ )
					{
						block = player.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);

						logOutput( "block " + xc + "," + yc + "," + zc + " = " 
						+ block.getTypeId() + ":" + block.getData() );
						
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );

			saveChunkData( player, blocks, file, x, y, z );
		}
		
		else if( dir == BlockFace.SOUTH )
		{
			// When facing SOUTH, X = Z-- and Z = X++

			startx = loc.getZ();
			starty = loc.getY();
			startz = loc.getX();
			
			endx = startx + x;
			endy = starty + y;
			endz = startz - z;

			logOutput( "startX: " + startx );
			logOutput( "startY: " + starty );
			logOutput( "startZ: " + startz );
			logOutput( "endX: " + endx );
			logOutput( "endY: " + endy );
			logOutput( "endZ: " + endz );

			for( zc = startz; zc != endz; zc-- )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc++ )
					{
						block = player.getWorld().getBlockAt((int)zc, (int)yc, (int)xc);

						logOutput( "block " + zc + "," + yc + "," + xc + " = " 
						+ block.getTypeId() + ":" + block.getData() );
						
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );

			saveChunkData( player, blocks, file, x, y, z );
		}
		else
		{
			// When facing WEST, X = X-- and Z = Z++

			startx = loc.getX();
			starty = loc.getY();
			startz = loc.getZ();
			
			endx = startx - x;
			endy = starty + y;
			endz = startz + z;

			logOutput( "startX: " + startx );
			logOutput( "startY: " + starty );
			logOutput( "startZ: " + startz );
			logOutput( "endX: " + endx );
			logOutput( "endY: " + endy );
			logOutput( "endZ: " + endz );

			for( zc = startz; zc != endz; zc++ )
			{
				for( yc = starty; yc != endy; yc++ )
				{
					for( xc = startx; xc != endx; xc-- )
					{
						block = player.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);

						logOutput( "block " + xc + "," + yc + "," + zc + " = " 
						+ block.getTypeId() + ":" + block.getData() );
						
						blocks.add( block.getTypeId() + " " + block.getData() );
					}
				}
			}
			
			player.sendMessage( "Saving " + blocks.size() + " blocks.." );

			saveChunkData( player, blocks, file, x, y, z );
		}
	}

	public static void saveChunkData( Player p, ArrayList<String> data, String file, int x, int y, int z )
	{
		int counter = 0;
		String filename = "plugins/Chunker/" + p.getName() + "." + file + ".chunk";
		
		try
	    {
			FileWriter outFile = new FileWriter( filename );
			PrintWriter outP = new PrintWriter( outFile );
			
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
			StringBuilder cDate = new StringBuilder( df.format(now) );

			outP.println( "[BEGIN CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
			outP.println( "SIZE " + x + " " + y + " " + z );
			
			for( counter = 0; counter < data.size(); counter++ )
			{
				outP.println( data.get(counter) );
			}
			
			outP.println( "[END CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
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
