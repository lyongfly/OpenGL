package com.lyong.opengl.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

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
    
}
