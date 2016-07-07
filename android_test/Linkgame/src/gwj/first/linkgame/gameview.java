package gwj.first.linkgame;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class gameview extends View {
	private piece selectePiece;
	private piece currentPiece;
	private drawlink linkinfo;
	private Bitmap selectBitmap;
	Paint piant;

	public gameview(Context context , AttributeSet attrs) {
		super(context,attrs);
		
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		
	}

}
