package gwj.first.linkgame;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class drawlink {
	List<Point> linkinfo = new ArrayList<Point>();
	public List<Point> drawlink(Point point1, Point point2)
	{
		linkinfo.add(point1);
		linkinfo.add(point2);
		return linkinfo;
		
	}
	
	public List<Point> drawlink(Point point1, Point point2, Point point3)
	{
		linkinfo.add(point1);
		linkinfo.add(point2);
		linkinfo.add(point3);
		return linkinfo;
		
	}
	public List<Point> drawlink(Point point1, Point point2,Point point3 , Point point4)
	{
		linkinfo.add(point1);
		linkinfo.add(point2);
		linkinfo.add(point3);
		linkinfo.add(point4);
		return linkinfo;
		
	}
	
	// 返回连接集合
		public List<Point> getLinkPoints()
		{
			return linkinfo;
		}
}
