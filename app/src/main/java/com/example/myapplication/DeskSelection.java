package com.example.myapplication;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeskSelection extends AppCompatActivity {
    private ImageView c2, c3, c4, c5, c6, c7, c8, c9, c10, b2, b3, b4, b5, b6, b7, b8, b9, b10, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            h2, h3, h4, h5, h6, h7, h8, h9, h10, s2, s3, s4, s5, s6, s7, s8, s9, s10, sq, hq, dq, cq, sj, dj, hj, cj, ck, dk, hk, sk,
            sa, da, ha, ca, arrowRight, arrowLeft;
    public List<ImageView> cards;
    volatile static int counter = 0;
    private ViewGroup mMoveLayout;
    private int mX;
    private int mY;
    int i = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        setContentView(R.layout.desk_selection);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);
        mMoveLayout = (ViewGroup) findViewById(R.id.move);
        c2 = findViewById(R.id.c2); c3 = findViewById(R.id.c3); c4 = findViewById(R.id.c4); c5 = findViewById(R.id.c6);
        c7=  findViewById(R.id.c7); c8 = findViewById(R.id.c9); c9 = findViewById(R.id.c9); h2 = findViewById(R.id.h2);
        s2 = findViewById(R.id.s2); s3 = findViewById(R.id.s3); d3 = findViewById(R.id.d3); h3 = findViewById(R.id.h3);
        d3 = findViewById(R.id.d3); arrowLeft = findViewById(R.id.arrowLeft); arrowRight = findViewById(R.id.arrowRight);
        d4 = findViewById(R.id.d4); h4 = findViewById(R.id.h4);
        cards = new ArrayList<>(Arrays.asList(c3, d3, h3, s3, c4, d4, h4, s4, c5, d5, h5, s5, c6, d6, h6, s6,
                c7, d7, h7, s7, c8, d8, h8, s8, c9, d9, h9, s9, c10, d10, h10, s10, cj, dj, hj, sj, cq, dq, hq, sq, ck, dk, hk, sk, ca, da, ha, sa, c2, h2, s2));
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread t0, t1, t2, t3, t_1, t_2, t_3;
                Display display = getWindowManager().getDefaultDisplay();
                RelativeLayout.LayoutParams lParams1 = new RelativeLayout.LayoutParams(110, 200),
                lParams_1 = new RelativeLayout.LayoutParams(110, 200),
                lParams2 = new RelativeLayout.LayoutParams(110, 200),
                lParams_2 = new RelativeLayout.LayoutParams(110, 200),
                        lParams3 = new RelativeLayout.LayoutParams(110, 200),
                        lParams_3 = new RelativeLayout.LayoutParams(110, 200);
                RelativeLayout.LayoutParams lParams0 = (RelativeLayout.LayoutParams) cards.get(counter).getLayoutParams();

                int cnt = 0;
                for(int i = counter - 3; cnt < 7;i++)
                {
                    if (i == 50 ) i = 0;
                    else if (i == -2) i = 50;
                    else if ( i < 0 ) i = 51 + i;
                    switch (cnt)
                    {
                        case 0:
                            lParams_3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t_3 = new Thread (new ThreadRight(lParams_3, cards.get(i % cards.size())));
                            t_3.start();
                            break;
                        case 1:
                            lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t_2 = new Thread (new ThreadRight(lParams_2, cards.get(i % cards.size())));
                            t_2.start();
                            break;
                        case 2:
                            lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t_1 = new Thread (new ThreadRight(lParams_1, cards.get(i % cards.size())));
                            t_1.start();
                            break;
                        case 3:
                            lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t0 = new Thread (new ThreadRight(lParams0, cards.get(i % cards.size())));
                            t0.start();
                            break;
                        case 4:
                            lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t1 = new Thread (new ThreadRight(lParams1, cards.get(i % cards.size())));
                            t1.start();
                            break;
                        case 5:
                            lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t2 = new Thread (new ThreadRight(lParams2, cards.get(i % cards.size())));
                            t2.start();
                            break;
                        case 6:
                            lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            t3 = new Thread (new ThreadRight(lParams3, cards.get(i % cards.size())));
                            t3.start();
                            if (counter == 50) counter = 0;
                            else counter++;
                    }
                    cnt++;
                    }

            }
        });
    }
    public void update(ImageView iv, RelativeLayout.LayoutParams params)
    {
            runOnUiThread(new Runnable() {
                @Override
                public void run () {

                    iv.setLayoutParams(iv.getLayoutParams());


                }
            });
    }
    class ThreadRight implements Runnable
    {
        RelativeLayout.LayoutParams params;
        ImageView iv;
        ThreadRight( RelativeLayout.LayoutParams params, ImageView iv)
        {
            this.iv = iv;
            this.params = params;


        }
        public void run() {
            Display display = getWindowManager().getDefaultDisplay();
            Point p = new Point();
            display.getSize(p);
            if ((int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()) == 0 && (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, params.rightMargin, getResources().getDisplayMetrics()) == 1806) {
                while ((int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()) < (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, p.x, getResources().getDisplayMetrics())) {
                    params.leftMargin += (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    Log.d("Первый цикл", "params.leftMargin() = " + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()));
                    Log.d("Первый цикл", "params.rightMargin() = " + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, params.rightMargin, getResources().getDisplayMetrics()));
                    Log.d("Первый цикл", "display.getWidth() = " + (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, display.getWidth(), getResources().getDisplayMetrics()));
                    update(iv, params);
                }
            }
            else
            {
                int originParams = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics());

                int cnt = 0;

                while( originParams + (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 111, getResources().getDisplayMetrics()) > (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()))
                {
                    params.leftMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e){}
                    update(iv, params);
                    if (cnt % 10 == 0 ) Log.d("", "" + (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()));
                    cnt++;
                }
            }

        }
    }
}
/* class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
{


    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    void update()
    {

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
class DrawThread extends Thread {
    private ImageView c2, c3, c4, c5, c6, c7, c8, c9, c10, b2, b3, b4, b5, b6, b7, b8, b9, b10, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            h2, h3, h4, h5, h6, h7, h8, h9, h10, s2, s3, s4, s5, s6, s7, s8, s9, s10, sq, hq, dq, cq, sj, dj, hj, cj, ck, dk, hk, sk,
            sa, da, ha, ca, arrowRight, arrowLeft;

    static int counter = 0;
    Thread t_4, t_3, t_2, t_1, t0, t1, t2, t3;


    private SurfaceHolder surfaceHolder;
    Bitmap bitmap_4, bitmap_3,  bitmap_2,  bitmap_1,  bitmap0,  bitmap1,  bitmap2, bitmap3;
    private volatile boolean running = true;

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        for(int i = counter - 3; cnt < 8; i++)
        {
            if (i == 50 ) i = 0;
            else if (i < 0) i = 50 + i + 1;
            switch (cnt)
            {
                case 0:
                    bitmap_4 = BitmapFactory.decodeResource(context.getResources(),);
                    t_3 = new Thread (new DeskSelection.ThreadRight(lParams_3, cards.get(i % cards.size())));
                    Log.d("Пока", "С первым в порядке");
                    t_3.start();

                    break;

                case 1:
                    lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t_2 = new Thread (new DeskSelection.ThreadRight(lParams_2, cards.get(i % cards.size())));
                    Log.d("Пока", "С вторым в порядке");
                    t_2.start();

                    break;
                case 2:
                    lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t_1 = new Thread (new DeskSelection.ThreadRight(lParams_1, cards.get(i % cards.size())));
                    Log.d("Пока", "С первым в порядке");
                    t_1.start();
                    break;
                case 3:
                    lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t0 = new Thread (new DeskSelection.ThreadRight(lParams0, cards.get(i % cards.size())));
                    t0.start();
                    break;
                case 4:
                    lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t1 = new Thread (new DeskSelection.ThreadRight(lParams1, cards.get(i % cards.size())));
                    t1.start();
                    break;
                case 5:
                    lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t2 = new Thread (new DeskSelection.ThreadRight(lParams2, cards.get(i % cards.size())));
                    t2.start();
                    break;
                case 6:
                    lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                    t3 = new Thread (new DeskSelection.ThreadRight(lParams3, cards.get(i % cards.size())));
                    t3.start();
                    if (counter == 50) counter = 0;
                    else counter++;
                case 7:
            }
            cnt++;
        }

    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        int cnt = 0;

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {

                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
/*
 */


/*c2.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    mX = X - lParams.leftMargin;
                    mY = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                    layoutParams.leftMargin = X - mX;
                    layoutParams.topMargin = Y - mY;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    v.setLayoutParams(layoutParams);
                    break;
            }
            return true;

        }
        }); */