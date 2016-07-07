package com.example.ontouchevent;


import net.qiujuer.imageblurring.jni.FastBlur;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private VerticalProgressBar progressBar;
	private float x , y ,x1 , y1;
	ChildView childView;
//	private NotificationManager notificationManager;
//	private Notification n;
    @SuppressLint("WrongCall") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //顶部状态栏不占据应用控件
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); 
        //底部工具栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        全屏
//        getWindow().getDecorView().setSystemUiVisibility(
//				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
////				| View.SYSTEM_UI_FLAG_FULLSCREEN
//				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
       // getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_FULLSCREEN);
        //底部工具栏
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//		getWindow().setStatusBarColor(getResources().getColor(R.color.color_title_bar));
//		getWindow().setNavigationBarColor(getResources().getColor(R.color.color_title_bar));
        setContentView(R.layout.activity_main);
        
        progressBar = (VerticalProgressBar)findViewById(R.id.progressBar);
        childView = (ChildView)findViewById(R.id.ChildView);
        childView.onMeasure(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight());
        
        Button dis  = (Button)findViewById(R.id.disappear);
        jniBlurArray(((BitmapDrawable)getResources().getDrawable(R.drawable.test)).getBitmap(),childView);
        dis.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				notificationManager.notify(1, n);
				getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_FULLSCREEN);
				
			}
		});
        Button showButton = (Button)findViewById(R.id.show);
        showButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
			}
		});
        childView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "childView被点击");
				
			}
		});
        
       
        
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    }
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, " down");
			x = event.getX();
			y = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			x1 = event.getX();
			y1 = event.getY();
			
			Log.i(TAG, " move" + (int)((y-y1)));
			if ((int)((y-y1)) > 100) {
				 progressBar.setProgress(100);
			}else {
				
				progressBar.setProgress((int)((y-y1)));
			}
			 y = y1;
			break;
		case MotionEvent.ACTION_UP:
			Log.i(TAG, " up");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
    
    
    void applyBlur(final View image, final View frost) {
		image.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					@Override
					public boolean onPreDraw() {
						image.getViewTreeObserver().removeOnPreDrawListener(
								this);
						image.buildDrawingCache();

						Bitmap bmp = image.getDrawingCache();
						jniBlurArray(bmp, frost);
						return true;
					}
				});
	}

	void jniBlurArray(Bitmap bkg, View view) {
		long startMs = System.currentTimeMillis();
		float scaleFactor = 8;
		float radius = 20;

		int vw = view.getMeasuredWidth();
		int vh = view.getMeasuredHeight();
		int width = (int) (vw / scaleFactor);
		int height = (int) (vh / scaleFactor);
		Log.i(TAG, "vw = " + vw + "   vh"+ vh);
		Bitmap overlay = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		float dx = -view.getLeft() / scaleFactor;
		float dy = -view.getTop() / scaleFactor;
		canvas.translate(dx, dy);
		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
		Paint paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
		canvas.drawBitmap(bkg, 0, 0, paint);

		overlay = FastBlur.doBlurJniArray(overlay, (int) radius, true);
		view.setBackground(new BitmapDrawable(getResources(), overlay));
		long useTime = System.currentTimeMillis() - startMs;
		Log.e(TAG, "jniBlur time=" + useTime + "ms");
	}
}
