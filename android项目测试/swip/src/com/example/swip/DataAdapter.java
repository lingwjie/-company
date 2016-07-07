package com.example.swip;

import com.fortysevendeg.swipelistview.SwipeListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter
{
	private LayoutInflater Inflater; //得到一个LayoutInfalter对象用来导入布局
	 private SwipeListView mSwipeListView ;  
	public DataAdapter(Context context , SwipeListView swipeListView)
    {
    	 this.Inflater = LayoutInflater.from(context);
    	 mSwipeListView = swipeListView;
    }

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// Get a View that displays the data at the specified position in the data set.
		convertView = Inflater.inflate(R.layout.list_item, null);
		
		ImageView bg_white =(ImageView)convertView.findViewById(R.id.bg_white);
        bg_white.setImageResource(R.drawable.bg_white);
        ImageView img = (ImageView)convertView.findViewById(R.id.bg_versions);
        img.setImageResource(R.drawable.iv_book_info);
        ImageView book = (ImageView)convertView.findViewById(R.id.book); 
        book.setImageResource(R.drawable.iv_course_icon);
        

        //版本信息，年纪，科目内容
        TextView versions = (TextView)convertView.findViewById(R.id.version_text);
        versions.setText("人教版三年级上册数学");
        //第二列显示正在学习该课时的人数
        TextView learning_person = (TextView)convertView.findViewById(R.id.learning_person);
        learning_person.setText(20 + "");
        //显示“正在学习该课时 的人”
        TextView learning_course = (TextView)convertView.findViewById(R.id.learning_course);
        learning_course.setText("正在学习该课时的人");
        //第三列显示该学习的课时
        TextView course = (TextView)convertView.findViewById(R.id.course);
        course.setText("第一课时：有理数");
        //显示学习状态
        TextView learning_state = (TextView)convertView.findViewById(R.id.learning_state);
        learning_state.setText("未开启");
        //学习整个计划的人
        TextView allpersion = (TextView)convertView.findViewById(R.id.all_person);
        allpersion.setText(120+"也在学习该计划");
        
        return convertView;
	}
	
	@Override
	public int getCount() {
		// How many items are in the data set represented by this Adapter.(在此适配器中所代表的数据集中的条目数)
		return 6;
	}

	@Override
	public Object getItem(int arg0) {
		// Get the data item associated with the specified position in the data set.(获取数据集中与指定索引对应的数据项)
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// Get the row id associated with the specified position in the list.(取在列表中与指定索引对应的行id)
		return 0;
	}
	
}