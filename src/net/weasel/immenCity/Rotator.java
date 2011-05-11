package net.weasel.immenCity;

import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Rotator 
{
	static int LEFT = 0;
	static int RIGHT = 1;
	
	public static HashMap<Integer,int[]> orientedBlocks = OrientedBlocks.BlockHash();
	
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
		
		return( dirTemp[vTemp] );
	}

	public static void rotate( Block block, int data, BlockFace oldDir, BlockFace newDir )
	{
		int[] dataValues = null;

		dataValues = orientedBlocks.get( block.getTypeId() );
		
		if( newDir == BlockFace.NORTH )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) data );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, RIGHT) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT) );
		}
		else if( newDir == BlockFace.EAST )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) data );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT ) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, RIGHT ) );
		}
		else if( newDir == BlockFace.SOUTH )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 2, RIGHT ) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT ) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) data );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT ) );
		}
		else if( newDir == BlockFace.WEST )
		{
			if( oldDir == BlockFace.NORTH ) 
				block.setData( (byte) getRotatedValue(dataValues, data, 1, LEFT ) );
			if( oldDir == BlockFace.EAST )
				block.setData( (byte) getRotatedValue(dataValues, data, 2, RIGHT ) );
			if( oldDir == BlockFace.SOUTH )
				block.setData( (byte) getRotatedValue(dataValues, data, 1, RIGHT ) );
			if( oldDir == BlockFace.WEST )
				block.setData( (byte) data );
		}
	}
}
