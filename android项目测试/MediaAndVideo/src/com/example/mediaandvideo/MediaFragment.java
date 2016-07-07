package com.example.mediaandvideo;

import java.io.IOException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MediaFragment extends Fragment implements OnClickListener{
	private Button mediaPlayerButton , assetButton , storeButton ,networkButton;
	private View rooterView;
	private MediaPlayer mediaPlayer;
	private Context context;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rooterView = inflater.inflate(R.layout.fragment_media, container, false);
		context = inflater.getContext();
		mediaPlayerButton = (Button)rooterView.findViewById(R.id.mediaPlayer);
		assetButton = (Button)rooterView.findViewById(R.id.Assets);
		storeButton = (Button)rooterView.findViewById(R.id.external_store);
		networkButton = (Button)rooterView.findViewById(R.id.network);
		mediaPlayerButton.setOnClickListener(this);
		assetButton.setOnClickListener(this);
		storeButton.setOnClickListener(this);
		networkButton.setOnClickListener(this);
		return rooterView;
	}
	@Override
	public void onDetach() {
		super.onDetach();
	}
	@Override
	public void onDestroy() {
		
		super.onDestroy();
		Log.i("", "onDestroy");
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			
		}

	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mediaPlayer:
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
			mediaPlayer = MediaPlayer.create(context, R.raw.high1);
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mediaPlayer.start();
			break;
		case R.id.Assets:
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
			AssetManager assetManager = context.getAssets();
			try {
				AssetFileDescriptor afd = assetManager.openFd("high/high2.ogg");
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case R.id.external_store:
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
			String path = Environment.getExternalStorageDirectory().getPath();
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(path+"/‘§÷∆“Ù∆µ/see-You.mp3");				
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		case R.id.network:
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
			Uri uri =  Uri.parse("");//Õ¯…œ“Ù∆µµƒµÿ÷∑
			mediaPlayer = new MediaPlayer();
			try {
				mediaPlayer.setDataSource(context,uri);
				mediaPlayer.prepare();
				mediaPlayer.start();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		default:
			break;
		}
		
	}
	
	
	

}
