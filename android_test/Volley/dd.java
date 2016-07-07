import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.GridView;
import android.widget.Toast;

import com.readboy.plan.R;
import com.readboy.plan.PageAllCourse.MyItemClickListener;
import com.readboy.plan.PageAllCourse.b;
import com.readboy.plan.adapter.CourseInfoAdapter;
import com.readboy.plan.service.CourseService;

b BroadcastReceiver = new b();  
        
        if (BroadcastReceiver == null) {
			BroadcastReceiver = new b();
		}
        intentFilter = new IntentFilter();
        intentFilter.addAction("org.crazyit.action.CRAZY_BROADCA");
		context.registerReceiver(BroadcastReceiver, intentFilter);
		
		
		
		class b extends BroadcastReceiver{

			@Override
			public void onReceive(Context context, Intent intent) {
				if(intent.getAction().equals("org.crazyit.action.CRAZY_BROADCA")){
					grid = (GridView) view.findViewById(R.id.gv_book_info);
					CourseInfoAdapter adapter = new CourseInfoAdapter(context);
					courses = (new CourseService(context)).getCourses();
					adapter.setLists(courses);
					grid.setAdapter(adapter);
					grid.setOnItemClickListener(new MyItemClickListener());
					Toast.makeText(context, "hhh", 1000);
		        } 
			}}