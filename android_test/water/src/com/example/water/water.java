package com.example.water;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class water extends Thread implements Runnable {

	boolean flag1 = false;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			water.sleep(7490);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainActivity.flag = false;
	}

}
