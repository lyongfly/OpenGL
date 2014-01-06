package com.lyong.opengl.renderer;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.lyong.opengl.util.BufferUtil;

/**
 * 绘制一个球体
 * @author Oliver
 *
 */
public class MySphereRenderer extends AbstractRenderer {

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glColor4f(1f, 0f, 0f, 1f);
        
        //模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
        
        //旋转
        gl.glRotatef(xrotate, 1, 0, 0);
        gl.glRotatef(yrotate, 0, 1, 0);
        
        
        //计算球体坐标
        float R = 0.7f ;//球半径
        int statck = 12 ;//水平层数
        float stackStep = (float) (Math.PI / statck) ;//单位角度值
        
        int slice = 20 ;
        float sliceStep = (float) (Math.PI /slice) ;//水平圆递增的角度
        
        float r0 ,r1,y0,y1,x0,x1,z0,z1;
        
        float alpha0 = 0,alpha1 = 0;
        float beta = 0 ;
        
        List<Float> coordsList = new ArrayList<Float>();
        //外层循环
        for(int i = 0 ; i < statck ; i ++ ){
            alpha0 = (float) (- Math.PI / 2 + (i * stackStep)) ;
            alpha1 = (float) (- Math.PI / 2 + ((i+1) * stackStep)) ;
            y0 = (float) (R * Math.sin(alpha0));
            r0 = (float) (R * Math.cos(alpha0));
            y1 = (float) (R * Math.sin(alpha1));
            r1 = (float) (R * Math.cos(alpha1));
            //循环每一层圆
            for(int j = 0 ; j <= (slice * 2) ; j ++){
                beta = j * sliceStep ;
                x0 = (float) (r0 * Math.cos(beta));
                z0 = -(float) (r0 * Math.sin(beta));
                x1 = (float) (r1 * Math.cos(beta));
                z1 = -(float) (r1 * Math.sin(beta));
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
