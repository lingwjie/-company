package comreadboy.serchfile;

import java.io.File;
import java.util.ArrayList;

import android.R.array;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	public static final String TAG = MainActivity.class.getSimpleName();
	private ListView videoListView;
	private listAdapter videoAdapter;
	private VideoInfo video;
	private ArrayList<File> files;
	private long[] videoSize;
	private ArrayList<File> videoFiles;
	/** 测试需要按钮 */
	private Button button1;
	private Button button2;
	private Button button3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		video = new VideoInfo();
		videoListView = (ListView) findViewById(R.id.videoListView);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		/** 获取SD卡中视频基础路径 */
		String path = Environment.getExternalStorageDirectory().getPath() + "/"
				+ "名师辅导班";
		Log.i(TAG, "路径 = " + path);

		/** 获取名师班下面的所有文件夹 */
		File searchVideo = new File(path);
		files = fileArrayToArrayList(searchVideo.listFiles());

		if (files != null) {
			
			/** 去掉名师风采文件夹 */
			for (int i = 0; i < files.size(); i++) {
				Log.i(TAG, "文件名 = " + files.get(i).getName());
				if (files.get(i).getName().equals("名师风采")) {
					files.remove(i);
				}
			}

			for (int i = 0; i < files.size(); i++) {
				Log.i(TAG, "文件名 = " + files.get(i).getName());
			}
		} else {
			Log.i(TAG, "空");
		}

		/** 获取名师辅导班子文件中的视频名称和视频文件路径 */
		if (files != null) {

			for (int i = 0; i < files.size(); i++) {
				videoFiles = fileArrayToArrayList(files.get(i).listFiles());
				videoSize = new long[videoFiles.size()];
				for (int k = 0; k < videoFiles.size(); k++) {
					String videoPath = videoFiles.get(k).getPath();
					videoSize[k] = videoFiles.get(k).length();
					Log.i(TAG, "视频路径为： " + videoPath);
					Log.i(TAG, "视频大小为： " + videoSize[k]);
				}

			}
		}

	}

	private ArrayList<File> fileArrayToArrayList(File[] files) {

		ArrayList<File> fileList = new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			fileList.add(files[i]);
		}
		return fileList;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			videoFiles = fileArrayToArrayList(files.get(0).listFiles());
			Log.i(TAG, "video1=====" + videoFiles.size());
			videoSize = new long[videoFiles.size()];
			for (int k = 0; k < videoFiles.size(); k++) {
				String videoPath = videoFiles.get(k).getPath();
				videoSize[k] = videoFiles.get(k).length();
				Log.i(TAG, "视频路径为： " + videoPath);
			}
			Log.i(TAG, videoFiles.toString());
			// 根据按钮点击显示listView
			video.setdata(videoFiles, videoSize);
			videoAdapter = new listAdapter(this);
			videoAdapter.setVideo(video);
			videoListView.setAdapter(videoAdapter);

			break;
		case R.id.button2:
			videoFiles = fileArrayToArrayList(files.get(1).listFiles());
			Log.i(TAG, "video1=====" + videoFiles.size());
			videoSize = new long[videoFiles.size()];
			for (int k = 0; k < videoFiles.size(); k++) {
				String videoPath = videoFiles.get(k).getPath();
				videoSize[k] = videoFiles.get(k).length();
				Log.i(TAG, "视频路径为： " + videoPath);
			}
			Log.i(TAG, "video2=====" + videoFiles.size());
			// 根据按钮点击显示listView
			video.setdata(videoFiles, videoSize);
			videoAdapter = new listAdapter(this);
			videoAdapter.setVideo(video);

			videoListView.setAdapter(videoAdapter);

			break;
		case R.id.button3:
			videoFiles = fileArrayToArrayList(files.get(2).listFiles());
			Log.i(TAG, "video1=====" + videoFiles.size());
			videoSize = new long[videoFiles.size()];
			for (int k = 0; k < videoFiles.size(); k++) {
				String videoPath = videoFiles.get(k).getPath();
				videoSize[k] = videoFiles.get(k).length();
				Log.i(TAG, "视频路径为： " + videoPath);
			}
			Log.i(TAG, "video3========" + videoFiles.size());
			// 根据按钮点击显示listView
			video.setdata(videoFiles, videoSize);
			videoAdapter = new listAdapter(this);
			videoAdapter.setVideo(video);
			videoListView.setAdapter(videoAdapter);

			break;
		default:
			break;
		}
	}
}
