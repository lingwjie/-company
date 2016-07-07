package comreadboy.serchfile;

import java.io.File;
import java.util.ArrayList;

import android.util.Log;

public class VideoInfo {
	
	public String[] courseName;
	public long[] videoSize;
	public VideoInfo(){
		
	}
	public VideoInfo(String[] courseName, long[] videoSize){
		this.courseName = courseName;
		this.videoSize = videoSize;
	}
	public void setdata(ArrayList<File>files , long[] videoSize ){
		this.courseName = new String[files.size()];
		this.videoSize = new long[files.size()];
		String name;
		for (int i = 0; i < files.size(); i++) {
			name = files.get(i).getName();
			int nameEnd = name.lastIndexOf(".");
			courseName[i] = name.substring(0, nameEnd);
			
		}		
		this.videoSize = videoSize;
	}
	
}
