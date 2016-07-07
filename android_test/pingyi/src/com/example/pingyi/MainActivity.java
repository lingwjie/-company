package com.example.pingyi;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	myview myview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TextView(this));
    }
    class TextView extends View{

    	final String  s = "java讲义";
    	Path path ;
    	Paint paint;
		public TextView(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			path = new Path();
			RectF rectf = new RectF(0,0,200,120);
			path.addOval(rectf, Path.Direction.CCW);
			paint  = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.CYAN);
			paint.setStrokeWidth(1);
		}
		
		@Override
    	protected void onDraw(Canvas canvas)
    	{
			//super.onDraw(canvas);
    		canvas.drawColor(Color.WHITE);
    		canvas.translate(40,40);
    		
    		paint.setTextAlign(Paint.Align.RIGHT);
    		paint.setTextSize(20);
    		//lujing
    		paint.setStyle(Paint.Style.STROKE);
    		canvas.drawPath(path,paint);
    		paint.setStyle(Paint.Style.FILL);
    		canvas.drawTextOnPath(s, path, -8, 20, paint);
    	}
    }
    }

//   @SuppressLint("DrawAllocation") class myview extends View {
//
//    	final int BACK_HEIGHT = 1700;
//    	//初始化图片资源
//    	private Bitmap back;
//    	private Bitmap plane;
//    	WindowManager window = getWindowManager();
//    	Display display = window.getDefaultDisplay();
//    	
//    	@SuppressWarnings("deprecation")
//		final int WIDTH = display.getWidth();
//    	@SuppressWarnings("deprecation")
//		final int HEIGHT = display.getHeight();
//    	
//    	private int startY= BACK_HEIGHT-HEIGHT;
//    	
//    	//private Matrix matrix = new Matrix();
//    	public myview(Context context,AttributeSet attrs)
//{
//    		super(context,attrs);
//    		// TODO Auto-generated constructor stub
//    	//	bitmap = ((Bitmap) context.getResources().getDrawable(R.drawable.piano01)).getBitmap();
//    		
//    		back = BitmapFactory.decodeResource(context.getResources(), R.drawable.piano01);
//    		
//    		plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.piano01);
//    		
//    		final Handler handler = new Handler()
//    		{
//    			public void handleMessage(Message msg)
//    			{
//    				if(msg.what== 0x123)
//    				{
//    					if(startY <= 0)
//    					{
//    						startY= BACK_HEIGHT-HEIGHT;
//    					}
//    					else
//    					{
//    						startY -= 3;
//    					}
//    				}
//    				invalidate();
//    			}
//    		};
//    		new Timer().schedule(new TimerTask()
//    		{
//    			@Override			
//    			public void run()
//    			{
//    				handler.sendEmptyMessage(0x123);
//    			}
//    		}, 0,100);
//    		
//    	}
//    	@Override
//    	public void onDraw(Canvas canvas)
//    	{
//    		
//    		Bitmap bitmap2 = Bitmap.createBitmap(back,0,0,100,40);
//    		
//    		canvas.drawBitmap(bitmap2, 0,0,null);
//    		
//    		canvas.drawBitmap(plane, 220, 150, null);
//    	}
//
//    }

