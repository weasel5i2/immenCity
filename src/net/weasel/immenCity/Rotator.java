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
	
	public static HashMap<Integer,int[]> BlockHash()
	{
		HashMap<Integer,int[]> matrix = new HashMap<Integer,int[]>();
		
		//          ID                E  N  W  S

		matrix.put(  23,  new int[]{  4, 2, 5, 3 } );	// Dispensers
		matrix.put(  26,  new int[]{  1, 2, 3, 0 } );	// Bed (foot)
		matrix.put( 926,  new int[]{  9,10,11, 8 } );	// Bed (head)
		matrix.put(  27,  new int[]{  0, 1, 0, 1 } );	// Powered rails (straight)
		matrix.put( 927,  new int[]{  2, 5, 3, 4 } );	// Powered rails (inclined)
		matrix.put(  28,  new int[]{  0, 1, 0, 1 } );	// Detector rails (straight)
		matrix.put( 928,  new int[]{  2, 5, 3, 4 } );	// Detector rails (inclined)
		matrix.put(  50,  new int[]{  2, 4, 1, 3 } );	// Torches
		matrix.put(  53,  new int[]{  0, 2, 1, 3 } );	// Wooden stairs
		matrix.put(  54,  new int[]{  4, 3, 5, 2 } );	// Chests
		matrix.put(  63,  new int[]{  4, 8,12, 0 } );	// Signposts
		matrix.put(  64,  new int[]{  2, 3, 2, 3 } );	// Wooden doors (bottom)
		matrix.put( 964,  new int[]{ 10,11,10,11 } );	// Wooden doors (top)
		matrix.put(  65,  new int[]{  4, 2, 5, 3 } );	// Ladders
		matrix.put(  66,  new int[]{  0, 1, 0, 1 } );	// Rails  (straight)
		matrix.put( 866,  new int[]{  2, 5, 3, 4 } );	// Rails  (inclined)
		matrix.put( 966,  new int[]{  6, 7, 8, 9 } );	// Rails  (corner)
		matrix.put(  67,  new int[]{  0, 2, 1, 3 } );	// Stone stairs
		matrix.put(  68,  new int[]{  4, 2, 5, 3 } );	// Wall signs
		matrix.put(  69,  new int[]{  2, 4, 1, 3 } );	// Levers (up)
		matrix.put( 969,  new int[]{ 10,12, 9,11 } );	// Levers (down)
		matrix.put(  71,  new int[]{  2, 3, 2, 3 } );	// Iron doors (bottom)
		matrix.put( 971,  new int[]{ 10,11,10,11 } );	// Iron doors (bottom)
		matrix.put(  75,  new int[]{  2, 4, 1, 3 } );	// Redstone torches
		matrix.put(  76,  new int[]{  2, 4, 1, 3 } );	// Redstone torches (lit)
		matrix.put(  77,  new int[]{  2, 4, 1, 3 } );	// Stone buttons
		matrix.put(  86,  new int[]{  1, 2, 3, 0 } );	// Pumpkins
		matrix.put(  91,  new int[]{  1, 2, 3, 0 } );	// Jacks-o-Lantern
		matrix.put(  93,  new int[]{  3, 0, 1, 2 } );	// Repeater (off) position 1
		matrix.put( 793,  new int[]{  7, 4, 5, 6 } );	// Repeater (off) position 2
		matrix.put( 893,  new int[]{ 11, 8, 9,10 } );	// Repeater (off) position 3
		matrix.put( 993,  new int[]{ 15,12,13,14 } );	// Repeater (off) position 4
		matrix.put(  94,  new int[]{  3, 0, 1, 2 } );	// Repeater (on) position 1
		matrix.put( 794,  new int[]{  7, 4, 5, 6 } );	// Repeater (on) position 2
		matrix.put( 894,  new int[]{ 11, 8, 9,10 } );	// Repeater (on) position 3
		matrix.put( 994,  new int[]{ 15,12,13,14 } );	// Repeater (on) position 4
		matrix.put(  96,  new int[]{  0, 2, 1, 3 } );	// Trapdoor (closed)
		matrix.put( 996,  new int[]{  4, 6, 5, 7 } );	// Trapdoor (open)	
		matrix.put(  99,  new int[]{  1, 7, 9, 3 } );	// Huge Brown Mushroom (sides)
		matrix.put( 599,  new int[]{  4, 8, 6, 2 } );	// Huge Brown Mushroom (sides)
		matrix.put( 699,  new int[]{  5, 5, 5, 5 } );	// Huge Brown Mushroom (top)
		matrix.put( 799,  new int[]{ 10,10,10,10 } );	// Huge Brown Mushroom (stem)
		matrix.put( 899,  new int[]{ 14,14,14,14 } );	// Huge Brown Mushroom (all stem)
		matrix.put( 999,  new int[]{ 15,15,15,15 } );	// Huge Brown Mushroom (all cap)
		matrix.put( 100,  new int[]{  1, 7, 9, 3 } );	// Huge Red Mushroom (sides)
		matrix.put( 5100, new int[]{  4, 8, 6, 2 } );	// Huge Red Mushroom (sides)
		matrix.put( 6100, new int[]{  5, 5, 5, 5 } );	// Huge Red Mushroom (top)
		matrix.put( 7100, new int[]{ 10,10,10,10 } );	// Huge Red Mushroom (stem)
		matrix.put( 8100, new int[]{ 14,14,14,14 } );	// Huge Red Mushroom (all stem)
		matrix.put( 9100, new int[]{ 15,15,15,15 } );	// Huge Red Mushroom (all cap)

		return matrix;
	}

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

			case 96: // Trapdoors
				if( data > 3 ) retVal = 996;

			case 99: // Huge brown mushroom..
				if( data == 5 )
					retVal = 699;
				else if( data == 1 || data == 3 || data == 7 || data == 9 ) 
					retVal = 99;
				else if( data == 2 || data == 4 || data == 6 || data == 8 ) 
					retVal = 599;
				else if( data == 10 )
					retVal = 799;
				else if( data == 14 )
					retVal = 899;
				else if( data == 15 )
					retVal = 999;
				
			case 100: // Huge red mushroom..
				if( data == 5 )
					retVal = 6100;
				else if( data == 1 || data == 3 || data == 7 || data == 9 ) 
					retVal = 100;
				else if( data == 2 || data == 4 || data == 6 || data == 8 ) 
					retVal = 5100;
				else if( data == 10 )
					retVal = 7100;
				else if( data == 14 )
					retVal = 8100;
				else if( data == 15 )
					retVal = 9100;

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
