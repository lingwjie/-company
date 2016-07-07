package com.example.pingyi;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class myview extends View {

	public myview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	/*private Bitmap bitmap;
	private Matrix matrix = new Matrix();
	private int width,height;
	public myview(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.piano01);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		
		this.setFocusable(true);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		matrix.reset();
		matrix.setTranslate(100, 30);
		matrix.setTranslate(100, 40);
		matrix.setTranslate(100, 50);
		matrix.setTranslate(100, 60);
		//matrix.reset();
		canvas.drawBitmap(bitmap,matrix,null);
	}
*/
	/*final int BACK_HEIGHT = 1700;
	//初始化图片资源
	private Bitmap back;
	private Bitmap plane;
	
	final int WIDTH = 320;
	final int HEIGHT = 440;
	
	private int startY= BACK_HEIGHT-HEIGHT;
	
	//private Matrix matrix = new Matrix();
	public myview(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
	//	bitmap = ((Bitmap) context.getResources().getDrawable(R.drawable.piano01)).getBitmap();
		
		back = BitmapFactory.decodeResource(context.getResources(), R.drawable.piano01);
		
		plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.piano01);
		
		final Handler handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if(msg.what== 0X123)
				{
					if(startY <= 0)
					{
						startY= BACK_HEIGHT-HEIGHT;
					}
					else
					{
						startY -= 3;
					}
				}
				invalidate();
			}
		};
		new Timer().schedule(new TimerTask()
		{
			@Override			
			public void run()
			{
				handler.sendEmptyMessage(0x123);
			}
		}, 0,100);
		
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		Bitmap bitmap2 = Bitmap.createBitmap(back,0,startY,WIDTH,HEIGHT);
		canvas.drawBitmap(bitmap2, 0,0,null);
		
		canvas.drawBitmap(plane, 160, 380, null);
	}
*/
}
