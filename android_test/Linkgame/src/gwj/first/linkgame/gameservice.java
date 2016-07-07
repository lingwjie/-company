package gwj.first.linkgame;

public interface gameservice {
	void start();
	/******�ж�����ͼƬ�Ƿ��������****/
	drawlink surelink(piece piece1 , piece piece2);
	
	/**
	 * ����һ���ӿڷ���, ���ڷ���һ����ά����
	 * 
	 * @return ��ŷ������Ķ�ά����
	 */
	piece[][] getPieces();
	
	/**
	 * �жϲ���Piece[][]�������Ƿ񻹴��ڷǿյ�Piece����
	 * 
	 * @return �����ʣPiece���󷵻�true, û�з���false
	 */
	boolean hasPieces();
	
	/**
	 * ��������x�����y����, ���ҳ�һ��Piece����
	 * 
	 * @param touchX �������x����
	 * @param touchY �������y����
	 * @return ���ض�Ӧ��Piece����, û�з���null
	 */
	piece findPiece(float touchX, float touchY);

}
