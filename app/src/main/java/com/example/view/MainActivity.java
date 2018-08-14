package com.example.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private WaveView waveLine;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        waveLine = findViewById(R.id.wave_view1);
        img = findViewById(R.id.img);
        final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = Gravity.CENTER;
        waveLine.setAni(new WaveView.AnimationListener() {
            @Override
            public void getY(float y) {
                //用来设置iamge的位置  注意这里的y值是随着波浪起伏的值变化的是个变量
                layoutParams.setMargins(0,-200,0, (int) y+2);
                //这里吧设置好的参数赋值给我们的image
                img.setLayoutParams(layoutParams);
            }
        });
    }

}
