package gwj;

public class constant {
	public static byte[] ORG1 = {00,00,00,00};//文件开始地址
	public static byte[] ORG2 = {00,00,00,8};//加密开始地址
	public static byte[] ORG3 = {00,00,01,00};//版本信息开始地址
	public static byte filemark;
	public static byte[] versionifo = new byte[4];//版本信息
	public static byte[] date = new byte[4];//导出日期
	public static byte[] projectnameaddr = new byte[4];//项目名称地址
	public static byte[] datatableaddr = new byte[4];//数据表格地址
	public static byte[] projectname = new byte[4];//项目名称
	public static byte[] namearray = new byte[4];//名称数组
	public static byte[] allquestionnum = new byte[4];//总共多少题
	public static byte[][] questionIdaddr ;//对应项目地址
	public static byte[] questionDatasize = new byte[4];//题干大小（既有几个选择项）
	public static byte[][][] selectionaddr  = new byte [8][8][4];//对应选择项地址 ，1维表示第i个选项，2维表示地址所占字节一般为4个字节
	public static byte[] questionsize = new byte[4];
	public static byte[][] allifo ;//X坐标 * Y坐标 * 中文字符 * qst0ImagNum * qst0ImagAddr * wordSndAddr
	public static String[][] allifochinese = new String [8][6];
	public static byte[] imageaddr  = new byte [4];//图片数据地址
	public static byte[] imagesize = new byte[4];//图片大小
	public static byte[] imagewidth = new byte[2];//图片宽
	public static byte[] imageheight = new byte[2];//图片高

}
