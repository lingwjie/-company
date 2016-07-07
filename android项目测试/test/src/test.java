import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;




public class test {
	public static void main(String agrs[]){
		String word = "other,only,each,foot,long,street,corner,please,straight,left,park,supermarket,bank,station,subway,think,office,example,map,horse,short,morning,sport,forty,mean,traffic,road,here,school,cream,cheese,healthy,food,enough,computer,every,fruit,favorite,cola,be";
		String[] s = stringToArray( word);
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i]+" ");
		}
	
			
			
		
		/*double a =  111.33667777;
		BigDecimal testBigDecimal = new BigDecimal(a);
		System.out.println(testBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP));*/
		/*int a = 22222222;
		String b = a+"";
		
		for (int i = 10; i < 100000000; i = i*10) {
			System.err.println(i+" jjjjj"+ a/i);
			if (a/i <= 0) {
				b = "0"+b;
			}
		}
		
		System.out.println(b);*/
//		Date b = new Date();
//		System.out.println(b.getTime()/1000);
		
	}
	private static  String[] stringToArray(String word){
		ArrayList words = new ArrayList(); 
		//String [] words = {};
		int k = 0;
		int i;
		while (word.length() > 0) {
			i = word.indexOf(",");
			if (i != -1) {
				words.add(word.substring(0, i)) ;
				
			}else {
				words.add(word);
				word = "";
			}			
			word = word.substring(i+1, word.length());
			k++;
			
		}
		
		return array(words);
	}
	private static String [] array(ArrayList s){
		String [] words = new String[s.size()];
		for (int i = 0; i < s.size(); i++) {
			words[i] = (String) s.get(i);
		}
		return words;
		
	}
}
