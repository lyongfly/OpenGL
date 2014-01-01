package com.lyong.opengl.renderer;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.lyong.opengl.util.BufferUtil;

/**
 * 线代渲染器，连接 不闭合
 * @author luoyong
 *
 */
public class MyLineStripRenderer extends AbstractRenderer {

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
        List<Float> coordsList = new ArrayList<Float>();
        float x = 0f, y = 0f, z = 1f;
        float zStep = 0.005f;
        for(float alpha = 0f; alpha < Math.PI * 6; alpha = (float)(alpha + Math.PI / 32)){
            x = (float) (r * Math.cos(alpha));
            y = (float) (r * Math.sin(alpha));
            z = z - zStep;
            
            coordsList.add(x);
            coordsList.add(y);
            coordsList.add(z);
        }
        
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coordsList));
        
        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, coordsList.size() / 3);
        
    }

}
