package net.weasel.immenCity;

import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Rotator 
{
	static int LEFT = 0;
	static int RIGHT = 1;
	
	public static HashMap<Integer,int[]> orientedBlocks = immenCity.orientedBlocks;
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static String arrayToString(String[] a, String separator) { return immenCity.arrayToString(a, separator); }
	public static String intArrayToString( int[] a, String separator ) { return immenCity.intArrayToString(a, separator); }
	
	public static int[] RotateArray( int[] list, int direction )
	{
		if( direction == RIGHT )
		{
			int T = list[list.length-1];
			System.arraycopy(list, 0, list, 1, list.length - 1 );
			list[0] = T;
		}
		else if( direction == LEFT )
		{
			int T = list[0];
			System.arraycopy( list, 1, list, 0, list.length - 1 );
			list[list.length-1] = T;
		}
		return( list );
	}

	public static int getRotatedValue( int[] data, int value, int count, int direction )
	{
		int c = 0;
		int vTemp = -1;
		int[] dirTemp = data;
		
		for( c = 0; c < data.length; c++ )
		{
			if( data[c] == value )
			{
				vTemp = c;
				break;
			}
		}
		
		if( vTemp != -1 )
		{
			for( c = 0; c < count; c++ )
				dirTemp = RotateArray( dirTemp, direction );
		}
		
		if( immenCity.isDebugging )
		{
			logOutput( "getRotatedValue( " + intArrayToString(data," ") 
			+ ", " + value + ", " + count + ", " + direction + " ) = " + dirTemp[vTemp] );
		}
		
		return( dirTemp[vTemp] );
	}

	public static int multiDataValueId( Block block, int data )
	{
		int retVal = data;
		
		switch( block.getTypeId() )
		{
			case 26: // Bed
				if( data > 3 ) retVal = 926;
				
			case 27: // Powered rails
				if( data > 1 ) retVal = 927;
				
			case 28: // Detector rails
				if( data > 1 ) retVal = 928;
				
			case 64: // Wooden doors
				if( data > 3 ) retVal = 964;
				
			case 66: // Rails
				if( data > 1 && data < 6 )  retVal = 866;
				if( data > 5 && data < 10 ) retVal = 966;
				
			case 69: // Levers
				if( data > 4 ) retVal = 969;
				
			case 71: // Iron doors
				if( data > 3 ) retVal = 971;

			case 93: // Redstone repeaters (off)
				if( data > 11 ) 
					retVal = 993;
				else if( data > 7  ) 
					retVal = 893;
				else if( data > 3  ) 
					retVal = 793;

			case 94: // Redstone repeaters (on)
				if( data > 11 ) 
					retVal = 994;
				else if( data > 7  ) 
					retVal = 894;
				else if( data > 3  ) 
					retVal = 794;
		}
		
		return retVal;
	}

	public static void rotate( Block block, int data, BlockFace oldDir, BlockFace newDir )
	{
		int[] dataValues = null;
		int id = block.getTypeId();

		id = multiDataValueId( block, data );
		
		dataValues = orientedBlocks.get( id );
		
		if( newDir == BlockFace.NORTH )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) data );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, LEFT) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT) );
		}
		else if( newDir == BlockFace.EAST )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) data );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT ) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, LEFT ) );
		}
		else if( newDir == BlockFace.SOUTH )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 2, LEFT ) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT ) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) data );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT ) );
		}
		else if( newDir == BlockFace.WEST )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT ) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, LEFT ) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT ) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) data );
		}
	}
}
