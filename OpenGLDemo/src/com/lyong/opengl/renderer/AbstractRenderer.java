package com.lyong.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public abstract class AbstractRenderer implements Renderer {

    public float ratio;
    public float xrotate = 0f;
    public float yrotate = 0f;
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
      //设置清屏色
        gl.glClearColor(0f, 0f, 0f, 1f);
        //启用顶点缓冲区数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置视口
        gl.glViewport(0, 0, width, height);
        ratio = (float)width /(float) height;
        //投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //加载单位矩阵
        gl.glLoadIdentity();
        //设置平截头体
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 3f, 7f);
    }

    @Override
    public abstract void onDrawFrame(GL10 gl);

}
