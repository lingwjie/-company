package test;

public class test {
	static int[] hanshu(int[] b,int n)
	{
		for(int i = n;n<b.length-1;n++)
		{
			b[n] = b[n+1];
			//free(b[b.length]);
		}
		return b;}
	public static void main(String[] agrs)
	{
		int[] arry = new int[7];
		int[] a = new int[7];
		int j; 
		for(int i = 0; i<7;i++)
			{arry[i]=i;
			//System.out.print(arry[i]+" ");
			}
		int m = 0;
		int n =arry.length;
		for(int i = 0; i<7;i++)
		{
		j = (int)(Math.random()*10%(n--));
		System.out.print(j+" ");
		a[m] =arry[j];
		m++;
		int[] b;
		b = hanshu(arry,j);
		}
		System.out.println();
		for(int i = 0 ;i<7;i++)
		System.out.print(a[i]+" ");
		}

	}
