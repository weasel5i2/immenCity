package net.weasel.immenCity;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor
{
	public immenCity instance;
	
	public static BlockFace getPlayerDirection( Player player ) { return immenCity.getPlayerDirection(player); }
	public static void saveChunkFile( Player p, Location l, BlockFace d, String f, Integer x, Integer y, Integer z ) { SaveStructure.saveChunkFile( p, l, d, f, x, y, z ); }
	public static void loadChunkFile( Player p, Location l, BlockFace d, String f ) { LoadStructure.loadChunkFile( p, l, d, f ); }
	public static Integer[] getChunkDimensions( String f ) { return immenCity.getChunkDimensions( f ); }
	public static String[] getLocalChunkList() { return immenCity.getLocalChunkList(); };
	
	public Commands(immenCity i) 
	{
		instance = i;
	}

	@Override
	public boolean onCommand( CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) 
	{
		if( arg0 instanceof Player )
		{
			String a1 = ( arg3.length > 0 ? arg3[0] : "" );
			String a2 = ( arg3.length > 1 ? arg3[1] : "" );
			String a3 = ( arg3.length > 2 ? arg3[2] : "" );
			String a4 = ( arg3.length > 3 ? arg3[3] : "" );
			String a5 = ( arg3.length > 4 ? arg3[4] : "" );
			
			if( a1.equals( "start" ) )
			{
				Player p = (Player)arg0;
				Block b = p.getTargetBlock( null, 20 );

				immenCity.playerBlocks.put( p, b.getLocation() );
				immenCity.playerFacing.put( p, getPlayerDirection(p) );
				
				p.sendMessage( ChatColor.BLUE + "Starting point set: " 
				+ ChatColor.YELLOW + b.getX() + ChatColor.BLUE + ","
				+ ChatColor.YELLOW + b.getY() + ChatColor.BLUE + "," 
				+ ChatColor.YELLOW + b.getZ() + ChatColor.BLUE + "." );
				
				p.sendMessage( ChatColor.BLUE + "You are facing " + ChatColor.YELLOW 
				+ ( getPlayerDirection(p) == BlockFace.NORTH ? "north" :
				  ( getPlayerDirection(p) == BlockFace.EAST ? "east" : 
				  ( getPlayerDirection(p) == BlockFace.SOUTH ? "south" : "west")))
				+ ChatColor.BLUE + "." );
				
				return true;
			}
			
			if( a1.equals( "measure" ) )
			{
				
			}

			if( a1.equals( "save" ) )
			{
				if( immenCity.playerBlocks.containsKey((Player)arg0) == false )
				{
					arg0.sendMessage( ChatColor.BLUE + "You have not set a start point." );
					arg0.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
					+ "/icity start" + ChatColor.BLUE + " to set one." );
					
					return true;
				}
				else if( a5.equals("") == false )
				{
					String fName = a2;
					Integer xSize = Integer.parseInt(a3);
					Integer ySize = Integer.parseInt(a4);
					Integer zSize = Integer.parseInt(a5);
					
					if( xSize > 0 && ySize > 0 && zSize > 0 )
					{
						saveChunkFile( (Player)arg0, immenCity.playerBlocks.get((Player)arg0),
									   getPlayerDirection((Player)arg0), fName, xSize, ySize,
									   zSize );
						return true;
					}
				}
			}

			if( a1.equals( "list" ) )
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

			if( a1.equals( "load" ) )
			{
				if( immenCity.playerBlocks.containsKey((Player)arg0) == false )
				{
					arg0.sendMessage( ChatColor.BLUE + "You have not set a start point." );
					arg0.sendMessage( ChatColor.BLUE + "Use " + ChatColor.YELLOW
					+ "/icity start" + ChatColor.BLUE + " to set one." );
					
					return true;
				}
				else if( a2.equals("") == false )
				{
					loadChunkFile( (Player)arg0, immenCity.playerBlocks.get((Player)arg0),
							 	   immenCity.playerFacing.get((Player)arg0), a2 );
					return true;
				}
			}
			
			arg0.sendMessage( ChatColor.BLUE + "Usage: " + ChatColor.YELLOW + "/icity start" );
			arg0.sendMessage( ChatColor.BLUE + "         Sets the start point for save/load operations, based" );
			arg0.sendMessage( ChatColor.BLUE + "         on the block you are targeting." );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/icity save <filename> <X> <Y> <Z>" );
			
			arg0.sendMessage( ChatColor.BLUE + "         Saves <X> <Y> <Z>-sized chunk as <filename> relative " );
			arg0.sendMessage( ChatColor.BLUE + "         to the start point. You " + ChatColor.RED + "MUST" + ChatColor.BLUE + " set a start point first!" );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/icity load <filename>" );
			arg0.sendMessage( ChatColor.BLUE + "         Loads the chunk <filename> relative to the start point." );
			arg0.sendMessage( ChatColor.BLUE + "         You " + ChatColor.RED + "MUST" + ChatColor.BLUE + " set a start point first!" );

			arg0.sendMessage( "         " + ChatColor.YELLOW + "/icity list"
			+ ChatColor.BLUE + " - Lists your saved chunks." );

			return true;
		}
		return true;
	}
}