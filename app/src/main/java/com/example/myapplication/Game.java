package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
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

import java.lang.reflect.Array;
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
    private int field1Size = 0, field2Size = 0, field3Size = 0;
    private int[] directions = {0, 0, 0};
    int numOfCard = 8;

    private int indexOfCard = -1;
    private float previousX, previousY;
    private boolean field1Direction, field2Direction, field3Direction;
    static int remainingCards;
    boolean isOutOfBorders = false, isOnCard = false, isOnPreviousCard = false, isFirstTime = false, fromRight = false, fromLeft = false, cardChosen = false;
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
    private float frame1X, frame1Y, frame2X, frame2Y, frame3X, frame3Y;
    Iterator it;
    private Bitmap card1, card2, card3, card4, card5, card6, card7, card8, background, frame1, frame2, frame3,
            frame4, frame5, frame6, back0,
            back1, back2, back3, back4, back5, back6, back7, back8;
    private Object[][] hand = new Object[8][6];
    private Object[][] field1Cards = new Object[11][6];
    private Object[][] field2Cards = new Object[11][6];
    private Object[][] field3Cards = new Object[11][6];
    ArrayList<Object[][]> fields = new ArrayList<>();
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
        fields.add(field1Cards);
        fields.add(field2Cards);
        fields.add(field3Cards);
        for(int i = 0; i < 3; i++){
            fields.get(i)[10][0] = (int) 0;

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
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
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
                back0 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.back), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                for(int i = 0; i < 8; i++){
                    int index = (int) it.next();
                    hand[i][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get(index)), (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), true);
                    hand[i][1] = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics());
                    hand[i][2] = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());

                    hand[i][5] = names.get(index);
                    if (i != 7) {
                        hand[i][3] = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 535 + i * 20, getResources().getDisplayMetrics());

                    }
                    else {
                        hand[i][3] = TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 746, getResources().getDisplayMetrics());
                    }
                }
                for(int i = 0; i < 8; i++)
                {
                    hand[i][4] = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 430, getResources().getDisplayMetrics());
                }



                Rect r = new Rect();
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setARGB(100000, 255, 0, 0);
                paint.setTextSize(paint.getTextSize() + 25);
                background = BitmapFactory.decodeResource(getResources(), R.drawable.background2);
                this.surfaceHolder = surfaceHolder;
                frame1X = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics());
                frame1Y = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 155, getResources().getDisplayMetrics());
                frame2X = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
                frame2Y = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 155, getResources().getDisplayMetrics());
                frame3X = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 545, getResources().getDisplayMetrics());
                frame3Y = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 155, getResources().getDisplayMetrics());
                for(int i = 0; i < 3; i++){
                    fields.get(i)[10][0] = (int) 0;
                    fields.get(i)[10][0] = (int) 0;
                    fields.get(i)[10][0] = (int) 0;
                }

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
                            canvas.drawColor(Color.parseColor("#434865"));
                            canvas.drawBitmap(frame1, frame1X, frame1Y, new Paint());
                            canvas.drawBitmap(frame2, frame2X,  frame2Y, new Paint());
                            canvas.drawBitmap(frame3,  frame3X,  frame3Y, new Paint());
                            canvas.drawBitmap(frame4, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame5, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawBitmap(frame6, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 545, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()), new Paint());
                            Rect r = new Rect(1, 78, 89, 89);

                                for(int i = 0; i < (int)fields.get(0)[10][0]; i++)
                                {
                                    try {
                                        canvas.drawBitmap((Bitmap) fields.get(0)[i][0], (float) fields.get(0)[i][1], (float) fields.get(0)[i][2], new Paint());
                                    }
                                    catch (Exception exp)
                                    {

                                    }
                                }

                            for(int i = 0; i < hand.length; i++)
                            {
                                try {
                                    canvas.drawBitmap((Bitmap) hand[i][0], (float) hand[i][1], (float) hand[i][2], new Paint());
                                }
                                catch (Exception exp){

                                }
                            }

                            canvas.drawBitmap(back0, (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 756, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics()), new Paint());
                            canvas.drawText(remainingCards + "", (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 795, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 380, getResources().getDisplayMetrics()), paint);

                            try{
                                for(int i = 0; i < (int)fields.get(1)[10][0]; i++)
                                {
                                    canvas.drawBitmap((Bitmap) fields.get(1)[i][0], (float) fields.get(1)[i][1], (float) fields.get(1)[i][2], new Paint());
                                }
                            }
                            catch (Exception exp){

                            }
                           try {
                               for(int i = 0; i < (int)fields.get(2)[10][0]; i++)
                               {
                                   canvas.drawBitmap((Bitmap) fields.get(2)[i][0], (float) fields.get(2)[i][1], (float) fields.get(2)[i][2], new Paint());
                               }
                           }
                           catch (Exception exp){

                           }

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
    private void init(int numOfCards){
        for(int i = 0; i < numOfCards; i++){
            hand[i][1] = (float) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 515 + i* 20, getResources().getDisplayMetrics());
            if(i == numOfCards - 1){
                hand[i][3] = (float) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 535 + i* 20 + 71, getResources().getDisplayMetrics());
            }
            else{
                hand[i][3] = (float) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 535 + i* 20, getResources().getDisplayMetrics());
            }

        }

    }
    private void placeCard() {
                int size  = (int) fields.get(chosenField)[10][0];
        fields.get(chosenField)[size][0] = hand[cnt][0];
        hand[cnt][ 0]= null;
        if(chosenField == 2){
            fields.get(chosenField)[size][1]  =  TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 545  + size * 20, getResources().getDisplayMetrics());
            fields.get(chosenField)[size][3]  =   TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 636 + size * 20, getResources().getDisplayMetrics());
        }
        else{
            fields.get(chosenField)[size][1]  =  TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 25 + chosenField * 275  + size * 20, getResources().getDisplayMetrics());
            fields.get(chosenField)[size][3]  =   TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 116 + chosenField * 275 + size * 20, getResources().getDisplayMetrics());
        }

        fields.get(chosenField)[size][2]  =   TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 155, getResources().getDisplayMetrics());

        fields.get(chosenField)[size][4] =  TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 270, getResources().getDisplayMetrics());
        fields.get(chosenField)[size][5] =  hand[cnt][5];
        turns++;
        fields.get(chosenField)[10][0] = size + 1;
        if(turns >= 0 && turns < 4) {

            for (int j = 0; j < (hand.length - cnt) * 38; j++) {
                int f = j % hand.length + cnt;
                if(f == cnt) continue;
                try {
                    Thread.sleep(3);
                    hand[f][1] = (float) hand[f][1] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    hand[f][3] = (float) hand[f][1] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                } catch (Exception exp) {

                }
            }

            for(int i = cnt; i < numOfCard - 1; i++)
            {
                hand[i][0] = hand[i + 1][0];
                hand[i][5] = hand[i + 1][5];
                hand[i][1] = hand[i + 1][1];
                hand[i][2] = hand[i + 1][2];
                hand[i][3] = hand[i + 1][3];
                hand[i][4] = hand[i + 1][4];

            }
            hand[numOfCard - 1][1] = -1000f;
            hand[numOfCard - 1][2] = -1000f;
            init(--numOfCard);
//            hand[cnt][1]  = (float) hand[cnt - 1][1] + TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
//            hand[cnt][2]  = TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());

        }
        else{
            for (int j = 0; j < (hand.length - cnt) * 38; j++) {
                int f = j % hand.length + cnt;
                if(f == cnt) continue;
                try {
                    Thread.sleep(3);
                    hand[f][1] = (float) hand[f][1] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    hand[f][3] = (float) hand[f][1] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                } catch (Exception exp) {

                }
            }
            for(int i = cnt; i < numOfCard - 1; i++)
            {
                hand[i][0] = hand[i + 1][0];
                hand[i][5] = hand[i + 1][5];
                hand[i][1] = hand[i + 1][1];
                hand[i][2] = hand[i + 1][2];
                hand[i][3] = hand[i + 1][3];
                hand[i][4] = hand[i + 1][4];

            }
            if(remainingCards == 0) {
                hand[4][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get(0)), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                hand[4][2] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics());
                hand[4][4] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, -5, getResources().getDisplayMetrics());
                hand[4][1] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                hand[4][3] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, -1, getResources().getDisplayMetrics());
            }
            else{
                remainingCards--;
                int index = (int) it.next();
                hand[4][0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), deck.get(index)), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                hand[4][2] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                hand[4][4] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 430, getResources().getDisplayMetrics());
                hand[4][1] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 756, getResources().getDisplayMetrics());
                hand[4][3] =  TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 847, getResources().getDisplayMetrics());
                hand[4][5] =  names.get(index);
                while ((float) hand[4][1] != TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 655, getResources().getDisplayMetrics())){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    hand[4][1] =(float) hand[4][1] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    hand[4][3] = (float) hand[4][3] - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                }
                init(numOfCard);
            }

        }

        cardChosen = false;
        chosenField = -1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        final float X = (int) e.getRawX();
        final float Y = (int) e.getRawY();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for(int i = 0 ; i < numOfCard; i++)
                {
                    if((x >= (float) hand[i][1] && x <= (float) hand[i][3]) && y >= (float) hand[i][2] && y <= (float) hand[0][4])
                    {
                        hand[i][0] = Bitmap.createScaledBitmap((Bitmap) hand[i][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics()), false);
                        float fX = (float) hand[i][1], fY = (float) hand[i][2];
                        fX -= (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                        fY -= (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                        mX = X - fX;
                        mY = Y - fY;
                        hand[i][1] = fX;
                        hand[i][2] = fY;
                        return true;
                    }
                }
                isOutOfBorders = true;
                return true;

            case MotionEvent.ACTION_UP:
                previousX = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 1000, getResources().getDisplayMetrics());
                if (isOnField) {
                    if((turns >= 0 && turns < 3) && (int)((fields.get(chosenField))[10][0]) == 1){
                        if(cnt != numOfCard - 1){
                            hand[cnt][1] = (float) hand[cnt + 1][1] - TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                            hand[cnt][2] = (float) hand[cnt + 1][2];
                        }
                        else{
                            hand[cnt][1] = (float) hand[cnt - 1][1] + TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                            hand[cnt][2] = (float) hand[cnt - 1][2];
                        }
                        hand[cnt][0] = Bitmap.createScaledBitmap((Bitmap) hand[cnt][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        isOutOfBorders = false;
                        isOnField = false;
                        chosenField = -1;
                        return  true;
                    }
                    else if ((turns >= 0 && turns < 3) && (int)((fields.get(chosenField))[10][0]) == 0) {
                        placeCard();
                        isOnField = false;
                    }
                    else {
                        try {
                            String s = hand[cnt][5] + "";
                            String value = s.substring(1, 2);
                            switch(value){
                                    case "k":

                                        if((int)((fields.get(chosenField))[10][0]) == 0 ){
                                            if((((String) (fields.get(chosenField)[indexOfCard - 1][5])).charAt(1) == 'q')){
                                                for (int j = indexOfCard; j < (int) fields.get(chosenField)[10][0]; j++) {
                                                    fields.get(chosenField)[j][1] = (float) fields.get(chosenField)[j + 1][1] + TypedValue.applyDimension(

                                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                                                    fields.get(chosenField)[j][3] = (float) fields.get(chosenField)[j + 1][1] + TypedValue.applyDimension(
                                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                                                }
                                            }
                                            chosenField = -1;
                                            return true;
                                        }
                                        int size  = (int) fields.get(chosenField)[10][0];
                                        fields.get(chosenField)[10][0] = size + 1;
                                        break;
                                    case "j":
                                        if((int)((fields.get(chosenField))[10][0]) == 0)
                                        {
                                            chosenField = -1;
                                            return  true;
                                        }
                                        if(indexOfCard != -1)
                                        {
                                            if(indexOfCard != 0)
                                            {
                                                fields.get(chosenField)[indexOfCard - 1][0] = null;
                                                fields.get(chosenField)[indexOfCard - 1][1] = null;
                                                fields.get(chosenField)[indexOfCard - 1][2] = null;
                                                fields.get(chosenField)[indexOfCard - 1][3] = null;
                                                fields.get(chosenField)[indexOfCard - 1][4] = null;
                                                fields.get(chosenField)[indexOfCard - 1][5] = null;
                                            }
                                            for(int i = indexOfCard + 1; i < (int) fields.get(chosenField)[10][0]; i++){
                                                fields.get(chosenField)[i][0] = null;
                                                fields.get(chosenField)[i][1] = null;
                                                fields.get(chosenField)[i][2] = null;
                                                fields.get(chosenField)[i][3] = null;
                                                fields.get(chosenField)[i][4] = null;
                                                fields.get(chosenField)[i][5] = null;
                                            }
                                        }
                                        break;

                                    case "q":
                                        if((int)((fields.get(chosenField))[10][0]) == 0)
                                        {
                                            chosenField = -1;
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

                                    case "a":
                                    case "2":
                                    case "3":
                                    case "4":
                                    case "5":
                                    case "6":
                                    case "7":
                                    case "8":
                                case "1":
                                    case "9":
                                        String previousValue = (String )(fields.get(chosenField)[(int) fields.get(chosenField)[10][0] - 1][5]);
                                        if(s.charAt(0) == previousValue.charAt(0) && (directions[chosenField] != 0)){
                                            placeCard();

                                            break;
                                        }
                                        else {

                                            int value1, value2;
                                            if(s.charAt(1) == 'a'){
                                                 value1 = 1;
                                            }
                                                else{
                                                 value1 = Integer.parseInt(s.substring(1, s.length()));

                                            }

                                            for (int i = (int) fields.get(chosenField)[10][0] - 1; i >= 0; i--)
                                                one:
                                                        {
                                                            String str = (String) fields.get(chosenField)[i][5];
                                                            try {
                                                                if(s.charAt(1) == 'a'){
                                                                     value2 = 1;
                                                                }
                                                                else {
                                                                     value2 = Integer.parseInt(((String) str).substring(1, ((String) str).length()));
                                                                }
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
                                                                        chosenField = -1;
                                                                        isOnField = false;
                                                                        cardChosen = false;
                                                                        throw new Exception();
                                                                    }
                                                                }
                                                                else if(s.charAt(0) == previousValue.charAt(0)){
                                                                    break;
                                                                } else{
                                                                    if (directions[chosenField] == 1 && value1 < value2) {
                                                                        break;
                                                                    } else if (directions[chosenField] == 2 && value1 > value2) {
                                                                        break;
                                                                    } else {
                                                                        chosenField = -1;
                                                                        isOnField = false;
                                                                        cardChosen = false;
                                                                        throw new Exception();
                                                                    }
                                                                }
                                                            } catch (Exception exp) {
                                                                throw new Exception();
                                                            }
                                                        }

                                            placeCard();
                                        }
                                        break;

                                }

                            }
                            catch (Exception exp) {
                              chosenField = -1;
                                for (int i = 0; i < numOfCard; i++) {
                                    try {
                                        hand[i][0] = Bitmap.createScaledBitmap((Bitmap) hand[i][0], (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false );
                                        hand[i][1] = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics());
                                        hand[i][2] = TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                                    } catch (Exception exception) {
                                        exception.printStackTrace();
                                    }
                                }
                                cardChosen = false;
                                break;
                            }
                            chosenField = -1;
                    }
                } else if (cardChosen) {
                    for (int i = 0; i < numOfCard; i++) {
                        try {
                            hand[i][0] = Bitmap.createScaledBitmap((Bitmap) hand[i][0], (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false );
                            hand[i][1] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics());
                            hand[i][2] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    cardChosen = false;
                    break;
                }
                if (isOutOfBorders) {
                    isOutOfBorders = false;
                    break;
                }
                for (int i = 0; i < numOfCard; i++) {
                    int border = i < 7 ? 535 + i * 20 : 746;
                    if ((x >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 515 + i * 20, getResources().getDisplayMetrics()) && x <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, border, getResources().getDisplayMetrics())) && (y >= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics())) && y <= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 451, getResources().getDisplayMetrics())) {
                        hand[i][0] = Bitmap.createScaledBitmap((Bitmap) hand[i][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        float fX = (float) hand[i][1], fY = (float) hand[i][2];
                        fX += (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                        fY += (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                        hand[i][1] = fX;
                        hand[i][2] = fY;
                    }
                }
                break;


            //!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!

            case MotionEvent.ACTION_MOVE:

                //!!!!!!!!!!!!!!!!!!!!
                //!!!!!!!!!!!!!!!!!!!!!

                if (isOutOfBorders) {
                    break;
                }
                if(chosenField > - 1 && cardChosen && (int)((fields.get(chosenField))[10][0]) > 2 &&(((String) hand[cnt][5]).charAt(1) == 'j' || ((String) hand[cnt][5]).charAt(1) == 'k') &&(
                        (float)hand[cnt][1] >= (float)fields.get(chosenField)[0][1] && (float)hand[cnt][2] >= (float)fields.get(chosenField)[0][2] &&
                (float)hand[cnt][3] <= (float)fields.get(chosenField)[(int)fields.get(chosenField)[10][0] - 1][3] &&
                                (float)hand[cnt][4] <= (float)fields.get(chosenField)[(int)fields.get(chosenField)[10][0] - 1][4]
                ))
                {
                    for (int i = 0; i < (int) fields.get(chosenField)[10][0] - 1; i++)
                    {
                        if((e.getX() >  TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()) + i *  TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 225, getResources().getDisplayMetrics())) &&(
                                (e.getX() <  TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 116, getResources().getDisplayMetrics()) + i *  TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                        TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 255, getResources().getDisplayMetrics())
                                ))){
                            if(((previousX >  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()) + i*  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                    TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 225, getResources().getDisplayMetrics())))){
                                break;
                            }
                            if(((previousX >  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()) + i + 1*  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                    TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 255, getResources().getDisplayMetrics())) ||(
                                    (previousX <  TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 116, getResources().getDisplayMetrics()) + i + 1*  TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                            TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP, 255, getResources().getDisplayMetrics()))))

                                    || (previousX >  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()) + i - 1 *  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                    TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 255, getResources().getDisplayMetrics())) &&(
                                    (previousX <  TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 116, getResources().getDisplayMetrics()) + i - 1 *  TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                            TypedValue.applyDimension(
                                                    TypedValue.COMPLEX_UNIT_DIP, 255, getResources().getDisplayMetrics())))){
                                indexOfCard = i;
                                float fX = (float) hand[cnt][1];
                                float fY = (float) hand[cnt][2];
                                hand[cnt][1] = fields.get(chosenField)[i][1];
                                hand[cnt][2] = fields.get(chosenField)[i][2];
                                fields.get(chosenField)[i][1] = fX;
                                fields.get(chosenField)[i][2] = fY;
                                fX = (float) hand[cnt][3];
                                fY = (float) hand[cnt][4];
                                hand[cnt][3] = fields.get(chosenField)[i][3];
                                hand[cnt][4] = fields.get(chosenField)[i][4];
                                fields.get(chosenField)[i][3] = fX;
                                fields.get(chosenField)[i][4] = fY;
                                previousX = e.getX();
                                break;
                            }

                            hand[cnt][1] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()) + i *  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                    TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 177, getResources().getDisplayMetrics());
                            hand[cnt][2] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 315, getResources().getDisplayMetrics());
                            hand[cnt][3] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 116, getResources().getDisplayMetrics()) + i *  TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()) + (chosenField) *
                                    TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 177, getResources().getDisplayMetrics());
                            hand[cnt][4] = TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 430, getResources().getDisplayMetrics());
                            if(i != 0){
                                fields.get(chosenField)[i - 1][1] = (float) fields.get(chosenField)[i - 1][1] - TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                                fields.get(chosenField)[i - 1][3] = (float) fields.get(chosenField)[i - 1][1] - TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                            }
                            for (int j = (int) fields.get(chosenField)[10][0] - 1; j >= i; j++) {
                                if (j == (int) fields.get(chosenField)[10][0] - 1 ){
                                    fields.get(chosenField)[j][1] = (float) fields.get(chosenField)[j][1] + TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                                    fields.get(chosenField)[j][3] = (float) fields.get(chosenField)[j][3] + TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
                                }
                                fields.get(chosenField)[j][1] = (float) fields.get(chosenField)[j - 1][1];
                                fields.get(chosenField)[j][3] = (float) fields.get(chosenField)[j - 1][3];
                                fields.get(chosenField)[j][5] = (float) fields.get(chosenField)[j - 1][5];
                            }
                        }
                        previousX = e.getX();
                    }
                }








                if (cardChosen) {
                    hand[cnt][1] = X - mX;
                    hand[cnt][2] = Y - mY;
                    isOnField = true;
                    long begin = System.currentTimeMillis();
                    if (((float) hand[cnt][1] <= frame1X + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()) && (float) hand[cnt][1] >= frame1X - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics())) && ((float) hand[cnt][2] <= frame1Y + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()) && (float) hand[cnt][2] >= frame1Y  - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()))) {
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 0;

                    } else if (((float) hand[cnt][1] <= frame2X + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()) && (float) hand[cnt][1] >= frame2X - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics())) && ((float) hand[cnt][2] <= frame2Y + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()) && (float) hand[cnt][2] >= frame2Y  - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()))) {
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 1;
                    } else if (((float) hand[cnt][1] <= frame3X + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()) && (float) hand[cnt][1] >= frame3X - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics())) && ((float) hand[cnt][2] <= frame3Y + TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()) && (float) hand[cnt][2] >= frame3Y  - TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()))) {
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.green_field), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = 2;
                    } else {
                        long finish = System.currentTimeMillis();
                        frame1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        frame3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.frame), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                        chosenField = -1;
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
                        for (int i = 0; i < numOfCard; i++) {
                            try{
                                if (((Bitmap) (hand[i][0])).getHeight() == (int) TypedValue.applyDimension(
                                        TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics())) {
                                    cnt = i;

                                }
                            }catch (Exception exp){

                            }

                        }
                        hand[cnt][0] = Bitmap.createScaledBitmap((Bitmap) hand[cnt][0], (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                    }
                }
                for (int i = 0; i < numOfCard; i++) {
                    int border = i < 7 ? 535 + i * 20 : 746;

                    if (x >= (float) hand[i][1] && x <= (float) hand[i][3] && (y >= (float) hand[i][2] && y <= (float) hand[i][4])) {
                        if ( i > 0 && previousX < (float) hand[i][1] ) {
                            isFirstTime = true;
                            isOnPreviousCard = true;
                            fromLeft = true;

                        } else if (i < numOfCard - 1 && ((Bitmap) hand[i + 1][0]).getHeight() == (int) TypedValue.applyDimension(
                                                               TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics())) {
                           isFirstTime = true;
                            isOnPreviousCard = true;
                            fromRight = true;

                        }
                        previousX = e.getX();
                        previousY = e.getY();
                        isOnCard = true;
                        if (isFirstTime) {
                            hand[i][0] = Bitmap.createScaledBitmap((Bitmap) hand[i][0], (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 105, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 130, getResources().getDisplayMetrics()), false);
                            float fX = (float) hand[i][1], fY = (float) hand[i][2];
                            fX -= (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                            fY -= (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                            hand[i][1] = fX;
                            hand[i][2] = fY;
                            if (isOnPreviousCard) {

                                if (fromRight && i < 7) {

                                    hand[i + 1][0] = Bitmap.createScaledBitmap((Bitmap) hand[i + 1][0], (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                                    fX = (float) hand[i + 1][1];
                                    fY = (float) hand[i + 1][2];
                                    fX += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                                    fY += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                                    hand[i + 1][1] = fX;
                                    hand[i + 1][2] = fY;
                                    fromRight = false;
                                }
                                if (fromLeft && i > 0) {
                                    hand[i - 1][0] = Bitmap.createScaledBitmap((Bitmap) hand[i - 1][0], (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 91, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 115, getResources().getDisplayMetrics()), false);
                                    fX = (float) hand[i - 1][1];
                                    fY = (float) hand[i - 1][2];
                                    fX += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
                                    fY += (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
                                    hand[i - 1][1] = fX;
                                    hand[i - 1][2] = fY;
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
