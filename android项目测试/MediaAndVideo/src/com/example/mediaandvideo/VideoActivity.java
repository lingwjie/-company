package com.example.mediaandvideo;

import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity{
	VideoView videoView;
	MediaController mController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSPARENT);//什么意思
		setContentView(R.layout.activity_video);
		videoView = (VideoView)findViewById(R.id.video);
		mController = new MediaController(this);
		
		File video = new File(Environment.getExternalStorageDirectory().getPath()+"/预制视频/Test.mp4");
		if (video.exists()) {
			videoView.setVideoPath(video.getAbsolutePath());
			videoView.setMediaController(mController);
			mController.setMediaPlayer(videoView);
			videoView.requestFocus();
		}
		
	}
	

}
