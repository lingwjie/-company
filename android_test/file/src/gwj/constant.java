package gwj;

public class constant {
	public static byte[] ORG1 = {00,00,00,00};//�ļ���ʼ��ַ
	public static byte[] ORG2 = {00,00,00,8};//���ܿ�ʼ��ַ
	public static byte[] ORG3 = {00,00,01,00};//�汾��Ϣ��ʼ��ַ
	public static byte filemark;
	public static byte[] versionifo = new byte[4];//�汾��Ϣ
	public static byte[] date = new byte[4];//��������
	public static byte[] projectnameaddr = new byte[4];//��Ŀ���Ƶ�ַ
	public static byte[] datatableaddr = new byte[4];//���ݱ���ַ
	public static byte[] projectname = new byte[4];//��Ŀ����
	public static byte[] namearray = new byte[4];//��������
	public static byte[] allquestionnum = new byte[4];//�ܹ�������
	public static byte[][] questionIdaddr ;//��Ӧ��Ŀ��ַ
	public static byte[] questionDatasize = new byte[4];//��ɴ�С�����м���ѡ���
	public static byte[][][] selectionaddr  = new byte [8][8][4];//��Ӧѡ�����ַ ��1ά��ʾ��i��ѡ�2ά��ʾ��ַ��ռ�ֽ�һ��Ϊ4���ֽ�
	public static byte[] questionsize = new byte[4];
	public static byte[][] allifo ;//X���� * Y���� * �����ַ� * qst0ImagNum * qst0ImagAddr * wordSndAddr
	public static String[][] allifochinese = new String [8][6];
	public static byte[] imageaddr  = new byte [4];//ͼƬ���ݵ�ַ
	public static byte[] imagesize = new byte[4];//ͼƬ��С
	public static byte[] imagewidth = new byte[2];//ͼƬ��
	public static byte[] imageheight = new byte[2];//ͼƬ��

}
