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
	private LayoutInflater Inflater; //�õ�һ��LayoutInfalter�����������벼��
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
        

        //�汾��Ϣ����ͣ���Ŀ����
        TextView versions = (TextView)convertView.findViewById(R.id.version_text);
        versions.setText("�˽̰����꼶�ϲ���ѧ");
        //�ڶ�����ʾ����ѧϰ�ÿ�ʱ������
        TextView learning_person = (TextView)convertView.findViewById(R.id.learning_person);
        learning_person.setText(20 + "");
        //��ʾ������ѧϰ�ÿ�ʱ ���ˡ�
        TextView learning_course = (TextView)convertView.findViewById(R.id.learning_course);
        learning_course.setText("����ѧϰ�ÿ�ʱ����");
        //��������ʾ��ѧϰ�Ŀ�ʱ
        TextView course = (TextView)convertView.findViewById(R.id.course);
        course.setText("��һ��ʱ��������");
        //��ʾѧϰ״̬
        TextView learning_state = (TextView)convertView.findViewById(R.id.learning_state);
        learning_state.setText("δ����");
        //ѧϰ�����ƻ�����
        TextView allpersion = (TextView)convertView.findViewById(R.id.all_person);
        allpersion.setText(120+"Ҳ��ѧϰ�üƻ�");
        
        return convertView;
	}
	
	@Override
	public int getCount() {
		// How many items are in the data set represented by this Adapter.(�ڴ�������������������ݼ��е���Ŀ��)
		return 6;
	}

	@Override
	public Object getItem(int arg0) {
		// Get the data item associated with the specified position in the data set.(��ȡ���ݼ�����ָ��������Ӧ��������)
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// Get the row id associated with the specified position in the list.(ȡ���б�����ָ��������Ӧ����id)
		return 0;
	}
	
}