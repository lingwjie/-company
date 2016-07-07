package com.gwj.test;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.activation.FileDataSource;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;

public class MainActivity extends Activity {	
	Button start ;
	byte[] a = new byte[6001];
	MediaPlayer mediaPlayer = new MediaPlayer();
	File temp = new File("mnt//sdcard//jjj.ogg");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        start = (Button)findViewById(R.id.button1);
        File file = new File("mnt//sdcard//click.ogg");
        try {
			RandomAccessFile in = new RandomAccessFile(file, "r");
			in.read(a);		 
			
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
        try {
				mediaPlayer .setDataSource("mnt//sdcard//jjj.ogg");
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        
        test an = new test();
        an.analysiswordSndAddr("mnt//sdcard//mfh.pin");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(temp);
			fos.write(constant.sound);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 	   
        
        
        start.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				//play(constant.sound);
				mediaPlayer.start();
			}
		});
    }
   public void play(byte[] m)
   {
	   File temp;
	try {
		temp = File.createTempFile("word", "ogg" , getCacheDir());	
	   temp.deleteOnExit();
	   FileOutputStream fos = new FileOutputStream(temp);
	   fos.write(m);
	   MediaPlayer mediaPlayer = new MediaPlayer();
	   
	   FileInputStream fis = new FileInputStream(temp);
	   mediaPlayer.setDataSource(temp.getPath());
	   mediaPlayer.prepare();
	   mediaPlayer.start();
	   } catch (IOException e) {
		e.printStackTrace();
	}
   }
}
