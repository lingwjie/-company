package com.gwj.clothes;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener ,OnTouchListener{
	int snailspeed = 2;
	int clickword = 0 ;
	snail snail ;
	Button exit;
	float currentX , currentY ;//���ʵ�ǰλ��
	float initialX , initialY ;//���ʳ�ʼλ��
	float imageX  ,imageY  ;//���ʶ�ӦͼƬ��λ��
	static boolean direction = true; //true�� false��
	ImageView[] imageViews = new ImageView [6];
	ImageView[] words = new ImageView [6];
	int[] imageId = {R.id.view1 , R.id.view2 , R.id.view3 , 
			R.id.view4 , R.id.view5 , R.id.view6};
	int[] wordId = {R.id.word1 , R.id.word2 , R.id.word3 , 
			R.id.word4 , R.id.word5 , R.id.word6};
	Bitmap[] imagepic = new Bitmap[6] ;
	Bitmap[] wordpic = new Bitmap[6] ;
	static Bitmap[] pobBitmap = new Bitmap[8];
	analysisrealize analysisrealize = new analysisrealize();
	String pobpath = "mnt/sdcard/pobing";
	RelativeLayout main;//��������
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ�����ڱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ȫ����ʾ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		main = (RelativeLayout)findViewById(R.id.root);
		snail = (snail)findViewById(R.id.snail);
		snail.setX(0);
		
		exit = (Button)findViewById(R.id.quit);
		exit.setOnClickListener(this);
		snail.setOnClickListener(this);
		
		//�������ݣ���ȡ��ĿͼƬ
	    analysisrealize.getimage("mnt//sdcard//mfh.pin");
		for (int i = 0; i < imageViews.length; i++) {
			imageViews[i] = (ImageView) findViewById(imageId[i]);
			words[i] = (ImageView) findViewById(wordId[i]);
			imagepic[i] = BitmapFactory.decodeByteArray(constant.image[6][i][0], 0,
					constant.image[6][i][0].length);
			wordpic[i] = BitmapFactory.decodeByteArray(constant.image[6][i][1], 0,
					constant.image[6][i][1].length);
			imageViews[i].setImageBitmap(toGrayscale(imagepic[i]));
			words[i].setImageBitmap(wordpic[i]);
			words[i].setOnTouchListener(this);
		}
		
		/* 
	     * ���û��onClickListener����ô�������ƶ�ʱ�������ֻ��down�¼������������ᴥ�� 
	     * ���������ˣ���move��up�¼�������Ӧ   
	     */  
		for (int i = 0; i < words.length; i++) {
			words[i].setOnClickListener(new OnClickListener(){  

	        @Override  
	        public void onClick(View arg0) { }
	        });
		words[i].setOnTouchListener(this);
		}
		//��ȡpobͼƬ
		File pob = new File(pobpath);
	    File []files = pob.listFiles();
	    if (files == null) {
			System.out.println("hhhhhhhhhh");
		}
	    for (int i = 0; i < files.length; i++) {
	    	String name = files[i].getName();
		if (name.endsWith(".png")) {	    	
				pobBitmap[i] = BitmapFactory.decodeFile(pobpath + "/" + name);
			}
		}
		//��ţ��ǰ�˶�
		final Handler snailHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 0x11) {
					if (direction && snail.move) {
						if (snail.getX() >= 170) {
							direction = false;
						}
						snail.setX(snail.getX()+snailspeed);
					}
					else if(snail.move){
						if (snail.getX() <= 0) {
							direction = true;
						}
						snail.setX(snail.getX()-snailspeed);
					}					
				}
			}
		};
		new Timer().schedule(new TimerTask(){

			@Override
			public void run() {
				snailHandler.sendEmptyMessage(0x11);
			}}
		,0, 150);
	}
	@Override
	protected void onDestroy() 
	{
		android.os.Process.killProcess(android.os.Process.myPid());
	};
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();    

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.snail:
			if (snail.move) {
				snail.move = false;
				snail.maxindex = 24;
			}			
			break;
		case R.id.quit:
			finish();
			break;			
		}
	}
	@Override
	public boolean onTouch(View view, MotionEvent e) {
		switch (view.getId()) {
		case R.id.word1:
			clickword = 0;
			break;
		case R.id.word2:
			clickword = 1;
			break;
		case R.id.word3:
			clickword = 2;
			break;
		case R.id.word4:
			clickword = 3;
			break;
		case R.id.word5:
			clickword = 4;
			break;
		case R.id.word6:
			clickword = 5;
			break;			
		}
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			initialX = words[clickword].getX();
			initialY = words[clickword].getY();
			imageX = imageViews[clickword].getX();
			imageY = imageViews[clickword].getY();
			words[clickword].bringToFront();
			break;
		case MotionEvent.ACTION_MOVE:			 
			currentX = e.getRawX() - 80;
			currentY = e.getRawY() - 30;
			words[clickword].setX(currentX );
			words[clickword].setY(currentY );
			break;
		case MotionEvent.ACTION_UP:
			if ( currentX > imageX - 10 && currentX < imageX+140 &&
					currentY > imageY && currentY < imageY+160) 
			{
				final Pobing pobing = new Pobing(MainActivity.this,null);
				main .removeView(words[clickword]);
				pobing.setX(imageX -5);
				pobing.setY(imageY-20);
				main.addView(pobing);
				final Handler delete = new Handler(){
					@Override
					public void handleMessage(android.os.Message msg) {
						super.handleMessage(msg);
						if (msg.what == 0x11) {
							imageViews[clickword].setImageBitmap(imagepic[clickword]);
							main.removeView(pobing);
						}
					}};
					new Timer().schedule(new TimerTask(){@Override
					public void run() {
						delete.sendEmptyMessage(0x11);
					}}, 1200);
			}
			else {
				words[clickword].setX(initialX);
				words[clickword].setY(initialY);
			}
			break;
		}
		return false;
	}
	
}
