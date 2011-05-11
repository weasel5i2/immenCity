package net.weasel.immenCity;

import java.util.HashMap;

public class OrientedBlocks 
{
	public static HashMap<Integer,int[]> BlockHash()
	{
		HashMap<Integer,int[]> matrix = new HashMap<Integer,int[]>();

		matrix.put(  23, new int[]{ 4, 2, 5, 3 } );		// Dispensers
		matrix.put(  26, new int[]{ 1, 2, 3, 0 } );		// Bed (foot)
		matrix.put( 926, new int[]{ 9, 10, 11, 8 } );	// Bed (head)
		matrix.put(  27, new int[]{ 0, 1, 0, 1 } );		// Powered rails (straight)
		matrix.put( 927, new int[]{ 2, 5, 3, 4 } );		// Powered rails (inclined)
		matrix.put(  28, new int[]{ 0, 1, 0, 1 } );		// Detector rails (straight)
		matrix.put(  28, new int[]{ 2, 5, 3, 4 } );		// Detector rails (inclined)
		matrix.put(  50, new int[]{ 2, 4, 1, 3 } );		// Torches
		matrix.put(  53, new int[]{ 0, 2, 1, 3 } );		// Wooden stairs
		matrix.put(  55, new int[]{ } );				// Redstone wires
		matrix.put(  63, new int[]{ 4, 8,12, 0 } );		// Signposts
		matrix.put(  64, new int[]{ } );				// Wooden doors
		matrix.put(  65, new int[]{ 4, 2, 5, 3 } );		// Ladders
		matrix.put(  66, new int[]{ } );				// Rails  MULTI!!
		matrix.put(  67, new int[]{ 0, 2, 1, 3 } );		// Stone stairs
		matrix.put(  68, new int[]{ 4, 2, 5, 3 } );		// Wall signs
		matrix.put(  69, new int[]{ } );				// Levers
		matrix.put(  71, new int[]{ } );				// Iron doors
		matrix.put(  75, new int[]{ } );				// Redstone torches
		matrix.put(  76, new int[]{ } );				// Redstone torches (lit)
		matrix.put(  77, new int[]{ } );				// Stone buttons
		matrix.put(  85, new int[]{ } );				// Fences
		matrix.put(  86, new int[]{ 1, 2, 3, 0 } );		// Pumpkins
		matrix.put(  90, new int[]{ } );				// Portals
		matrix.put(  91, new int[]{ 1, 2, 3, 0 } );		// Jacks-o-Lantern
		matrix.put(  93, new int[]{ } );				// Repeater (off)
		matrix.put(  94, new int[]{ } );				// Repeater (on)

		return matrix;
	}
}
