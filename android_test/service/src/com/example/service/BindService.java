package com.example.service;

import android.R.integer;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service{

	private int count = 0;
	private boolean quit;
	//����onbind���ض���
	private MyBinder binder = new MyBinder();
	public class MyBinder extends Binder
	{
		public int getCount()
		{return count;}
	}
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("service id binded");
		return binder;
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.print("service is created");
		new Thread()
		{
			@Override
			public void run()
			{
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
					}
					count++;
				}
			}
		}.start();
	}
	//�Ͽ������ӻص��÷���
	@Override
	public boolean onUnbind(Intent intent)
	{
		System.out.println("service is Unbinded");
		return true;}
	//�ر�ǰ����
	@Override
	public void onDestroy(){
		//unbindService(MainActivity.connection);
		super.onDestroy();
		this.quit = true;
		System.out.println("service is destroyed");
	}

}
