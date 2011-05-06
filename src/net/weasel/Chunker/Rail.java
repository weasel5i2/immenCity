package net.weasel.Chunker;

import org.bukkit.block.BlockFace;

public class Rail 
{
	public static int reorientBlockData( BlockFace oldDir, BlockFace newDir, int type, int data )
	{
		int retVal = data;

		// Standard (straight) rails
		//
		if( data == 0 || data == 1 )
		{
			if( newDir == BlockFace.WEST || newDir == BlockFace.EAST )
			{
				if( oldDir == BlockFace.NORTH || oldDir == BlockFace.SOUTH )
				{
					if( data == 1 ) retVal = 0;
					if( data == 0 ) retVal = 1;
				}
			}
			
			else if( newDir == BlockFace.NORTH || newDir == BlockFace.SOUTH )
			{
				if( oldDir == BlockFace.EAST || oldDir == BlockFace.WEST )
				{
					if( data == 1 ) retVal = 0;
					if( data == 0 ) retVal = 1;
				}
			}
		}
					
		// Corner rails
		//
		else if( data >= 6 || data <= 9 )
		{
			if( newDir == BlockFace.NORTH )
			{
				if( oldDir == BlockFace.EAST )
				{
					if( data == 6 ) retVal = 9;
					if( data == 7 ) retVal = 6;
					if( data == 8 ) retVal = 7;
					if( data == 9 ) retVal = 8;
				}
				else if( oldDir == BlockFace.SOUTH )
				{
					if( data == 7 ) retVal = 9;
					if( data == 8 ) retVal = 6;
					if( data == 9 ) retVal = 7;
					if( data == 6 ) retVal = 8;
				}
				else if( oldDir == BlockFace.WEST )
				{
					if( data == 8 ) retVal = 9;
					if( data == 9 ) retVal = 6;
					if( data == 6 ) retVal = 7	;
					if( data == 7 ) retVal = 8;
				}
			}

			if( newDir == BlockFace.EAST )
			{
				if( oldDir == BlockFace.NORTH )
				{
					if( data == 9 ) retVal = 6;
					if( data == 6 ) retVal = 7;
					if( data == 7 ) retVal = 8;
					if( data == 8 ) retVal = 9;
				}
				else if( oldDir == BlockFace.SOUTH )
				{
					if( data == 7 ) retVal = 6;
					if( data == 8 ) retVal = 7;
					if( data == 9 ) retVal = 8;
					if( data == 6 ) retVal = 9;
				}
				else if( oldDir == BlockFace.WEST )
				{
					if( data == 8 ) retVal = 6;
					if( data == 9 ) retVal = 7;
					if( data == 6 ) retVal = 8;
					if( data == 7 ) retVal = 9;
				}
			}
		
			if( newDir == BlockFace.SOUTH )
			{
				if( oldDir == BlockFace.NORTH )
				{
					if( data == 9 ) retVal = 7;
					if( data == 6 ) retVal = 8;
					if( data == 7 ) retVal = 9;
					if( data == 8 ) retVal = 6;
				}
				else if( oldDir == BlockFace.EAST )
				{
					if( data == 6 ) retVal = 7;
					if( data == 7 ) retVal = 8;
					if( data == 8 ) retVal = 9;
					if( data == 9 ) retVal = 6;
				}
				else if( oldDir == BlockFace.WEST )
				{
					if( data == 8 ) retVal = 7;
					if( data == 9 ) retVal = 8;
					if( data == 6 ) retVal = 9;
					if( data == 7 ) retVal = 6;
				}
			}

			if( newDir == BlockFace.WEST )
			{
				if( oldDir == BlockFace.NORTH )
				{
					if( data == 9 ) retVal = 8;
					if( data == 6 ) retVal = 9;
					if( data == 7 ) retVal = 6;
					if( data == 8 ) retVal = 7;
				}
				else if( oldDir == BlockFace.EAST )
				{
					if( data == 6 ) retVal = 8;
					if( data == 7 ) retVal = 9;
					if( data == 8 ) retVal = 6;
					if( data == 9 ) retVal = 7;
				}
				else if( oldDir == BlockFace.SOUTH )
				{
					if( data == 7 ) retVal = 8;
					if( data == 8 ) retVal = 9;
					if( data == 9 ) retVal = 6;
					if( data == 6 ) retVal = 7;
				}
			}
		}
		
		return retVal;
	}
}