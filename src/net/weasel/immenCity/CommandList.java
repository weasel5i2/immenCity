package net.weasel.immenCity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandList implements CommandExecutor
{

	public immenCity instance;
	
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static BlockFace getPlayerDirection( Player player ) { return immenCity.getPlayerDirection(player); }
	public static void saveChunkFile( Player p, Location l, BlockFace d, String f, Integer x, Integer y, Integer z ) { SaveStructure.saveChunkFile( p, l, d, f, x, y, z ); }
	public static void loadChunkFile( Player p, Location l, BlockFace d, String f ) { LoadStructure.loadChunkFile( p, l, d, f ); }
	public static Integer[] getChunkDimensions( String f ) { return LoadStructure.getChunkDimensions( f ); }
	public static String[] getLocalChunkList() { return immenCity.getLocalChunkList(); };
	
	public CommandList(immenCity i) 
	{
		instance = i;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if( arg0 instanceof Player )
		{
			String[] chunks = getLocalChunkList();
			Player p = (Player)arg0;
			Integer[] s = new Integer[3];
			String file = "";
			
			if( chunks.length > 0 )
			{
				for( int x = 0; x < chunks.length; x++ )
				{
					file = chunks[x];
					
					if( file.contains( p.getName() + "." ) )
					{
						file = file.replace( p.getName() + ".", "" );
						file = file.replace( ".chunk", "" );
						s = getChunkDimensions(chunks[x]);
						p.sendMessage( ChatColor.BLUE + "Chunk " + ChatColor.YELLOW
						+ file.toUpperCase() + ChatColor.WHITE + " (" + s[0] + "x" + s[1] 
						+ "x" + s[2] + ")" );
					}
				}
			}
			else
			{
				p.sendMessage( "You have no chunk files saved." );
			}
			
			return true;
		}

		return false;
	}

}
