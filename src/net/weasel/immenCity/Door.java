package net.weasel.immenCity;

import org.bukkit.block.BlockFace;

public class Door 
{
	public static int reorientBlockData( BlockFace oldDir, BlockFace newDir, int type, int data )
	{
		int retVal = data;
		
		// Door
		//
		if( newDir == BlockFace.NORTH )
		{
			if( oldDir == BlockFace.EAST )
			{
				if( data == 3 ) retVal = 2;
				if( data == 11 ) retVal = 10;
			}
			if( oldDir == BlockFace.SOUTH )
			{
				if( data == 0 ) retVal = 2;
				if( data == 8 ) retVal = 10;
			}
			if( oldDir == BlockFace.WEST )
			{
				if( data == 1 ) retVal = 2;
				if( data == 9 ) retVal = 10;
			}
		}

		if( newDir == BlockFace.EAST )
		{
			if( oldDir == BlockFace.NORTH )
			{
				if( data == 2 ) retVal = 3;
				if( data == 10 ) retVal = 11;
			}
			if( oldDir == BlockFace.SOUTH )
			{
				if( data == 0 ) retVal = 3;
				if( data == 8 ) retVal = 11;
			}
			if( oldDir == BlockFace.WEST )
			{
				if( data == 1 ) retVal = 3;
				if( data == 9 ) retVal = 11;
			}
		}

		if( newDir == BlockFace.SOUTH )
		{
			if( oldDir == BlockFace.NORTH )
			{
				if( data == 2 ) retVal = 0;
				if( data == 10 ) retVal = 8;
			}
			if( oldDir == BlockFace.EAST )
			{
				if( data == 3 ) retVal = 0;
				if( data == 11 ) retVal = 8;
			}
			if( oldDir == BlockFace.WEST )
			{
				if( data == 1 ) retVal = 0;
				if( data == 9 ) retVal = 8;
			}
		}

		if( newDir == BlockFace.WEST )
		{
			if( oldDir == BlockFace.NORTH )
			{
				if( data == 2 ) retVal = 1;
				if( data == 10 ) retVal = 9;
			}
			if( oldDir == BlockFace.EAST )
			{
				if( data == 3 ) retVal = 1;
				if( data == 11 ) retVal = 9;
			}
			if( oldDir == BlockFace.SOUTH )
			{
				if( data == 0 ) retVal = 1;
				if( data == 8 ) retVal = 9;
			}
		}

		return retVal;
	}
}

