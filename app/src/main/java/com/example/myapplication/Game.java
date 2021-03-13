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
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
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
    private Bitmap bitmap1, bitmap2,  bitmap3,  bitmap4,  bitmap5,  bitmap6,  bitmap7, bitmap8, background, frame1, frame2, frame3,
            frame4, frame5, frame6,
               back1,  back2, back3, back4, back5, back6, back7, back8;
    public static Bitmap convertToMutable(Bitmap imgIn) {
        try {
            //this is the file going to use temporally to save the bytes.
            // This file will not be a image, it will store the raw image data.
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");

            //Open an RandomAccessFile
            //Make sure you have added uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
            //into AndroidManifest.xml file
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            // get the width and height of the source bitmap.
            int width = imgIn.getWidth();
            int height = imgIn.getHeight();
            Bitmap.Config type = imgIn.getConfig();

            //Copy the byte to the file
            //Assume source bitmap loaded using options.inPreferredConfig = Config.ARGB_8888;
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes()*height);
            imgIn.copyPixelsToBuffer(map);
            //recycle the source bitmap, this will be no longer used.
            imgIn.recycle();
            System.gc();// try to force the bytes from the imgIn to be released

            //Create a new bitmap to load the bitmap again. Probably the memory will be available.
            imgIn = Bitmap.createBitmap(width, height, type);
            map.position(0);
            //load it back from temporary
            imgIn.copyPixelsFromBuffer(map);
            //close the temporary file and channel, then delete that also
            channel.close();
            randomAccessFile.close();

            // delete the temp file
            file.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgIn;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deck = getIntent().getIntegerArrayListExtra("Array");
        Collections.shuffle(deck);
        it = deck.listIterator();
        Log.d("haha", "gobrrrr2");
        setContentView(new MySurfaceView(this));
        Log.d("haha", "gobrrrr2");

    }
    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
    {
        DrawThread drawThread;
        private volatile boolean running = true;
        private SurfaceHolder surfaceHolder;
        class DrawThread extends Thread {
            private SurfaceHolder surfaceHolder;
            private volatile boolean running = true;
            private Paint backgroundPaint = new Paint();
            private Bitmap bitmap;
            private int towardPointX;
            private int towardPointY;
            public DrawThread(Context context, SurfaceHolder surfaceHolder) {
                frame1 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                frame2 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                frame3 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                frame4 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                frame5 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                frame6 = BitmapFactory.decodeResource(getResources(), R.drawable.frame);
                back1 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back2 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back3 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back4 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back5 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back6 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back7 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back8 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                bitmap1 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap2 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap3 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap4 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap5 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap6 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap7 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                bitmap8 = BitmapFactory.decodeResource(getResources(), (Integer) it.next());
                background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

                this.surfaceHolder = surfaceHolder;
            }
            public void requestStop() {
                running = false;
            }
            @Override
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    background = background.copy(Bitmap.Config.ARGB_8888, true);
                    background.setWidth(canvas.getWidth());
                    background.setHeight(canvas.getHeight());
                    if (canvas != null) {
                        try {
                            Log.d("haha", "gobrrrr");
                          canvas.drawBitmap(background, 0, 0, new Paint());
//                            canvas.drawBitmap(frame1, (int) TypedValue.applyDimension(
//                                    TypedValue.COMPLEX_UNIT_DIP, 76, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
//                                  TypedValue.COMPLEX_UNIT_DIP, 242, getResources().getDisplayMetrics()), new Paint());
//                           canvas.drawBitmap(frame2, (int) TypedValue.applyDimension(
//                                    TypedValue.COMPLEX_UNIT_DIP, 210, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
//                                   TypedValue.COMPLEX_UNIT_DIP, 242, getResources().getDisplayMetrics()), new Paint());
//                            canvas.drawBitmap(frame3, (int) TypedValue.applyDimension(
//                                    TypedValue.COMPLEX_UNIT_DIP, 334, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
//                                    TypedValue.COMPLEX_UNIT_DIP, 242, getResources().getDisplayMetrics()), new Paint());


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
            Log.d("haha", "gobrrrr3");
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
