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
	 * ��ȡ��Ŀ���Ƶ�ַ�����ݱ���ַ
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
		constant.filemark = tempbyte[0];//�ļ���ʶ
		/**
		 * ��ת���汾��Ϣ��
		 */
		in.seek(bytearraytoint(rightorder(constant.ORG3) ));
//		System.out.println(righorderhexString(rightorder(constant.ORG3)));
//		System.out.println(bytearraytoint(rightorder(constant.ORG3) ));
		//��ȡ�汾��Ϣ
		in.read(constant.versionifo);
//		System.out.println("�汾��ϢString������ʾ��"+righorderhexString(constant.versionifo));
		//��ȡ��������
		in.read(constant.date);
//		System.out.println("�������ڣ�"+righorderhexString(constant.date) + "ת������ȷ��ʽ");
		//��ȡ��Ŀ���Ƶ�ַ
		in.read(constant.projectnameaddr);
//		System.out.println("��Ŀ�������ļ��еĵ�ַ 16������ʾ��"+ righorderhexString(constant.projectnameaddr));
		//��ȡ���ݱ���ַ
		in.read(constant.datatableaddr);			
		//System.out.println("���ݱ�����ļ��еĵ�ַ16������ʾ��" + righorderhexString(constant.datatableaddr));
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �������ݱ��
	 * @param path
	 */
	public static void analysisdatatable(String path)
	{
		getinitaladdress(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");			
			/** ��ת�����ݱ���ַ */
			in.seek(bytearraytoint(constant.datatableaddr));
			//��ȡ�ܹ�������
			in.read(constant.allquestionnum);
			int a = bytearraytoint(constant.allquestionnum);
			constant.questionIdaddr = new byte [8][4];
			//��ȡ��Ŀ��ַ ��ë��������ʾ32����Ŀ��ʵ�ʵ�ַֻ��8,����8����Ŀ�ĵ�ַ
			for (int i = 0; i < 8; i++) {
				in.read(constant.questionIdaddr[i]);
			}
			/** ��ת����i����Ŀ */
			for (int i = 0; i < constant.questionIdaddr.length; i++) {
				in.seek(bytearraytoint(constant.questionIdaddr[i]));				
				//��ȡ��ɴ�С���м���ѡ�
				in.read(constant.questionDatasize);
				int b = bytearraytoint(constant.questionDatasize);
				//��ȡ����ѡ����ĵ�ַ
				for (int j = 0; j < 8; j++) {
					in.read(constant.selectionaddr[i][j]);}
			}
			/** ��ת����i��ѡ����*/
			for (int k = 0; k < 8; k++) {
				for (int i = 0; i < 6; i++) {
				in.seek(bytearraytoint(constant.selectionaddr[k][i]));
			//��ȡ��С
			in.read(constant.questionsize);
			//��ȡһЩ��Ϣ
			constant.allifo = new byte [8][bytearraytoint(constant.questionsize)];
			in.read(constant.allifo[k]);
			constant.allifochinese[k][i] = new String(constant.allifo[k],"UTF-8");//���ֽ�ת��Ϊ����
			in.read();}			
				}				
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
	}
}
	/**
	 * ������������е�ͼƬ��Ϣ
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
			
			/** ��ת������ͼƬ��ַ*/
			//��ȡ����һ����Ϣ��ͼƬ�ĵ�ַ
			System.out.println("ͼƬ�����ַΪD:\\ͼƬ" );
			for (int j = 0; j < qstimageaddr.length; j++) {
				for (int k = 0; k < qstimageaddr[j].length; k++) {
					in.seek(wipeblank(qstimageaddr[j][k]));
					//��ȡͼƬ��Ϣ
					int[] imageaddr = new int [2];
					for (int i = 0; i < imageaddr.length; i++) {
						in.read(constant.imageaddr);
					 imageaddr[i] = bytearraytoint(constant.imageaddr);
					}		
					/** ��ת����i��ͼƬ */				
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
						File newFile = new File("d:\\ͼƬ\\"+j+k +"�� "+ (i+1) + "��ͼƬ.png");
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
	 * ����wordSndAddr
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
				System.out.println("��Ƶ��СΪ"+bytearraytoint(a));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������Ŀ����
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
	 * ��byte����ת����16�����ַ���
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
	 * �����е�ַ˳��ߵ�λ�ߵ�����ȷ˳���ַ��16�����ַ���
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
	 * ����ַת����10���ƣ������ַ��ת
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
	 * �ݴ���
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
	 * ��ַ�ߵ�˳��
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