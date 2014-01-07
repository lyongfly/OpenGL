package com.lyong.opengl.renderer;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.lyong.opengl.util.BufferUtil;

import android.opengl.GLU;

public class MyRingRenderer extends AbstractRenderer {

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glColor4f(0f, 1f, 0f, 1f);
        
        //模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
        
        //旋转
        gl.glRotatef(xrotate, 1, 0, 0);
        gl.glRotatef(yrotate, 0, 1, 0);
        
        float Rinner = 0.2f;    //内环半径
        float Ring = 0.3f;      //圆环半径
        float count = 20;
        float count0 = 20;

        float alpha = 0f;
        float beta = 0f;
        float alphaStep = (float) (2 * Math.PI / count);
        float betaStep = (float) (2 * Math.PI / count0);
        
        float x0,y0,z0,x1,y1,z1;
        
        List<Float> coordsList = new ArrayList<Float>();
        
        for(int i = 0; i < count; i++){
            alpha = i * alphaStep;
            for(int j = 0; j <= count0; j++){
                beta = j * betaStep;
                x0 = (float) ((Rinner + Ring * (1 + Math.cos(beta))) * Math.cos(alpha));
                y0 = (float) ((Rinner + Ring * (1 + Math.cos(beta))) * Math.sin(alpha));
                z0 = -(float) (Ring * Math.sin(beta));
                
                x1 = (float) ((Rinner + Ring * (1 + Math.cos(beta))) * Math.cos(alpha + alphaStep));
                y1 = (float) ((Rinner + Ring * (1 + Math.cos(beta))) * Math.sin(alpha + alphaStep));
                z1 = -(float) (Ring * Math.sin(beta));
                
                coordsList.add(x0);
                coordsList.add(y0);
                coordsList.add(z0);
                coordsList.add(x1);
                coordsList.add(y1);
                coordsList.add(z1);
            }
        }
        
        FloatBuffer fbb = BufferUtil.list2FloatBuffer(coordsList);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fbb);
        gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, coordsList.size() / 3);
        
    }

}
