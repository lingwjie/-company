package com.example.mediaandvideo;

import java.util.HashMap;

import android.R.integer;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SoundPoolFragment extends Fragment implements OnClickListener{
	private Button start1 ,start2 , start3 ,stop;
	private Button pause1,pause2, pause3 ,release;
	private View view;
	private SoundPool soundPool;
	private Context context;
	int streamId1 = 0,streamId2 = 0,streamId3 = 0;
	private HashMap<Integer, Integer> soundmap = new HashMap<Integer, Integer>();
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_soundpool, container, false);
		context = inflater.getContext();
		start1 = (Button)view.findViewById(R.id.button1);
		start2 = (Button)view.findViewById(R.id.button2);
		start3 = (Button)view.findViewById(R.id.button3);
		pause1 = (Button)view.findViewById(R.id.pause1);
		pause2 = (Button)view.findViewById(R.id.pause2);
		pause3 = (Button)view.findViewById(R.id.pause3);
		release = (Button)view.findViewById(R.id.release);
		stop = (Button)view.findViewById(R.id.stop);
		soundPool =  new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		soundmap.put(1, soundPool.load(context, R.raw.high1, 1));
		soundmap.put(2, soundPool.load(context, R.raw.high2, 1));
		soundmap.put(3, soundPool.load(context, R.raw.high3, 1));
		start1.setOnClickListener(this);
		start2.setOnClickListener(this);
		start3.setOnClickListener(this);
		pause1.setOnClickListener(this);
		pause2.setOnClickListener(this);
		pause3.setOnClickListener(this);
		release.setOnClickListener(this);
		stop.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.button1:
			streamId1 = soundPool.play(soundmap.get(1), 1, 1, 0, -1, 1);
			break;
		case R.id.button2:
			streamId2 = soundPool.play(soundmap.get(2), 1, 1, 0, -1, 1);
			break;
		case R.id.button3:
			streamId3 = soundPool.play(soundmap.get(3), 1, 1, 0, -1, 1);
			break;
		case R.id.pause1:
			soundPool.pause(streamId1);
			break;
		case R.id.pause2:
			soundPool.pause(streamId2);
			break;
		case R.id.pause3:
			soundPool.pause(streamId3);
			break;
		case R.id.release:	
			//释放整个soundpool资源，不能再使用
			soundPool.release();
			break;
		case R.id.stop:
			//停止之后还可以继续
			soundPool.stop(streamId1);
			soundPool.stop(streamId2);
			soundPool.stop(streamId3);
		default:
			break;
		}
		
	}
	

}
