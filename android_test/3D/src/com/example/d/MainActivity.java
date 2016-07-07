package com.example.d;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.app.Activity;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }}
class MyRenderer implements Renderer {

	float [] triangleData = new float[] {
			0.1f , 0.6f , 0.0f,//上顶点
			-0.3f , 0.0f , 0.0f,//左顶点
			0.3f , 0.1f , 0.0f//右顶点
			};
	int [] triangleColor  = new int []{
			65535 , 0 , 0 , 0,//上红色
			0 ,65535 ,0 , 0,//左绿色
			0 ,0 ,65535 , 0//右蓝色
	};
	float [] rectDate = new float []{
			0.4f , 0.4f , 0.0f,//右上顶点
			0.4f , -0.4f , 0.0f,//右下顶点
			-0.4f , 0.4f , 0.0f,//左上顶点
			-0.4f , -0.4f , 0.0f,//左下顶点
	};
	int [] rectColor = new int []{
			0,65535,0,0,
			0,0,65535,0,
			65535,0 , 0 ,0 ,
			65535 , 65535 , 0 , 0 			
	};
	
	float [] rectDate2 = new float []{
			-0.4f , 0.4f , 0.0f,//左上顶点
			0.4f , 0.4f , 0.0f,//右上顶点
			0.4f , -0.4f , 0.0f,//右下顶点
			-0.4f , -0.4f , 0.0f,//左下顶点
	};
	float [] pentacle = new float[] {
			0.4f ,0.4f , 0.0f,
			-0.2f , 0.3f , 0.0f,
			0.5f , 0.0f , 0f ,
			-0.4f , 0.0f , 0f ,
			-0.1f , -0.3f , 0f ,
	};
	FloatBuffer triangleDataBuffer ;
	IntBuffer triangleColorBuffer;
	FloatBuffer rectDataBuffer;
	IntBuffer rectColorBuffer;
	FloatBuffer rectDataBuffer2;
	FloatBuffer pentacleBuffer;
	public MyRenderer(){
		//将顶点位置数据数组包装成flaotbuffer
		triangleDataBuffer = FloatBuffer.wrap(triangleData);
		rectDataBuffer = FloatBuffer.wrap(rectDate);
		rectDataBuffer2 = FloatBuffer.wrap(rectDate2);
		pentacleBuffer = FloatBuffer.wrap(pentacle);
		triangleColorBuffer = IntBuffer.wrap(triangleColor);
		rectColorBuffer = IntBuffer.wrap(rectColor);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		//清空屏幕缓存和深度缓存
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//启动顶点坐标数据
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//启动顶点颜色数据
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		//设置当前矩阵堆栈为模型堆栈
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/***************************绘制第一个图像*************************/
		//重置当前的模型视图矩阵
		gl.glLoadIdentity();
		gl.glTranslatef(-0.32f, 0.35f, -1f);
		//设置顶点的位置数据
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, triangleColorBuffer);
		//根据顶点数据绘制平面图形
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int heigh) {

		gl.glViewport(0, 0, width, heigh);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		float ratio =(float)width/heigh;
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//关闭抖动
		gl.glDisable(GL10.GL_DITHER);
		//设置系统对透视进行修改
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}
}
