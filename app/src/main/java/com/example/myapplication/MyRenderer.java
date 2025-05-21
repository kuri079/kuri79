package com.example.myapplication;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import java.nio.*;

public class MyRenderer implements GLSurfaceView.Renderer {
    private int width, height;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 初期化処理（今は空）
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(-1f, 1f, -1f, 1f, 2f, -2f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);

        FloatBuffer vertices = makeBuffer(new float[]{
                -0.5f, 0f, 0f,
                0.5f, 0f, 0f,
                0f, 1f, 0f
        });

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glColor4f(1f, 1f, 1f, 1f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertices);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
        gl.glFlush();
    }

    private FloatBuffer makeBuffer(float[] vertices) {
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(vertices);
        fb.position(0);
        return fb;
    }
}
