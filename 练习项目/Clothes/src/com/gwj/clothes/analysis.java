package com.gwj.clothes;

public interface analysis {
	/**
	 * ��ȡ��Ŀ���Ƶ�ַ�����ݱ���ַ
	 * @param path
	 */
	//void getinitaladdress(String path);
	/**
	 * �������ݱ��
	 * @param path
	 */
	void analysisdatatable(String path);
	/**
	 * ������������е�ͼƬ��Ϣ
	 * @param path
	 */
	void analysisImage(String path);
	/**
	 * ����wordSndAddr
	 * @param path
	 */
	void analysiswordSndAddr(String path);
	/**
	 * ������Ŀ����
	 * @param path
	 */
	void analysisprojectname(String path);
	/**
	 * ��byte����ת����16�����ַ���
	 * @param m
	 * @return
	 */
	String bytetohexString(byte [] m);
	/**
	 * �����е�ַ˳��ߵ�λ�ߵ�����ȷ˳���ַ��16�����ַ���
	 * @param m
	 * @return
	 */
	String righorderhexString(byte [] m);
	/**
	 * ����ַת����10���ƣ������ַ��ת
	 * @param m
	 * @return
	 */
	int bytearraytoint(byte[] m );
	int wipeblank(String str );
	/**
	 * �ݴ���
	 * @param data
	 * @param scale
	 * @return
	 */
	int adecimal(int data , int scale);
	/**
	 * ��ַ�ߵ�˳��
	 * @param addr
	 * @return
	 */
	byte [] rightorder(byte [] addr);
	/**
	 *  �����л�ȡͼƬ���ֽ�����
	 * @param name
	 * @param number
	 * @return
	 */
	 
	void getimage(String name);

}
