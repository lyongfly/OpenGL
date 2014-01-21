package com.lyong.opengl.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * 缓冲区工具类
 * @author luoyong
 *
 */
public class BufferUtil {

    /**
     * 将浮点数组转换成字节缓冲区
     * @param arr
     * @return
     */
    public static ByteBuffer arr2ByteBuffer(float[] arr){
        
        ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        FloatBuffer fbb = ibb.asFloatBuffer();
        fbb.put(arr);
        //指定顶点指针
        ibb.position(0);
        return ibb;
    }
    
    /**
     * 将字节数组转换成字节缓冲区
     * @param arr
     * @return
     */
    public static ByteBuffer arr2ByteBuffer(byte[] arr){
        
        ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length);
        ibb.order(ByteOrder.nativeOrder());
        ibb.put(arr);
        //指定顶点指针
        ibb.position(0);
        return ibb;
    }
    
    /**
     * 将浮集合转换成字节缓冲区
     * @param arr
     * @return
     */
    public static ByteBuffer list2ByteBuffer(List<Float> arr){
       
        ByteBuffer ibb = ByteBuffer.allocateDirect(arr.size() * 4);
        ibb.order(ByteOrder.nativeOrder());
        FloatBuffer fbb = ibb.asFloatBuffer();
        for(float f : arr){
            fbb.put(f);
        }
        //指定顶点指针
        ibb.position(0);
        return ibb;
    }
    
    /**
     * 将list集合转换成字节缓冲区
     * @param arr
     * @return
     */
    public static FloatBuffer list2FloatBuffer(List<Float> arr){
       
        ByteBuffer ibb = ByteBuffer.allocateDirect(arr.size() * 4);
        ibb.order(ByteOrder.nativeOrder());
        FloatBuffer fbb = ibb.asFloatBuffer();
        for(float f : arr){
            fbb.put(f);
        }
        //指定顶点指针
        fbb.position(0);
        return fbb;
    }

    /**
     * 浮点数组转浮点缓冲区
     * @param coords
     * @return
     */
    public static FloatBuffer arr2FloatBuffer(float[] coords) {
        ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length * 4);
        ibb.order(ByteOrder.nativeOrder());
        FloatBuffer fbb = ibb.asFloatBuffer();
        fbb.put(coords);
        //指定顶点指针
        fbb.position(0);
        return fbb;
    }
    
    /**
     * 绘制球
     * @param gl
     * @param r 半径
     * @param stack   水平层数 
     * @param slice 步长
     */
    public static void drawSphere(GL10 gl, float r, int stack, int slice){
      //计算球体坐标
        float stackStep = (float) (Math.PI / stack) ;//单位角度值
        float sliceStep = (float) Math.PI /slice ;//水平圆递增的角度
        float r0 ,r1,y0,y1,x0,x1,z0,z1;
        float alpha0 = 0,alpha1 = 0;
        float beta = 0 ;
        List<Float> coordsList = new ArrayList<Float>();
        //外层循环
        for(int i = 0 ; i < stack ; i ++ ){
            alpha0 = (float) (- Math.PI / 2 + (i * stackStep)) ;
            alpha1 = (float) (- Math.PI / 2 + ((i+1) * stackStep)) ;
            y0 = (float) (r * Math.sin(alpha0));
            r0 = (float) (r * Math.cos(alpha0));
            y1 = (float) (r * Math.sin(alpha1));
            r1 = (float) (r * Math.cos(alpha1));
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
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, coordsList.size() / 3);
    }
    
    
}
