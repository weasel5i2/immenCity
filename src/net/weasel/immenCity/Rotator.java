package net.weasel.immenCity;

import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Rotator 
{
	static int LEFT = 0;
	static int RIGHT = 1;
	
	public static void logOutput( String message ) { immenCity.logOutput(message); }
	public static String arrayToString(String[] a, String separator) { return immenCity.arrayToString(a, separator); }
	public static String intArrayToString( int[] a, String separator ) { return immenCity.intArrayToString(a, separator); }
	public static HashMap<Integer,int[]> orientedBlocks = BlockHash();
	public static HashMap<Integer,int[]> BlockHash()
	{
		HashMap<Integer,int[]> matrix = new HashMap<Integer,int[]>();

		//					  (when player is facing block)
		//            ID               E  N  W  S

		matrix.put(   23,  new int[]{  4, 2, 5, 3 } );	// Dispenser
		matrix.put(   26,  new int[]{  1, 2, 3, 0 } );	// Bed (foot)
		matrix.put(  926,  new int[]{  9,10,11, 8 } );	// Bed (head)
		matrix.put(   27,  new int[]{  0, 1, 0, 1 } );	// Powered Rail (straight)
		matrix.put(  927,  new int[]{  2, 5, 3, 4 } );	// Powered Rail (inclined)
		matrix.put(   28,  new int[]{  0, 1, 0, 1 } );	// Detector Rail (straight)
		matrix.put(  928,  new int[]{  2, 5, 3, 4 } );	// Detector Rail (inclined)
		matrix.put(   29,  new int[]{  4, 3, 5, 2 } );	// Sticky Piston
		matrix.put(   33,  new int[]{  4, 3, 5, 2 } );	// Piston
		matrix.put(   50,  new int[]{  2, 4, 1, 3 } );	// Torch
		matrix.put(   53,  new int[]{  0, 2, 1, 3 } );	// Wooden Stairs
		matrix.put(   54,  new int[]{  4, 3, 5, 2 } );	// Chest
		matrix.put(   61,  new int[]{  4, 3, 5, 2 } );	// Furnace
		matrix.put(   62,  new int[]{  4, 3, 5, 2 } );	// Furnace (lit)
		matrix.put(   63,  new int[]{  4, 8,12, 0 } );	// Signpost
		matrix.put(   64,  new int[]{  2, 3, 2, 3 } );	// Wooden Door (bottom)
		matrix.put(  964,  new int[]{ 10,11,10,11 } );	// Wooden Door (top)
		matrix.put(   65,  new int[]{  4, 2, 5, 3 } );	// Ladder
		matrix.put(   66,  new int[]{  0, 1, 0, 1 } );	// Rail (straight)
		matrix.put(  866,  new int[]{  2, 5, 3, 4 } );	// Rail (inclined)
		matrix.put(  966,  new int[]{  6, 7, 8, 9 } );	// Rail (corner)
		matrix.put(   67,  new int[]{  0, 2, 1, 3 } );	// Cobblestone Stairs
		matrix.put(   68,  new int[]{  4, 2, 5, 3 } );	// Wall Sign
		matrix.put(   69,  new int[]{  2, 4, 1, 3 } );	// Lever (up)
		matrix.put(  969,  new int[]{ 10,12, 9,11 } );	// Lever (down)
		matrix.put(   71,  new int[]{  2, 3, 2, 3 } );	// Iron Door (bottom)
		matrix.put(  971,  new int[]{ 10,11,10,11 } );	// Iron Door (top)
		matrix.put(   75,  new int[]{  2, 4, 1, 3 } );	// Redstone Torch
		matrix.put(   76,  new int[]{  2, 4, 1, 3 } );	// Redstone Torch (lit)
		matrix.put(   77,  new int[]{  2, 4, 1, 3 } );	// Stone Button
		matrix.put(   86,  new int[]{  1, 2, 3, 0 } );	// Pumpkin
		matrix.put(   91,  new int[]{  1, 2, 3, 0 } );	// Jack-o-Lantern
		matrix.put(   93,  new int[]{  3, 0, 1, 2 } );	// Repeater (off) position 1
		matrix.put(  793,  new int[]{  7, 4, 5, 6 } );	// Repeater (off) position 2
		matrix.put(  893,  new int[]{ 11, 8, 9,10 } );	// Repeater (off) position 3
		matrix.put(  993,  new int[]{ 15,12,13,14 } );	// Repeater (off) position 4
		matrix.put(   94,  new int[]{  3, 0, 1, 2 } );	// Repeater (on) position 1
		matrix.put(  794,  new int[]{  7, 4, 5, 6 } );	// Repeater (on) position 2
		matrix.put(  894,  new int[]{ 11, 8, 9,10 } );	// Repeater (on) position 3
		matrix.put(  994,  new int[]{ 15,12,13,14 } );	// Repeater (on) position 4
		matrix.put(   96,  new int[]{  0, 2, 1, 3 } );	// Trapdoor (closed)
		matrix.put(  996,  new int[]{  4, 6, 5, 7 } );	// Trapdoor (open)	
		matrix.put(   99,  new int[]{  1, 7, 9, 3 } );	// Huge Brown Mushroom (sides)
		matrix.put(  599,  new int[]{  4, 8, 6, 2 } );	// Huge Brown Mushroom (sides)
		matrix.put(  699,  new int[]{  5, 5, 5, 5 } );	// Huge Brown Mushroom (top)
		matrix.put(  799,  new int[]{ 10,10,10,10 } );	// Huge Brown Mushroom (stem)
		matrix.put(  899,  new int[]{ 14,14,14,14 } );	// Huge Brown Mushroom (all stem)
		matrix.put(  999,  new int[]{ 15,15,15,15 } );	// Huge Brown Mushroom (all cap)
		matrix.put(  100,  new int[]{  1, 7, 9, 3 } );	// Huge Red Mushroom (sides)
		matrix.put( 5100, new int[]{  4, 8, 6, 2 } );	// Huge Red Mushroom (sides)
		matrix.put( 6100, new int[]{  5, 5, 5, 5 } );	// Huge Red Mushroom (top)
		matrix.put( 7100, new int[]{ 10,10,10,10 } );	// Huge Red Mushroom (stem)
		matrix.put( 8100, new int[]{ 14,14,14,14 } );	// Huge Red Mushroom (all stem)
		matrix.put( 9100, new int[]{ 15,15,15,15 } );	// Huge Red Mushroom (all cap)
		matrix.put(  106, new int[]{  8, 4, 2, 1 } );	// Vines
		matrix.put(  107, new int[]{  3, 2, 1, 0 } );	// Fence Gate
		matrix.put(  108, new int[]{  0, 3, 1, 2 } );	// Brick Stairs
		matrix.put( 9108, new int[]{  4, 7, 5, 6 } );	// Brick Stairs (inverted)
		matrix.put(  109, new int[]{  0, 3, 1, 2 } );	// Stone Brick Stairs
		matrix.put( 9109, new int[]{  4, 7, 5, 6 } );	// Stone Brick Stairs (inverted)
		matrix.put(  114, new int[]{  0, 3, 1, 2 } );	// Nether Brick Stairs
		matrix.put( 9114, new int[]{  4, 7, 5, 6 } );	// Nether Brick Stairs (inverted)
		matrix.put(  128, new int[]{  0, 3, 1, 2 } );	// Sandstone Stairs
		matrix.put( 9128, new int[]{  4, 7, 5, 6 } );	// Sandstone Stairs (inverted)
		matrix.put(  130, new int[]{  4, 3, 5, 2 } );	// Ender Chest
		matrix.put(  131, new int[]{  1, 0, 3, 2 } );	// Tripwire Hook (inactive)
		matrix.put( 9131, new int[]{  5, 4, 7, 6 } );	// Tripwire Hook (active)
		matrix.put(  134, new int[]{  0, 3, 1, 2 } );	// Spruce Wood Stairs
		matrix.put( 9134, new int[]{  4, 7, 5, 6 } );	// Spruce Wood Stairs (inverted)
		matrix.put(  135, new int[]{  0, 3, 1, 2 } );	// Birch Wood Stairs
		matrix.put( 9135, new int[]{  4, 7, 5, 6 } );	// Birch Wood Stairs (inverted)
		matrix.put(  136, new int[]{  0, 3, 1, 2 } );	// Jungle Wood Stairs
		matrix.put( 9136, new int[]{  4, 7, 5, 6 } );	// Jungle Wood Stairs (inverted)
		matrix.put(  143, new int[]{  2, 4, 1, 3 } );	// Wooden Button
		matrix.put(  144, new int[]{  4, 3, 5, 2 } );	// Head
		matrix.put(  145, new int[]{ xx,xx,xx,xx } );	// Anvil
		matrix.put( 8145, new int[]{ xx,xx,xx,xx } );	// Anvil (slightly damaged)
		matrix.put( 9145, new int[]{ xx,xx,xx,xx } );	// Anvil (very damaged)
		
		matrix.put(   -1, new int[]{ -1,-1,-1,-1 } );	// Place Holder

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
				
			case 27: // Powered Rail
				if( data > 1 ) retVal = 927;
				
			case 28: // Detector Rail
				if( data > 1 ) retVal = 928;
				
			case 64: // Wooden Door
				if( data > 3 ) retVal = 964;
				
			case 66: // Rails
				if( data > 1 && data < 6 )  retVal = 866;
				if( data > 5 && data < 10 ) retVal = 966;
				
			case 69: // Lever
				if( data > 4 ) retVal = 969;
				
			case 71: // Iron Door
				if( data > 3 ) retVal = 971;

			case 93: // Redstone Repeater (off)
				if( data > 11 ) 
					retVal = 993;
				else if( data > 7  ) 
					retVal = 893;
				else if( data > 3  ) 
					retVal = 793;

			case 94: // Redstone Repeater (on)
				if( data > 11 ) 
					retVal = 994;
				else if( data > 7  ) 
					retVal = 894;
				else if( data > 3  ) 
					retVal = 794;

			case 96: // Trap Door
				if( data > 3 ) retVal = 996;

			case 99: // Huge Brown Mushroom
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
				
			case 100: // Huge Red Mushroom..
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

			case 108: // Brick Stairs
				if( data > 3 ) retVal = 9108;

			case 109: // Stone Brick Stairs
				if( data > 3 ) retVal = 9109;

			case 114: // Nether Brick Stairs
				if( data > 3 ) retVal = 9114;

			case 128: // Sandstone Stairs
				if( data > 3 ) retVal = 9128;
				
			case 131: // Tripwire Hook
				if( data > 1 ) retVal = 9131;

			case 134: // Spruce Wood Stairs
				if( data > 3 ) retVal = 9134;
				
			case 135: // Birch Wood Stairs
				if( data > 3 ) retVal = 9135;
				
			case 136: // Jungle Wood Stairs
				if( data > 3 ) retVal = 9136;
		}
		
		return retVal;
	}

	public static void rotate( Block block, int data, BlockFace oldDir, BlockFace newDir )
	{
		System.out.println( "BLOCK: " + block + ":" + data + " - " + oldDir + "/" + newDir );
		
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
