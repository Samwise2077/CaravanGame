package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game extends Activity {
    public Parcelable[] arr;
    private List<Integer> deck = new ArrayList<>();
    private ImageView[] arr2;
    DeckSelection ds = new DeckSelection();
    Iterator it;
    private Bitmap bitmap1, bitmap2,  bitmap3,  bitmap4,  bitmap5,  bitmap6,  bitmap7, bitmap8;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 200);
        setContentView(R.layout.game);
        addContentView(new MySurfaceView(this), params);
        deck = getIntent().getIntegerArrayListExtra("Array");
        Collections.shuffle(deck);
        it = deck.listIterator();
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
        Bitmap bitmap8 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());

    }
    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
    {
        DrawThread drawThread;
        class DrawThread extends Thread {
            private SurfaceHolder surfaceHolder;
            private volatile boolean running = true;
            public DrawThread(Context context, SurfaceHolder surfaceHolder) {
            }
            public void requestStop() {
                running = false;
            }
            @Override
            public void run() {
                int cnt = 0;
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    Paint p = new Paint();
                    p.setColor(Color.RED);
                    if (canvas != null) {
                        try {
                            canvas.drawText("Оставшиеся карты ", 1000, 500, p);
                            canvas.drawBitmap(bitmap1, 1000, 1000, null);
                        } finally {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
        public MySurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            drawThread = new DrawThread(getContext(),getHolder());
            drawThread.start();
        }
        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }
        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            drawThread.requestStop();
            boolean retry = true;
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
