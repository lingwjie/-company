package com.example.nogif;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.example.nogif.R.anim;
import com.example.nogif.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class third extends Activity {
	boolean [] flag = {true,false,false,false,false,false,false};//七个音符按顺序点击标志	
	boolean end[]= {true,true,true,true,true,true,true};//音符点击结束标志
	int [] count = {0,0,0,0,0,0,0};//音符点击几次标志
	boolean second = false;
	boolean uanddflag = false;
	MediaPlayer gamePlayer= new MediaPlayer();//关卡声音
	AssetFileDescriptor afd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		//去掉窗口标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
		setContentView(R.layout.third);
		//获取整个界面的布局
		final RelativeLayout thirdLayout = (RelativeLayout)findViewById(R.id.third);
		
		 //退出弹出框  tip2      
        final View tip2 = this.getLayoutInflater().inflate(R.layout.tip2, null);
    	final PopupWindow exitpopup = new PopupWindow(tip2,display.getWidth(),display.getHeight()+50);
    	ImageButton quit = (ImageButton)findViewById(R.id.quit);
    	//final boolean mp = true;
        quit.setOnClickListener(new OnClickListener ()
        {
 		@Override
 		public void onClick(View arg0) {
 			// TODO Auto-generated method stub
 			//第一个参数为当前View的ID
 			exitpopup.showAtLocation(findViewById(R.id.third), Gravity.CENTER, 20, 20);
 			//System.exit(0);
 			//android.os.Process.killProcess(android.os.Process.myPid());
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
		       exitpopup.dismiss();
			}
        	
        });
        //分数
        final ImageView score1 = new ImageView(this);
        final ImageView score2 = new ImageView(this);
        score1.setImageResource(R.drawable.point0);
        score2.setImageResource(R.drawable.point0);
        thirdLayout.addView(score1);
        thirdLayout.addView(score2);
        score1.setX(120);
        score1.setY(40);
        score2.setX(150);
        score2.setY(40);
     //分数周围出现的星星
        final ImageView scorestar = (ImageView)findViewById(R.id.scorestar);
        final AnimationDrawable scorestarani = (AnimationDrawable)scorestar.getBackground();
   //游戏结束后周围出现的星星
        final ImageView fstar = new ImageView(this);
        fstar.setBackgroundResource(R.anim.finishstar);      
        final AnimationDrawable finishstarani = (AnimationDrawable)fstar.getBackground();    
   //关卡游戏结束后出现的图片
        final ImageView scor100 = new ImageView(this);
        scor100.setBackgroundResource(R.drawable.prizekuangbg);
        final ImageView jingling = new ImageView(this);
        final ImageView gift = new ImageView(this);
        final ImageView wordImageView = new ImageView(this);
    //第三关游戏开始的声音
        AssetManager gameAssetManager = getAssets();
	    try {
			afd = gameAssetManager.openFd("raw/pass.ogg");
			gamePlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			gamePlayer.prepare();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
	    gamePlayer.start();
	  //最后位置的七个音符
		 final ImageView []notebackground = new ImageView[7];
		 for (int i = 0; i < notebackground.length; i++) {
			notebackground[i] = new ImageView(this);
			thirdLayout.addView(notebackground[i]);
			notebackground[i].setX(50+(i+1)*115);
			notebackground[i].setY(430-i*34+2);
		}
		 notebackground[0].setImageResource(R.drawable.bowdo);
		 notebackground[1].setImageResource(R.drawable.bowre);
		 notebackground[2].setImageResource(R.drawable.bowmi);
		 notebackground[3].setImageResource(R.drawable.bowfa);
		 notebackground[4].setImageResource(R.drawable.bowsol);
		 notebackground[5].setImageResource(R.drawable.bowla);
		 notebackground[6].setImageResource(R.drawable.bowsi);
		//七个音符
		final ImageView[] note = new ImageView[7]; 
		 for(int i = 0 ; i< 7;  i++)
		 {
			 note[i]= new ImageView(this);
			 thirdLayout.addView(note[i]);
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
	 /*******关卡的出现与消失********/
        final Animation gamedis= AnimationUtils.loadAnimation(this, R.anim.gameone);
        gamedis.setFillAfter(true);
        final Animation gameshow= AnimationUtils.loadAnimation(this, R.anim.gameoneshow);
        gameshow.setFillAfter(true);
        final ImageView game= new ImageView(this);
        game.setImageResource(R.drawable.game_03);
        game.setX(350);
        game.setY(220);
	    thirdLayout.addView(game);
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
    
		//最后音符跳动伴随的声音
	    final ImageView lowImageView = new ImageView(this);//第四关旁边的低音符号
		 AssetManager a1 = getAssets();		
		 final MediaPlayer[] high ={new MediaPlayer(),new MediaPlayer(),
					new MediaPlayer(),new MediaPlayer(),new MediaPlayer(),
					new MediaPlayer(),new MediaPlayer()} ;									
			try {
				AssetFileDescriptor[] y = {a1.openFd("high/high1.ogg"),
						a1.openFd("high/high2.ogg"),a1.openFd("high/high3.ogg"),
						a1.openFd("high/high4.ogg"),a1.openFd("high/high5.ogg"),
						a1.openFd("high/high6.ogg"),a1.openFd("high/high7.ogg")};
				for(int i = 0; i < 7; i++){								
				high[i].setDataSource(y[i].getFileDescriptor(), y[i].getStartOffset(), y[i].getLength());
				high[i].prepare();}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 final MediaPlayer[] low ={new MediaPlayer(),new MediaPlayer(),
					new MediaPlayer(),new MediaPlayer(),new MediaPlayer(),
					new MediaPlayer(),new MediaPlayer()} ;									
			try {
				AssetFileDescriptor[] y = {a1.openFd("low/low1.ogg"),
						a1.openFd("low/low2.ogg"),a1.openFd("low/low3.ogg"),
						a1.openFd("low/low4.ogg"),a1.openFd("low/low5.ogg"),
						a1.openFd("low/low6.ogg"),a1.openFd("low/low7.ogg")};
				for(int i = 0; i < 7; i++){								
				low[i].setDataSource(y[i].getFileDescriptor(), y[i].getStartOffset(), y[i].getLength());
				low[i].prepare();}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		//七个音符上下跳动的补件动画
	final TranslateAnimation [] up = { new TranslateAnimation(-note[0].getX()+140, -note[0].getX()+140, -90, -120),
					new TranslateAnimation(-note[1].getX()+132*2, -note[1].getX()+132*2, -(90+33*1), -(90+33*1+30)),
					new TranslateAnimation(-note[2].getX()+125*3, -note[2].getX()+125*3,-(90+33*2), -(90+33*2+30)),
					new TranslateAnimation(-note[3].getX()+123*4, -note[3].getX()+123*4, -(90+33*3), -(90+33*3+30)),
					new TranslateAnimation(-note[4].getX()+122*5, -note[4].getX()+122*5, -(90+33*4), -(90+33*4+30)),
					new TranslateAnimation(-note[5].getX()+121*6, -note[5].getX()+121*6, -(90+33*5), -(90+33*5+30)),
					new TranslateAnimation(-note[6].getX()+120*7, -note[6].getX()+120*7, -(90+33*6), -(90+33*6+30))
	                };
			for (int i = 0; i < up.length; i++) {
				up[i].setFillAfter(true);
				up[i].setDuration(250);
			}
	final TranslateAnimation [] down = {new TranslateAnimation(-note[0].getX()+140, -note[0].getX()+140,-120 ,-90 ),
					 new TranslateAnimation(-note[1].getX()+132*2, -note[1].getX()+132*2, -(90+33*1+30), -(90+33*1)),
					 new TranslateAnimation(-note[2].getX()+125*3, -note[2].getX()+125*3,-(90+33*2+30),- (90+33*2)),
					 new TranslateAnimation(-note[3].getX()+123*4, -note[3].getX()+123*4, -(90+33*3+30), -(90+33*3)),
					 new TranslateAnimation(-note[4].getX()+122*5, -note[4].getX()+122*5, -(90+33*4+30), -(90+33*4)),
					 new TranslateAnimation(-note[5].getX()+121*6, -note[5].getX()+121*6, -(90+33*5+30), -(90+33*5)),
					 new TranslateAnimation(-note[6].getX()+120*7, -note[6].getX()+120*7, -(90+33*6+30), -(90+33*6)),
					 };			
			for (int i = 0; i < down.length; i++) {
				down[i].setFillAfter(true);
				down[i].setDuration(250);
			}
	//游戏开始双击音符
	gamePlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
		 note[0].setOnClickListener(new OnClickListener()
			{
				private MediaPlayer mediaPlayer1;
				AssetManager am = getAssets();
				AssetFileDescriptor note1adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[0] ++;
					if(count[0]%2 == 1 && end[0])
					{						
						if(second){
							note[0].setImageResource(R.drawable.noteslight_1);						
						try
				        {
				     	   note1adf = am.openFd("low/low1.ogg");
				     	   mediaPlayer1 =new MediaPlayer();
				     	   mediaPlayer1.setDataSource(note1adf.getFileDescriptor(),
				     			   note1adf.getStartOffset(),note1adf.getLength());
				     	   mediaPlayer1.prepare();
				     	   mediaPlayer1.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[0].setImageResource(R.drawable.notelight_1);
						AssetManager am = getAssets();
						try
				        {				     	  
				     	   note1adf = am.openFd("high/high1.ogg");
				     	   mediaPlayer1 =new MediaPlayer();
				     	   mediaPlayer1.setDataSource(note1adf.getFileDescriptor(),
				     			   note1adf.getStartOffset(),note1adf.getLength());
				     	   mediaPlayer1.prepare();
				     	   mediaPlayer1.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
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
				{
					note[0].setImageResource(R.drawable.notes1);
					TranslateAnimation ani = new TranslateAnimation(0,-note[0].getX()+140,
							0,-215);
					ani.setDuration(700);
					note[0].startAnimation(ani);
					ani.setFillAfter(true);	
				}
				else{
				note[0].setImageResource(R.drawable.note1);				
				TranslateAnimation ani = new TranslateAnimation(0,-note[0].getX()+140,
						0,-90);
				ani.setDuration(700);
				note[0].startAnimation(ani);
				ani.setFillAfter(true);}
				final Handler note1Handler = new Handler(){
					@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);
					if (msg.what == 0x1) {
						thirdLayout.removeView(notebackground[0]);
					}
				}};
				new Timer().schedule(new TimerTask() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						note1Handler.sendEmptyMessage(0x1);
					}
				}, 700);
			rightv();
			score1.setImageResource(R.drawable.point1);
			flag[1]=true;
			end[0] = false;}//if
		   }			
		});
		 note[1].setOnClickListener(new OnClickListener()
			{
			 private MediaPlayer mediaPlayer2;
				AssetManager am = getAssets();
				AssetFileDescriptor note2adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[1] ++;
					if(count[1]%2 == 1 && end[1])
					{						
						if(second){
							note[1].setImageResource(R.drawable.noteslight_2);						
						try
				        {
				     	   note2adf = am.openFd("low/low2.ogg");
				     	   mediaPlayer2 =new MediaPlayer();
				     	   mediaPlayer2.setDataSource(note2adf.getFileDescriptor(),
				     			   note2adf.getStartOffset(),note2adf.getLength());
				     	   mediaPlayer2.prepare();
				     	   mediaPlayer2.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[1].setImageResource(R.drawable.notelight_2);
						AssetManager am = getAssets();
						try
				        {
				     	   //AssetFileDescriptor 
				     	   note2adf = am.openFd("high/high2.ogg");
				     	   mediaPlayer2 =new MediaPlayer();
				     	   mediaPlayer2.setDataSource(note2adf.getFileDescriptor(),
				     			   note2adf.getStartOffset(),note2adf.getLength());
				     	   mediaPlayer2.prepare();
				     	   mediaPlayer2.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
					mediaPlayer2.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
					{
						@Override
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							if(mediaPlayer2 != null){
								mediaPlayer2.release();
								mediaPlayer2 = null;
							}
						}});
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
						{
							note[1].setImageResource(R.drawable.notes2);
							TranslateAnimation ani = new TranslateAnimation(0,-note[1].getX()+132*2,
									0,-(215+33*1));
							ani.setDuration(700);
							note[1].startAnimation(ani);
							ani.setFillAfter(true);
						}
						else{
						note[1].setImageResource(R.drawable.note2);
						
						TranslateAnimation ani = new TranslateAnimation(0,-note[1].getX()+132*2,
								0,-(90+33*1));
						ani.setDuration(700);
						note[1].startAnimation(ani);
						ani.setFillAfter(true);}
						final Handler note2Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x2) {
								thirdLayout.removeView(notebackground[1]);
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note2Handler.sendEmptyMessage(0x2);
							}
						}, 700);
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
				AssetManager am = getAssets();
				AssetFileDescriptor note3adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[2] ++;
					if(count[2]%2 == 1 && end[2])
					{						
						if(second){
							note[2].setImageResource(R.drawable.noteslight_3);						
						try
				        {
				     	   note3adf = am.openFd("low/low3.ogg");
				     	   mediaPlayer3 =new MediaPlayer();
				     	   mediaPlayer3.setDataSource(note3adf.getFileDescriptor(),
				     			   note3adf.getStartOffset(),note3adf.getLength());
				     	   mediaPlayer3.prepare();
				     	   mediaPlayer3.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[2].setImageResource(R.drawable.notelight_3);						
						try
				        {				     	  
				     	   note3adf = am.openFd("high/high3.ogg");
				     	   mediaPlayer3 =new MediaPlayer();
				     	   mediaPlayer3.setDataSource(note3adf.getFileDescriptor(),
				     			   note3adf.getStartOffset(),note3adf.getLength());
				     	   mediaPlayer3.prepare();
				     	   mediaPlayer3.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
					mediaPlayer3.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
					{
						@Override
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							if(mediaPlayer3 != null){
								mediaPlayer3.release();
								mediaPlayer3 = null;
							}
						}});
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
			        {note[0].setImageResource(R.drawable.note1);
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
				    count[6]=0;}
			        }
					if(count[2]%2==0 && flag[2] && end[2])
			        {
						if(second)
						{
							note[2].setImageResource(R.drawable.notes3);
							TranslateAnimation ani = new TranslateAnimation(0,-note[2].getX()+125*3,
									0,-(215+33*2));
							ani.setDuration(700);
							note[2].startAnimation(ani);
							ani.setFillAfter(true);
						}
						else{
						note[2].setImageResource(R.drawable.note3);						
						TranslateAnimation ani = new TranslateAnimation(0,-note[2].getX()+125*3,
								0,-(90+33*2));
						ani.setDuration(700);
						note[2].startAnimation(ani);
						ani.setFillAfter(true);}
						final Handler note3Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x3) {
								thirdLayout.removeView(notebackground[2]);
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note3Handler.sendEmptyMessage(0x3);
							}
						}, 700);
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
				AssetManager am = getAssets();
				AssetFileDescriptor note4adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[3] ++;
					if(count[3]%2 == 1 && end[3])
					{						
						if(second){
							note[3].setImageResource(R.drawable.noteslight_4);						
						try
				        {
				     	   note4adf = am.openFd("low/low4.ogg");
				     	   mediaPlayer4 =new MediaPlayer();
				     	   mediaPlayer4.setDataSource(note4adf.getFileDescriptor(),
				     			   note4adf.getStartOffset(),note4adf.getLength());
				     	   mediaPlayer4.prepare();
				     	   mediaPlayer4.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[3].setImageResource(R.drawable.notelight_4);						
						try
				        {
				     	   //AssetFileDescriptor 
				     	   note4adf = am.openFd("high/high4.ogg");
				     	   mediaPlayer4 =new MediaPlayer();
				     	   mediaPlayer4.setDataSource(note4adf.getFileDescriptor(),
				     			   note4adf.getStartOffset(),note4adf.getLength());
				     	   mediaPlayer4.prepare();
				     	   mediaPlayer4.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
					mediaPlayer4.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
					{
						@Override
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							if(mediaPlayer4 != null){
								mediaPlayer4.release();
								mediaPlayer4 = null;
							}
						}});
			        if(second){
			        note[0].setImageResource(R.drawable.notes1);
				    note[2].setImageResource(R.drawable.notes3);
				   	note[1].setImageResource(R.drawable.notes2);
				   	note[4].setImageResource(R.drawable.notes5);
				   	note[5].setImageResource(R.drawable.notes6);
				    note[6].setImageResource(R.drawable.notes7);
				    count[0]=0;
				    count[2]=0;
				    count[1]=0;
				    count[4]=0;
				    count[5]=0;
				    count[6]=0;}
			        else
			        {note[0].setImageResource(R.drawable.note1);
				    note[2].setImageResource(R.drawable.note3);
				   	note[1].setImageResource(R.drawable.note2);
				   	note[4].setImageResource(R.drawable.note5);
				   	note[5].setImageResource(R.drawable.note6);
				    note[6].setImageResource(R.drawable.note7);
				    count[0]=0;
				    count[2]=0;
				    count[1]=0;
				    count[4]=0;
				    count[5]=0;
				    count[6]=0;}
			        }
					if(count[3]%2==0 && flag[3] && end[3])
			        {
						
						if(second)
						{
							thirdLayout.addView(scorestar);
							note[3].setImageResource(R.drawable.notes4);
							TranslateAnimation ani = new TranslateAnimation(0,-note[3].getX()+123*4,
									0,-(215+33*3));
							ani.setDuration(700);
							ani.setFillAfter(true);
							note[3].startAnimation(ani);							
						}
						else{
						note[3].setImageResource(R.drawable.note4);						
						TranslateAnimation ani = new TranslateAnimation(0,-note[3].getX()+123*4,
								0,-(90+33*3));
						ani.setDuration(700);
						ani.setFillAfter(true);
						note[3].startAnimation(ani);
						}
						final Handler note4Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x4) {
								thirdLayout.removeView(notebackground[3]);
								
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note4Handler.sendEmptyMessage(0x4);
							}
						},700);
						rightv();
						score1.setImageResource(R.drawable.point4);
						scorestarani.start();
						flag[4]=true;
						end[3]=false;
						}//if
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
				AssetManager am = getAssets();
				AssetFileDescriptor note5adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[4] ++;					
					if(count[4]%2 == 1 && end[4])
					{						
						if(second){
							note[4].setImageResource(R.drawable.noteslight_5);						
						try
				        {
				     	   note5adf = am.openFd("low/low5.ogg");
				     	   mediaPlayer5 =new MediaPlayer();
				     	   mediaPlayer5.setDataSource(note5adf.getFileDescriptor(),
				     			   note5adf.getStartOffset(),note5adf.getLength());
				     	   mediaPlayer5.prepare();
				     	   mediaPlayer5.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[4].setImageResource(R.drawable.notelight_5);
						AssetManager am = getAssets();
						try
				        {
				     	   //AssetFileDescriptor 
				     	   note5adf = am.openFd("high/high5.ogg");
				     	   mediaPlayer5 =new MediaPlayer();
				     	   mediaPlayer5.setDataSource(note5adf.getFileDescriptor(),
				     			   note5adf.getStartOffset(),note5adf.getLength());
				     	   mediaPlayer5.prepare();
				     	   mediaPlayer5.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
					mediaPlayer5.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
					{
						@Override
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							if(mediaPlayer5 != null){
								mediaPlayer5.release();
								mediaPlayer5 = null;
							}
						}});
			        if(second){
			        note[0].setImageResource(R.drawable.notes1);
				    note[2].setImageResource(R.drawable.notes3);
				   	note[3].setImageResource(R.drawable.notes4);
				   	note[1].setImageResource(R.drawable.notes2);
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
				    note[2].setImageResource(R.drawable.note3);
				   	note[3].setImageResource(R.drawable.note4);
				   	note[1].setImageResource(R.drawable.note2);
				   	note[5].setImageResource(R.drawable.note6);
				    note[6].setImageResource(R.drawable.note7);
				    count[0]=0;
				    count[2]=0;
				    count[3]=0;
				    count[1]=0;
				    count[5]=0;
				    count[6]=0;}
			        }
					if(count[4]%2==0 && flag[4] && end[4])
			        {
						thirdLayout.removeView(scorestar);
						if(second)
						{
							note[4].setImageResource(R.drawable.notes5);
							TranslateAnimation ani = new TranslateAnimation(0,-note[4].getX()+122*5,
									0,-(215+33*4));
							ani.setDuration(700);
							ani.setFillAfter(true);
							note[4].startAnimation(ani);
						}
						else{
						note[4].setImageResource(R.drawable.note5);						
						TranslateAnimation ani = new TranslateAnimation(0,-note[4].getX()+122*5,
								0,-(90+33*4));
						ani.setDuration(700);
						ani.setFillAfter(true);
						note[4].startAnimation(ani);}						
						final Handler note4Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x5) {
								thirdLayout.removeView(notebackground[4]);
								rightv();
								score1.setImageResource(R.drawable.point5);
								scorestarani.stop();
								flag[5]=true;
								end[4]=false;
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note4Handler.sendEmptyMessage(0x5);
							}
						}, 700);
						
						}//if
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
				AssetManager am = getAssets();
				AssetFileDescriptor note6adf;
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub			
					count[5] ++;
					if(count[5]%2 == 1 && end[5])
					{						
						if(second){
							note[5].setImageResource(R.drawable.noteslight_6);						
						try
				        {
				     	   note6adf = am.openFd("low/low6.ogg");
				     	   mediaPlayer6 =new MediaPlayer();
				     	   mediaPlayer6.setDataSource(note6adf.getFileDescriptor(),
				     			   note6adf.getStartOffset(),note6adf.getLength());
				     	   mediaPlayer6.prepare();
				     	   mediaPlayer6.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[5].setImageResource(R.drawable.notelight_6);
						AssetManager am = getAssets();
						try
				        {
				     	   //AssetFileDescriptor 
				     	   note6adf = am.openFd("high/high6.ogg");
				     	   mediaPlayer6 =new MediaPlayer();
				     	   mediaPlayer6.setDataSource(note6adf.getFileDescriptor(),
				     			   note6adf.getStartOffset(),note6adf.getLength());
				     	   mediaPlayer6.prepare();
				     	   mediaPlayer6.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
					mediaPlayer6.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
					{
						@Override
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							if(mediaPlayer6 != null){
								mediaPlayer6.release();
								mediaPlayer6 = null;
							}
						}});
			        if(second){
			        note[0].setImageResource(R.drawable.notes1);
				    note[2].setImageResource(R.drawable.notes3);
				   	note[3].setImageResource(R.drawable.notes4);
				   	note[4].setImageResource(R.drawable.notes5);
				   	note[1].setImageResource(R.drawable.notes2);
				    note[6].setImageResource(R.drawable.notes7);
				    count[0]=0;
				    count[2]=0;
				    count[3]=0;
				    count[4]=0;
				    count[1]=0;
				    count[6]=0;}
			        else
			        {note[0].setImageResource(R.drawable.note1);
				    note[2].setImageResource(R.drawable.note3);
				   	note[3].setImageResource(R.drawable.note4);
				   	note[4].setImageResource(R.drawable.note5);
				   	note[1].setImageResource(R.drawable.note2);
				    note[6].setImageResource(R.drawable.note7);
				    count[0]=0;
				    count[2]=0;
				    count[3]=0;
				    count[4]=0;
				    count[1]=0;
				    count[6]=0;}
			        }
					if(count[5]%2==0 && flag[5] && end[5])
			        {
						if(second)
							{note[5].setImageResource(R.drawable.notes6);
							TranslateAnimation ani = new TranslateAnimation(0,-note[5].getX()+121*6,
									0,-(215+33*5));
							ani.setDuration(700);
							ani.setFillAfter(true);
							note[5].startAnimation(ani);							
							}
						else
						{note[5].setImageResource(R.drawable.note6);
						
						TranslateAnimation ani = new TranslateAnimation(0,-note[5].getX()+121*6,
								0,-(90+33*5));
						ani.setDuration(700);
						ani.setFillAfter(true);
						note[5].startAnimation(ani);
						}
						final Handler note6Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x6) {
								thirdLayout.removeView(notebackground[5]);
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note6Handler.sendEmptyMessage(0x6);
							}
						}, 700);
						rightv();
						score1.setImageResource(R.drawable.point6);
						flag[6]=true;
						end[5]=false;
						}//if
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
				AssetManager am = getAssets();
				AssetFileDescriptor note7adf;
				private MediaPlayer mediaPlayer7;
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub				
					count[6] ++;					
					if(count[6]%2 == 1 && end[6])
					{						
						if(second){
							note[6].setImageResource(R.drawable.noteslight_7);						
						try
				        {
				     	   note7adf = am.openFd("low/low7.ogg");
				     	  mediaPlayer7 =new MediaPlayer();
				     	   mediaPlayer7.setDataSource(note7adf.getFileDescriptor(),
				     			   note7adf.getStartOffset(),note7adf.getLength());
				     	   mediaPlayer7.prepare();
				     	   mediaPlayer7.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}else{
							note[6].setImageResource(R.drawable.notelight_7);						
						try
				        {				     	   
				     	   note7adf = am.openFd("high/high7.ogg");
				     	  mediaPlayer7 =new MediaPlayer();
				     	   mediaPlayer7.setDataSource(note7adf.getFileDescriptor(),
				     			   note7adf.getStartOffset(),note7adf.getLength());
				     	   mediaPlayer7.prepare();
				     	   mediaPlayer7.start();
				        }
				        catch(IOException e)
				        {
				     	   e.printStackTrace();
				        }
						}					
			        if(second){
			        note[0].setImageResource(R.drawable.notes1);
				    note[2].setImageResource(R.drawable.notes3);
				   	note[3].setImageResource(R.drawable.notes4);
				   	note[4].setImageResource(R.drawable.notes5);
				   	note[5].setImageResource(R.drawable.notes6);
				    note[1].setImageResource(R.drawable.notes2);
				    count[0]=0;
				    count[2]=0;
				    count[3]=0;
				    count[4]=0;
				    count[5]=0;
				    count[1]=0;}
			        else
			        {note[0].setImageResource(R.drawable.note1);
				    note[2].setImageResource(R.drawable.note3);
				   	note[3].setImageResource(R.drawable.note4);
				   	note[4].setImageResource(R.drawable.note5);
				   	note[5].setImageResource(R.drawable.note6);
				    note[1].setImageResource(R.drawable.note2);
				    count[0]=0;
				    count[2]=0;
				    count[3]=0;
				    count[4]=0;
				    count[5]=0;
				    count[1]=0;}
			        }
					if(count[6]%2==0 && flag[6] && end[6])
			        {
						thirdLayout .addView(scorestar);
						if(second)
							{note[6].setImageResource(R.drawable.notes7);
							TranslateAnimation ani = new TranslateAnimation(0,-note[6].getX()+120*7,
									0,-(215+33*6));
							ani.setDuration(700);
							note[6].startAnimation(ani);
							ani.setFillAfter(true);
							}
						else{
						note[6].setImageResource(R.drawable.note7);						
						TranslateAnimation ani = new TranslateAnimation(0,-note[6].getX()+120*7,
								0,-(90+33*6));
						ani.setDuration(700);
						note[6].startAnimation(ani);
						ani.setFillAfter(true);}
						final Handler note7Handler = new Handler(){
							@Override
						public void handleMessage(Message msg) {
							// TODO Auto-generated method stub
							super.handleMessage(msg);
							if (msg.what == 0x7) {
								thirdLayout.removeView(notebackground[6]);
								rightv();
								score1.setX(110);
								score1.setY(40);
								score2.setX(160);
								score2.setY(40);
								score1.setImageResource(R.drawable.point7);								
								scorestarani.start();						
								end[6]=false;
	/***************音符上下跳动******************/	
								//七个音符上下跳动的补件动画		
final TranslateAnimation [] up2 = { new TranslateAnimation(-note[0].getX()+140, -note[0].getX()+140, -215, -235),
		new TranslateAnimation(-note[1].getX()+132*2, -note[1].getX()+132*2, -(215+33*1), -(215+33*1+30)),
		new TranslateAnimation(-note[2].getX()+125*3, -note[2].getX()+125*3,-(215+33*2), -(215+33*2+30)),
		new TranslateAnimation(-note[3].getX()+123*4, -note[3].getX()+123*4, -(215+33*3), -(215+33*3+30)),
		new TranslateAnimation(-note[4].getX()+122*5, -note[4].getX()+122*5, -(215+33*4), -(215+33*4+30)),
		new TranslateAnimation(-note[5].getX()+121*6, -note[5].getX()+121*6, -(215+33*5), -(215+33*5+30)),
		new TranslateAnimation(-note[6].getX()+120*7, -note[6].getX()+120*7, -(215+33*6), -(215+33*6+30))
        };
for (int i = 0; i < up2.length; i++) {
	up2[i].setFillAfter(true);
	up2[i].setDuration(250);
}
final TranslateAnimation [] down2 = {new TranslateAnimation(-note[0].getX()+140, -note[0].getX()+140,-235 ,-215 ),
				 new TranslateAnimation(-note[1].getX()+132*2, -note[1].getX()+132*2, -(215+33*1+30), -(215+33*1)),
				 new TranslateAnimation(-note[2].getX()+125*3, -note[2].getX()+125*3,-(215+33*2+30),- (215+33*2)),
				 new TranslateAnimation(-note[3].getX()+123*4, -note[3].getX()+123*4, -(215+33*3+30), -(215+33*3)),
				 new TranslateAnimation(-note[4].getX()+122*5, -note[4].getX()+122*5, -(215+33*4+30), -(215+33*4)),
				 new TranslateAnimation(-note[5].getX()+121*6, -note[5].getX()+121*6, -(215+33*5+30), -(215+33*5)),
				 new TranslateAnimation(-note[6].getX()+120*7, -note[6].getX()+120*7, -(215+33*6+30), -(215+33*6)),
				 };			
		for (int i = 0; i < down2.length; i++) {
			down2[i].setFillAfter(true);
			down2[i].setDuration(250);
		}
							final Handler upHandler1 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x1) {									
									if (second) 
									{
										note[0].startAnimation(up2[0]);
										note[0].setImageResource(R.drawable.noteslight_1);
										low[0].start();}
									else
										{
										note[0].startAnimation(up[0]);
										note[0].setImageResource(R.drawable.notelight_1);
										high[0].start();}
							final Handler downHandler1 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x11) {
									if (second) {
										note[0].startAnimation(down2[0]);
									} else {
										note[0].startAnimation(down[0]);
									}																		
							final Handler upHandler2 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x2) {
									if (second) {													
										low[1].start();
										note[0].setImageResource(R.drawable.notes1);
										note[1].startAnimation(up2[1]);
										note[1].setImageResource(R.drawable.noteslight_2);
									} else {
										high[1].start();
										note[0].setImageResource(R.drawable.note1);
										note[1].startAnimation(up[1]);
										note[1].setImageResource(R.drawable.notelight_2);
									}
							final Handler downHandler2 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x22)
									if (second) {
										note[1].startAnimation(down2[1]);	
									} else {
										note[1].startAnimation(down[1]);
									}									
							final Handler upHandler3 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if (msg.what == 0x3) {
										if (second) {														
											low[2].start();
											note[1].setImageResource(R.drawable.notes2);
											note[2].startAnimation(up2[2]);
											note[2].setImageResource(R.drawable.noteslight_3);
										} else {
											high[2].start();
											note[1].setImageResource(R.drawable.note2);
											note[2].startAnimation(up[2]);
											note[2].setImageResource(R.drawable.notelight_3);
										}
							final Handler downHandler3 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x33)
									if (second) {
										note[2].startAnimation(down2[2]);
									} else {
										note[2].startAnimation(down[2]);
									}									
							final Handler upHandler4 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if (msg.what == 0x4) {
										if (second) {
											low[3].start();
											note[2].setImageResource(R.drawable.notes3);
											note[3].startAnimation(up2[3]);
											note[3].setImageResource(R.drawable.noteslight_4);
										} else {
											high[3].start();
											note[2].setImageResource(R.drawable.note3);
											note[3].startAnimation(up[3]);
											note[3].setImageResource(R.drawable.notelight_4);
										}
							final Handler downHandler4 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x44)
									if (second) {
										note[3].startAnimation(down2[3]);
									} else {
										note[3].startAnimation(down[3]);
									}									
							final Handler upHandler5 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if (msg.what == 0x5) {
										if (second) {
											low[4].start();
											note[3].setImageResource(R.drawable.notes4);
											note[4].startAnimation(up2[4]);
											note[4].setImageResource(R.drawable.noteslight_5);
										} else {
											high[4].start();
											note[3].setImageResource(R.drawable.note4);
											note[4].startAnimation(up[4]);
											note[4].setImageResource(R.drawable.notelight_5);
										}
							final Handler downHandler5 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x55)
									if (second) {
										note[4].startAnimation(down2[4]);
									} else {
										note[4].startAnimation(down[4]);
									}
									
							final Handler upHandler6 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if (msg.what == 0x6) {
										if (second) {
											low[5].start();
											note[4].setImageResource(R.drawable.notes5);
											note[5].startAnimation(up2[5]);
											note[5].setImageResource(R.drawable.noteslight_6);
										} else {
											high[5].start();
											note[4].setImageResource(R.drawable.note5);
											note[5].startAnimation(up[5]);
											note[5].setImageResource(R.drawable.notelight_6);
										}
							final Handler downHandler6 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x66)
									if (second) {
										note[5].startAnimation(down2[5]);	
									} else {
										note[5].startAnimation(down[5]);
									}									
							final Handler upHandler7 = new Handler(){@Override
								public void handleMessage(Message msg) {
									// TODO Auto-generated method stub
									super.handleMessage(msg);
									if (msg.what == 0x7) {
										if (second) {
											low[6].start();
											note[5].setImageResource(R.drawable.notes6);
											note[6].startAnimation(up2[6]);
											note[6].setImageResource(R.drawable.noteslight_7);
										} else {
											high[6].start();
											note[5].setImageResource(R.drawable.note6);
											note[6].startAnimation(up[6]);
											note[6].setImageResource(R.drawable.notelight_7);
										}
							final Handler downHandler7 = new Handler(){@Override
							public void handleMessage(Message msg) {
								// TODO Auto-generated method stub
								super.handleMessage(msg);
								if (msg.what == 0x77)
									if (second) {
										note[6].startAnimation(down2[6]);
									} else {
										note[6].startAnimation(down[6]);
									}									
							
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler7.sendEmptyMessage(0x77);
								}
							}, 300);
										
									}
								}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler7.sendEmptyMessage(0x7);
								}
							}, 300);
							
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler6.sendEmptyMessage(0x66);
								}
							}, 300);
										
									}
								}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler6.sendEmptyMessage(0x6);
								}
							}, 300);
							
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler5.sendEmptyMessage(0x55);
								}
							}, 300);
										
									}
								}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler5.sendEmptyMessage(0x5);
								}
							}, 300);
							
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler4.sendEmptyMessage(0x44);
								}
							}, 300);
										
									}
								}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler4.sendEmptyMessage(0x4);
								}
							}, 300);
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler3.sendEmptyMessage(0x33);
								}
							}, 300);
										
									}
								}};
								new Timer().schedule(new TimerTask() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										upHandler3.sendEmptyMessage(0x3);
									}
								}, 300);
						}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler2.sendEmptyMessage(0x22);
								}
							}, 300);
									
								}
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler2.sendEmptyMessage(0x2);
								}
							}, 300);
								}
							}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									downHandler1.sendEmptyMessage(0x11);
								}
							}, 300);
						}
					}};
							new Timer().schedule(new TimerTask() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									upHandler1.sendEmptyMessage(0x1);
								}
							}, 900);
										
							}
						}};
						new Timer().schedule(new TimerTask() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								note7Handler.sendEmptyMessage(0x7);
							}
						}, 700);
			}//if
					if(count[6]%2==0 && flag[6] == false)
					{
						if(second)
							note[6].setImageResource(R.drawable.notes7);
						else
						note[6].setImageResource(R.drawable.note7);
						wrong();}
				}				
			});
      }});//gameplayer声音监听结束
	//进入最后一关
	high[6].setOnCompletionListener(new OnCompletionListener(){
			 MediaPlayer finishone = new MediaPlayer();			
			 	@Override
			 	public void onCompletion(MediaPlayer arg0) {
			 		// TODO Auto-generated method stub
			 		final AlphaAnimation finishAlphadis = new AlphaAnimation(1, 0);
			 		finishAlphadis.setDuration(1000);
			 		finishAlphadis.setFillAfter(true);
			 		final AlphaAnimation finishAlphashow = new AlphaAnimation(0, 1);
			 		finishAlphashow.setDuration(1);
			 		finishAlphashow.setFillAfter(true);
			 		note[6].setImageResource(R.drawable.note6);
			 		AssetManager finishAssetManager = getAssets();
			 		try {
			 			AssetFileDescriptor finishafd = finishAssetManager.openFd("raw/prizemp2.ogg");
			 			finishone.setDataSource(finishafd.getFileDescriptor(),
			 					finishafd.getStartOffset(),finishafd.getLength());
			 			finishone.prepare();
			 		} catch (IOException e) {
			 			// TODO Auto-generated catch block
			 			e.printStackTrace();
			 		}
			 		finishone.start();
			 		thirdLayout.removeView(scorestar);
			 		thirdLayout.addView(scor100);
			 		thirdLayout.addView(wordImageView);
			 		thirdLayout.addView(jingling);
			 		thirdLayout.addView(gift);
			 		thirdLayout.addView(fstar);			
			 		jingling.setX(190);
			 		jingling.setY(220);		
			 		wordImageView.setImageResource(R.drawable.text3);
			 		wordImageView.setX(200);
			 		wordImageView.setY(380);
			 		jingling.setImageResource(R.drawable.jingling3);
			 		jingling.setX(190);
			 		jingling.setY(150);		
			 		gift.setImageResource(R.drawable.gift2);
			 		gift.setX(630);
			 		gift.setY(200);		
			 		fstar.setX(630);
			 		fstar.setY(200);		
			 		finishstarani.start();		
			 		finishone.setOnCompletionListener(new OnCompletionListener() {
			 			
			 			MediaPlayer gameMediaPlayer = new MediaPlayer();
						@Override			
						public void onCompletion(MediaPlayer arg0) {
							// TODO Auto-generated method stub
							gamePlayer = new MediaPlayer();		
							AssetManager passAssetManager = getAssets();
							try {
								afd = passAssetManager.openFd("raw/pass.ogg");
								gameMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
								gameMediaPlayer.prepare();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							gameMediaPlayer.start();
							gameMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
								
								@Override
								public void onCompletion(MediaPlayer arg0) {
									// TODO Auto-generated method stub
									AssetManager  lowAssetManager = getAssets();
									try {
										afd = lowAssetManager.openFd("raw/tip_low");
										gamePlayer.setDataSource(afd.getFileDescriptor(), 
												afd.getStartOffset(), afd.getLength());
										gamePlayer.prepare();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									gamePlayer.start();
								}
							});
			 				scor100.startAnimation(finishAlphadis);
			 				jingling.startAnimation(finishAlphadis);
			 				gift.startAnimation(finishAlphadis);
			 				fstar.startAnimation(finishAlphadis);
			 				wordImageView.startAnimation(finishAlphadis);
			 				thirdLayout.removeView(fstar);
			 				//thirdLayout.removeView(scorestar);
			 				score1.setImageResource(R.drawable.point0);
			 				score1.setX(120);
			 				score1.setY(40);
			 				game.setImageResource(R.drawable.game_04);
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
			 							up[i].setFillAfter(false);
			 							down[i].setFillAfter(false);
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
			 						 //修改第四关的背景图片为低音图片
			 						 ImageView scene = (ImageView)findViewById(R.id.scene);
			 						 scene.setBackgroundResource(R.drawable.scenetwo_b);
			 						 lowImageView.setBackgroundResource(R.anim.low);
			 						 lowImageView.setX(15);
			 						 lowImageView.setY(170);
			 						 final AnimationDrawable lowaAnimation = (AnimationDrawable)lowImageView.getBackground();
			 						 lowaAnimation.start();
			 						 thirdLayout.addView(lowImageView);
			 						 //初始化七个音符的各个标志和添加七个音符的背景图
			 						 for (int k = 0; k < 7; k++) {
			 							 if( k==0 )
			 							 {flag[k] = true;
			 							 end[k]=true;
			 							count[k] = 0;}
			 							 else{
			 							flag[k]=false;
			 							end[k]=true;
			 							count[k] = 0;}			 							
			 							thirdLayout.addView(notebackground[k]);
			 							notebackground[k].setX(50+(k+1)*115);
			 							notebackground[k].setY(295-k*34+2);			 							
			 						}						
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
			}});
	low[6].setOnCompletionListener(new OnCompletionListener() {
	 MediaPlayer finishtwo = new MediaPlayer();
	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		note[6].setImageResource(R.drawable.notes7);
		final AlphaAnimation finishAlphadis = new AlphaAnimation(1, 0);
 		finishAlphadis.setDuration(1000);
 		finishAlphadis.setFillAfter(true); 		
		AssetManager finishAssetManager = getAssets();
		try {
			AssetFileDescriptor finish2afd = finishAssetManager.openFd("raw/prizemp3.ogg");
			finishtwo.setDataSource(finish2afd.getFileDescriptor(),
					finish2afd.getStartOffset(),finish2afd.getLength());				
			finishtwo.prepare();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finishtwo.start();
		thirdLayout.removeView(scorestar);
		jingling.setImageResource(R.drawable.jingling4);
		gift.setImageResource(R.drawable.gift3);
		wordImageView.setImageResource(R.drawable.text4);
		thirdLayout.removeView(scor100);
		thirdLayout.removeView(jingling);
		thirdLayout.removeView(gift);
		thirdLayout.removeView(wordImageView);
		thirdLayout.addView(scor100);
		thirdLayout.addView(jingling);
		thirdLayout.addView(gift);
		thirdLayout.addView(wordImageView);
		fstar.setX(630);
		fstar.setY(200);
		thirdLayout.addView(fstar);
		finishstarani.start();
		final Handler scencedis = new Handler()
		{
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what == 0x0)
					//thirdLayout.startAnimation(finishAlphadis);
				{MediaPlayer byeMediaPlayer = new MediaPlayer();
				AssetManager byeAssetManager = getAssets();
				try {
					AssetFileDescriptor finish2afd = byeAssetManager.openFd("raw/byebye.ogg");
					byeMediaPlayer.setDataSource(finish2afd.getFileDescriptor(),
							finish2afd.getStartOffset(),finish2afd.getLength());				
					byeMediaPlayer.prepare();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byeMediaPlayer.start();
				byeMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					
					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						finish();
					}
				});}
				
				}
		};
		new Timer().schedule(new TimerTask() {				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				scencedis.sendEmptyMessage(0x0);
			}
		}, 3500);

	}
});
	}
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
