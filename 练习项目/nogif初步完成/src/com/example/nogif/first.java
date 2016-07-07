package com.example.nogif;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import android.R.integer;
import android.R.mipmap;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class first extends Activity {
	protected boolean mp = true;//退出时候是否接着播放声音的标志
	MediaPlayer mediaPlayer = null ;
	MediaPlayer mPlayer = null;//最开始声音的播放
	AssetFileDescriptor afd;
	MediaPlayer gamePlayer= new MediaPlayer();//关卡声音
	TranslateAnimation[] an = new TranslateAnimation[7];//七个音符向前移动
	boolean [] flag = {true,false,false,false,false,false,false};//七个音符按顺序点击标志	
	boolean end[]= {true,true,true,true,true,true,true};//音符点击结束标志
	int [] count = {0,0,0,0,0,0,0};//音符点击几次标志
	boolean second = false;
	@Override
	 public void onCreate(Bundle savedInstanceState) {		    
	        super.onCreate(savedInstanceState);	       
	        //去掉窗口标题
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        WindowManager wm = getWindowManager();
	        Display display = wm.getDefaultDisplay();
	        //全屏显示
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
	       //必须在下面
	        setContentView(R.layout.first);
	        
	      //龙舟
	        final ImageView vlzh = (ImageView)findViewById(R.id.lzh);
	        final ImageView vlzd = (ImageView)findViewById(R.id.lzd);
	        final ImageView vlzr = (ImageView)findViewById(R.id.lzr);
	        final ImageView vlzm = (ImageView)findViewById(R.id.lzm);
	        final ImageView vlzf = (ImageView)findViewById(R.id.lzf);
	        final ImageView vlzso = (ImageView)findViewById(R.id.lzso);
	        final ImageView vlzl = (ImageView)findViewById(R.id.lzl);
	        final ImageView vlzsi = (ImageView)findViewById(R.id.lzsi);
	   	 	//加载动画资源
		   	 final Animation lzh = AnimationUtils.loadAnimation(this,R.anim.longzhou);
		   	 //动画结束后保留状态
		   	 lzh.setFillAfter(true);		   	 
		   	 final Animation lzdis = AnimationUtils.loadAnimation(this,R.anim.lzdis);
		   	 //动画结束后保留状态
		   	 lzdis.setFillAfter(true);
		   	 lzdis.setInterpolator(new LinearInterpolator());
		   	 vlzh.startAnimation(lzh);
		   	 vlzd.startAnimation(lzh);
		   	 vlzr.startAnimation(lzh);
		   	 vlzm.startAnimation(lzh);
		   	 vlzf.startAnimation(lzh);
		   	 vlzso.startAnimation(lzh);
		   	 vlzl.startAnimation(lzh);
		   	 vlzsi.startAnimation(lzh);
	        //循环播放五线谱
	        ImageView imageview1 = (ImageView)findViewById(R.id.xianpu);	        
	        final AnimationDrawable anim = (AnimationDrawable)imageview1.getBackground();
	        anim.start();
	        //播放音效
			try
	        {
				AssetManager am = getAssets();
	     	  // AssetFileDescriptor
	     	   afd = am.openFd("raw/tip_howdo.ogg");
	     	   mPlayer =new MediaPlayer();
	     	   mPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
	     	   mPlayer.prepare();
	        }
	        catch(IOException e)
	        {
	     	   e.printStackTrace();
	        }
	        mPlayer.start();
        //退出弹出框  tip2      
	        final View tip2 = this.getLayoutInflater().inflate(R.layout.tip2, null);
	    	final PopupWindow exitpopup = new PopupWindow(tip2,display.getWidth(),
	    			display.getHeight()+50);
	    	ImageButton quit = (ImageButton)findViewById(R.id.quit);
	    	//final boolean mp = true;
	        quit.setOnClickListener(new OnClickListener ()
	        {
	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			//第一个参数为当前View的ID
	 			exitpopup.showAtLocation(findViewById(R.id.first), Gravity.CENTER, 20, 20);
	 			//System.exit(0);
	 			//android.os.Process.killProcess(android.os.Process.myPid());
	 			if(mp)
	 			mPlayer.pause();
	 			
	 		}
	 	});
	        tip2.findViewById(R.id.sure).setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
	 	           exitpopup.dismiss();
	 	           finish();		}
	        	
	        });
	        tip2.findViewById(R.id.cancel).setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub 
			        if(mp)
			        { mPlayer.start();
			        exitpopup.dismiss();}
			        
			        else
			        	{exitpopup.dismiss();}}
	        	
	        });
	        
    /*******关卡的出现与消失********/
    final Animation gamedis= AnimationUtils.loadAnimation(this, R.anim.gameone);
    gamedis.setFillAfter(true);
    final Animation gameshow= AnimationUtils.loadAnimation(this, R.anim.gameoneshow);
    gameshow.setFillAfter(true);
    final ImageView game= new ImageView(this);
    game.setImageResource(R.drawable.game_01);
    game.setX(350);
    game.setY(220);
	        
    //七个音符
	final RelativeLayout lll= (RelativeLayout)findViewById(R.id.first);
	ImageView mImageView = new ImageView(this);	
	lll.addView(mImageView);
	
	//双击时出现的手
	 final ImageView hand = new ImageView(this);
     hand.setImageResource(R.drawable.hand);
     //双击的图片
     final ImageView doupic = new ImageView(this);
     doupic.setImageResource(R.drawable.click);
     //七个音符的图片
	 final ImageView[] note = new ImageView[7]; 
	 for(int i = 0 ; i< 7;  i++)
	 {
		 note[i]= new ImageView(this);
		 lll.addView(note[i]);
	 }	
	 note[0].setImageResource(R.drawable.note1);			 
	 note[1].setImageResource(R.drawable.note2);			 
	 note[2].setImageResource(R.drawable.note3);			
	 note[3].setImageResource(R.drawable.note4);			 
	 note[4].setImageResource(R.drawable.note5);			 
	 note[5].setImageResource(R.drawable.note6);			
	 note[6].setImageResource(R.drawable.note7);
	 int [] arry = {0,1,2,3,4,5,6};
	 int[] a = new int[7];
	 int j; 	 
	 int m1 = 0;
	 int n =arry.length;
	 for(int i = 0; i<7;i++)
	 {
		 j = (int)(Math.random()*10%(n--));
		 a[m1] =arry[j];
		 m1++;
		 int[] b;
		 b = chongzu(arry,j);
	}
	 for(int i =0 ; i<7;i++)
	 {
		 note[a[i]].setX((i+1)*120);
		 note[a[i]].setY(500);
	 }	
	 //分数
	 final ImageView score1 = new ImageView(this);
	 final ImageView score2 = new ImageView(this);
	 score1.setImageResource(R.drawable.point0);
	 score2.setImageResource(R.drawable.point0);
	 lll.addView(score1);
	 lll.addView(score2);
	 score1.setX(120);
	 score1.setY(40);
	 score2.setX(150);
	 score2.setY(40);
//最后龙舟向前运动的声音	
	 final MediaPlayer[] m ={new MediaPlayer(),new MediaPlayer(),
				new MediaPlayer(),new MediaPlayer(),new MediaPlayer(),
				new MediaPlayer(),new MediaPlayer()} ;
		AssetManager a1 = getAssets();							
		try {
			AssetFileDescriptor[] y = {a1.openFd("high/high1.ogg"),
					a1.openFd("high/high2.ogg"),a1.openFd("high/high3.ogg"),
					a1.openFd("high/high4.ogg"),a1.openFd("high/high5.ogg"),
					a1.openFd("high/high6.ogg"),a1.openFd("high/high7.ogg")};
			for(int i = 0; i < 7; i++){								
			m[i].setDataSource(y[i].getFileDescriptor(), y[i].getStartOffset(), y[i].getLength());
			m[i].prepare();}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//分数周围出现的星星
	 final ImageView scorestar = (ImageView)findViewById(R.id.scorestar);
     final AnimationDrawable ss = (AnimationDrawable)scorestar.getBackground();
//游戏结束后周期出现的星星
     final ImageView fstar = new ImageView(this);
     fstar.setBackgroundResource(R.anim.finishstar);
     //fstar.setImageResource(R.anim.finishstar);
     final AnimationDrawable finishstarani = (AnimationDrawable)fstar.getBackground();
//第一、二关游戏结束后出现的图片
     final ImageView scor100 = new ImageView(this);
     scor100.setBackgroundResource(R.drawable.prizekuangbg);
     final ImageView jingling = new ImageView(this);
     final ImageView gift = new ImageView(this);
     final ImageView wordImageView = new ImageView(this);
     
/*********演示提示框*******/
//装载对应界面tip
View tip = this.getLayoutInflater().inflate(R.layout.tip, null);///注意！！
//创建popup对象
final PopupWindow popup = new PopupWindow(tip,480,280);
mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {    		
	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub

	if(mPlayer != null)
	{
		mPlayer.release();
		mPlayer = null;}
	mp  = false;
	//第一个参数为当前View的ID
	popup.showAtLocation(findViewById(R.id.first), Gravity.CENTER, 20, 20);
	}
});
//演示提示框上的按钮监听
tip.findViewById(R.id.begin).setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//bn.setBackgroundResource(R.id.lzl);
		AssetManager am = getAssets();
		try
		{
		   //AssetFileDescriptor
		   afd = am.openFd("raw/tip_ensure.ogg");
		   mediaPlayer =new MediaPlayer();
		   mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		   mediaPlayer.prepare();
		}
		catch(IOException e)
		{
		   e.printStackTrace();
		}
		mediaPlayer.start();
		popup.dismiss();
      //do
		lll.addView(hand);
		lll.addView(doupic);
		final TranslateAnimation handam = new TranslateAnimation(0,0,0,20);
		handam.setDuration(200);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				
					//双击图案加载与坐标
			        doupic.setX(note[0].getX()+90);
			        doupic.setY(420);
			        //手的坐标
					hand.setX(note[0].getX()+30);
					hand.setY(400);
					//第一次点击
					hand.startAnimation(handam);
					note[0].setImageResource(R.drawable.notelight_1);
					final Handler handler = new Handler()
					{
						@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
						    	super.handleMessage(msg);
						    	if(msg.what == 0x123){
						    		//第二次点击
						    		hand.startAnimation(handam);
						    		lll.removeView(hand);
						    		lll.removeView(doupic);
						    		final TranslateAnimation and = new TranslateAnimation(0,-note[0].getX()+130,0,-220);
	    							and.setDuration(1000);
	    							note[0].startAnimation(and);
	    							and.setFillAfter(true);
	    							final Handler han = new Handler(){
	    								public void handleMessage(Message msg) {
	    									if(msg.what==0x111)
	    									{
	    										vlzd.setBackgroundResource(R.drawable.shipl_do);
	    										note[0].setImageResource(R.drawable.note1);
	    										//re
	    										lll.addView(hand);
	    							      		lll.addView(doupic);
	    							      		//双击图案加载与坐标
    									        doupic.setX(note[1].getX()+90);
    									        doupic.setY(420);
    									        //手的坐标
    											hand.setX(note[1].getX()+30);
    											hand.setY(400);
    											//第一次点击
    											hand.startAnimation(handam);
	    											note[1].setImageResource(R.drawable.notelight_2);
	    											final Handler handler1 = new Handler()
	    											{
	    												@Override
	    												public void handleMessage(Message msg) {
	    													// TODO Auto-generated method stub
	    												    	super.handleMessage(msg);
	    												    	if(msg.what == 0x123){
	    												    		//第二次点击
	    												    		hand.startAnimation(handam);
	    												    		lll.removeView(hand);
	    												    		lll.removeView(doupic);
	    												    		//re
	    												    		final TranslateAnimation anr = new TranslateAnimation(0,-note[1].getX()+130*2,0,-220);
	    							    							anr.setDuration(1000);
	    							    							note[1].startAnimation(anr);
	    							    							anr.setFillAfter(true);
	    							    							final Handler han = new Handler(){
	    							    								public void handleMessage(Message msg) {
	    							    									if(msg.what==0x111)
	    							    									{
	    							    										vlzr.setBackgroundResource(R.drawable.shipl_re);
	    							    										note[1].setImageResource(R.drawable.note2);	    							    					
	    							    										//mi
	    							    										lll.addView(hand);
	    							    							      		lll.addView(doupic);
	    							    							      		//双击图案加载与坐标
    							    									        doupic.setX(note[2].getX()+90);
    							    									        doupic.setY(420);
    							    									        //手的坐标
    							    											hand.setX(note[2].getX()+30);
    							    											hand.setY(400);
    							    											//第一次点击
    							    											hand.startAnimation(handam);
    							    											note[2].setImageResource(R.drawable.notelight_3);
    							    											final Handler handler1 = new Handler()
	    							    											{
	    							    												@Override
	    							    												public void handleMessage(Message msg) {
	    							    													// TODO Auto-generated method stub
	    							    												    	super.handleMessage(msg);
	    							    												    	if(msg.what == 0x123){
	    							    												    		//第二次点击
	    							    												    		hand.startAnimation(handam);	    							    					
	    							    												    		lll.removeView(hand);
	    							    												    		lll.removeView(doupic);
	    							    												    		final TranslateAnimation anm = new TranslateAnimation(0,-note[2].getX()+130*3,0,-220);
	    							    							    							anm.setDuration(1000);
	    							    							    							note[2].startAnimation(anm);
	    							    							    							anm.setFillAfter(true);
	    							    							    							final Handler han = new Handler(){
	    							    							    								public void handleMessage(Message msg) {
	    							    							    									if(msg.what==0x111)
	    							    							    									{
	    							    							    										vlzm.setBackgroundResource(R.drawable.shipl_mi);
	    							    							    										note[2].setImageResource(R.drawable.note3);
	    							    							    										//fa
	    							    							    										lll.addView(hand);
	    							    							    							      		lll.addView(doupic);
	    							    							    							      		//双击图案加载与坐标
	    							    							    									        doupic.setX(note[3].getX()+90);
	    							    							    									        doupic.setY(420);
	    							    							    									        //手的坐标
	    							    							    											hand.setX(note[3].getX()+30);
	    							    							    											hand.setY(400);
	    							    							    											//第一次点击
	    							    							    											hand.startAnimation(handam);
	    							    							    											note[3].setImageResource(R.drawable.notelight_4);
	    							    							    											final Handler handler1 = new Handler()
	    							    							    											{
	    							    							    												@Override
	    							    							    												public void handleMessage(Message msg) {
	    							    							    													// TODO Auto-generated method stub
	    							    							    												    	super.handleMessage(msg);
	    							    							    												    	if(msg.what == 0x123){
	    							    							    												    		//第二次点击
	    							    							    												    		hand.startAnimation(handam);
	    							    							    												    		lll.removeView(hand);
	    							    							    												    		lll.removeView(doupic);
	    							    							    												    		final TranslateAnimation anf = new TranslateAnimation(0,-note[3].getX()+130*4,0,-220);
	    							    							    							    							anf.setDuration(1000);
	    							    							    							    							note[3].startAnimation(anf);
	    							    							    							    							anf.setFillAfter(true);
	    							    							    							    							final Handler han = new Handler(){
	    							    							    							    								public void handleMessage(Message msg) {
	    							    							    							    									if(msg.what==0x111)
	    							    							    							    									{
	    							    							    							    										vlzf.setBackgroundResource(R.drawable.shipl_fa);
	    							    							    							    										note[3].setImageResource(R.drawable.note4);
	    							    							    							    										//sol
	    							    							    							    										lll.addView(hand);
	    							    							    							    							      		lll.addView(doupic);
	    							    							    							    							      		//双击图案加载与坐标
	    							    							    							    									        doupic.setX(note[4].getX()+90);
	    							    							    							    									        doupic.setY(420);
	    							    							    							    									        //手的坐标
	    							    							    							    											hand.setX(note[4].getX()+30);
	    							    							    							    											hand.setY(400);
	    							    							    							    											//第一次点击
	    							    							    							    											hand.startAnimation(handam);
	    							    							    							    											note[4].setImageResource(R.drawable.notelight_5);
	    							    							    							    											final Handler handler1 = new Handler()
	    							    							    							    											{
	    							    							    							    												@Override
	    							    							    							    												public void handleMessage(Message msg) {
	    							    							    							    													// TODO Auto-generated method stub
	    							    							    							    												    	super.handleMessage(msg);
	    							    							    							    												    	if(msg.what == 0x123){
	    							    							    							    												    		//第二次点击
	    							    							    							    												    		hand.startAnimation(handam);
	    							    							    							    												    		lll.removeView(hand);
	    							    							    							    												    		lll.removeView(doupic);
	    							    							    							    												    		final TranslateAnimation ans = new TranslateAnimation(0,-note[4].getX()+130*5,0,-220);
	    							    							    							    							    							ans.setDuration(1000);
	    							    							    							    							    							note[4].startAnimation(ans);
	    							    							    							    							    							ans.setFillAfter(true);
	    							    							    							    							    							final Handler han = new Handler(){
	    							    							    							    							    								public void handleMessage(Message msg) {
	    							    							    							    							    									if(msg.what==0x111)
	    							    							    							    							    									{
	    							    							    							    							    										vlzso.setBackgroundResource(R.drawable.shipl_sol);
	    							    							    							    							    										note[4].setImageResource(R.drawable.note5);
	    							    							    							    							    										//la
	    							    							    							    							    										lll.addView(hand);
	    							    							    							    							    							      		lll.addView(doupic);
	    							    							    							    							    							      		//双击图案加载与坐标
	    							    							    							    							    									        doupic.setX(note[5].getX()+90);
	    							    							    							    							    									        doupic.setY(420);
	    							    							    							    							    									        //手的坐标
	    							    							    							    							    											hand.setX(note[5].getX()+30);
	    							    							    							    							    											hand.setY(400);
	    							    							    							    							    											//第一次点击
	    							    							    							    							    											hand.startAnimation(handam);
	    							    							    							    							    											note[5].setImageResource(R.drawable.notelight_6);
	    							    							    							    							    											final Handler handler1 = new Handler()
	    							    							    							    							    											{
	    							    							    							    							    												@Override
	    							    							    							    							    												public void handleMessage(Message msg) {
	    							    							    							    							    													// TODO Auto-generated method stub
	    							    							    							    							    												    	super.handleMessage(msg);
	    							    							    							    							    												    	if(msg.what == 0x123){
	    							    							    							    							    												    		//第二次点击
	    							    							    							    							    												    		hand.startAnimation(handam);
	    							    							    							    							    												    		lll.removeView(hand);
	    							    							    							    							    												    		lll.removeView(doupic);
	    							    							    							    							    												    		final TranslateAnimation anl = new TranslateAnimation(0,-note[5].getX()+130*6,0,-220);
	    							    							    							    							    							    							anl.setDuration(1000);
	    							    							    							    							    							    							note[5].startAnimation(anl);
	    							    							    							    							    							    							anl.setFillAfter(true);
	    							    							    							    							    							    							final Handler han = new Handler(){
	    							    							    							    							    							    								public void handleMessage(Message msg) {
	    							    							    							    							    							    									if(msg.what==0x111)
	    							    							    							    							    							    									{
	    							    							    							    							    							    										vlzl.setBackgroundResource(R.drawable.shipl_la);
	    							    							    							    							    							    										note[5].setImageResource(R.drawable.note6);
/************************************************************************************************************************************************************si***************************************************si***********/
	    							    							    							    							    							    										lll.addView(hand);
	    							    							    							    							    							    							      		lll.addView(doupic);
	    							    							    							    							    							    							      		//双击图案加载与坐标
	    							    							    							    							    							    									        doupic.setX(note[6].getX()+90);
	    							    							    							    							    							    									        doupic.setY(420);
	    							    							    							    							    							    									        //手的坐标
	    							    							    							    							    							    											hand.setX(note[6].getX()+30);
	    							    							    							    							    							    											hand.setY(400);
	    							    							    							    							    							    											//第一次点击
	    							    							    							    							    							    											hand.startAnimation(handam);
	    							    							    							    							    							    											note[6].setImageResource(R.drawable.notelight_7);
	    							    							    							    							    							    											final Handler handler1 = new Handler()
	    							    							    							    							    							    											{
	    							    							    							    							    							    												@Override
	    							    							    							    							    							    												public void handleMessage(Message msg) {
	    							    							    							    							    							    													// TODO Auto-generated method stub
	    							    							    							    							    							    												    	super.handleMessage(msg);
	    							    							    							    							    							    												    	if(msg.what == 0x123){
	    							    							    							    							    							    												    		//第二次点击
	    							    							    							    							    							    												    		hand.startAnimation(handam);
	    							    							    							    							    							    												    		lll.removeView(hand);
	    							    							    							    							    							    												    		lll.removeView(doupic);
	    							    							    							    							    							    												    		final TranslateAnimation ani = new TranslateAnimation(0,-note[6].getX()+130*7,0,-220);
	    							    							    							    							    							    							    							ani.setDuration(1000);
	    							    							    							    							    							    							    							note[6].startAnimation(ani);
	    							    							    							    							    							    							    							ani.setFillAfter(true);
	    							    							    							    							    							    							    							final Handler han = new Handler(){
	    							    							    							    							    							    							    								public void handleMessage(Message msg) {
	    							    							    							    							    							    							    									if(msg.what==0x111)
	    							    							    							    							    							    							    									{
	    							    							    							    							    							    							    										vlzsi.setBackgroundResource(R.drawable.shipl_si);
	    							    							    							    							    							    							    										note[6].setImageResource(R.drawable.note7);
	    							    							    							    							    							    							    										and.setFillAfter(false);
	    							    							    							    							    							    							    										anr.setFillAfter(false);
	    							    							    							    							    							    							    										anm.setFillAfter(false);
	    							    							    							    							    							    							    										anf.setFillAfter(false);
	    							    							    							    							    							    							    										ans.setFillAfter(false);
	    							    							    							    							    							    							    										anl.setFillAfter(false);
	    							    							    							    							    							    							    										ani.setFillAfter(false);
	    							    							    							    							    							    							    										vlzd.setBackgroundResource(R.drawable.shipg_do);
	    							    							    							    							    							    							    										vlzr.setBackgroundResource(R.drawable.shipg_re);
	    							    							    							    							    							    							    										vlzm.setBackgroundResource(R.drawable.shipg_mi);
	    							    							    							    							    							    							    										vlzf.setBackgroundResource(R.drawable.shipg_fa);
	    							    							    							    							    							    							    										vlzso.setBackgroundResource(R.drawable.shipg_sol);
	    							    							    							    							    							    							    										vlzl.setBackgroundResource(R.drawable.shipg_la);
	    							    							    							    							    							    							    										vlzsi.setBackgroundResource(R.drawable.shipg_si);	    							    							    							    							    							    							    										
	    							    							    							    							    							    							    										/***重新排位置***/
	    							    							    							    							    							    							    										int [] arry = {0,1,2,3,4,5,6};
	    							    							    							    							    							    							    										 int[] a = new int[7];
	    							    							    							    							    							    							    										 int j; 	 
	    							    							    							    							    							    							    										 int m = 0;
	    							    							    							    							    							    							    										 int n =arry.length;
	    							    							    							    							    							    							    										 for(int i = 0; i<7;i++)
	    							    							    							    							    							    							    										 {
	    							    							    							    							    							    							    											 j = (int)(Math.random()*10%(n--));
	    							    							    							    							    							    							    											 a[m] =arry[j];
	    							    							    							    							    							    							    											 m++;
	    							    							    							    							    							    							    											 int[] b;
	    							    							    							    							    							    							    											 b = chongzu(arry,j);
	    							    							    							    							    							    							    										}
	    							    							    							    							    							    							    										 for(int i =0 ; i<7;i++)
	    							    							    							    							    							    							    										 {
	    							    							    							    							    							    							    											 note[a[i]].setX((i+1)*120);
	    							    							    							    							    							    							    											 note[a[i]].setY(450);
	    							    							    							    							    							    							    									     }
	    							    							    							    							    							    							    										   //弹出第一关
	    							    							    							    							    							    							    										 AssetManager gameAssetManager = getAssets();
							    							    							    							    							    							    										    	    try {
							    							    							    							    							    							    										    			afd = gameAssetManager.openFd("raw/tip_start.ogg");
							    							    							    							    							    							    										    			gamePlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
							    							    							    							    							    							    										    			gamePlayer.prepare();
							    							    							    							    							    							    										    	    } catch (IOException e) {
							    							    							    							    							    							    										    			// TODO Auto-generated catch block
							    							    							    							    							    							    										    			e.printStackTrace();
							    							    							    							    							    							    										    		}    
							    							    							    							    							    							    										    	    gamePlayer.start();
							    							    							    							    							    							    										    	    lll.addView(game);
							    							    							    							    							    							    										    	    game.startAnimation(gameshow);
							    							    							    							    							    							    										    	    final Handler gameHandler = new Handler(){
							    							    							    							    							    							    										    	    	@Override
							    							    							    							    							    							    										    	    	public void handleMessage(Message msg) {
							    							    							    							    							    							    										    	    		// TODO Auto-generated method stub
							    							    							    							    							    							    										    	    		super.handleMessage(msg);
							    							    							    							    							    							    										    	    		if(msg.what == 0x333)
							    							    							    							    							    							    										    	    			game.startAnimation(gamedis);
							    							    							    							    							    							    										    	    	}
							    							    							    							    							    							    										    	    };
							    							    							    							    							    							    										    	    new Timer().schedule(new TimerTask() {
							    							    							    							    							    							    										    			
							    							    							    							    							    							    										    			@Override
							    							    							    							    							    							    										    			public void run() {
							    							    							    							    							    							    										    				// TODO Auto-generated method stub
							    							    							    							    							    							    										    				gameHandler.sendEmptyMessage(0x333);
							    							    							    							    							    							    										    			}
							    							    							    							    							    							    										    		}, 1600); 
	    							    							    							    							    							    							    									}//if
	    							    							    							    							    							    							    								};
	    							    							    							    							    							    							    							};//han
	    							    							    							    							    							    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    							    							    							    							    							    								@Override
	    							    							    							    							    							    							    								public void run() {
	    							    							    							    							    							    							    									// TODO Auto-generated method stub
	    							    							    							    							    							    							    									han.sendEmptyMessage(0x111);
	    							    							    							    							    							    							    								}
	    							    							    							    							    							    							    							}, 1000);//timer
	    							    							    							    							    							    												    	}//if
	    							    							    							    							    							    												    } 						
	    							    							    							    							    							    								              };
	    							    							    							    							    							    							              new Timer().schedule(new TimerTask(){
	    							    							    							    							    							    											  @Override
	    							    							    							    							    							    											  public void run() {
	    							    							    							    							    							    												  // TODO Auto-generated method stub
	    							    							    							    							    							    												  handler1.sendEmptyMessage(0x123);
	    							    							    							    							    							    												  }}, 200);	
	    							    							    							    							    							    									}//if
	    							    							    							    							    							    								};
	    							    							    							    							    							    							};//han
	    							    							    							    							    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    							    							    							    							    								@Override
	    							    							    							    							    							    								public void run() {
	    							    							    							    							    							    									// TODO Auto-generated method stub
	    							    							    							    							    							    									han.sendEmptyMessage(0x111);
	    							    							    							    							    							    								}
	    							    							    							    							    							    							}, 1000);//timer
	    							    							    							    							    												    	}//if
	    							    							    							    							    												    } 						
	    							    							    							    							    								              };
	    							    							    							    							    							              new Timer().schedule(new TimerTask(){
	    							    							    							    							    											  @Override
	    							    							    							    							    											  public void run() {
	    							    							    							    							    												  // TODO Auto-generated method stub
	    							    							    							    							    												  handler1.sendEmptyMessage(0x123);
	    							    							    							    							    												  }}, 200);	
	    							    							    							    							    									}//if
	    							    							    							    							    								};
	    							    							    							    							    							};//han
	    							    							    							    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    							    							    							    								@Override
	    							    							    							    							    								public void run() {
	    							    							    							    							    									// TODO Auto-generated method stub
	    							    							    							    							    									han.sendEmptyMessage(0x111);
	    							    							    							    							    								}
	    							    							    							    							    							}, 1000);//timer
	    							    							    							    												    	}//if
	    							    							    							    												    } 						
	    							    							    							    								              };
	    							    							    							    							              new Timer().schedule(new TimerTask(){
	    							    							    							    											  @Override
	    							    							    							    											  public void run() {
	    							    							    							    												  // TODO Auto-generated method stub
	    							    							    							    												  handler1.sendEmptyMessage(0x123);
	    							    							    							    												  }}, 200);	
	    							    							    							    										
	    							    							    							    									}//if
	    							    							    							    								};
	    							    							    							    							};//han
	    							    							    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    							    							    								@Override
	    							    							    							    								public void run() {
	    							    							    							    									// TODO Auto-generated method stub
	    							    							    							    									han.sendEmptyMessage(0x111);
	    							    							    							    								}
	    							    							    							    							}, 1000);//timer
	    							    							    												    	}//if
	    							    							    												    } 						
	    							    							    								              };
	    							    							    							              new Timer().schedule(new TimerTask(){
	    							    							    											  @Override
	    							    							    											  public void run() {
	    							    							    												  // TODO Auto-generated method stub
	    							    							    												  handler1.sendEmptyMessage(0x123);
	    							    							    												  }}, 200);	
	    							    							    									}//if
	    							    							    								};
	    							    							    							};//han
	    							    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    							    								@Override
	    							    							    								public void run() {
	    							    							    									// TODO Auto-generated method stub
	    							    							    									han.sendEmptyMessage(0x111);
	    							    							    								}
	    							    							    							}, 1000);//timer
	    							    												    	}//if
	    							    												    } 						
	    							    								              };
	    							    							              new Timer().schedule(new TimerTask(){
	    							    											  @Override
	    							    											  public void run() {
	    							    												  // TODO Auto-generated method stub
	    							    												  handler1.sendEmptyMessage(0x123);
	    							    												  }}, 200);	
	    							    									}//if
	    							    								};
	    							    							};//han
	    							    							 new Timer().schedule(new TimerTask() {	    								
	    							    								@Override
	    							    								public void run() {
	    							    									// TODO Auto-generated method stub
	    							    									han.sendEmptyMessage(0x111);
	    							    								}
	    							    							}, 1000);//timer
	    												    	}//if
	    												    } 						
	    								              };
	    							              new Timer().schedule(new TimerTask(){
	    											  @Override
	    											  public void run() {
	    												  // TODO Auto-generated method stub
	    												  handler1.sendEmptyMessage(0x123);
	    												  }}, 200);	
	    									}//if
	    								};
	    							};//han
	    							 new Timer().schedule(new TimerTask() {	    								
	    								@Override
	    								public void run() {
	    									// TODO Auto-generated method stub
	    									han.sendEmptyMessage(0x111);
	    								}
	    							}, 1000);//timer
						    	}//if
						    } 						
		              };
	              new Timer().schedule(new TimerTask(){
					  @Override
					  public void run() {
						  // TODO Auto-generated method stub
						  handler.sendEmptyMessage(0x123);
						  }}, 200);
////////////////////////////////////////////////////			
			}//listener中函数
		});//listener
	}//按钮监听函数	
});//按钮监听
tip.findViewById(R.id.skip).setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	AssetManager am = getAssets();
	try
	{
		//AssetFileDescriptor 
		afd = am.openFd("raw/tip_cancel.ogg");
 	   mediaPlayer =new MediaPlayer();
 	   mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
 	   mediaPlayer.prepare();
 	 }
    catch(IOException e)
    {
 	   e.printStackTrace();
    }
    mediaPlayer.start();
    popup.dismiss();	
    //弹出第一关
    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
    	@Override
    public void onCompletion(MediaPlayer arg0) {
    	// TODO Auto-generated method stub    		
    	    AssetManager gameAssetManager = getAssets();
    	    try {
    			afd = gameAssetManager.openFd("raw/tip_start.ogg");
    			gamePlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
    			gamePlayer.prepare();
    	    } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}    
    	    gamePlayer.start();
    	    lll.addView(game);
    	    game.startAnimation(gameshow);
    	    final Handler gameHandler = new Handler(){
    	    	@Override
    	    	public void handleMessage(Message msg) {
    	    		// TODO Auto-generated method stub
    	    		super.handleMessage(msg);
    	    		if(msg.what == 0x333)
    	    			game.startAnimation(gamedis);
    	    	}
    	    };
    	    new Timer().schedule(new TimerTask() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    				gameHandler.sendEmptyMessage(0x333);
    			}
    		}, 1600); 
    }});    
    int [] arry = {0,1,2,3,4,5,6};
	 int[] a = new int[7];
	 int j; 	 
	 int m = 0;
	 int n =arry.length;
	 for(int i = 0; i<7;i++)
	 {
		 j = (int)(Math.random()*10%(n--));
		 a[m] =arry[j];
		 m++;
		 int[] b;
		 b = chongzu(arry,j);
	}
	 for(int i =0 ; i<7;i++)
	 {
		 note[a[i]].setX((i+1)*120);
		 note[a[i]].setY(500);
	 }	
   }
 }); 

/*****开始游戏,音符点击*****/
gamePlayer.setOnCompletionListener(new OnCompletionListener() {
	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub		
	note[0].setOnClickListener(new OnClickListener()
	{
		private MediaPlayer mediaPlayer1;

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub			
			count[0] ++;
			if(count[0]%2 == 1 && end[0])
			{
				if(second)
					note[0].setImageResource(R.drawable.noteslight_1);
				else
					note[0].setImageResource(R.drawable.notelight_1);
			AssetManager am = getAssets();
			try
	        {
	     	   //AssetFileDescriptor 
	     	   afd = am.openFd("high/high1.ogg");
	     	   mediaPlayer1 =new MediaPlayer();
	     	   mediaPlayer1.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
	     	   mediaPlayer1.prepare();
	     	   mediaPlayer1.start();
	        }
	        catch(IOException e)
	        {
	     	   e.printStackTrace();
	        }
			mediaPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
			{
				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					if(mediaPlayer1 != null){
						mediaPlayer1.release();
						mediaPlayer1 = null;
					}
				}});
			if(second){
	   	 note[1].setImageResource(R.drawable.notes2);
	   	 note[2].setImageResource(R.drawable.notes3);
	   	 note[3].setImageResource(R.drawable.notes4);
	   	 note[4].setImageResource(R.drawable.notes5);
	   	 note[5].setImageResource(R.drawable.notes6);
	   	 note[6].setImageResource(R.drawable.notes7);
	      count[1]=0;
	      count[2]=0;
	      count[3]=0;
	      count[4]=0;
	      count[5]=0;
	      count[6]=0;}
			else
			{
				note[1].setImageResource(R.drawable.note2);
			   	 note[2].setImageResource(R.drawable.note3);
			   	 note[3].setImageResource(R.drawable.note4);
			   	 note[4].setImageResource(R.drawable.note5);
			   	 note[5].setImageResource(R.drawable.note6);
			   	 note[6].setImageResource(R.drawable.note7);
			      count[1]=0;
			      count[2]=0;
			      count[3]=0;
			      count[4]=0;
			      count[5]=0;
			      count[6]=0;
			}
	      }				
	if(count[0]%2==0 && flag[0] && end[0])
	{
		if(second)
			note[0].setImageResource(R.drawable.notes1);
		else
		note[0].setImageResource(R.drawable.note1);
		
		TranslateAnimation ani = new TranslateAnimation(0,-note[0].getX()+130,0,-220);
		ani.setDuration(1000);
		note[0].startAnimation(ani);
		ani.setFillAfter(true);
		final Handler han = new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what==0x111)
				{
					vlzd.setBackgroundResource(R.drawable.shipl_do);
				}
				};
			};
	 new Timer().schedule(new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			han.sendEmptyMessage(0x111);
		}
	}, 1000);
	rightv();
	score1.setImageResource(R.drawable.point1);
	flag[1]=true;
	end[0] = false;}//if
   }			
});
	note[1].setOnClickListener(new OnClickListener()
	{
		private MediaPlayer mediaPlayer2;

		@Override			
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			count[1]++;
		if(count[1]%2 == 1&& end[1])
			{
			if(second)
			note[1].setImageResource(R.drawable.noteslight_2);
			else
				note[1].setImageResource(R.drawable.notelight_2);
			AssetManager am = getAssets();
			try
	        {
	     	   //AssetFileDescriptor 
	     	   afd = am.openFd("high/high2.ogg");
	     	   mediaPlayer2 =new MediaPlayer();
	     	   mediaPlayer2.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
	     	   mediaPlayer2.prepare();
	     	  mediaPlayer2.start();
	        }
	        catch(IOException e)
	        {
	     	   e.printStackTrace();
	        }        
	        mediaPlayer2.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					if(mediaPlayer2 != null)
					{
						mediaPlayer2.release();
						mediaPlayer2 = null;
					}
				}
			});
	        if(second){
	        note[0].setImageResource(R.drawable.notes1);
		    note[2].setImageResource(R.drawable.notes3);
		   	note[3].setImageResource(R.drawable.notes4);
		   	note[4].setImageResource(R.drawable.notes5);
		   	note[5].setImageResource(R.drawable.notes6);
		    note[6].setImageResource(R.drawable.notes7);
		    count[0]=0;
		    count[2]=0;
		    count[3]=0;
		    count[4]=0;
		    count[5]=0;
		    count[6]=0;}
	        else
	        {note[0].setImageResource(R.drawable.note1);
		    note[2].setImageResource(R.drawable.note3);
		   	note[3].setImageResource(R.drawable.note4);
		   	note[4].setImageResource(R.drawable.note5);
		   	note[5].setImageResource(R.drawable.note6);
		    note[6].setImageResource(R.drawable.note7);
		    count[0]=0;
		    count[2]=0;
		    count[3]=0;
		    count[4]=0;
		    count[5]=0;
		    count[6]=0;}
	        }
			if(count[1]%2==0 && flag[1] && end[1])
	        {
				if(second)
					note[1].setImageResource(R.drawable.notes2);
				else
				note[1].setImageResource(R.drawable.note2);
				
				TranslateAnimation ani = new TranslateAnimation(0,-note[1].getX()+130*2,0,-220);
				ani.setDuration(1000);
				note[1].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzr.setBackgroundResource(R.drawable.shipl_re);
						}
						
					};
				};
			 new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					han.sendEmptyMessage(0x111);
				}
			}, 1000);
			 rightv();
			 score1.setImageResource(R.drawable.point2);
			 flag[2]=true;
			 end[1]=false;
			}//if
			if(count[1]%2==0 && flag[1] == false)
			{
				if(second)
					note[1].setImageResource(R.drawable.notes2);
				else
				note[1].setImageResource(R.drawable.note2);
				wrong();}
		}
	});
	note[2].setOnClickListener(new OnClickListener()
		{
			private MediaPlayer mediaPlayer3;
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count[2]++;
				if(count[2]%2 == 1&& end[2]){
					if(second)
				note[2].setImageResource(R.drawable.noteslight_3);
					else
				note[2].setImageResource(R.drawable.notelight_3);
				AssetManager am = getAssets();
				try
		        {
		     	   //AssetFileDescriptor 
		     	   afd = am.openFd("high/high3.ogg");
		     	   mediaPlayer3 =new MediaPlayer();
		     	   mediaPlayer3.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		     	   mediaPlayer3.prepare();
		     	   mediaPlayer3.start();
		        }
		        catch(IOException e)
		        {
		     	   e.printStackTrace();
		        }
		        
		        mediaPlayer3.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						if(mediaPlayer3 != null)
						{
							mediaPlayer3.release();
							mediaPlayer3 = null;
						}
					}
				});	
		        if(second){
		        note[0].setImageResource(R.drawable.notes1);
			   	note[1].setImageResource(R.drawable.notes2);			 
			   	note[3].setImageResource(R.drawable.notes4);
			   	note[4].setImageResource(R.drawable.notes5);
			   	note[5].setImageResource(R.drawable.notes6);
			   	note[6].setImageResource(R.drawable.notes7);
		      count[0]=0;
		      count[1]=0;		   
		      count[3]=0;
		      count[4]=0;
		      count[5]=0;
		      count[6]=0;}
		        else
		        {
		        	note[0].setImageResource(R.drawable.note1);
				   	note[1].setImageResource(R.drawable.note2);			 
				   	note[3].setImageResource(R.drawable.note4);
				   	note[4].setImageResource(R.drawable.note5);
				   	note[5].setImageResource(R.drawable.note6);
				   	note[6].setImageResource(R.drawable.note7);
			      count[0]=0;
			      count[1]=0;		   
			      count[3]=0;
			      count[4]=0;
			      count[5]=0;
			      count[6]=0;
		        }
		     }
				if(count[2]%2==0 && flag[2] && end[2]){
					if(second)
				note[2].setImageResource(R.drawable.notes3);
					else
						note[2].setImageResource(R.drawable.note3);
		        TranslateAnimation ani = new TranslateAnimation(0,-note[2].getX()+390,0,-220);
				ani.setDuration(1000);
				note[2].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzm.setBackgroundResource(R.drawable.shipl_mi);
						}
						
					};
				};
				 new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						han.sendEmptyMessage(0x111);
					}
				}, 1000);
				 rightv();
				 score1.setImageResource(R.drawable.point3);
				 flag[3]=true;
				 end[2]=false;
				 }//if
				if(count[2]%2==0 && flag[2] == false)
				{
					if(second)
					note[2].setImageResource(R.drawable.notes3);
					else
						note[2].setImageResource(R.drawable.note3);
					wrong();}
		        }
			
		});
	note[3].setOnClickListener(new OnClickListener()
		{
			private MediaPlayer mediaPlayer4;
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count[3]++;
				if(count[3]%2 == 1 && end[3])
				{
					if(second)
					note[3].setImageResource(R.drawable.noteslight_4);
					else
						note[3].setImageResource(R.drawable.notelight_4);
				AssetManager am = getAssets();
				try
		        {
		     	   //AssetFileDescriptor 
		     	   afd = am.openFd("high/high4.ogg");
		     	   mediaPlayer4 =new MediaPlayer();
		     	   mediaPlayer4.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		     	   mediaPlayer4.prepare();
		     	   mediaPlayer4.start();
		        }
		        catch(IOException e)
		        {
		     	   e.printStackTrace();
		        }		        
		        mediaPlayer4.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						if(mediaPlayer4 != null)
						{
							mediaPlayer4.release();
							mediaPlayer4 = null;
						}
					}
				});
		        if(second){
		        note[0].setImageResource(R.drawable.notes1);
			   	 note[1].setImageResource(R.drawable.notes2);
			   	 note[2].setImageResource(R.drawable.notes3);			  
			   	 note[4].setImageResource(R.drawable.notes5);
			   	 note[5].setImageResource(R.drawable.notes6);
			   	 note[6].setImageResource(R.drawable.notes7);
			      count[0]=0;
			      count[1]=0;
			      count[2]=0;			    
			      count[4]=0;
			      count[5]=0;
			      count[6]=0;}
		        else
		        {
		        	note[0].setImageResource(R.drawable.note1);
				   	 note[1].setImageResource(R.drawable.note2);
				   	 note[2].setImageResource(R.drawable.note3);			  
				   	 note[4].setImageResource(R.drawable.note5);
				   	 note[5].setImageResource(R.drawable.note6);
				   	 note[6].setImageResource(R.drawable.note7);
				      count[0]=0;
				      count[1]=0;
				      count[2]=0;			    
				      count[4]=0;
				      count[5]=0;
				      count[6]=0;
		        }
		      }
				if(count[3]%2 ==0 && flag[3] && end[3]){
					if(second)
						{lll.addView(scorestar);
					note[3].setImageResource(R.drawable.notes4);}
					else
						note[3].setImageResource(R.drawable.note4);
		        TranslateAnimation ani = new TranslateAnimation(0,-note[3].getX()+130*4,0,-220);
				ani.setDuration(1000);
				note[3].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzf.setBackgroundResource(R.drawable.shipl_fa);
						}
						
					};
				};
				 new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						han.sendEmptyMessage(0x111);
					}
				}, 1000);
				 rightv();
				 score1.setImageResource(R.drawable.point4);
				 ss.start();//闪星星
				 flag[4]=true;
				 end[3]= false;}
				if(count[3]%2==0 && flag[3] == false)
				{
					if(second)
					note[3].setImageResource(R.drawable.notes4);
					else
						note[3].setImageResource(R.drawable.note4);
					wrong();}
		        }
			
		});
	note[4].setOnClickListener(new OnClickListener()
		{
			
			private MediaPlayer mediaPlayer5;	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count[4]++;
				if(count[4]%2 ==1 && end[4]){
					if(second)
				note[4].setImageResource(R.drawable.noteslight_5);
					else
						note[4].setImageResource(R.drawable.notelight_5);
				AssetManager am = getAssets();
				try
		        {
		     	   //AssetFileDescriptor 
		     	   afd = am.openFd("high/high5.ogg");
		     	   mediaPlayer5 =new MediaPlayer();
		     	   mediaPlayer5.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		     	   mediaPlayer5.prepare();
		        }
		        catch(IOException e)
		        {
		     	   e.printStackTrace();
		        }
		        mediaPlayer5.start();
		        mediaPlayer5.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						if(mediaPlayer5 != null)
						{
							mediaPlayer5.release();
							mediaPlayer5 = null;
						}
					}
				});
		        if(second){
		        note[0].setImageResource(R.drawable.notes1);
			   	note[1].setImageResource(R.drawable.notes2);
			    note[2].setImageResource(R.drawable.notes3);
			   	note[3].setImageResource(R.drawable.notes4);
			  //  note[4].setImageResource(R.drawable.note5);
			    note[5].setImageResource(R.drawable.notes6);
			    note[6].setImageResource(R.drawable.notes7);
			     count[0]=0;
			     count[1]=0;
			     count[2]=0;
			     count[3]=0;
			     count[5]=0;
			     count[6]=0;}
		        else
		        {note[0].setImageResource(R.drawable.note1);
			   	note[1].setImageResource(R.drawable.note2);
			    note[2].setImageResource(R.drawable.note3);
			   	note[3].setImageResource(R.drawable.note4);
			  //  note[4].setImageResource(R.drawable.note5);
			    note[5].setImageResource(R.drawable.note6);
			    note[6].setImageResource(R.drawable.note7);
			     count[0]=0;
			     count[1]=0;
			     count[2]=0;
			     count[3]=0;
			    // count[4]=0;
			     count[5]=0;
			     count[6]=0;}
			     }
				if(count[4]%2 == 0 && flag[4]&& end[4])
				{
					if(second)
				 note[4].setImageResource(R.drawable.notes5);
					else
						note[4].setImageResource(R.drawable.note5);
		        TranslateAnimation ani = new TranslateAnimation(0,-note[4].getX()+130*5,0,-220);
				ani.setDuration(1000);
				note[4].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzso.setBackgroundResource(R.drawable.shipl_sol);
						}
						
					};
				};
				 new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						han.sendEmptyMessage(0x111);
					}
				}, 1000);
				 rightv();
				 score1.setImageResource(R.drawable.point5);
				 ss.stop();
				 lll.removeView(scorestar);
				 flag[5]=true;
				 end[4]= false;
				}
				if(count[4]%2==0 && flag[4] == false)
				{
					if(second)
					note[4].setImageResource(R.drawable.notes5);
					else
						note[4].setImageResource(R.drawable.note5);
					wrong();}
		        }
			
		});
	note[5].setOnClickListener(new OnClickListener()
		{
			private MediaPlayer mediaPlayer6;
	
			//int count = 0;
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count[5]++;
				if(count[5]%2 == 1 && end[5])
				{
					if(second)
					note[5].setImageResource(R.drawable.noteslight_6);
					else
						note[5].setImageResource(R.drawable.notelight_6);
				AssetManager am = getAssets();
				try
		        {
		     	   //AssetFileDescriptor 
		     	   afd = am.openFd("high/high6.ogg");
		     	   mediaPlayer6 =new MediaPlayer();
		     	   mediaPlayer6.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		     	   mediaPlayer6.prepare();
		        }
		        catch(IOException e)
		        {
		     	   e.printStackTrace();
		        }
		        mediaPlayer6.start();
		        mediaPlayer6.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						if(mediaPlayer6 != null)
						{
							mediaPlayer6.release();
							mediaPlayer6 = null;
						}
					}
				});
		        if(second){
		        note[0].setImageResource(R.drawable.notes1);
			   	note[1].setImageResource(R.drawable.notes2);
			    note[2].setImageResource(R.drawable.notes3);
			   	note[3].setImageResource(R.drawable.notes4);
			    note[4].setImageResource(R.drawable.notes5);			   
			    note[6].setImageResource(R.drawable.notes7);
			     count[0]=0;
			     count[1]=0;
			     count[2]=0;
			     count[3]=0;
			     count[4]=0;
			    // count[5]=0;
			     count[6]=0;}
		        else
		        {
		        	note[0].setImageResource(R.drawable.note1);
				   	note[1].setImageResource(R.drawable.note2);
				    note[2].setImageResource(R.drawable.note3);
				   	note[3].setImageResource(R.drawable.note4);
				    note[4].setImageResource(R.drawable.note5);			   
				    note[6].setImageResource(R.drawable.note7);
				     count[0]=0;
				     count[1]=0;
				     count[2]=0;
				     count[3]=0;
				     count[4]=0;
				    // count[5]=0;
				     count[6]=0;
			        
		        }
		        }
				if(count[5]%2 == 0 && flag[5] && end[5]){
					if(second)
					note[5].setImageResource(R.drawable.notes6);
					else
						note[5].setImageResource(R.drawable.note6);
		        TranslateAnimation ani = new TranslateAnimation(0,-note[5].getX()+130*6,0,-220);
				ani.setDuration(1000);
				note[5].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzl.setBackgroundResource(R.drawable.shipl_la);
						}
						
					};
				};
				 new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						han.sendEmptyMessage(0x111);
					}
				}, 1000);
				 rightv();
				 score1.setImageResource(R.drawable.point6);
				 flag[6]=true;
				 end[5]=false;
				}
				if(count[5]%2==0 && flag[5] == false)
				{
					if(second)
					note[5].setImageResource(R.drawable.notes6);
					else
					note[5].setImageResource(R.drawable.note6);
					wrong();}
		        }
			
		});
	note[6].setOnClickListener(new OnClickListener()
		{
			private MediaPlayer mediaPlayer7;
	
			//int count = 0;
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				count[6]++;
				if(count[6]%2 ==1 && end[6])
				{
					if(second)
						note[6].setImageResource(R.drawable.noteslight_7);
					else
					note[6].setImageResource(R.drawable.notelight_7);
				AssetManager am = getAssets();
				try
		        {
		     	   //AssetFileDescriptor 
		     	   afd = am.openFd("high/high7.ogg");
		     	   mediaPlayer7 =new MediaPlayer();
		     	   mediaPlayer7.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		     	   mediaPlayer7.prepare();
		        }
		        catch(IOException e)
		        {
		     	   e.printStackTrace();
		        }
		        mediaPlayer7.start();
		        mediaPlayer7.setOnCompletionListener(new OnCompletionListener() {					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						if(mediaPlayer7 != null)
						{
							mediaPlayer7.release();
							mediaPlayer7 = null;
						}
					}
				});
		        if(second){
		        note[0].setImageResource(R.drawable.notes1);
			   	note[1].setImageResource(R.drawable.notes2);
			    note[2].setImageResource(R.drawable.notes3);
			   	note[3].setImageResource(R.drawable.notes4);
			    note[4].setImageResource(R.drawable.notes5);
			    note[5].setImageResource(R.drawable.notes6);
			     count[0]=0;
			     count[1]=0;
			     count[2]=0;
			     count[3]=0;
			     count[4]=0;
			     count[5]=0;}
		        else {
		        	note[0].setImageResource(R.drawable.note1);
				   	note[1].setImageResource(R.drawable.note2);
				    note[2].setImageResource(R.drawable.note3);
				   	note[3].setImageResource(R.drawable.note4);
				    note[4].setImageResource(R.drawable.note5);
				    note[5].setImageResource(R.drawable.note6);
				     count[0]=0;
				     count[1]=0;
				     count[2]=0;
				     count[3]=0;
				     count[4]=0;
				     count[5]=0;
				}
		        }
				if(count[6]%2 == 0 && flag[6] && end[6]){
					if(second)
						note[6].setImageResource(R.drawable.notes7);
					else 
					note[6].setImageResource(R.drawable.note7);				
		        TranslateAnimation ani = new TranslateAnimation(0,-note[6].getX()+130*7,0,-220);
				ani.setDuration(1000);
				note[6].startAnimation(ani);
				ani.setFillAfter(true);
				final Handler han = new Handler(){
					public void handleMessage(Message msg) {
						if(msg.what==0x111)
						{
							vlzsi.setBackgroundResource(R.drawable.shipl_si);
							rightv();
							lll.addView(scorestar);
							score1.setImageResource(R.drawable.point7);
							score1.setX(110);
							score1.setY(40);
							score2.setX(160);
							score2.setY(40);
							ss.start();
							end[6]=false;
							//结束后龙舟向前移动														
							for(int i = 0; i<7 ; i++){
							an[i] = new TranslateAnimation(-note[i].getX()+130*(i+1),
									-note[i].getX()+130*(i+1)-1100,-220,-220);
							an[i].setDuration(4300);
							an[i].setFillAfter(true);
							an[i].setInterpolator(new LinearInterpolator());
							}
							note[0].startAnimation(an[0]);
							note[1].startAnimation(an[1]);
							note[2].startAnimation(an[2]);
							note[3].startAnimation(an[3]);
							note[4].startAnimation(an[4]);
							note[5].startAnimation(an[5]);
							note[6].startAnimation(an[6]);
							vlzh.startAnimation(lzdis);
							vlzd.startAnimation(lzdis);
							vlzr.startAnimation(lzdis);
							vlzm.startAnimation(lzdis);
							vlzf.startAnimation(lzdis);
							vlzso.startAnimation(lzdis);
							vlzl.startAnimation(lzdis);
							vlzsi.startAnimation(lzdis);					
							//龙舟向前运动伴随的七个音符声音
							m[0].start();
							if (second)
								note[0].setImageResource(R.drawable.noteslight_1);
							 else 
								note[0].setImageResource(R.drawable.notelight_1);							
							final Handler h1 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if(msg.what == 0x1){
									//m[0].stop();
									if(second)
										note[0].setImageResource(R.drawable.notes1);
									else
									note[0].setImageResource(R.drawable.note1);
							m[1].start();
							if(second)
								note[1].setImageResource(R.drawable.noteslight_2);
							else 
							note[1].setImageResource(R.drawable.notelight_2);
							final Handler h2 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if(msg.what == 0x2){
										//m[1].stop();
										if(second)
											note[1].setImageResource(R.drawable.notes2);
										else
										note[1].setImageResource(R.drawable.note2);
							m[2].start();
							if(second)
								note[2].setImageResource(R.drawable.noteslight_3);
							else
							note[2].setImageResource(R.drawable.notelight_3);
							final Handler h3 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if(msg.what == 0x3){
										//m[2].stop();
										if(second)
											note[2].setImageResource(R.drawable.notes3);
										else 
										note[2].setImageResource(R.drawable.note3);
							m[3].start();
							if(second)
								note[3].setImageResource(R.drawable.noteslight_4);
							else
							note[3].setImageResource(R.drawable.notelight_4);
							final Handler h4 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if(msg.what == 0x4){
										//m[3].stop();
										if(second)
											note[3].setImageResource(R.drawable.notes4);
										else
										note[3].setImageResource(R.drawable.note4);
							m[4].start();
							if(second)
								note[4].setImageResource(R.drawable.noteslight_5);
							else
							note[4].setImageResource(R.drawable.notelight_5);
							final Handler h5 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if(msg.what == 0x5){
										//m[4].stop();
										if(second)
											note[4].setImageResource(R.drawable.notes5);
										else
										note[4].setImageResource(R.drawable.note5);
							m[5].start();
							if (second) 
								note[5].setImageResource(R.drawable.noteslight_6);
							else 
							note[5].setImageResource(R.drawable.notelight_6);
							final Handler h6 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if(msg.what == 0x6){
										//m[5].stop();
										if(second)
											note[5].setImageResource(R.drawable.notes6);
										else
										note[5].setImageResource(R.drawable.note6);
							m[6].start();							
							if(second)
								note[6].setImageResource(R.drawable.noteslight_7);
							else
							note[6].setImageResource(R.drawable.notelight_7);
							}
					}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h6.sendEmptyMessage(0x6);
						}
					}, 600);
							}//note[5]if
					}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h5.sendEmptyMessage(0x5);
						}
					}, 600);
									}//note[4]if
							}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h4.sendEmptyMessage(0x4);
						}
					}, 600);
								}//note[3].if
					}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h3.sendEmptyMessage(0x3);
						}
					}, 600);
						}//note[1]if
					}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h2.sendEmptyMessage(0x2);
						}
					}, 600);
						}//note[0]if
					}};
					new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							h1.sendEmptyMessage(0x1);
						}
					}, 600);
				}//if						
			};//handlermessge
		};//handler
	new Timer().schedule(new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			han.sendEmptyMessage(0x111);
			}
		}, 1000);
	}//if
	
	if(count[6]%2==0 && flag[6] == false)
	     {
		if(second)
			note[6].setImageResource(R.drawable.notes7);
		else
			note[6].setImageResource(R.drawable.note7);
			wrong();}  }
	}); 
	}
});
//进入下一关
m[6].setOnCompletionListener(new OnCompletionListener(){
MediaPlayer finishone = new MediaPlayer();
MediaPlayer finishtwo = new MediaPlayer();
	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		final AlphaAnimation finishAlphadis = new AlphaAnimation(1, 0);
		finishAlphadis.setDuration(1000);
		finishAlphadis.setFillAfter(true);
		final AlphaAnimation finishAlphashow = new AlphaAnimation(0, 1);
		finishAlphashow.setDuration(1);
		finishAlphashow.setFillAfter(true);
		if(!second)
		{AssetManager finishAssetManager = getAssets();
		try {
			AssetFileDescriptor finishafd = finishAssetManager.openFd("raw/prizemp0.ogg");
			finishone.setDataSource(finishafd.getFileDescriptor(),
					finishafd.getStartOffset(),finishafd.getLength());
			finishone.prepare();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finishone.start();
		lll.removeView(scorestar);
		lll.addView(scor100);
		lll.addView(wordImageView);
		lll.addView(jingling);
		lll.addView(gift);
		lll.addView(fstar);			
		jingling.setX(190);
		jingling.setY(220);		
		wordImageView.setImageResource(R.drawable.text1);
		wordImageView.setX(200);
		wordImageView.setY(380);
		jingling.setImageResource(R.drawable.jingling1);
		jingling.setX(190);
		jingling.setY(150);		
		gift.setImageResource(R.drawable.gift0);
		gift.setX(630);
		gift.setY(200);		
		fstar.setX(630);
		fstar.setY(200);		
		finishstarani.start();		
		finishone.setOnCompletionListener(new OnCompletionListener() {			 
			@Override			
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				gamePlayer = new MediaPlayer();				
				AssetManager passAssetManager = getAssets();
				try {
					afd = passAssetManager.openFd("raw/pass.ogg");
					gamePlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
					gamePlayer.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				gamePlayer.start();
				scor100.startAnimation(finishAlphadis);
				jingling.startAnimation(finishAlphadis);
				gift.startAnimation(finishAlphadis);
				fstar.startAnimation(finishAlphadis);
				wordImageView.startAnimation(finishAlphadis);
				lll.removeView(fstar);
				lll.removeView(scorestar);
				score1.setImageResource(R.drawable.point0);
				score1.setX(120);
				score1.setY(40);
				game.setImageResource(R.drawable.game_02);
				game.startAnimation(gameshow);
				final Handler gamehan = new Handler(){
					@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					if (msg.what==0x123) {
						game.startAnimation(gamedis);
						second = true;
						//修改七个音符的背景图片
						for(int i = 0 ; i< 7;  i++)
						 {							
							an[i].setFillAfter(false);
						 }	
						 note[0].setImageResource(R.drawable.notes1);			 
						 note[1].setImageResource(R.drawable.notes2);			 
						 note[2].setImageResource(R.drawable.notes3);			
						 note[3].setImageResource(R.drawable.notes4);			 
						 note[4].setImageResource(R.drawable.notes5);			 
						 note[5].setImageResource(R.drawable.notes6);			
						 note[6].setImageResource(R.drawable.notes7);
						 int [] arry = {0,1,2,3,4,5,6};
						 int[] a = new int[7];
						 int j; 	 
						 int m1 = 0;
						 int n =arry.length;
						 for(int i = 0; i<7;i++)
						 {
							 j = (int)(Math.random()*10%(n--));
							 a[m1] =arry[j];
							 m1++;
							 int[] b;
							 b = chongzu(arry,j);
						}
						 for(int i =0 ; i<7;i++)
						 {
							 note[a[i]].setX((i+1)*120);
							 note[a[i]].setY(500);
						 }
						 //龙舟重新运动过去
						 TranslateAnimation longzhouAnimation = new TranslateAnimation(1100, 0, 0, 0);
						 longzhouAnimation.setDuration(1500);
						 longzhouAnimation.setFillAfter(true);
						 longzhouAnimation.setInterpolator(new LinearInterpolator()) ;
						 vlzh.startAnimation(longzhouAnimation);
						 vlzd.startAnimation(longzhouAnimation);
						 vlzr.startAnimation(longzhouAnimation);
						 vlzm.startAnimation(longzhouAnimation);
						 vlzf.startAnimation(longzhouAnimation);
						 vlzso.startAnimation(longzhouAnimation);
						 vlzl.startAnimation(longzhouAnimation);
						 vlzsi.startAnimation(longzhouAnimation);
						 vlzd.setBackgroundResource(R.drawable.shipg_do);
						 vlzr.setBackgroundResource(R.drawable.shipg_re);
						 vlzm.setBackgroundResource(R.drawable.shipg_mi);
						 vlzf.setBackgroundResource(R.drawable.shipg_fa);
						 vlzso.setBackgroundResource(R.drawable.shipg_sol);
						 vlzl.setBackgroundResource(R.drawable.shipg_la);
						 vlzsi.setBackgroundResource(R.drawable.shipg_si);
						 //初始化七个音符的各个标志
						 final Handler initflag = new Handler(){@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x000) {
								 for (int k = 0; k < 7; k++) {
									 if( k==0 )
									 {flag[k] = true;
									 end[k]=true;
									count[k] = 0;}
									 else{
									flag[k]=false;
									end[k]=true;
									count[k] = 0;}
								}			
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								initflag.sendEmptyMessage(0x000);
							}
						}, 1500);
									
					}//hander中if
					
				}};
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						gamehan.sendEmptyMessage(0x123);
					}
				}, 1000);	
				
			}
		});
		
	}//if
		else {			
			AssetManager finishAssetManager = getAssets();
			try {
				AssetFileDescriptor finish2afd = finishAssetManager.openFd("raw/prizemp1.ogg");
				finishtwo.setDataSource(finish2afd.getFileDescriptor(),
						finish2afd.getStartOffset(),finish2afd.getLength());				
				finishtwo.prepare();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finishtwo.start();
			jingling.setImageResource(R.drawable.jingling2);
			gift.setImageResource(R.drawable.gift1);
			wordImageView.setImageResource(R.drawable.text2);
			lll.removeView(scorestar);
			scor100.startAnimation(finishAlphashow);
			jingling.startAnimation(finishAlphashow);
			gift.startAnimation(finishAlphashow);
			wordImageView.startAnimation(finishAlphashow);
			fstar.setX(630);
			fstar.setY(200);
			lll.addView(fstar);
			finishstarani.start();
			final Handler scencedis = new Handler()
			{
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					if(msg.what == 0x0)
						lll.startAnimation(finishAlphadis);
					Intent intent2 = new Intent(first.this, third.class); 
					intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent2);
					finish();
					}
			};
			new Timer().schedule(new TimerTask() {				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					scencedis.sendEmptyMessage(0x0);
				}
			}, 3500);
			
			}//else
}});

}//倒数第二的括号
//七个音符答对的声
 void rightv()
	{
		AssetManager am = getAssets();
		//final
		final MediaPlayer rplay = new MediaPlayer(); 
		try
    {
		AssetFileDescriptor[] afd = {am.openFd("prizevoice/right0.ogg"),am.openFd("prizevoice/right1.ogg"),
				am.openFd("prizevoice/right2.ogg"),am.openFd("prizevoice/right3.ogg"),
				am.openFd("prizevoice/right4.ogg"),am.openFd("prizevoice/right5.ogg"),
				am.openFd("prizevoice/right6.ogg"),am.openFd("prizevoice/right7.ogg"),
				am.openFd("prizevoice/right8.ogg")};
		
		int i =(int) (Math.random()*10%9 );
		rplay.setDataSource(afd[i].getFileDescriptor(),afd[i].getStartOffset(),afd[i].getLength());
		rplay.prepare();		
		rplay.start();		
     }
     catch(IOException e)
     {
  	   e.printStackTrace();
     }
		rplay.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
		{
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				if(rplay != null){
					rplay.release();
					//rplay = null;
				}
			}});
	}
	//七个音符答错的声音
void wrong()
	{
	    final MediaPlayer wplay = new MediaPlayer();
		AssetManager am = getAssets();
		try
		{
			AssetFileDescriptor[] afd = {am.openFd("prizevoice/wrong0.ogg"),am.openFd("prizevoice/wrong1.ogg"),
				am.openFd("prizevoice/wrong2.ogg"),am.openFd("prizevoice/wrong3.ogg"),
				am.openFd("prizevoice/wrong4.ogg"),am.openFd("prizevoice/wrong5.ogg"),
				am.openFd("prizevoice/wrong6.ogg")};
			int i =(int) (Math.random()*10%7);
			wplay.setDataSource(afd[i].getFileDescriptor(),afd[i].getStartOffset(),afd[i].getLength());
			wplay.prepare();
			wplay.start();
			wplay.setOnCompletionListener(new OnCompletionListener() {
				@Override
			public void onCompletion(MediaPlayer arg0) {
				// TODO Auto-generated method stub
				if(wplay != null)
				{
					wplay.release();
					//wplay = null;
				}
			}
		});
  }
  catch(IOException e)
  {
	   e.printStackTrace();
  }

	}
 //七个音符重组的函数
static int[] chongzu(int[] b,int n)
	{
		for(int i = n;n<b.length-1;n++)		
			b[n] = b[n+1];
		return b;
	}
}


/*	 final ImageView water = (ImageView)findViewById(R.id.waterleft);
// water water = new water(this, null);
 //final Animation lzh = AnimationUtils.loadAnimation(this,R.anim.longzhou);
 //动画结束后保留状态
 //lzh.setFillAfter(true);
 water.startAnimation(lzh); */  
 
 //七个音符
/* RelativeLayout lll= (RelativeLayout)findViewById(R.id.first);
 final Button[] note = new Button[7]; 
 for(int i = 0 ; i< 7;  i++)
 {
	 note[i]= new Button(this);
	 lll.addView(note[i]);
 }	
 note[0].setBackgroundResource(R.drawable.note1);
 note[1].setBackgroundResource(R.drawable.note2);
 note[2].setBackgroundResource(R.drawable.note3);
 note[3].setBackgroundResource(R.drawable.note4);
 note[4].setBackgroundResource(R.drawable.note5);
 note[5].setBackgroundResource(R.drawable.note6);
 note[6].setBackgroundResource(R.drawable.note7);
 int [] arry = {0,1,2,3,4,5,6};
 int[] a = new int[7];
 int j; 	 
 int m = 0;
 int n =arry.length;
 for(int i = 0; i<7;i++)
 {
	 j = (int)(Math.random()*10%(n--));
	 a[m] =arry[j];
	 m++;
	 int[] b;
	 b = chongzu(arry,j);
}
 for(int i =0 ; i<7;i++)
 {
	 note[a[i]].setX((i+1)*120);
	 note[a[i]].setY(450);
 }	
 note[0].setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		note[0].setBackgroundResource(R.drawable.notelight_1);
		AssetManager am = getAssets();
		try
        {
     	   //AssetFileDescriptor 
     	   afd = am.openFd("high/high1.ogg");
     	   mediaPlayer =new MediaPlayer();
     	   mediaPlayer.setDataSource(afd.getFileDescriptor(),
     	   afd.getStartOffset(),afd.getLength());
     	   mediaPlayer.prepare();
        }
        catch(IOException e)
        {
     	   e.printStackTrace();
        }
        mediaPlayer.start();
	}
	 
 });
 note[1].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[1].setBackgroundResource(R.drawable.notelight_2);
		}
		 
	 });
 note[2].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[2].setBackgroundResource(R.drawable.notelight_3);
		}
		 
	 });
 note[3].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[3].setBackgroundResource(R.drawable.notelight_4);
		}
		 
	 });
 note[4].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[4].setBackgroundResource(R.drawable.notelight_5);
		}
		 
	 });
 note[5].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[5].setBackgroundResource(R.drawable.notelight_6);
		}
		 
	 });
 note[6].setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			note[6].setBackgroundResource(R.drawable.notelight_7);
		}
		 
	 });
}
//七个音符随即数组中需要的重组函数
static int[] chongzu(int[] b,int n)
{
	for(int i = n;n<b.length-1;n++)		
		b[n] = b[n+1];
	return b;}
}*/
//语音结束跳出演示操作提示(对话框)空指针	        
//final Builder builder = new AlertDialog.Builder(this);
//mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//	
//	@Override
//	public void onCompletion(MediaPlayer arg0) {
//		// TODO Auto-generated method stub
//		mediaPlayer.release();
//		
//		RelativeLayout tip = (RelativeLayout)getLayoutInflater().inflate(R.id.tip, null);
//		
//		builder.setView(tip);
//		builder.create().show();
//	
//	}
//});
/* 
 * 错误获取弹出框按钮方法
 Button begin = (Button)findViewById(R.id.begin);
 Button skip = (Button)findViewById(R.id.skip);
     ///空指针
  begin.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			AssetManager am = getAssets();
			try
	        {
	     	   AssetFileDescriptor afd = am.openFd("raw/tip_start.ogg");
	     	   mediaPlayer =new MediaPlayer();
	     	   mediaPlayer.setDataSource(afd.getFileDescriptor(),
	     	   afd.getStartOffset(),afd.getLength());
	     	   mediaPlayer.prepare();
	        }
	        catch(IOException e)
	        {
	     	   e.printStackTrace();
	        }
	        mediaPlayer.start();
			
		}
 	
 });
  skip.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
		}
 });*/

//消除七个音符的组件
//		final Handler removeHandler = new Handler() {
//@Override
//public void handleMessage(Message msg) {
//// TODO Auto-generated method stub
//super.handleMessage(msg);
//if(msg.what == 0x000);
//for(int i = 0; i < 7; i++){
//lll.removeView(note[i]);}
//}};
//new Timer().schedule(new TimerTask() {
//
//@Override
//public void run() {
//	// TODO Auto-generated method stub
//	removeHandler.sendEmptyMessage(0x000);
//}
//}, 3490);