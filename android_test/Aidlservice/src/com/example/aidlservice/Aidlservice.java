package com.example.aidlservice;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.view.Menu;
import android.view.MenuItem;

public class Aidlservice extends Service{
	private CatBinder catbinder;
	Timer timer = new Timer();
	String [] Colors = {"红色","黑色","黄色"};
	double [] weights = {2.3 , 3.1 , 1.58};
	private String color;
	private double weight;
	//继承stub就视线里icat和ibinder接口
	public class CatBinder extends com.example.aidlservice.ICat.Stub
	{

		@Override
		public String getColor() throws RemoteException {
			// TODO Auto-generated method stub
			return color;
		}

		@Override
		public double getWeight() throws RemoteException {
			// TODO Auto-generated method stub
			return weight;
		}
		
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		catbinder = new CatBinder();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int rand = (int)(Math.random()*3);
				color = Colors[rand];
				weight = weights[rand];
			}
		}, 0, 800);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		
		return catbinder;
	}
	@Override
	public void onDestroy()
	{
		timer.cancel();
	}
	
    
}
