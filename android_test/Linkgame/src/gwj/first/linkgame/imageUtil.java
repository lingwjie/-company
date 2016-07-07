package gwj.first.linkgame;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class imageUtil {
	private static List<Integer> imagevalues = getimagevalues();
	private static List<Integer> getimagevalues() {
		Field[] drawFields =  R.drawable.class.getFields();
		List<Integer> resourceValues = new ArrayList<Integer>();
		for(Field field : drawFields)
		{
		if (field.getName().indexOf("p_") != -1)
		{
			try {
				resourceValues.add(field.getInt(R.drawable.class));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
	}
	return resourceValues;
	}
	
	public static List<Integer> getrandomvalue(List<Integer>sourceList , int size)
	{
		Random random = new Random();
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			try {
				int index = random.nextInt(sourceList.size());
				Integer image = sourceList.get(index);
				result.add(image);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return result;
		
	}
	public static List<Integer> getpalyvalues (int size)
	{
		if (size % 2 == 1) {
			size++;
		}
		List<Integer> playimagevalues = getrandomvalue(imagevalues, size/2);
		playimagevalues.addAll(playimagevalues);
		Collections.shuffle(playimagevalues);
		return playimagevalues;
	}
	
	public static List<pieceImage> getplayimage(Context context,
			int size) {
		
		List<Integer> resourceValues = getpalyvalues(size);
		List<pieceImage> result = new ArrayList<pieceImage>();
		for (Integer value : resourceValues) {
			Bitmap bm = BitmapFactory.decodeResource(context.getResources(), value);
			pieceImage pieceImage = new pieceImage(bm, value);
			result.add(pieceImage);
		}
		return result;
	}
	public static Bitmap getselectimage(Context context)
	{
		Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.selected);
		return bm;
		
	}
}
