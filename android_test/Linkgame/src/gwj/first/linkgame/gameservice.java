package gwj.first.linkgame;

public interface gameservice {
	void start();
	/******判断两个图片是否可以相连****/
	drawlink surelink(piece piece1 , piece piece2);
	
	/**
	 * 定义一个接口方法, 用于返回一个二维数组
	 * 
	 * @return 存放方块对象的二维数组
	 */
	piece[][] getPieces();
	
	/**
	 * 判断参数Piece[][]数组中是否还存在非空的Piece对象
	 * 
	 * @return 如果还剩Piece对象返回true, 没有返回false
	 */
	boolean hasPieces();
	
	/**
	 * 根据鼠标的x座标和y座标, 查找出一个Piece对象
	 * 
	 * @param touchX 鼠标点击的x座标
	 * @param touchY 鼠标点击的y座标
	 * @return 返回对应的Piece对象, 没有返回null
	 */
	piece findPiece(float touchX, float touchY);

}
