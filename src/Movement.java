//Movement control

public class Movement 
{
	//Find the x position
	static int findX(int x)
	{
		int num = 0;
		
		if( (x % 10) == 0)
		{
			num = 55;
		}
		else if( (x % 10) == 1)
		{
			num = 115;
		} 
		else if( (x % 10) == 2)
		{
			num = 175;
		} 
		else if( (x % 10) == 3)
		{
			num = 235;
		} 
		else if( (x % 10) == 4)
		{
			num = 295;
		} 
		else if( x < 0)
		{
			num = 1000;
		} 
		
		return num;
	}
	
	//Find the y position
	static int findY(int y)
	{
		int num = 0;
		
		if( (y / 10) == 0)
		{
			num = 68;
		}
		else if( (y / 10) == 1)
		{
			num = 128;
		}
		else if( (y / 10) == 2)
		{
			num = 188;
		}
		else if( (y / 10) == 3)
		{
			num = 248;
		}
		else if( (y / 10) == 4)
		{
			num = 308;
		}
		else if( y < 0)
		{
			num = 1000;
		} 
		
		return num;
	}
	
	//Check the up direction
	static int checkUp(int p, int [][]a)
	{
		int num = -1;
		int j = p%10;

		if((p / 10) != 0)
			{
			for(int i = ((p / 10)-1) ; i>=0; i--)
			{	
				if(a[i][j] != 0)
				{	
					num = (i +1)* 10 + j;
					return num;
				}		
			}
		}
		return num;
	}
	
	//Check the down direction
	static int checkDown(int p, int [][]a)
	{
		int num = -1;
		int j = p%10;

		if((p / 10) != 4)
		{
			for(int i = ((p / 10)+1) ; i< 5; i++)
			{	
				if(a[i][j] != 0)
				{	
					num = (i -1)* 10 + j;
					return num;
				}		
			}
		}
		return num;
	}
	
	//Check the left direction
	static int checkLeft(int p, int [][]a)
	{
		int num = -1;
		int i = p / 10;
		int point = (p%10) -1 ;
		
		if((p % 10) != 0)
		{
			for(int j = point ; j>=0; j--)
			{
				if(a[i][j] != 0)
				{
					num = (j +1) + ( p / 10 * 10);
					return num;
				}
			}
		}
		return num;
	}
	
	//Check the right direction
	static int checkRight(int p, int [][]a)
	{
		int num = -1;
		int i = p / 10;
		int point = (p%10) +1 ;
		
		if((p % 10) != 4)
		{
			for(int j = point ; j<=4; j++)
			{
				if(a[i][j] != 0)
				{
					num = (j -1) + ( p / 10 * 10);
					return num;
				}
			}
		}
		return num;
	}
	
	//Find the array index of x
	static int findIndexX(int p)
	{
		int num = (p / 10);
		return num;
	}
	
	//Find the array index of y
	static int findIndexY(int p)
	{
		int num = (p % 10);
		return num;
	}

	//Check win
	static boolean checkWin(int p, int w)
	{
		boolean check = false;
		
		if ( p == w )
		{
			check = true;
		}
		
		return check;
	}
	
}
