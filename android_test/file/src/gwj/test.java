package gwj;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
public class test {		
	public static void main(String args[])
	{
		String filepath = "D:\\mfh.pin";
		analysiswordSndAddr(filepath);
		
	}
	/**
	 * 获取项目名称地址和数据表格地址
	 * @param path
	 */
	public static void getinitaladdress(String path)
	{
		File file = new File(path);
		RandomAccessFile in = null;
		byte[] tempbyte = new byte[1] ;
		try {
			in = new RandomAccessFile(file, "r");
			in.read(tempbyte);	
		constant.filemark = tempbyte[0];//文件标识
		/**
		 * 跳转到版本信息处
		 */
		in.seek(bytearraytoint(rightorder(constant.ORG3) ));
//		System.out.println(righorderhexString(rightorder(constant.ORG3)));
//		System.out.println(bytearraytoint(rightorder(constant.ORG3) ));
		//读取版本信息
		in.read(constant.versionifo);
//		System.out.println("版本信息String类型显示："+righorderhexString(constant.versionifo));
		//读取导出日期
		in.read(constant.date);
//		System.out.println("导出日期："+righorderhexString(constant.date) + "转换成正确格式");
		//读取项目名称地址
		in.read(constant.projectnameaddr);
//		System.out.println("项目名称在文件中的地址 16进制显示："+ righorderhexString(constant.projectnameaddr));
		//读取数据表格地址
		in.read(constant.datatableaddr);			
		//System.out.println("数据表格在文件中的地址16进制显示：" + righorderhexString(constant.datatableaddr));
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 分析数据表格
	 * @param path
	 */
	public static void analysisdatatable(String path)
	{
		getinitaladdress(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");			
			/** 跳转到数据表格地址 */
			in.seek(bytearraytoint(constant.datatableaddr));
			//读取总共多少题
			in.read(constant.allquestionnum);
			int a = bytearraytoint(constant.allquestionnum);
			constant.questionIdaddr = new byte [8][4];
			//读取项目地址 有毛病数据显示32个项目，实际地址只有8,读出8个项目的地址
			for (int i = 0; i < 8; i++) {
				in.read(constant.questionIdaddr[i]);
			}
			/** 跳转到第i个项目 */
			for (int i = 0; i < constant.questionIdaddr.length; i++) {
				in.seek(bytearraytoint(constant.questionIdaddr[i]));				
				//读取题干大小（有几个选项）
				in.read(constant.questionDatasize);
				int b = bytearraytoint(constant.questionDatasize);
				//读取各个选择项的地址
				for (int j = 0; j < 8; j++) {
					in.read(constant.selectionaddr[i][j]);}
			}
			/** 跳转到第i个选择项*/
			for (int k = 0; k < 8; k++) {
				for (int i = 0; i < 6; i++) {
				in.seek(bytearraytoint(constant.selectionaddr[k][i]));
			//读取大小
			in.read(constant.questionsize);
			//读取一些信息
			constant.allifo = new byte [8][bytearraytoint(constant.questionsize)];
			in.read(constant.allifo[k]);
			constant.allifochinese[k][i] = new String(constant.allifo[k],"UTF-8");//将字节转化为中文
			in.read();}			
				}				
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
	}
}
	/**
	 * 解析表格数据中的图片信息
	 * @param path
	 */
	public static void analysisImage(String path)
	{		
		analysisdatatable(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");
			String s = constant.allifochinese[0][0].substring(6, 7);
			String [][] qstimageaddr = new String[8][6];
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 6; k++) {				
				qstimageaddr[j][k] = "";
				int length1= 0  ,length2 = 0;
				for (int i = 0; i < constant.allifochinese[j][k].length(); i++) {
					if (constant.allifochinese[j][k].substring(i, i+1).equals(s)) {
						length1++;
						}
					if (length1 == 4) {
						length2++;
						if (length2 != 1) {
							qstimageaddr[j][k] += constant.allifochinese[j][k].substring(i, i+1);
							}						
						}
					}
				}
			}
			
			/** 跳转到所有图片地址*/
			//获取上面一串信息中图片的地址
			System.out.println("图片保存地址为D:\\图片" );
			for (int j = 0; j < qstimageaddr.length; j++) {
				for (int k = 0; k < qstimageaddr[j].length; k++) {
					in.seek(wipeblank(qstimageaddr[j][k]));
					//读取图片信息
					int[] imageaddr = new int [2];
					for (int i = 0; i < imageaddr.length; i++) {
						in.read(constant.imageaddr);
					 imageaddr[i] = bytearraytoint(constant.imageaddr);
					}		
					/** 跳转到第i张图片 */				
					int [] imagewidth = new int[2];
					int [] imageheight = new int[2];
					for (int i = 0; i < imageaddr.length; i++) {
						in.seek(imageaddr[i]);
						in.read(constant.imagesize);
						in.read(constant.imagewidth);
						imagewidth[i] = bytearraytoint(constant.imagewidth);
						in.read(constant.imageheight);
						imagewidth[i] = bytearraytoint(constant.imageheight);
						byte [] imagebyte = new byte [bytearraytoint(constant.imagesize)];
						in.read(imagebyte);				
						ByteArrayInputStream imagein = new ByteArrayInputStream(imagebyte);
						BufferedImage image = ImageIO.read(imagein);
						File newFile = new File("d:\\图片\\"+j+k +"第 "+ (i+1) + "张图片.png");
						ImageIO.write(image, "png", newFile);
					}		
				}
			}
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
	}
}
	/**
	 * 解析wordSndAddr
	 * @param path
	 */
	public static void analysiswordSndAddr(String path)
	{
		analysisdatatable(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");
			String s = constant.allifochinese[0][0].substring(6, 7);
			String [][] wordaddr = new String[8][6];
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 6; k++) {				
				wordaddr[j][k] = "";
				int length1= 0  ,length2 = 0;
				for (int i = 0; i < constant.allifochinese[j][k].length(); i++) {
					if (constant.allifochinese[j][k].substring(i, i+1).equals(s)) {
						length1++;
						}
					if (length1 == 5) {
						length2++;
						if (length2 != 1) {
							wordaddr[j][k] += constant.allifochinese[j][k].substring(i, i+1);
							}						
						}
					}
				}
			}
			byte[] a = new byte[4];
			byte[] b = new byte[2];
			try {
				in.seek(wipeblank(wordaddr[0][0]));
				System.out.println(wipeblank(wordaddr[0][0]));
				in.read(a);
				in.read(b);
				System.out.println(righorderhexString(a)+"  "+bytetohexString(b));
				System.out.println("音频大小为"+bytearraytoint(a));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 分析项目名称
	 * @param path
	 */
	public static void analysisprojectname(String path)
	{
		getinitaladdress(path);
		File file = new File(path);
		RandomAccessFile in = null;			
		try {
			in = new RandomAccessFile(file, "r");
			//in.seek(bytearraytoint(constant.projectnameaddr));
			in.seek(16*16+16*2+4);
			byte [] b = new byte[10];
			///in.read(constant.projectname);
			in.read(b);
			String string =  new String( b,"GB2312");
			System.out.println(string);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			}
		}
	/**
	 * 将byte数组转换成16进制字符串
	 * @param m
	 * @return
	 */
	public static String bytetohexString(byte [] m)
	{
		String hex = "";
		String ret = "";
		for (int i = 0; i < m.length; i++) {
			hex = Integer.toHexString(m[i] & 0xFF);
			if (hex.length() == 1) { 
				hex = '0' + hex; 
				}
			ret += hex;
			}
		
		return ret;	
	}
	/**
	 * 数据中地址顺序高地位颠倒，正确顺序地址的16进制字符串
	 * @param m
	 * @return
	 */
	public static String righorderhexString(byte [] m)
	{
		String hex = "";
		String ret = "";
		m = rightorder(m);
		for (int i = 0; i < m.length; i++) {
			hex = Integer.toHexString(m[i] & 0xFF);
			if (hex.length() == 1) { 
				hex = '0' + hex; 
				}
			ret += hex ;
			}
		
		return ret;	
	}
	/**
	 * 将地址转换成10进制，方便地址跳转
	 * @param m
	 * @return
	 */
	public static int bytearraytoint(byte[] m )
	{
		String a = righorderhexString(m);
		int b = 0;
		int temp;
		for (int i = 0; i < a.length(); i++) {
			b += Integer.parseInt(a.substring(i, i+1), 16) * adecimal(a.length()-1-i,16); 
		}
		return b;
	}
	public static int wipeblank(String str )
	{
		String temp = "";
		byte [] s = {00};
		String string = "";
		try {
			string = new String(s,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int b = 0;
		for (int i = 0; i < str.length(); i++) {
			if(!str.substring(i, i+1) .equals(string)){
				temp += str.substring(i, i+1);
				}
			}
		for (int j = 0; j < temp.length(); j++) {
			b += Integer.parseInt(temp.substring(j, j+1), 16) * adecimal(temp.length()-1-j,10); 
		}
		
		return b;
	}
	/**
	 * 幂次数
	 * @param data
	 * @param scale
	 * @return
	 */
	public static int adecimal(int data , int scale)
	{
		int a = 1;
		for (int j = 1; j <= data; j++) {
			a *= scale; 
		}
		return a;
	}
	/**
	 * 地址颠倒顺序
	 * @param addr
	 * @return
	 */
	public static byte [] rightorder(byte [] addr)
	{
		byte [] temp = new byte[addr.length];
		for (int i = 0; i < addr.length; i++) {
			temp[temp.length-1-i] = addr[i];
		}
		return temp;
	}
}