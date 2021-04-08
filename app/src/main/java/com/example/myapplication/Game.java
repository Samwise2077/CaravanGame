package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game extends Activity {
    private List<Integer> deck = new ArrayList<>();
    private List<Integer> indexes = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> field1 = new ArrayList<>();
    private List<String> field2 = new ArrayList<>();
    private List<String> field3 = new ArrayList<>();
    private List[] fields = {field1, field2, field3};
    private int[] directions = {0, 0, 0};

    private float previousX, previousY;
    private boolean field1Direction, field2Direction, field3Direction;
    static int remainingCards;
    boolean isOutOfBorders = false, isOnCard = false, isOnPreviousCard = false, isFirstTime = false, fromRight = false, fromLeft = false, cardChosen = false;
    float card1X, card1Y, card2X, card2Y, card3X, card3Y, card4X, card4Y, card5X, card5Y, card6X, card6Y, card7X, card7Y, card8X, card8Y;
    float borderX, borderY;
    int cnt, cnt2 = 0;
    String name = "";
    private int chosenField = 0;
    private int turns = 0;
    boolean isOnField = false;
    private float mX, mY;
    SurfaceView surfaceView;
    DeckSelection ds = new DeckSelection();
    RelativeLayout.LayoutParams fillParentLayout;
    RelativeLayout rootPanel;
    Iterator it;
    private Bitmap card1, card2, card3, card4, card5, card6, card7, card8, background, frame1, frame2, frame3,
            frame4, frame5, frame6, back0,
            back1, back2, back3, back4, back5, back6, back7, back8;
    private Object[][] cards = {{card1, card1X, card1Y, borderX, borderY, name}, {card2, card2X, card2Y, borderX, borderY, name}, {card3, card3X, card3Y, borderX, borderY, name},
            {card4, card4X, card4Y, borderX, borderY, name}
            , {card5, card5X, card5Y, borderX, borderY, name}, {card6, card6X, card6Y, borderX, borderY, name}, {card7, card7X, card7Y, borderX, borderY, name},
            {card8, card8X, card8Y, borderX, borderY, name}};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        surfaceView = new MySurfaceView(this);
        deck = getIntent().getIntegerArrayListExtra("Array");
        names = getIntent().getStringArrayListExtra("Names");
        for(int i = 0; i < deck.size(); i++)
        {
            indexes.add(i);
        }
        remainingCards = deck.size() - 8;
        Collections.shuffle(indexes);
        it = indexes.listIterator();
        previousX = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1000, getResources().getDisplayMetrics());
        surfaceView.setZOrderOnTop(true);
        surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        ImageView bgImagePanel = new ImageView(this);
        bgImagePanel.setBackgroundResource(R.drawable.background);
        fillParentLayout = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        rootPanel = new RelativeLayout(this);
        rootPanel.setLayoutParams(fillParentLayout);
        rootPanel.addView(surfaceView, fillParentLayout);
        rootPanel.addView(bgImagePanel, fillParentLayout);
        setContentView(rootPanel);
    }

    class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        Paint paint = new Paint();

        Drawable back;
        DrawThread drawThread;
        Matrix matrix = new Matrix();

        public void setBackgroundDrawable(Drawable background) {
            this.back = back;
        }

        public void setBackground(Drawable background) {
            this.back = back;
        }

        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            matrix = null;
            if (back != null)
                back.setBounds(left, top, right, bottom);
        }

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
                display.getSize(p);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = false;
                frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                frame4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                frame5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                frame6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                Resources.Theme theme = getTheme();
                back = getResources().getDrawable(R.drawable.background, theme);
                back.setBounds(0, p.y, p.x, 0);
                back1 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back2 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back3 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back4 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back5 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back6 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back7 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back8 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                back0 = BitmapFactory.decodeResource(getResources(), R.drawable.back);
                cards[0][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 515, getResources().getDisplayMetrics());
                cards[0][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[1][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 535, getResources().getDisplayMetrics());
                cards[1][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[2][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 555, getResources().getDisplayMetrics());
                cards[2][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[3][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 575, getResources().getDisplayMetrics());
                cards[3][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[4][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 595, getResources().getDisplayMetrics());
                cards[4][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[5][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 615, getResources().getDisplayMetrics());
                cards[5][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[6][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 635, getResources().getDisplayMetrics());
                cards[6][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[6][4] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 655, getResources().getDisplayMetrics());
                cards[7][1] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 655, getResources().getDisplayMetrics());
                cards[7][2] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                back0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.back), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[0][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[1][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[2][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[3][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[4][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[5][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[6][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[7][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get((Integer) it.next())), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                cards[0][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 535, getResources().getDisplayMetrics());
                cards[1][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 555, getResources().getDisplayMetrics());
                cards[2][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 575, getResources().getDisplayMetrics());
                cards[3][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 595, getResources().getDisplayMetrics());
                cards[4][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 615, getResources().getDisplayMetrics());
                cards[5][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 635, getResources().getDisplayMetrics());
                cards[6][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 655, getResources().getDisplayMetrics());
                cards[7][3] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 746, getResources().getDisplayMetrics());
                cards[0][4] = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 430, getResources().getDisplayMetrics());
                for(int i = 0; i < 8; i++)
                {
                    cards[i][5] = names.get(i);
                }

                Rect r = new Rect();
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setARGB(100000, 255, 0, 0);
                paint.setTextSize(paint.getTextSize() + 25);
                background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
                this.surfaceHolder = surfaceHolder;
            }

            public void requestStop() {
                running = false;
            }

            @Override
            public void run() {
                while (running) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        try {
                            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                            canvas.drawBitmap(frame1, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame2, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 199, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame3, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 374, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame4, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame5, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 199, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame6, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 374, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 515, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 535, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 555, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 575, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 595, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 615, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 635, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 655, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            Rect r = new Rect(1, 78, 89, 89);
                            canvas.drawBitmap((Bitmap) cards[0][0], (float) cards[0][1], (float) cards[0][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[1][0], (float) cards[1][1], (float) cards[1][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[2][0], (float) cards[2][1], (float) cards[2][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[3][0], (float) cards[3][1], (float) cards[3][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[4][0], (float) cards[4][1], (float) cards[4][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[5][0], (float) cards[5][1], (float) cards[5][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[6][0], (float) cards[6][1], (float) cards[6][2], new Paint());
                            canvas.drawBitmap((Bitmap) cards[7][0], (float) cards[7][1], (float) cards[7][2], new Paint());
                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 756, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawText(remainingCards + "", (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 795, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 380, getResources().getDisplayMetrics()), paint);
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
            drawThread = new DrawThread(getContext(), getHolder());
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
    private void placeCard()
    {
        switch(chosenField)
        {
            case 1:
                cards[cnt][1]  =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
                cards[cnt][2]  =   TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[cnt][3]  =   TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 116, getResources().getDisplayMetrics());
                field1.add((String) cards[cnt][5]);
                break;
            case 2:
                cards[cnt][1]  =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 199, getResources().getDisplayMetrics());
                cards[cnt][2]  =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[cnt][3]  =   TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 280, getResources().getDisplayMetrics());
                field2.add((String) cards[cnt][5]);
                break;
            case 3:
                cards[cnt][1]  =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 374, getResources().getDisplayMetrics());
                cards[cnt][2]  =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                cards[cnt][3]  =   TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 466, getResources().getDisplayMetrics());
                field3.add((String) cards[cnt][5]);
                break;
        }
        if(cnt == 7)
        {
            cards[6][3] = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 726, getResources().getDisplayMetrics());
        }
        else if(cnt != 0){
            float f = (float) cards[cnt - 1][3];
            f += TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            cards[cnt - 1][3] = f;
        }
        else{
            float f = (float) cards[cnt][1];
            f += TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            cards[cnt][3] = f;
        }
        turns++;
        cardChosen = false;
        chosenField = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        final float X = (int) e.getRawX();
        final float Y = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for(int i = 0 ; i < 8; i++)
                {
                    if((x >= (float) cards[i][1] && x <= (float) cards[i][3]) && y >= (float) cards[i][2] && y <= (float) cards[0][4])
                    {
                        cards[i][0] = Bitmap.createScaledBitmap((Bitmap) cards[i][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics()), false);
                        float fX = (float) cards[i][1], fY = (float) cards[i][2];
                        fX -= (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                        fY -= (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                        mX = X - fX;
                        mY = Y - fY;
                        cards[i][1] = fX;
                        cards[i][2] = fY;
                        return true;
                    }
                    Log.d("ff", "lol");
                }
                isOutOfBorders = true;
                return true;




            case MotionEvent.ACTION_UP:
                previousX = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 670, getResources().getDisplayMetrics());
                if (isOnField) {
                    fields[0] = field1;
                    fields[1] = field2;
                    fields[2] = field3;
                    if ((turns >= 0 && turns < 4) || fields[cnt - 1].size() == 1) {
                        placeCard();
                    }
                    else {
                            try {
                                switch(((String) cards[cnt][5]).substring(1, 1)){
                                    case "j":
                                        if(fields[chosenField].size() == 0)
                                        {
                                            return  true;
                                        }
                                        break;
                                    case "q":
                                        if(fields[chosenField].size() == 0)
                                        {
                                            return  true;
                                        }
                                        if(directions[chosenField] != 0)
                                        {
                                            if(directions[chosenField] == 1){
                                                directions[chosenField]++;
                                            }
                                            else {
                                                directions[chosenField]--;
                                            }
                                        }
                                        placeCard();
                                        break;
                                    case "k":
                                        if(fields[chosenField].size() == 0)
                                        {
                                            return  true;
                                        } /hj
                                        break;
                                    case "a":
                                    case "2":
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                    case "9":
                                    case "1":
                                        if(((String) cards[cnt][5]).charAt(0) == ((String )(fields[chosenField].get(fields[chosenField].size() - 1))).charAt(0) && (directions[chosenField] != 0)){
                                            break;

                                        }
                                        else {
                                            for (int i = fields[chosenField].size() - 1; i >= 0; i--)
                                                one:
                                                        {
                                                            String str = (String) fields[chosenField].get(i);
                                                            try {
                                                                int value2 = Integer.parseInt(((String) str).substring(1, ((String) str).length() - 1));
                                                                int value1 = Integer.parseInt(((String) cards[cnt][5]).substring(1, ((String) cards[cnt][5]).length() - 1));
                                                                if (directions[chosenField] == 0) {
                                                                    if (value1 > value2){
                                                                        directions[chosenField] = 2;
                                                                        break;
                                                                    }

                                                                    else if (value1 < value2) {
                                                                        directions[chosenField] = 1;
                                                                        break;
                                                                    }
                                                                    else {
                                                                        return true;
                                                                    }
                                                                }
                                                                else if(((String) cards[cnt][5]).charAt(0) == ((String )(fields[chosenField].get(fields[chosenField].size() - 1))).charAt(0)){
                                                                    break;
                                                                } else{
                                                                    if (directions[chosenField] == 1 && value1 < value2) {
                                                                        break;
                                                                    } else if (directions[chosenField] == 2 && value1 < value2) {
                                                                        break;
                                                                    } else {
                                                                        return true;
                                                                    }
                                                                }
                                                            } catch (Exception exp) {

                                                            }
                                                        }
                                            placeCard();
                                        }
                                        break;

                                }

                            }
                            catch (Exception exp) {

                            }
                    }
                } else if (cardChosen) {
                    for (int i = 0; i < 8; i++) {
                        Log.d("agagagaga", "sdf");
                        cards[i][0] = Bitmap.createScaledBitmap((Bitmap) cards[i][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false );
                        cards[i][1] = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics());
                        cards[i][2] = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                    }
                    cardChosen = false;
                    break;
                }
                if (isOutOfBorders) {
                    isOutOfBorders = false;
                    break;
                }
                for (int i = 0; i < 8; i++) {
                    int border = i < 7 ? 535 + i * 20 : 746;
                    if ((x >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics()) && x <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, border, getResources().getDisplayMetrics())) && (y >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics())) && y <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 451, getResources().getDisplayMetrics())) {
                        cards[i][0] = Bitmap.createScaledBitmap((Bitmap) cards[i][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        float fX = (float) cards[i][1], fY = (float) cards[i][2];
                        fX += (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                        fY += (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                        cards[i][1] = fX;
                        cards[i][2] = fY;
                    }
                }
                isOutOfBorders = false;
                break;


            //!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!

            case MotionEvent.ACTION_MOVE:

                //!!!!!!!!!!!!!!!!!!!!
                //!!!!!!!!!!!!!!!!!!!!!
                Log.d("count", ++cnt2 + "");
                if (isOutOfBorders) {
                    break;
                }
                if (cardChosen) {


                    cards[cnt][1] = X - mX;
                    cards[cnt][2] = Y - mY;
                    isOnField = true;
                    long begin = System.currentTimeMillis();

                    if (((float) cards[cnt][1] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 114, getResources().getDisplayMetrics()) && (float) cards[cnt][1] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, -25, getResources().getDisplayMetrics())) && ((float) cards[cnt][2] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 425, getResources().getDisplayMetrics()) && (float) cards[cnt][2] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()))) {
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 1;

                    } else if (((float) cards[cnt][1] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics()) && (float) cards[cnt][1] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 108, getResources().getDisplayMetrics())) && ((float) cards[cnt][2] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 425, getResources().getDisplayMetrics()) && (float) cards[cnt][2] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()))) {
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 2;
                    } else if (((float) cards[cnt][1] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 465, getResources().getDisplayMetrics()) && (float) cards[cnt][1] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 283, getResources().getDisplayMetrics())) && ((float) cards[cnt][2] <= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 425, getResources().getDisplayMetrics()) && (float) cards[cnt][2] >= TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()))) {
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 3;
                    } else {
                        long finish = System.currentTimeMillis();
                        Log.d("gg", "FinishTime = " + (finish - begin));
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 0;
                        isOnField = false;
                    }



                    break;
                }
                if ((x < (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 515, getResources().getDisplayMetrics()) || x >= (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 746, getResources().getDisplayMetrics())) || (y <= (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics())) || y >= (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 451, getResources().getDisplayMetrics())) {
                    isOutOfBorders = true;
                    if ((previousX >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 515, getResources().getDisplayMetrics()) && previousX <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 746, getResources().getDisplayMetrics())) && (previousY >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics())) && previousY <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 451, getResources().getDisplayMetrics())) {
                        isOutOfBorders = false;
                        cardChosen = true;
                        for (int i = 0; i < 8; i++) {
                            if (((Bitmap) (cards[i][0])).getHeight() == (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics())) {
                                cnt = i;

                            }
                        }
                        cards[cnt][0] = Bitmap.createScaledBitmap((Bitmap) cards[cnt][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                    }
                }
                for (int i = 0; i < 8; i++) {
                    int border = i < 7 ? 535 + i * 20 : 746;

                    if (x >= (float) cards[i][1] && x <= (float) cards[i][3] && (y >= (float) cards[i][2] && y <= (float) cards[i][4])) {
                        Log.d("what", "chego");
                        if ( i > 0 && previousX < (float) cards[i][1] ) {
                            isFirstTime = true;
                            isOnPreviousCard = true;
                            fromLeft = true;

                        } else if (i < 7 && previousX >= (float) cards[i][3] && previousX <= (float) cards[i + 1][1]) {
                            isFirstTime = true;
                            isOnPreviousCard = true;
                            fromRight = true;

                        }
                        previousX = e.getX();
                        previousY = e.getY();
                        isOnCard = true;
                        if (isFirstTime) {
                            cards[i][0] = Bitmap.createScaledBitmap((Bitmap) cards[i][0], (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics()), false);
                            float fX = (float) cards[i][1], fY = (float) cards[i][2];
                            fX -= (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                            fY -= (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                            cards[i][1] = fX;
                            cards[i][2] = fY;
                            if (isOnPreviousCard) {

                                if (fromRight && i < 7) {

                                    cards[i + 1][0] = Bitmap.createScaledBitmap((Bitmap) cards[i + 1][0], (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                                    fX = (float) cards[i + 1][1];
                                    fY = (float) cards[i + 1][2];
                                    fX += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                                    fY += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                                    cards[i + 1][1] = fX;
                                    cards[i + 1][2] = fY;
                                    fromRight = false;
                                }
                                if (fromLeft && i > 0) {
                                    cards[i - 1][0] = Bitmap.createScaledBitmap((Bitmap) cards[i - 1][0], (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                                    fX = (float) cards[i - 1][1];
                                    fY = (float) cards[i - 1][2];
                                    fX += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                                    fY += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                                    cards[i - 1][1] = fX;
                                    cards[i - 1][2] = fY;
                                    fromLeft = false;
                                }
                            }
                            isFirstTime = false;
                            isOnPreviousCard = false;
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(e);
    }
}
