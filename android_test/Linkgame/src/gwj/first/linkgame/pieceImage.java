package gwj.first.linkgame;

import android.graphics.Bitmap;

public class pieceImage {
	
	private Bitmap image;
	private int imageId;
	public pieceImage(Bitmap imageBitmap , int imageId)
	{
		super();
		this.image = imageBitmap;
		this.imageId = imageId;
	}
	public Bitmap getImage()
	{
		return image;
	}
	public void setImage(Bitmap image)
	{
		this.image = image;
	}
	public int getImageId()
	{
		return imageId;
	}
	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}

}
