package com.gwj.clothes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import javax.imageio.ImageIO;

public class analysisrealize implements analysis{

	/*@Override
	public void getinitaladdress(String path) {
		File file = new File(path);
		RandomAccessFile in = null;
		byte[] tempbyte = new byte[1] ;
		try {
			in = new RandomAccessFile(file, "r");
			in.read(tempbyte);	
		constant.filemark = tempbyte[0];//�ļ���ʶ
		*//** ��ת���汾��Ϣ��*//*
		in.seek(bytearraytoint(rightorder(constant.ORG3) ));
		//��ȡ�汾��Ϣ
		in.read(constant.versionifo);
		//��ȡ��������
		in.read(constant.date);
		//��ȡ��Ŀ���Ƶ�ַ
		in.read(constant.projectnameaddr);
		//��ȡ���ݱ���ַ
		in.read(constant.datatableaddr);			
		in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	@Override
	public void analysisdatatable(String path) {
		
		int addr = getinitaladdress(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");			
			/** ��ת�����ݱ���ַ */
			in.seek(addr);
			//in.seek(bytearraytoint(constant.datatableaddr));
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

	@Override
	public void analysisImage(String path) {
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

	@Override
	public void analysiswordSndAddr(String path) {
		analysisdatatable(path);
		File file = new File(path);
		RandomAccessFile in = null;
		try {
			in = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void analysisprojectname(String path) {
		getinitaladdress(path);
		File file = new File(path);
		RandomAccessFile in = null;			
		try {
			in = new RandomAccessFile(file, "r");
			in.seek(bytearraytoint(constant.projectnameaddr));
			in.read(constant.projectname);
			System.out.println(new String(constant.projectname,"UTF-8"));
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	@Override
	public String bytetohexString(byte[] m) {
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

	@Override
	public String righorderhexString(byte[] m) {
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

	@Override
	public int bytearraytoint(byte[] m) {
		String a = righorderhexString(m);
		int b = 0;
		int temp;
		for (int i = 0; i < a.length(); i++) {
			b += Integer.parseInt(a.substring(i, i+1), 16) * adecimal(a.length()-1-i,16); 
		}
		return b;
	}

	@Override
	public int wipeblank(String str) {
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

	@Override
	public int adecimal(int data, int scale) {
		int a = 1;
		for (int j = 1; j <= data; j++) {
			a *= scale; 
		}
		return a;
	}

	@Override
	public byte[] rightorder(byte[] addr) {
		byte [] temp = new byte[addr.length];
		for (int i = 0; i < addr.length; i++) {
			temp[temp.length-1-i] = addr[i];
		}
		return temp;
	}

	@Override
	public void getimage(String path ) {
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
						
						constant.image[j][k][i] = imagebyte;
//						ByteArrayInputStream imagein = new ByteArrayInputStream(imagebyte);
//						BufferedImage image = ImageIO.read(imagein);
//						File newFile = new File("d:\\ͼƬ\\"+j+k +"�� "+ (i+1) + "��ͼƬ.png");
//						ImageIO.write(image, "png", newFile);
					}		
				}
			}
			in.close();
		}catch (IOException e) {
			e.printStackTrace();
	}
		//return constant.image;
	}
	public  native int getinitaladdress(String path);
	static
	{
		System.loadLibrary("calljni");
	}
	
}

