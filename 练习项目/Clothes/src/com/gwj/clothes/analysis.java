package com.gwj.clothes;

public interface analysis {
	/**
	 * 获取项目名称地址和数据表格地址
	 * @param path
	 */
	//void getinitaladdress(String path);
	/**
	 * 分析数据表格
	 * @param path
	 */
	void analysisdatatable(String path);
	/**
	 * 解析表格数据中的图片信息
	 * @param path
	 */
	void analysisImage(String path);
	/**
	 * 解析wordSndAddr
	 * @param path
	 */
	void analysiswordSndAddr(String path);
	/**
	 * 分析项目名称
	 * @param path
	 */
	void analysisprojectname(String path);
	/**
	 * 将byte数组转换成16进制字符串
	 * @param m
	 * @return
	 */
	String bytetohexString(byte [] m);
	/**
	 * 数据中地址顺序高地位颠倒，正确顺序地址的16进制字符串
	 * @param m
	 * @return
	 */
	String righorderhexString(byte [] m);
	/**
	 * 将地址转换成10进制，方便地址跳转
	 * @param m
	 * @return
	 */
	int bytearraytoint(byte[] m );
	int wipeblank(String str );
	/**
	 * 幂次数
	 * @param data
	 * @param scale
	 * @return
	 */
	int adecimal(int data , int scale);
	/**
	 * 地址颠倒顺序
	 * @param addr
	 * @return
	 */
	byte [] rightorder(byte [] addr);
	/**
	 *  程序中获取图片的字节数组
	 * @param name
	 * @param number
	 * @return
	 */
	 
	void getimage(String name);

}
