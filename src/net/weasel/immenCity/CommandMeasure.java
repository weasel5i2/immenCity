package net.weasel.immenCity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMeasure implements CommandExecutor 
{
	public immenCity instance;
	
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static BlockFace getPlayerDirection( Player player ) { return immenCity.getPlayerDirection(player); }
	public static void saveChunkFile( Player p, Location l, BlockFace d, String f, Integer x, Integer y, Integer z ) { SaveStructure.saveChunkFile( p, l, d, f, x, y, z ); }
	public static void loadChunkFile( Player p, Location l, BlockFace d, String f ) { LoadStructure.loadChunkFile( p, l, d, f ); }
	public static Integer[] getChunkDimensions( String f ) { return LoadStructure.getChunkDimensions( f ); }
	public static String[] getLocalChunkList() { return immenCity.getLocalChunkList(); };
	
	public CommandMeasure(immenCity i) 
	{
		instance = i;
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if( arg0 instanceof Player )
		{
			if( immenCity.playerBlocks.containsKey( (Player)arg0) == false )
			{
				arg0.sendMessage( ChatColor.BLUE + "You have not set a start point." );
				arg0.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
				+ "/istart" + ChatColor.BLUE + " to set one." );
				
				return true;
			}
			else
			{
				double sX, sY, sZ;  // Start coordinates
				double eX, eY, eZ;  // End coordinates
				double dX, dY, dZ;  // Difference
				
				Player p = (Player)arg0;
				Location startLoc = immenCity.playerBlocks.get( p );
				Location endLoc = p.getTargetBlock(null, 20).getLocation();
				BlockFace startDir = immenCity.playerFacing.get( p );
				
				sX = startLoc.getX();
				sY = startLoc.getY();
				sZ = startLoc.getZ();
				eX = endLoc.getX();
				eY = endLoc.getY();
				eZ = endLoc.getZ();
				
				dX = Math.abs( eX - sX );
				dY = Math.abs( eY - sY );
				dZ = Math.abs( eZ - sZ );

				logOutput( "MEASURE RESULT (" + startDir.name() + "): " + dX + "," + dY + "," + dZ );
				
				if( startDir == BlockFace.NORTH )
				{
					
				}
				else if( startDir == BlockFace.EAST )
				{
					
				}
				else if( startDir == BlockFace.SOUTH )
				{
					
				}
				else if( startDir == BlockFace.WEST )
				{
					
				}
				
			}

			arg0.sendMessage( ChatColor.BLUE + "Usage: " + ChatColor.YELLOW + "/istart" );
			arg0.sendMessage( ChatColor.BLUE + "         Sets the start point for save/load operations, based" );
			arg0.sendMessage( ChatColor.BLUE + "         on the block you are targeting." );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/isave <filename> <X> <Y> <Z>" );
			
			arg0.sendMessage( ChatColor.BLUE + "         Saves <X> <Y> <Z>-sized chunk as <filename> relative " );
			arg0.sendMessage( ChatColor.BLUE + "         to the start point. You " + ChatColor.RED + "MUST" + ChatColor.BLUE + " set a start point first!" );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/iload <filename>" );
			arg0.sendMessage( ChatColor.BLUE + "         Loads the chunk <filename> relative to the start point." );
			arg0.sendMessage( ChatColor.BLUE + "         You " + ChatColor.RED + "MUST" + ChatColor.BLUE + " set a start point first!" );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/ilist"
			+ ChatColor.BLUE + " - Lists your saved chunks." );

			return true;
		}

		return false;
	}
}
