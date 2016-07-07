package com.example.nogif;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	MediaPlayer mediaPlayer1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉窗口标题
       requestWindowFeature(Window.FEATURE_NO_TITLE);
       //全屏显示
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //循环播放图片piano
        ImageView imageview = (ImageView)findViewById(R.id.animation);
        final AnimationDrawable anim = (AnimationDrawable)imageview.getBackground();
        anim.start();
        //获取声音
       AssetManager am = getAssets();
       try
       {
    	   AssetFileDescriptor afd = am.openFd("welcome01.ogg");
    	   mediaPlayer1 =new MediaPlayer();
    	   mediaPlayer1.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
    	   mediaPlayer1.prepare();
       }
       catch(IOException e)
       {
    	   e.printStackTrace();
       }
       mediaPlayer1.start();       
       //退出按钮       
       Button quit = (Button)findViewById(R.id.quit);
       quit.setOnClickListener(new OnClickListener ()
       {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
			 mediaPlayer1.stop();
		}
    	   
       });
       //监听声音结束后进入下一界面
       mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			if(mediaPlayer1 != null)
			{
				//System.out.println("MainActivity__onCompletion");
				mediaPlayer1.release();
				mediaPlayer1 = null;
	    	   Intent intent = new Intent(MainActivity.this,first.class);
	    	   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    	   finish(); 
	    	   startActivity(intent);
			}
	    }
	});       
    }
    //点击屏幕进入下一界面
    @Override
    public boolean onTouchEvent(MotionEvent e )
    {
    	switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			break;

		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		}
    	if(mediaPlayer1 != null)
    	{
			//System.out.println("MainActivity__onTouchEvent"); 
			mediaPlayer1.release();
			mediaPlayer1 = null;
		   //mediaPlayer1.release();
			Intent intent1 = new Intent(MainActivity.this,first.class);
			intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			finish();
    	}
		return true;
    }
}

