package net.weasel.immenCity;

import java.util.HashMap;

public class OrientedBlocks 
{
	public static HashMap<Integer,int[]> BlockHash()
	{
		HashMap<Integer,int[]> matrix = new HashMap<Integer,int[]>();

		matrix.put(  23, new int[]{  4, 2, 5, 3 } );	// Dispensers
		matrix.put(  26, new int[]{  1, 2, 3, 0 } );	// Bed (foot)
		matrix.put( 926, new int[]{  9,10,11, 8 } );	// Bed (head)
		matrix.put(  27, new int[]{  0, 1, 0, 1 } );	// Powered rails (straight)
		matrix.put( 927, new int[]{  2, 5, 3, 4 } );	// Powered rails (inclined)
		matrix.put(  28, new int[]{  0, 1, 0, 1 } );	// Detector rails (straight)
		matrix.put( 928, new int[]{  2, 5, 3, 4 } );	// Detector rails (inclined)
		matrix.put(  50, new int[]{  2, 4, 1, 3 } );	// Torches
		matrix.put(  53, new int[]{  0, 2, 1, 3 } );	// Wooden stairs
//		matrix.put(  55, new int[]{  0, 0, 0, 0 } );	// Redstone wires
		matrix.put(  63, new int[]{  4, 8,12, 0 } );	// Signposts
		matrix.put(  64, new int[]{  2, 3, 2, 3 } );	// Wooden doors (bottom)
		matrix.put( 964, new int[]{ 10,11,10,11 } );	// Wooden doors (top)
		matrix.put(  65, new int[]{  4, 2, 5, 3 } );	// Ladders
		matrix.put(  66, new int[]{  0, 1, 0, 1 } );	// Rails  (straight)
		matrix.put( 866, new int[]{  2, 5, 3, 4 } );	// Rails  (inclined)
		matrix.put( 966, new int[]{  6, 7, 8, 9 } );	// Rails  (corner)
		matrix.put(  67, new int[]{  0, 2, 1, 3 } );	// Stone stairs
		matrix.put(  68, new int[]{  4, 2, 5, 3 } );	// Wall signs
		matrix.put(  69, new int[]{  2, 4, 1, 3 } );	// Levers (up)
		matrix.put( 969, new int[]{ 10,12, 9,11 } );	// Levers (down)
		matrix.put(  71, new int[]{  2, 3, 2, 3 } );	// Iron doors (bottom)
		matrix.put( 971, new int[]{ 10,11,10,11 } );	// Iron doors (bottom)
		matrix.put(  75, new int[]{  2, 4, 1, 3 } );	// Redstone torches
		matrix.put(  76, new int[]{  2, 4, 1, 3 } );	// Redstone torches (lit)
		matrix.put(  77, new int[]{  2, 4, 1, 3 } );	// Stone buttons
		matrix.put(  86, new int[]{  1, 2, 3, 0 } );	// Pumpkins
//		matrix.put(  90, new int[]{  0, 0, 0, 0 } );	// Portals
		matrix.put(  91, new int[]{  1, 2, 3, 0 } );	// Jacks-o-Lantern
		matrix.put(  93, new int[]{  3, 0, 1, 2 } );	// Repeater (off) pos.1
		matrix.put( 793, new int[]{  7, 4, 5, 6 } );	// Repeater (off) pos.2
		matrix.put( 893, new int[]{ 11, 8, 9,10 } );	// Repeater (off) pos.3
		matrix.put( 993, new int[]{ 15,12,13,14 } );	// Repeater (off) pos.4
		matrix.put(  94, new int[]{  3, 0, 1, 2 } );	// Repeater (on) pos.1
		matrix.put( 794, new int[]{  7, 4, 5, 6 } );	// Repeater (on) pos.2
		matrix.put( 894, new int[]{ 11, 8, 9,10 } );	// Repeater (on) pos.3
		matrix.put( 994, new int[]{ 15,12,13,14 } );	// Repeater (on) pos.4

		return matrix;
	}
}