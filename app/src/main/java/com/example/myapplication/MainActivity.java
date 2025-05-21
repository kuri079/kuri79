package com.example.myapplication;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MyRenderer;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GLSurfaceView view = new GLSurfaceView(this);
        MyRenderer renderer = new MyRenderer();
        view.setRenderer(renderer);
        setContentView(view);
    }
}
