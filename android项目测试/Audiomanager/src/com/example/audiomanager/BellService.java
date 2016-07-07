package com.example.audiomanager;

import java.sql.Date;
import java.util.HashMap;

import android.R.integer;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class BellService extends Service{
	private static final String SMS_RECEIVED_ACTION  = "android.provider.Telephony.SMS_RECEIVED";
	//ÁåÉùÐòºÅ
	private static final int ONE_SMS = 1;
	private static final int TWO_SMS = 2;
	private static final int THERR_SMS = 3;
	private static final int FOUR_SMS = 4;
	private static final int FIVE_SMS = 5;
//	private static final int SIX_SMS = 6;

	private HashMap<Integer,Integer> bellMap;
	private Date lastSMSTime;
	private int currentBell;
	private boolean justStart=true;
	private AudioManager audioManager;
	private int currentMediaStatus;
	private int currentMediaMax;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(SMS_RECEIVED_ACTION);
		Log.e("cookie", "Service start");
		//×¢²á¼àÌý
		registerReceiver(messageReceiver, intentFilter);
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	

	private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action .equals(SMS_RECEIVED_ACTION)) {
				playBell(context, 0);
			}
		}
	};
	
	private void playBell(Context context ,int num){
		audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		currentMediaStatus = audioManager.getStreamVolume(audioManager.STREAM_MUSIC);
		currentMediaMax =audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(audioManager.STREAM_MUSIC, currentMediaMax, 0);
		MediaPlayer mediaPlayer =  MediaPlayer.create(context, getBellResource());
		mediaPlayer.setOnCompletionListener(new musicListener());
		mediaPlayer.start();
	}
	
	private class musicListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer mp) {
			mp.release();
			audioManager.setStreamVolume(audioManager.STREAM_MUSIC, currentMediaStatus, 0);
		}
		
	}
	
	private int getBellResource(){
		int preferenceInterval;
		long interval;
		Date curTime = new Date(System.currentTimeMillis());
		interval = curTime.getTime() - lastSMSTime.getTime();
		lastSMSTime = curTime;
		preferenceInterval = getPreferenceInterval();
		if (interval < preferenceInterval*60*1000 && !justStart) {
			currentBell++;
			if (currentBell >5) {
				currentBell = 5;
			}else {
				currentBell = 1;
			}					
		}
		justStart = false;
		return bellMap.get(currentBell);
	}
	private int getPreferenceInterval(){
		SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(this);
		int interval = Integer.valueOf(setting.getString("interval_config", "5"));
		return interval;
	}
}
