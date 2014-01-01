package com.lyong.opengl.renderer;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.lyong.opengl.util.BufferUtil;

/**
 * 点渲染器，绘制螺旋线
 * @author luoyong
 *
 */
public class MyPointerSizeRenderer extends AbstractRenderer {

    /**
     * 3
     */
    @Override
    public void onDrawFrame(GL10 gl) {

        //清除颜色缓冲区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //设置绘图颜色
        gl.glColor4f(1f, 0f, 0f, 1f);
        //设置模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //加载单位矩阵
        gl.glLoadIdentity();
        
        //设置眼球的参数
        GLU.gluLookAt(gl, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 1f, 0f);
        
        //旋转角度
        gl.glRotatef(-90f, 1f, 0f, 0f);
        //gl.glRotatef(yrotate, 0f, 1f, 0f);
        
        //计算点的坐标
        float r = 0.5f; //半径
        float x = 0f, y = 0f, z = 1f;
        float zStep = 0.01f;
        
        float pointerSize = 1.0f;
        float pStep = 0.01f;
        
        for(float alpha = 0f; alpha < Math.PI * 6; alpha = (float)(alpha + Math.PI / 16)){
            x = (float) (r * Math.cos(alpha));
            y = (float) (r * Math.sin(alpha));
            z = z - zStep;
            
            pointerSize = pointerSize + pStep;
            //设置点的大小
            gl.glPointSize(pointerSize);
            
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(new float[]{x,y,z}));
            
            gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
        }
    }
}
