package net.weasel.immenCity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLoad implements CommandExecutor 
{
	public immenCity instance;
	
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static BlockFace getPlayerDirection( Player player ) { return immenCity.getPlayerDirection(player); }
	public static void saveChunkFile( Player p, Location l, BlockFace d, String f, Integer x, Integer y, Integer z ) { SaveStructure.saveChunkFile( p, l, d, f, x, y, z ); }
	public static void loadChunkFile( Player p, Location l, BlockFace d, String f ) { LoadStructure.loadChunkFile( p, l, d, f ); }
	public static Integer[] getChunkDimensions( String f ) { return LoadStructure.getChunkDimensions( f ); }
	public static String[] getLocalChunkList() { return immenCity.getLocalChunkList(); };
	
	public CommandLoad(immenCity i) 
	{
		instance = i;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if( arg0 instanceof Player )
		{
			String filename = ( arg3.length > 0 ? arg3[0] : "" );
			
			if( immenCity.playerBlocks.containsKey((Player)arg0) == false )
			{
				arg0.sendMessage( ChatColor.BLUE + "You have not set a start point." );
				arg0.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
				+ "/istart" + ChatColor.BLUE + " to set one." );
				
				return true;
			}
			else if( filename.equals("") == false )
			{
				loadChunkFile( (Player)arg0, immenCity.playerBlocks.get((Player)arg0),
						 	   immenCity.playerFacing.get((Player)arg0), filename );
				return true;
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
