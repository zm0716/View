package com.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;

public class WaveView extends View {

    private Paint paint;
    private Path path;
    private float φ;

    public WaveView(Context context) {
        super(context);
        initView();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();

    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //初始化画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        //消除锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        float y;
        path.moveTo(getLeft(), getBottom());
        /**
         *  y=Asin(ωx+φ)+k
         *  A—振幅越大，波形在y轴上最大与最小值的差值越大
         *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
         *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
         *  k—偏距，反映在坐标系上则为图像的上移或下移。
         */
        φ -= 0.5f;//从45度位置开始震动
        double ω = 2 * Math.PI / getWidth();
        for (int x = 0; x < getWidth(); x += 20) {
            y = (float) (8 * Math.sin(ω * x + φ));

            path.lineTo(x, y);
            animationListener.getY(y);

        }
        path.lineTo(getWidth(), getBottom());
        canvas.drawPath(path, paint);

        postInvalidateDelayed(200);
    }

    public void setAni(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }

    private AnimationListener animationListener;

    public interface AnimationListener {
        void getY(float y);
    }

    public void setA() {

    }
}
