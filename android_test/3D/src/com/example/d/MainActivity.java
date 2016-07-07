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
			0.1f , 0.6f , 0.0f,//�϶���
			-0.3f , 0.0f , 0.0f,//�󶥵�
			0.3f , 0.1f , 0.0f//�Ҷ���
			};
	int [] triangleColor  = new int []{
			65535 , 0 , 0 , 0,//�Ϻ�ɫ
			0 ,65535 ,0 , 0,//����ɫ
			0 ,0 ,65535 , 0//����ɫ
	};
	float [] rectDate = new float []{
			0.4f , 0.4f , 0.0f,//���϶���
			0.4f , -0.4f , 0.0f,//���¶���
			-0.4f , 0.4f , 0.0f,//���϶���
			-0.4f , -0.4f , 0.0f,//���¶���
	};
	int [] rectColor = new int []{
			0,65535,0,0,
			0,0,65535,0,
			65535,0 , 0 ,0 ,
			65535 , 65535 , 0 , 0 			
	};
	
	float [] rectDate2 = new float []{
			-0.4f , 0.4f , 0.0f,//���϶���
			0.4f , 0.4f , 0.0f,//���϶���
			0.4f , -0.4f , 0.0f,//���¶���
			-0.4f , -0.4f , 0.0f,//���¶���
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
		//������λ�����������װ��flaotbuffer
		triangleDataBuffer = FloatBuffer.wrap(triangleData);
		rectDataBuffer = FloatBuffer.wrap(rectDate);
		rectDataBuffer2 = FloatBuffer.wrap(rectDate2);
		pentacleBuffer = FloatBuffer.wrap(pentacle);
		triangleColorBuffer = IntBuffer.wrap(triangleColor);
		rectColorBuffer = IntBuffer.wrap(rectColor);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		//�����Ļ�������Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		//����������������
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		//����������ɫ����
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		//���õ�ǰ�����ջΪģ�Ͷ�ջ
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		/***************************���Ƶ�һ��ͼ��*************************/
		//���õ�ǰ��ģ����ͼ����
		gl.glLoadIdentity();
		gl.glTranslatef(-0.32f, 0.35f, -1f);
		//���ö����λ������
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleDataBuffer);
		gl.glColorPointer(4, GL10.GL_FIXED, 0, triangleColorBuffer);
		//���ݶ������ݻ���ƽ��ͼ��
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
		//�رն���
		gl.glDisable(GL10.GL_DITHER);
		//����ϵͳ��͸�ӽ����޸�
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(0, 0, 0, 0);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glDepthFunc(GL10.GL_LEQUAL);
	}
}
