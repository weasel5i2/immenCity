package net.weasel.Chunker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class ChunkerSave 
{
	public static void logOutput( String message ) { Chunker.logOutput(message); }
	
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
		double xm, zm;
				
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

			try 
		    {
				FileWriter outFile = new FileWriter( file );
				PrintWriter outP = new PrintWriter( outFile );
				
				Date now = new Date();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				StringBuilder cDate = new StringBuilder( df.format(now) );

				outP.println( "[BEGIN CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
				outP.println( "SIZE " + x + " " + y + " " + z );
				
				for( xc = startz; xc != endx; xc += xm )
				{
					for( yc = starty; yc != endy; yc++ )
					{
						for( zc = startx; zc != endz; zc += zm )
						{
							b = p.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
							outP.println( b.getTypeId() + " " + b.getData() );
						}
					}
				}
				
				outP.println( "[END CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
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
		
		else if( d == BlockFace.EAST )
		{
			// When facing EAST, X = X++ and Z = Z--
			xm = 1;
			zm = -1;
			endx = startx + x;
			endz = startz - z;

			try 
		    {
				FileWriter outFile = new FileWriter( file );
				PrintWriter outP = new PrintWriter( outFile );
				
				Date now = new Date();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				StringBuilder cDate = new StringBuilder( df.format(now) );

				outP.println( "[BEGIN CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
				outP.println( "SIZE " + x + " " + y + " " + z );
				
				for( xc = startx; xc != endx; xc += xm )
				{
					for( yc = starty; yc != endy; yc++ )
					{
						for( zc = startz; zc != endz; zc += zm )
						{
							b = p.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
							outP.println( b.getTypeId() + " " + b.getData() );
						}
					}
				}
				
				outP.println( "[END CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
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
		
		else if( d == BlockFace.SOUTH )
		{
			// When facing SOUTH, X = Z++ and Z = X++
			xm = 1;
			zm = 1;
			endx = startz + x;
			endz = startx + z;
			
			try 
		    {
				FileWriter outFile = new FileWriter( file );
				PrintWriter outP = new PrintWriter( outFile );
				
				Date now = new Date();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				StringBuilder cDate = new StringBuilder( df.format(now) );

				outP.println( "[BEGIN CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
				outP.println( "SIZE " + x + " " + y + " " + z );
				
				for( xc = startz; xc != endz; xc += xm )
				{
					for( yc = starty; yc != endy; yc++ )
					{
						for( zc = startx; zc != endx; zc += zm )
						{
							b = p.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
							outP.println( b.getTypeId() + " " + b.getData() );
						}
					}
				}
				
				outP.println( "[END CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
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
		else
		{
			// When facing WEST, X = X-- and Z = Z++
			xm = -1;
			zm = 1;
			endx = startx - x;
			endz = startz + z;

			try 
		    {
				FileWriter outFile = new FileWriter( file );
				PrintWriter outP = new PrintWriter( outFile );
				
				Date now = new Date();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				StringBuilder cDate = new StringBuilder( df.format(now) );

				outP.println( "[BEGIN CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
				outP.println( "SIZE " + x + " " + y + " " + z );
				
				for( xc = startx; xc != endx; xc += xm )
				{
					for( yc = starty; yc != endy; yc++ )
					{
						for( zc = startz; zc != endz; zc += zm )
						{
							b = p.getWorld().getBlockAt((int)xc, (int)yc, (int)zc);
							outP.println( b.getTypeId() + " " + b.getData() );
						}
					}
				}
				
				outP.println( "[END CHUNKFILE " + Chunker.pluginVersion + "] " + cDate );
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
	}
}
