
public class test {
	public static String bytetohexString(byte [] m)
	{
		String hex = "";
		String ret = "";
		for (int i = 0; i < m.length; i++) {
			hex = Integer.toHexString(m[i] & 0xFF);
			if (hex.length() == 1) { 
				hex = '0' + hex; 
				}
			ret += hex ;
			}
		
		return ret;	
	}
	public static int bytearraytoint(byte[] m)
	{
		String a = bytetohexString(m);
		int b = 0;
		int temp;
		for (int i = 0; i < a.length(); i++) {
			b += Integer.parseInt(a.substring(i, i+1), 16) * hexadecimal(a.length()-1-i,16); 
		}
		return b;
	}
	public static int hexadecimal(int data , int scale)
	{
		int a = 1;
		for (int j = 1; j <= data; j++) {
			a *= scale; 
		}
		return a;
	}
	 public static void main(String args[])
	 {
		 byte[] a = new byte[]{00,00,10,100};
		 //int b = Integer.parseInt(bytetohexString(a).substring(1, 2), 16);
		 System.out.println(bytetohexString(a));
		 System.out.println(bytearraytoint(a));
		 //System.out.println(bytetohexString(a).length());
		 //System.out.print(b);
	 }
}
