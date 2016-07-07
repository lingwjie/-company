package comreadboy.serchfile;

import java.math.BigDecimal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class listAdapter extends BaseAdapter{
	private static final String TAG = listAdapter.class.getSimpleName();
	
	private VideoInfo video;
	private LayoutInflater inflater;
	
	public listAdapter(Context context){
		this.inflater = LayoutInflater.from(context);
	}
	public void setVideo(VideoInfo video) {
		this.video = video;
	}
	@Override
	public int getCount() {
		if (video.courseName != null) {
			return video.courseName.length;
		}
		else {
			return 0;
		}
	}

	@Override
	public VideoInfo getItem(int position) {
		
		return video;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.listitem, null);
			holder.courseNum = (TextView) convertView.findViewById(R.id.courseNum);
			holder.courseName = (TextView)convertView.findViewById(R.id.courseName);
			holder.videoSize = (TextView)convertView.findViewById(R.id.videoSize);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.courseNum.setText(""+(position+1) );
		holder.courseName.setText( video.courseName[position]);
		BigDecimal size = new BigDecimal((double)video.videoSize[position]/(1024*1024));
		holder.videoSize.setText(size.setScale(2, BigDecimal.ROUND_HALF_UP) + " MB");
		return convertView;
	}
	private class ViewHolder{
		private TextView courseNum;
		private TextView courseName;
		private TextView videoSize;
		
	}

}
