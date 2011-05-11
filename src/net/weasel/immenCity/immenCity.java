package net.weasel.immenCity;

import java.io.File;
import java.util.HashMap;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class immenCity extends JavaPlugin
{
	public static String pluginName = "";
	public static String pluginVersion = "";
	public static String pluginIni = "plugins/immenCity/Settings.ini";
	public static HashMap<Player,Location> playerBlocks = null;
	public static HashMap<Player,String> playerParams = null;
	public static HashMap<Player,BlockFace> playerFacing = null;
	public static HashMap<Integer,int[]> orientedBlocks = null;
	
	public static boolean isOnlineRepo = false;
	public static String onlineRepoURL = "";
	public static boolean isDebugging = false;
	
	@Override
	public void onDisable() 
	{
		logOutput( getDescription().getName() + " v" 
		+ getDescription().getVersion() + " disabled." );
	}

	@Override
	public void onEnable() 
	{
		pluginName = getDescription().getName();
		pluginVersion = getDescription().getVersion();
		
		playerBlocks = new HashMap<Player,Location>();
		playerParams = new HashMap<Player,String>();
		playerFacing = new HashMap<Player,BlockFace>();
		
		orientedBlocks = Rotator.BlockHash();
		
		File checkConfig = new File( "plugins/immenCity" );
		
		if( checkConfig.exists() == false )
		{
			checkConfig.mkdirs();
			logOutput( "Created new ChunkFile directory." );
		}
		
		CommandExecutor cmdMain = new CommandMain(this);
		CommandExecutor cmdLoad = new CommandLoad(this);
		CommandExecutor cmdSave = new CommandSave(this);
		CommandExecutor cmdList = new CommandList(this);
		CommandExecutor cmdMeasure = new CommandMeasure(this);
		
		getCommand( "immencity" ).setExecutor( cmdMain );
		getCommand( "icity" ).setExecutor( cmdMain );
		getCommand( "iload" ).setExecutor( cmdLoad );
		getCommand( "isave" ).setExecutor( cmdSave );
		getCommand( "ilist" ).setExecutor( cmdList );
		getCommand( "imeasure" ).setExecutor( cmdMeasure );
		getCommand( "measure" ).setExecutor( cmdMeasure );

		checkConfig = new File( pluginIni );
		
		if( checkConfig.exists() == false ) Config.createIniFile();
		
		Config.loadSettings();
		
		logOutput( getDescription().getName() + " v" + getDescription().getVersion() + " enabled." );
	}

	public static void logOutput( String message )
	{
		System.out.println( "[" + pluginName + "] " + message );
	}

	public static void dbgOutput( String message )
	{
		if( isDebugging ) logOutput( message );
	}
	
	public static BlockFace getPlayerDirection( Player player )
	{
		BlockFace retVal = null;
		float yaw = player.getLocation().getYaw();
		
		if( yaw < 0 ) yaw += 360;
		if( yaw > 360 ) yaw -= 360;
		
		if( yaw > 45 && yaw < 135 ) 
			retVal = BlockFace.NORTH;
		else if( yaw > 135 && yaw < 225 ) 
			retVal = BlockFace.EAST;
		else if( yaw > 225 && yaw < 315 ) 
			retVal = BlockFace.SOUTH;
		else if( yaw > 315 || yaw < 45 ) 
			retVal = BlockFace.WEST;

		return retVal;
	}

	public static String arrayToString( String[] a, String separator ) 
    {
        String result = "";
        
        if (a.length > 0) 
        {
            result = a[0];    // start with the first element
            for (int i=1; i<a.length; i++) {
                result = result + separator + a[i];
            }
        }
        return result;
    }

	public static String intArrayToString( int[] a, String separator ) 
    {
        String result = "";
        
        if (a.length > 0) 
        {
            result = String.valueOf(a[0]);    // start with the first element
            for (int i=1; i<a.length; i++) {
                result = result + separator + a[i];
            }
        }
        return result;
    }

	public static String[] getLocalChunkList()
	{
		File dir = new File("plugins/immenCity/");
		File[] files = dir.listFiles();
		String file = "";
		
		int x = files.length;
		String[] retVal = new String[x];
				
		for( x = 0; x < files.length; x++ )
		{
			file = files[x].getName();
			retVal[x] = file;
		}
		
		return retVal;
	}
}