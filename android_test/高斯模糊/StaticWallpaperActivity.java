package com.dream.theme.wallpaper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.internal.view.menu.ActionMenuItemView.PopupCallback;
import com.dream.theme.Netroid;
import com.dream.theme.R;
import com.dream.theme.SelfImageLoader;
import com.dream.theme.data.Theme;
import com.dream.theme.utils.Constant;
import com.dream.theme.utils.FastBlur;
import com.dream.theme.utils.ToastUtils;
import com.dream.theme.utils.Utils;
import com.dream.theme.widget.HorizontalListView;
import com.mobile.netroid.image.NetworkImageView;

//import android.view.SurfaceControl;

public class StaticWallpaperActivity extends Activity implements
		OnClickListener, OnItemClickListener {

	private static final String TAG = "StaticWallpaperActivity";
	private static final String FOLDER_NAME = "static_wallpaper/";

	private Context context;
	private PopupWindow popWnd;
	
	private ImageButton mBtnBack;
	private HorizontalListView mHListView;
	private HorizontalListAdapter mHAdapter;
	private Button mBtnSetWallpaper;
	private ImageView mFrostImage, mStaticImage;

	private Bitmap mImageBitmap;

	private ArrayList<String> mWallpaperList;

	private View mEmptyView;
	private TextView mEmptyText;

	private View mProgressContainer;

	private LayoutInflater mInflater;

	private int mSelectedItem = -1;

	private ProgressDialog mProgressDialog;

	private static final int SCAN_THEME_FINISH = 0x222;
	private static final int SET_WALLPAPER_SUCCESS = 0x333;
	private static final int SET_WALLPAPER_FAILURE = 0x444;
	
	private Handler mHandler = new UIHandler();

	private class UIHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == SCAN_THEME_FINISH) {
				updateProgressBarStatus(false);
				if (mWallpaperList == null || mWallpaperList.size() == 0) {
					mEmptyView.setVisibility(View.VISIBLE);
				} else {
					clickItem(0);
				}
			} else if (msg.what == SET_WALLPAPER_SUCCESS) {
				if (mProgressDialog != null) {
					mProgressDialog.dismiss();
				}
				String text = getString(R.string.set_static_wallpaper_success);
				ToastUtils.showMessage(StaticWallpaperActivity.this, text);
			} else if (msg.what == SET_WALLPAPER_FAILURE) {
				if (mProgressDialog != null) {
					mProgressDialog.dismiss();
				}
				String text = getString(R.string.set_static_wallpaper_failure);
				ToastUtils.showMessage(StaticWallpaperActivity.this, text);
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// SurfaceControl.screenshot();
		context = this;

		mInflater = LayoutInflater.from(this);

		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		// getWindow().setFlags(
		// WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
		// WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// View decorView = getWindow().getDecorView();
		// decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
		// | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

		setContentView(R.layout.activity_static_wallpaper);

		mBtnBack = (ImageButton) findViewById(R.id.static_wallpaper_back);
		mBtnBack.setOnClickListener(this);

		mHListView = (HorizontalListView) findViewById(R.id.horizontallist);
		mHListView.setOnItemClickListener(this);
		mHAdapter = new HorizontalListAdapter();
		mHListView.setAdapter(mHAdapter);

		mFrostImage = (ImageView) findViewById(R.id.frost_image);
		mStaticImage = (ImageView) findViewById(R.id.static_image);

		mBtnSetWallpaper = (Button) findViewById(R.id.setWallpaper);
		mBtnSetWallpaper.setOnClickListener(this);

		mEmptyView = findViewById(R.id.emptyView);
		mEmptyText = (TextView) findViewById(R.id.emptyText);
		String title = getString(R.string.wallpaper);
		String info = getString(R.string.unavailable_no_theme, title);
		mEmptyText.setText(info);

		mProgressContainer = findViewById(R.id.progress_container);

//		showPopUpWindow();
		findWallpapers();
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		// overridePendingTransition(R.anim.side_in_left,
		// R.anim.side_out_right);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mImageBitmap != null && !mImageBitmap.isRecycled()) {
			mImageBitmap.recycle();
			mImageBitmap = null;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.static_wallpaper_back:
			onBackPressed();
			break;
		case R.id.setWallpaper:
			showPopUpWindow();
//			setWallpaper();
			break;
		case R.id.btn_desktop:
			setWallpaper(0);
			break;
		case R.id.btn_lockview:
			setWallpaper(1);
			break;
		default:
			break;
		}
	}

	private void showPopUpWindow() {
		// TODO Auto-generated method stub
		Log.e("show pop up window","show pop up window");
		View contentView = LayoutInflater.from(StaticWallpaperActivity.this).inflate(R.layout.static_wallpaper_setting_popupwindow, null);
		if(popWnd == null)
			popWnd = new PopupWindow(context);
		popWnd.setContentView(contentView);
		popWnd.setWidth(800);
		popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		
		popWnd.showAtLocation(mBtnSetWallpaper, Gravity.TOP, 0, 1320);
		popWnd.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
		Button deskTop = (Button) contentView.findViewById(R.id.btn_desktop);
		deskTop.setOnClickListener(this);
		Button lockView = (Button) contentView.findViewById(R.id.btn_lockview);
		lockView.setOnClickListener(this);
	}


	void applyBlur(final View image, final View frost) {
		image.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					@Override
					public boolean onPreDraw() {
						image.getViewTreeObserver().removeOnPreDrawListener(
								this);
						image.buildDrawingCache();

						Bitmap bmp = image.getDrawingCache();
						jniBlurArray(bmp, frost);
						return true;
					}
				});
	}

	void jniBlurArray(Bitmap bkg, View view) {
		long startMs = System.currentTimeMillis();
		float scaleFactor = 8;
		float radius = 20;

		int vw = view.getMeasuredWidth();
		int vh = view.getMeasuredHeight();
		int width = (int) (vw / scaleFactor);
		int height = (int) (vh / scaleFactor);
		Bitmap overlay = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(overlay);
		float dx = -view.getLeft() / scaleFactor;
		float dy = -view.getTop() / scaleFactor;
		canvas.translate(dx, dy);
		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
		Paint paint = new Paint();
		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
		canvas.drawBitmap(bkg, 0, 0, paint);

		overlay = FastBlur.doBlurJniArray(overlay, (int) radius, true);
		view.setBackground(new BitmapDrawable(getResources(), overlay));
		long useTime = System.currentTimeMillis() - startMs;
		Log.e(TAG, "jniBlur time=" + useTime + "ms");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(popWnd != null && popWnd.isShowing()) 
			popWnd.dismiss();
		return true;
	}


	private void setWallpaper(final int flag) {
		Log.e("show pop up window","show pop up window ----- setWallpaper");
		createProgressDialog(getString(R.string.wallpaper_setting));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					WallpaperManager wallpaper = WallpaperManager
							.getInstance(StaticWallpaperActivity.this);
					if(flag == 0)
						wallpaper.setBitmap(mImageBitmap);
					else if(flag == 1)
						wallpaper.setBitmapKeyguard(mImageBitmap);
					mHandler.sendEmptyMessage(SET_WALLPAPER_SUCCESS);
				} catch (Exception e) {
					Log.v(TAG, "setWallpaper err : " + e);
					mHandler.sendEmptyMessage(SET_WALLPAPER_FAILURE);
				}
			}
		}).start();
	}

	private void createProgressDialog(CharSequence message) {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mProgressDialog.setMessage(message);
			mProgressDialog.setCancelable(false);
		}
		mProgressDialog.show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		clickItem(position);
	}

	private void clickItem(int position) {
		if (mSelectedItem == position) {
			return;
		}
		mSelectedItem = position;
		mHAdapter.notifyDataSetChanged();
		long startMs = System.currentTimeMillis();
		String url = mWallpaperList.get(position);
		if (mImageBitmap != null && !mImageBitmap.isRecycled()) {
			mImageBitmap.recycle();
			mImageBitmap = null;
		}
//		mImageBitmap = WallpaperUtil.cropWallpaper(this, url);
//		BitmapDrawable drawable = new BitmapDrawable(getResources(),
//				mImageBitmap);
//		Log.e(TAG, "url = " + url + ", drawable = " + drawable + ", mImageBitmap = " + mImageBitmap);
		InputStream is = null;
		try {
			url = url.replace(SelfImageLoader.RES_ASSETS, "");
			is = getAssets().open(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mImageBitmap = WallpaperUtil.cropWallpaper2(this, is);
//		mImageBitmap = BitmapFactory.decodeStream(is);
		BitmapDrawable drawable = new BitmapDrawable(getResources(),
				mImageBitmap);
		Log.e(TAG, "url = " + url + ", drawable = " + drawable + ", mImageBitmap = " + mImageBitmap);
		mStaticImage.setBackground(drawable);
		mFrostImage.setBackground(drawable);
		applyBlur(mFrostImage, mFrostImage);
		System.gc();
		long useTime = System.currentTimeMillis() - startMs;
		Log.e(TAG, "clickItem useTime=" + useTime + "ms");
		try {
			if(is != null)
				is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateProgressBarStatus(boolean show) {
		if (show) {
			mProgressContainer.setVisibility(View.VISIBLE);
		} else {
			mProgressContainer.setVisibility(View.GONE);
		}
	}

	private void findWallpapers() {
		updateProgressBarStatus(true);
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (mWallpaperList == null) {
					mWallpaperList = new ArrayList<String>();
				} else {
					mWallpaperList.clear();
				}
				String ext = ".png";
				File file = new File(Constant.DEFAULT_FOLDER);
				/*if (file != null && file.exists()) {
					scanFolder(file, ext);
				}
				file = new File(
						Constant.THEME_STORAGE_PATH[Theme.TYPE_WALLPAPER]);
				if (file != null && file.exists()) {
					scanFolder(file, ext);
				}*/
				scanFolder(file, ext);
				mHandler.sendEmptyMessage(SCAN_THEME_FINISH);
			}
		}).start();
	}

	private void scanFolder(File file, String ext) {
		Utils.printe("ThemeApplication", "scanFolder" + " file = " + file + ",ext = " + ext);
		/*if (file != null && file.isDirectory()) {
			File[] listFile = file.listFiles();
			if (listFile != null) {
				for (int i = 0; i < listFile.length; i++) {
					scanFolder(listFile[i], ext);
				}
			}
		} else {
			String filePath = file.getAbsolutePath();
			if (filePath.endsWith(ext)) {
				mWallpaperList.add(filePath);
			}
		}*/
		try {
			String[] list = getAssets().list("static_wallpaper");
			Utils.printe("ThemeApplication", "getAssets" + " list= " + list + ",len = " + list.length);
			for (int i = 0; i < list.length; i++) {
				mWallpaperList.add(SelfImageLoader.RES_ASSETS + FOLDER_NAME + list[i]);
				Utils.printe("ThemeApplication", "getAssets" + " file = " + list[i] + ",ext = " + ext);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private class HorizontalListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (mWallpaperList == null) {
				return 0;
			}
			return mWallpaperList.size();
		}

		@Override
		public Object getItem(int position) {
			if (mWallpaperList == null) {
				return null;
			}
			return mWallpaperList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder;
			if (convertView == null) {
				view = mInflater.inflate(R.layout.static_wallpaper_item, null);
				holder = new ViewHolder();
				holder.image = (NetworkImageView) view
						.findViewById(R.id.wallpaper_image);
				holder.frame = view.findViewById(R.id.wallpaper_frame);
				view.setTag(holder);
			} else {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}
			if (mSelectedItem == position) {
				holder.frame.setSelected(true);
			} else {
				holder.frame.setSelected(false);
			}
			holder.image.setDefaultImageResId(R.drawable.default_wallpaper_bg);
			String url = mWallpaperList.get(position);
//			String url = SelfImageLoader.RES_ASSETS + "static_wallpaper/"
//					+ "wallpaper10.png";
			Netroid.displayImage(url, holder.image);
			return view;
		}
	}

	class ViewHolder {
		public NetworkImageView image;
		public View frame;
	}

}
