package gwj.first.linkgame;

import android.R.integer;
import android.graphics.Point;

public class piece {
	private pieceImage image;
	private int  beginx;
	private int beginy;
	private int indexX;
	private int indexY;
	
	public piece(int indexX , int indexY)
	{
		this.indexX = indexX;
		this.indexY = indexY;
		
	}
	public int getBeginX()
	{
		return beginx;
	}
	public int getBeginY()
	{
		return beginy;
	}
	public void setBeginX( int beginx)
	{
		this.beginx = beginx;
	}
	public void setBeginY(int beginY)
	{
		this.beginy = beginY;
	}

	public int getIndexX()
	{
		return indexX;
	}

	public void setIndexX(int indexX)
	{
		this.indexX = indexX;
	}

	public int getIndexY()
	{
		return indexY;
	}

	public void setIndexY(int indexY)
	{
		this.indexY = indexY;
	}
	

	public pieceImage getImage()
	{
		return image;
	}

	public void setImage(pieceImage image)
	{
		this.image = image;
	}

	// 获取该Piece的中心
	public Point getCenter()
	{
		return new Point(getImage().getImage().getWidth() / 2
			+ getBeginX(), getBeginY()
			+ getImage().getImage().getHeight() / 2);
	}
	public boolean isSameImage(piece other)
	{
		if (image == null) {
			if (other.image != null) {
				return false;
			}
		}
		return image.getImageId() == other.image.getImageId();
	}
}
