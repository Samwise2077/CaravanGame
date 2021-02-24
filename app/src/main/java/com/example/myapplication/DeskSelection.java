package com.example.myapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeskSelection extends AppCompatActivity {
    private ImageView c2, c3, c4, c5, c6, c7, c8, c9, c10, b2, b3, b4, b5, b6, b7, b8, b9, b10, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            h2, h3, h4, h5, h6, h7, h8, h9, h10, s2, s3, s4, s5, s6, s7, s8, s9, s10, sq, hq, dq, cq, sj, dj, hj, cj, ck, dk, hk, sk,
            sa, da, ha, ca, arrowRight, arrowLeft, back;
    private ImageView backc2, backc3, backc4, backc5, backc6, backc7, backc8, backc9, backc10, backb2, backb3, backb4, backb5, backb6,
            backb7, backb8, backb9, backb10, backd2, backd3, backd4, backd5, backd6, backd7, backd8, backd9, backd10,
            backh2, backh3, backh4, backh5, backh6, backh7, backh8, backh9, backh10, backs2, backs3, backs4, backs5, backs6, backs7, backs8,
            backs9, backs10, backsq, backhq, backdq, backcq, backsj, backdj, backhj,backcj, backck, backdk,  backhk, backsk,
            backsa, backda, backha, backca;
    public List<ImageView> cards;
    public List<ImageView> backs;
    volatile static int counter = 0;
    private ViewGroup mMoveLayout;
    private int mX;
    private int mY;
    private TextView cardsRemain;
    int i = 0;
    View.OnClickListener clickListener;
    public void setNumber(int n)
    {
        cardsRemain.setText("Оставшиеся карты: " + n);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);

        cardsRemain = findViewById(R.id.cardsRemain);
        setContentView(R.layout.desk_selection);
        mMoveLayout = (ViewGroup) findViewById(R.id.move);
        c2 = findViewById(R.id.c2); c3 = findViewById(R.id.c3); c4 = findViewById(R.id.c4); c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6); c7=  findViewById(R.id.c7); c8 = findViewById(R.id.c8); c9 = findViewById(R.id.c9);
        c10 = findViewById(R.id.c10);h2 = findViewById(R.id.h2);  h3 = findViewById(R.id.h3);  h4 = findViewById(R.id.h4);  h5 = findViewById(R.id.h5);
        h6= findViewById(R.id.h6);  h7 = findViewById(R.id.h7);  h8 = findViewById(R.id.h8);  h9 = findViewById(R.id.h9);
        h10 = findViewById(R.id.h10);s2 = findViewById(R.id.s2); s3 = findViewById(R.id.s3);  s4 = findViewById(R.id.s4); s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6); s7 = findViewById(R.id.s7);  s8 = findViewById(R.id.s8);  s9 = findViewById(R.id.s9);
        s10 = findViewById(R.id.s10);d2 = findViewById(R.id.d2); d3 = findViewById(R.id.d3); d4 = findViewById(R.id.d4); d5 = findViewById(R.id.d5);
        d6 = findViewById(R.id.d6); d7 = findViewById(R.id.d7); d8 = findViewById(R.id.d8); d9 = findViewById(R.id.d9);
        d10 = findViewById(R.id.d10);
        back = findViewById(R.id.back);
        backc2 = findViewById(R.id.backc2); backc3 = findViewById(R.id.backc3); backc4 = findViewById(R.id.backc4); backc5 = findViewById(R.id.backc5);
        backc6 = findViewById(R.id.backc6); backc7=  findViewById(R.id.backc7); backc8 = findViewById(R.id.backc8); backc9 = findViewById(R.id.backc9);
        backc10 = findViewById(R.id.backc10); backh2 = findViewById(R.id.backh2);  backh3 = findViewById(R.id.backh3);  backh4 = findViewById(R.id.backh4); backh5 = findViewById(R.id.backh5);
        backh6= findViewById(R.id.backh6);  backh7 = findViewById(R.id.backh7);  backh8 = findViewById(R.id.backh8);  backh9 = findViewById(R.id.backh9);
        backh10 = findViewById(R.id.backh10); backs2 = findViewById(R.id.backs2); backs3 = findViewById(R.id.backs3);  backs4 = findViewById(R.id.backs4); backs5 = findViewById(R.id.backs5);
        backs6 = findViewById(R.id.backs6); backs7 = findViewById(R.id.backs7);  backs8 = findViewById(R.id.backs8);  backs9 = findViewById(R.id.backs9);
        backs10 = findViewById(R.id.backs10); backd2 = findViewById(R.id.backd2); backd3 = findViewById(R.id.backd3); backd4 = findViewById(R.id.backd4); backd5 = findViewById(R.id.backd5);
        backd6 = findViewById(R.id.backd6); backd7 = findViewById(R.id.backd7); backd8 = findViewById(R.id.backd8); backd9 = findViewById(R.id.backd9);
        backd10 = findViewById(R.id.backd10);
        backsa = findViewById(R.id.backsa);  backha = findViewById(R.id.backha);  backca = findViewById(R.id.backca);  backda = findViewById(R.id.backda);
        backck = findViewById(R.id.backck);backsk = findViewById(R.id.backsk);backhk = findViewById(R.id.backhk);backdk = findViewById(R.id.backdk);
        backdq = findViewById(R.id.backdq);backhq = findViewById(R.id.backhq);backsq = findViewById(R.id.backsq);backcq = findViewById(R.id.backcq);
        backsj = findViewById(R.id.backsj);backcj = findViewById(R.id.backcj);backdj = findViewById(R.id.backdj);backhj= findViewById(R.id.backhj);
        arrowLeft = findViewById(R.id.arrowLeft); arrowRight = findViewById(R.id.arrowRight);
        RelativeLayout.LayoutParams lParams_back = (RelativeLayout.LayoutParams) back.getLayoutParams();
        sa = findViewById(R.id.sa);  ha = findViewById(R.id.ha);  ca = findViewById(R.id.ca);  da = findViewById(R.id.da);
        ck = findViewById(R.id.ck);sk = findViewById(R.id.sk);hk = findViewById(R.id.hk);dk = findViewById(R.id.dk);
        dq = findViewById(R.id.dq);hq = findViewById(R.id.hq);sq = findViewById(R.id.sq);cq = findViewById(R.id.cq);
        sj = findViewById(R.id.sj);cj = findViewById(R.id.cj);dj = findViewById(R.id.dj);hj= findViewById(R.id.hj);
        cards = new ArrayList<>(Arrays.asList(c3, d3, h3, s3, c4, d4, h4, s4, c5, d5, h5, s5, c6, d6, h6, s6,
                c7, d7, h7, s7, c8, d8, h8, s8, c9, d9, h9, s9, c10, d10, h10, s10, cj, dj, hj, sj, cq, dq, hq, sq, ck, dk, hk, sk, ca, da, ha, sa, c2, d2, h2, s2));
        backs = new ArrayList<>(Arrays.asList(backc3, backd3, backh3, backs3, backc4, backd4, backh4, backs4, backc5, backd5, backh5, backs5, backc6, backd6, backh6, backs6,
                backc7, backd7, backh7, backs7, backc8, backd8, backh8, backs8, backc9, backd9, backh9, backs9, backc10, backd10, backh10, backs10, backcj, backdj,
                backhj, backsj, backcq, backdq, backhq, backsq, backck, backdk, backhk, backsk, backca, backda, backha, backsa, backc2, backd2, backh2, backs2));
        Log.d("check", "" + cards.contains(sa));
        clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ids","" + v.getId());
                cards.get(counter).setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)  cards.get(counter).getLayoutParams();
                update( cards.get(counter), lParams, false);
                update(backs.get(counter), lParams, true);
            }
        };
        for(int i = 0; i < cards.size(); i++)
        {
            MyOnClickListener myOnClickListener = new MyOnClickListener(cards.get(counter), backs.get(counter));
            cards.get(counter).setOnClickListener(myOnClickListener);
            counter++;
        }
        counter = 0;
        for(int i = 0; i < cards.size(); i++)
        {
            MyOnClickListener myOnClickListener = new MyOnClickListener( backs.get(counter), cards.get(counter));
            backs.get(counter).setOnClickListener(myOnClickListener);
            counter++;
        }
        counter = 0;
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) d4.getLayoutParams();
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp=new MediaPlayer();
                try{
                    mp.setDataSource("O:/Music/sliding.mp3");
                    mp.prepare();
                    mp.start();

                }catch(Exception e){e.printStackTrace();}
                Log.d("","cards.size = " + cards.size());
                Thread t0, t1, t2, t3, t_1, t_2, t_3, t_4, t4;
                Display display = getWindowManager().getDefaultDisplay();
                RelativeLayout.LayoutParams lParams1 = new RelativeLayout.LayoutParams(110, 200),
                lParams_1 = new RelativeLayout.LayoutParams(110, 200),
                lParams2 = new RelativeLayout.LayoutParams(110, 200),
                lParams_2 = new RelativeLayout.LayoutParams(110, 200),
                lParams3 = new RelativeLayout.LayoutParams(110, 200),
                lParams_3 = new RelativeLayout.LayoutParams(110, 200),
                lParams_4 = new RelativeLayout.LayoutParams(110, 200),
                lParams4 = new RelativeLayout.LayoutParams(110, 200);

                RelativeLayout.LayoutParams lParams0 = (RelativeLayout.LayoutParams) cards.get(counter).getLayoutParams();
                int cnt = -1;
                for(int i = counter - 4; cnt < 8;i++)
                {
                    Log.d("Карты", "" + i);
                   if ( i < 0 ) i = 52 + i;

                    switch (cnt)
                    {
                        case -1:
                            lParams_4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты-1", "" + cards.get(i % cards.size()));
                            t_4 = new Thread (new ThreadRight(lParams_4, cards.get(i % cards.size()),"-4",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())) );
                            t_4.start();
                            break;
                        case 0:
                            lParams_3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты", "" + cards.get(i % cards.size()));
                            t_3 = new Thread (new ThreadRight(lParams_3, cards.get(i % cards.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_3.start();
                            break;
                        case 1:
                            lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты0", "" + cards.get(i % cards.size()));
                            t_2 = new Thread (new ThreadRight(lParams_2, cards.get(i % cards.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_2.start();
                            break;
                        case 2:
                            lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты1", "" + cards.get(i % cards.size()));
                            t_1 = new Thread (new ThreadRight(lParams_1, cards.get(i % cards.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t_1.start();
                            break;
                        case 3:
                            lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты2", "" + cards.get(i % cards.size()));

                            t0 = new Thread (new ThreadRight(lParams0, cards.get(i % cards.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t0.start();
                            break;
                        case 4:
                            lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты3", "" + cards.get(i % cards.size()));
                            t1 = new Thread (new ThreadRight(lParams1, cards.get(i % cards.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t1.start();
                            break;
                        case 5:
                            lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты5", "" + cards.get(i % cards.size()));
                            t2 = new Thread (new ThreadRight(lParams2, cards.get(i % cards.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t2.start();
                            break;
                        case 6:
                            lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты6", "" + cards.get(i % cards.size()));
                            t3 = new Thread (new ThreadRight(lParams3, cards.get(i % cards.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                            t3.start();

                             if(counter == 0) counter = 51;
                            else counter--;
                            break;
                        case 7:
                            lParams4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();


                                Log.d("Карты7", "" + cards.get(i % cards.size()));
                                t4 = new Thread(new ThreadRight(lParams4, cards.get(i % cards.size()), "8",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 900, getResources().getDisplayMetrics())));
                                t4.start();

                    }
                    cnt++;
                    }

            }
        });
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp=new MediaPlayer();
                try{
                    mp.setDataSource("O:/Music/sliding.mp3");
                    mp.prepare();
                    mp.start();
                }catch(Exception e){e.printStackTrace();}
                Log.d("","cards.size = " + cards.size());
                Thread t0, t1, t2, t3, t_1, t_2, t_3, t_4, t4;
                Display display = getWindowManager().getDefaultDisplay();
                RelativeLayout.LayoutParams lParams1 = new RelativeLayout.LayoutParams(110, 200),
                        lParams_1 = new RelativeLayout.LayoutParams(110, 200),
                        lParams2 = new RelativeLayout.LayoutParams(110, 200),
                        lParams_2 = new RelativeLayout.LayoutParams(110, 200),
                        lParams3 = new RelativeLayout.LayoutParams(110, 200),
                        lParams_3 = new RelativeLayout.LayoutParams(110, 200),
                        lParams_4 = new RelativeLayout.LayoutParams(110, 200),
                        lParams4 = new RelativeLayout.LayoutParams(110, 200);
                RelativeLayout.LayoutParams lParams0 = (RelativeLayout.LayoutParams) cards.get(counter).getLayoutParams();
                int cnt = -1;
                for(int i = counter - 3; cnt < 9;i++)
                {
                    Log.d("Карты", "" + i);
                    if ( i < 0 ) i = 52 + i;

                    switch (cnt)
                    {
                        case -1:
                            lParams_4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты-1", "" + cards.get(i % cards.size()));
                            t_4 = new Thread (new  ThreadLeft(lParams_4, cards.get(i % cards.size()),"-4",
                                    -cards.get(i % cards.size()).getWidth()));
                            t_4.start();
                            break;
                        case 0:
                            lParams_3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты", "" + cards.get(i % cards.size()));
                            t_3 = new Thread (new ThreadLeft(lParams_3, cards.get(i % cards.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())));
                            t_3.start();
                            break;
                        case 1:
                            lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты0", "" + cards.get(i % cards.size()));
                            t_2 = new Thread (new  ThreadLeft(lParams_2, cards.get(i % cards.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_2.start();
                            break;
                        case 2:
                            lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты1", "" + cards.get(i % cards.size()));
                            t_1 = new Thread (new  ThreadLeft(lParams_1, cards.get(i % cards.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_1.start();
                            break;
                        case 3:
                            lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты2", "" + cards.get(i % cards.size()));

                            t0 = new Thread (new  ThreadLeft(lParams0, cards.get(i % cards.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t0.start();
                            break;
                        case 4:
                            lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты3", "" + cards.get(i % cards.size()));
                            t1 = new Thread (new  ThreadLeft(lParams1, cards.get(i % cards.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t1.start();
                            break;
                        case 5:
                            lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты5", "" + cards.get(i % cards.size()));
                            t2 = new Thread (new  ThreadLeft(lParams2, cards.get(i % cards.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t2.start();
                            break;
                        case 6:
                            lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты6", "" + cards.get(i % cards.size()));
                            t3 = new Thread (new ThreadLeft(lParams3, cards.get(i % cards.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t3.start();

                            if(counter == 51) counter = 0;
                            else counter++;
                            break;
                        case 7:
                            lParams4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                                Log.d("Карты7", "" + cards.get(i % cards.size()));
                                t4 = new Thread(new  ThreadLeft(lParams4, cards.get(i % cards.size()), "8",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                                t4.start();
                    }
                    cnt++;
                }

            }
        });
    }
    public boolean contain(Object o)
    {
        return cards.contains(o);
    }
    public void update(ImageView iv, RelativeLayout.LayoutParams paramsб, boolean isVisible)
    {
            runOnUiThread(new Runnable() {
                @Override
                public void run () {
                    if(!isVisible) iv.setVisibility(View.INVISIBLE);
                    else iv.setVisibility(View.VISIBLE);
                    iv.setLayoutParams(paramsб);
                }
            });
    }
    class ThreadRight implements Runnable
    {
        RelativeLayout.LayoutParams params;
        ImageView iv;
        String name;
        private int position;
        ThreadRight( RelativeLayout.LayoutParams params, ImageView iv, String name, int position)
        {
            this.iv = iv;
            this.params = params;
            this.name = name;
            this.position = position;
        }
        public void run() {

            Display display = getWindowManager().getDefaultDisplay();
            Point p = new Point();
            display.getSize(p);
            if (this.name.equals("3")) {

               /* while ((int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()) < (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, p.x, getResources().getDisplayMetrics())) {
                    params.leftMargin += (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                   update(iv, params);

                } */
                int originParams = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics());

                int cnt = 0;

                while(params.leftMargin < position)
                {
                    params.leftMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                    if (cnt % 10 == 0 )
                        cnt++;
                }
            }
            else if(this.name.equals("-4"))
            {

                params.leftMargin = - iv.getWidth();
                params.rightMargin = p.x ;
                params.topMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics());
                params.bottomMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                int originParams = params.leftMargin;
                int cnt = 0;
                update(iv, params, true);
                while( params.leftMargin < position)
                {
                    params.leftMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                    if (cnt % 10 == 0 )
                        cnt++;
                }





            }
            else if(this.name.equals("8"))
            {
                Log.d("Карты89", "" + cards.get(i % cards.size()));
                while(params.leftMargin < position)
                {
                    params.leftMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    update(iv, params, true);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}

                }
            }
            else
            {
                Log.d("Карты89", "" + cards.get(i % cards.size()));
                int originParams = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics());

                int cnt = 0;

                while(params.leftMargin < position)
                {
                    params.leftMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin
                            -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                }
            }

        }

    }
    class ThreadLeft implements Runnable
    {
        RelativeLayout.LayoutParams params;
        ImageView iv;
        String name;
        private int position;
        ThreadLeft( RelativeLayout.LayoutParams params, ImageView iv, String name, int position)
        {
            this.iv = iv;
            this.params = params;
            this.name = name;
            this.position = position;
        }
        public void run() {
            Display display = getWindowManager().getDefaultDisplay();
            Point p = new Point();
            display.getSize(p);
            if (this.name.equals("3")) {
               /* while ((int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, params.leftMargin, getResources().getDisplayMetrics()) < (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, p.x, getResources().getDisplayMetrics())) {
                    params.leftMargin += (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    params.rightMargin -= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                   update(iv, params);

                } */
                while(params.leftMargin > position)
                {
                    params.leftMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                }
            }
            else if(this.name.equals("-4"))
            {
                update(iv, params, true);
                while( params.leftMargin > position)
                {
                    params.leftMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                }
            }
            else if(this.name.equals("8"))
            {
                params.leftMargin = p.x;
                params.rightMargin = -iv.getWidth();
                params.bottomMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                params.topMargin = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics());
                Log.d("Карты89", "" + cards.get(i % cards.size()));
                while(params.leftMargin > position)
                {
                    params.leftMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    update(iv, params, true);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                }
            }
            else
            {
                while(params.leftMargin > position)
                {
                    params.leftMargin -= (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    params.rightMargin += (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e){}
                    update(iv, params, true);
                }
            }
        }
    }
}
 /*class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback
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



/* c2.setOnTouchListener(new View.OnTouchListener() {
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