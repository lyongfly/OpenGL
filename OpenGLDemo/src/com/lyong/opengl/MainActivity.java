
package com.lyong.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.KeyEvent;

import com.lyong.opengl.renderer.AbstractRenderer;
import com.lyong.opengl.renderer.MySphereRenderer;
import com.lyong.opengl.view.MyGLSurfaceView;

public class MainActivity extends Activity {

    private AbstractRenderer myRenderer;
    private MyGLSurfaceView myGlSurfaceView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        myGlSurfaceView = new MyGLSurfaceView(this);
        myRenderer = new MySphereRenderer();
        
        //myGlSurfaceView.setEGLConfigChooser(5, 6, 5, 0, 16, 4);
        myGlSurfaceView.setRenderer(myRenderer);
        
        //myGlSurfaceView.setRenderer(new MyRenderer());
        //GLSurfaceView.RENDERMODE_CONTINUOUSLY 持续渲染（默认）
        //GLSurfaceView.RENDERMODE_WHEN_DIRTY 脏渲染，命令渲染
        myGlSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(myGlSurfaceView);
    }

  //自定义渲染器
    class MyRenderer implements android.opengl.GLSurfaceView.Renderer{

        private float ratio;

        //表层创建时
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //设置清屏颜色
            gl.glClearColor(0, 0, 0, 1);
            //启用顶点缓冲区数组
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            
        }
        
        //表层Size变化时
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //视口，画面输出区域
            gl.glViewport(0, 0, width, height);
            ratio = (float)width / (float)height;
            
            //矩阵模式，投影矩阵，OpenGL基于状态机
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //加载单位矩阵
            gl.glLoadIdentity();
            
            //平截头体
            gl.glFrustumf(-1f, 1f, -ratio, ratio, 3, 7);
            
        }

        //绘图
        @Override
        public void onDrawFrame(GL10 gl) {
            //清除颜色缓存区
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            //模型视图矩阵
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            //eyeX,eyeY,eyeZ 放置眼球的坐标
            //centerX,centerY,centerZ 眼球的观察点
            //upX，upY,upZ 指定眼球向上的向量
            //GLU.gluLookAt(gl, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
            
            //画三角形
            //绘制数组
            //三角形坐标
//            float[] coords = {
//              0f,0.5f,0f,
//              -0.5f,-0.5f,0f,
//              0.5f,-0.5f,0f
//            };
            
            float[] coords = {
                0f,ratio,2f,
                -0.5f,-ratio,2f,
                0.5f,-ratio,2f
            };
            
            //分配字节缓存区空间，存放顶点坐标数据
            ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length * 4);
            //设置的顺序（本地顺序）
            ibb.order(ByteOrder.nativeOrder());
            //放置顶点坐标数组
            FloatBuffer fbb = ibb.asFloatBuffer();
            fbb.put(coords);
            //定位指针的位置，从该位置开始读取顶点数据
            ibb.position(0);
            
            //设置绘图颜色，红色
            gl.glColor4f(1f, 0f, 0f, 1f);
            
            //3: 3维点，使用三个坐标值表示一个点
            //type：每个点的数据类型
            //stride：0，跨度 连着画的
            //ibb 缓冲区
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0 , ibb);
            //count  以点的数量来画，点的数量
            //gl.glDrawArrays(mode, first, count);
            //绘制三角形
            gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
            
        }
        
    }
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                            
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                
                break;
            default:
                break;
        }
        
        //请求渲染和咱渲染配合使用
        myGlSurfaceView.requestRender();
        
        return super.onKeyDown(keyCode, event);
    }
    
}
