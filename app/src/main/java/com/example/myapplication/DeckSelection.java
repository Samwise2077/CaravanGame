package com.example.myapplication;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckSelection extends AppCompatActivity{
    private ImageView c2, c3, c4, c5, c6, c7, c8, c9, c10, b2, b3, b4, b5, b6, b7, b8, b9, b10, d2, d3, d4, d5, d6, d7, d8, d9, d10,
            h2, h3, h4, h5, h6, h7, h8, h9, h10, s2, s3, s4, s5, s6, s7, s8, s9, s10, sq, hq, dq, cq, sj, dj, hj, cj, ck, dk, hk, sk,
            sa, da, ha, ca, arrowRight, arrowLeft, back;
    private DeckSelection oc2, oc3, oc4, oc5, oc6, oc7, oc8, oc9, oc10, ob2, ob3, ob4, ob5, ob6, ob7, ob8, ob9, ob10, od2, od3, od4, od5, od6, od7, od8, od9, od10,
            oh2, oh3, oh4, oh5, oh6, oh7, oh8, oh9, oh10, os2, os3, os4, os5, os6, os7, os8, os9, os10, osq, ohq, odq, ocq, osj, odj, ohj, ocj, ock, odk, ohk, osk,
            osa, oda, oha, oca;
    private ImageView backc2, backc3, backc4, backc5, backc6, backc7, backc8, backc9, backc10, backb2, backb3, backb4, backb5, backb6,
            backb7, backb8, backb9, backb10, backd2, backd3, backd4, backd5, backd6, backd7, backd8, backd9, backd10,
            backh2, backh3, backh4, backh5, backh6, backh7, backh8, backh9, backh10, backs2, backs3, backs4, backs5, backs6, backs7, backs8,
            backs9, backs10, backsq, backhq, backdq, backcq, backsj, backdj, backhj,backcj, backck, backdk,  backhk, backsk,
            backsa, backda, backha, backca;
    public ArrayList<Integer> deck = new ArrayList<>();
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Bitmap> deck2 = new ArrayList<>();
    public List<ImageView> cards  = new ArrayList<>();
    public List<ImageView> backs = new ArrayList<>();
    public List<DeckSelection> objects = new ArrayList<>();

    private int id = 6;

    volatile static int counter = 0;
    private ViewGroup mMoveLayout;
    private int mX;
    private Button btnStartGame, btnStartGame2, randomize;
    private int mY;
    private static int numberOfRemainingCards = 52;
    public List getDeck()
    {
        return deck;
    }
    public void init()
    {
        oc3.id = R.drawable.c3; oc4.id = R.drawable.c4; oc5.id = R.drawable.c5; oc6.id = R.drawable.c6;
        oc7.id = R.drawable.c7; oc8.id = R.drawable.c8; oc9.id = R.drawable.c9; oc10.id = R.drawable.c10; od2.id = R.drawable.d2; od3.id = R.drawable.d3; od4.id = R.drawable.d4; od5.id = R.drawable.d5;
        od6.id = R.drawable.d6; od7.id = R.drawable.d7; od8.id = R.drawable.d8; od9.id = R.drawable.d9; od10.id = R.drawable.d10;
        oh2.id = R.drawable.h2; oh3.id = R.drawable.h3; oh4.id = R.drawable.h4; oh5.id = R.drawable.h5; oh6.id = R.drawable.h6; oh7.id = R.drawable.h7; oh8.id = R.drawable.h8; oh9.id = R.drawable.h9; oh10.id = R.drawable.h10;
        os2.id = R.drawable.d2; os3.id = R.drawable.d4; os4.id = R.drawable.d5; os5.id = R.drawable.d6; os6.id = R.drawable.d7; os7.id = R.drawable.d8;
        os8.id = R.drawable.d8; os9.id = R.drawable.d9; os10.id = R.drawable.d10; osq.id = R.drawable.sq; ohq.id = R.drawable.hq; odq.id = R.drawable.dq;
        ocq.id = R.drawable.cq; osj.id = R.drawable.sj; odj.id = R.drawable.dj; ohj.id = R.drawable.hj; ocj.id = R.drawable.cj; ock.id = R.drawable.ck;
        odk.id = R.drawable.dk; ohk.id = R.drawable.hk; osk.id = R.drawable.sk;
        osa.id = R.drawable.sa; oda.id = R.drawable.da; oha.id = R.drawable.ha; oca.id = R.drawable.ca;
    }
    public void getId(ImageView iv)
    {

    }
    class MyOnClickListener implements View.OnClickListener
    {
        DeckSelection ds = new DeckSelection();
        ImageView iv, back;
        private List<ImageView> copy = ds.cards;
        private boolean presence;
        public MyOnClickListener(ImageView iv, ImageView back, boolean presence) {
            this.iv = iv;
            this.back = back;
            this.presence = presence;
        }
        @Override
        public void onClick(View v)
        {
        if(numberOfRemainingCards == 0 && presence) {
                return;
            }

            iv.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)  iv.getLayoutParams();
            RelativeLayout.LayoutParams lParams2 = (RelativeLayout.LayoutParams)  back.getLayoutParams();
            ds.update( iv, lParams2, true);
            ds.update(back, lParams, true);
            if(presence)
            {
                numberOfRemainingCards--;
                update("Оставшиеся карты: " + numberOfRemainingCards);
                init();

                deck.add(objects.get(cards.indexOf(iv)).id);
                String name = "";
                switch(cards.indexOf(iv) + 1 % 4){
                    case 0:
                        name = "c";
                    case 1:
                        name = "d";
                    case 2:
                        name = "h";
                    case 3:
                        name = "s";
                }
                switch (cards.indexOf(iv) + 1/ 4)
                {
                    case 0:
                        name += "3";
                        break;
                    case 1:
                        name += "4";
                        break;
                    case 2:
                        name += "5";
                        break;
                    case 3:
                        name = "6";
                        break;
                    case 4:
                        name += "7";
                        break;
                    case 5:
                        name += "8";
                        break;
                    case 6:
                        name += "9";
                        break;
                    case 7:
                        name += "10";
                        break;
                    case 8:
                        name += "j";
                        break;
                    case 9:
                        name += "q";
                        break;
                    case 10:
                        name += "k";
                        break;
                    case 11:
                        name += "a";
                        break;
                    case 12:
                        name += "2";
                        break;
                }
                names.add(name);
                Log.d("counter = ", "" + counter);
                if(numberOfRemainingCards == 40);
                {
                    Log.d("number555", "" + numberOfRemainingCards);
                    btnStartGame.setAlpha(1f);
                }
            }
            else {
                if(numberOfRemainingCards != 52)
                    update("Оставшиеся карты: " + ++numberOfRemainingCards);
                Log.d("number", "" + numberOfRemainingCards);
                deck.remove((Integer) objects.get(counter).id);
                names.remove(names.get(counter));
                if(numberOfRemainingCards == 23)
                {
                    btnStartGame.setAlpha(0.2f);
                }
            }
        }
    }
    private TextView cardsRemain;
    int i = 0;
    View.OnClickListener clickListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_selection);
        Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        cardsRemain = findViewById(R.id.cardsRemain);
        btnStartGame = findViewById(R.id.start);
        randomize = findViewById(R.id.randomize);
        randomize.setOnClickListener((l) ->{
            List<Integer> randomCards  = new ArrayList<>();
            List<String> temp  = new ArrayList<>();
            for(ImageView iv : cards){
                randomCards.add(objects.get(cards.indexOf(iv)).id);
                String name = "";
                switch(cards.indexOf(iv) + 1 % 4){
                    case 0:
                        name = "c";
                    case 1:
                        name = "d";
                    case 2:
                        name = "h";
                    case 3:
                        name = "s";
                }
                switch (cards.indexOf(iv) + 1/ 4)
                {
                    case 0:
                        name += "3";
                        break;
                    case 1:
                        name += "4";
                        break;
                    case 2:
                        name += "5";
                        break;
                    case 3:
                        name = "6";
                        break;
                    case 4:
                        name += "7";
                        break;
                    case 5:
                        name += "8";
                        break;
                    case 6:
                        name += "9";
                        break;
                    case 7:
                        name += "10";
                        break;
                    case 8:
                        name += "j";
                        break;
                    case 9:
                        name += "q";
                        break;
                    case 10:
                        name += "k";
                        break;
                    case 11:
                        name += "a";
                        break;
                    case 12:
                        name += "2";
                        break;
                }
                temp.add(name);
            }
            List<Integer> indexes  = new ArrayList<>();
            for(int i = 0; i < randomCards.size(); i++)
            {
                indexes.add(i);
            }
            Collections.shuffle(indexes);
            int num = (int) (Math.random() * (cards.size() - 30 + 2)) + 29;
            for(int i = 0; i < num; i++)
            {
                deck.add(randomCards.get(indexes.get(i)));
                names.add(temp.get(indexes.get(i)));
            }
            numberOfRemainingCards = 52 - num;
            btnStartGame.setAlpha(1f);
        });
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
        oc3 = new DeckSelection(); od3 = new DeckSelection(); oc3 = new DeckSelection(); od3 = new DeckSelection(); oh3 = new DeckSelection(); os3 = new DeckSelection(); oc4 = new DeckSelection(); od4 = new DeckSelection();
        oh4 = new DeckSelection(); os4 = new DeckSelection(); oc5 = new DeckSelection(); od5 = new DeckSelection();
        oh5 = new DeckSelection(); os5 = new DeckSelection(); oc6 = new DeckSelection(); od6 = new DeckSelection();
        oh6 = new DeckSelection(); os6 = new DeckSelection(); oc7 = new DeckSelection();
        od7 = new DeckSelection(); oh7 = new DeckSelection(); os7 = new DeckSelection(); oc8 = new DeckSelection();
        od8 = new DeckSelection(); oh8 = new DeckSelection(); os8 = new DeckSelection(); oc9 = new DeckSelection();
        od9 = new DeckSelection(); oh9 = new DeckSelection(); os9 = new DeckSelection(); oc10 = new DeckSelection();
        od10 = new DeckSelection(); oh10 = new DeckSelection(); os10 = new DeckSelection(); ocj = new DeckSelection();
        odj = new DeckSelection(); ohj = new DeckSelection(); osj = new DeckSelection(); ocq = new DeckSelection();
        odq = new DeckSelection(); ohq = new DeckSelection(); osq = new DeckSelection(); ock = new DeckSelection();
        odk = new DeckSelection(); ohk = new DeckSelection(); osk = new DeckSelection();
        oca = new DeckSelection(); oda = new DeckSelection(); oha = new DeckSelection(); osa = new DeckSelection();
        oc2 = new DeckSelection(); od2 = new DeckSelection();oh2 = new DeckSelection(); os2 = new DeckSelection();
        cards = new ArrayList<>(Arrays.asList(c3, d3, h3, s3, c4, d4, h4, s4, c5, d5, h5, s5, c6, d6, h6, s6,
                c7, d7, h7, s7, c8, d8, h8, s8, c9, d9, h9, s9, c10, d10, h10, s10, cj, dj, hj, sj, cq, dq, hq, sq, ck, dk, hk, sk, ca, da, ha, sa, c2, d2, h2, s2));
        objects = new ArrayList<>(Arrays.asList(oc3, od3, oh3, os3, oc4, od4, oh4, os4, oc5, od5, oh5, os5, oc6, od6, oh6, os6,
                oc7, od7, oh7, os7, oc8, od8, oh8, os8, oc9, od9, oh9, os9, oc10, od10, oh10, os10, ocj, odj, ohj, osj, ocq, odq, ohq, osq, ock, odk, ohk, osk, oca, oda, oha, osa, oc2, od2, oh2, os2));
        for (int i = 0; i < 52; i++){
            objects.set(i, new DeckSelection());
        }
        objects.get(48).id = R.drawable.c2; objects.get(0).id = R.drawable.c3;  objects.get(4).id = R.drawable.c4;  objects.get(7).id = R.drawable.c5;
         objects.get(11).id = R.drawable.c6;
        objects.get(15).id = R.drawable.c7;  objects.get(19).id = R.drawable.c8;  objects.get(24).id = R.drawable.c9;  objects.get(28).id = R.drawable.c10;
        objects.get(49).id = R.drawable.d2; objects.get(1).id = R.drawable.d3;  objects.get(5).id = R.drawable.d4;
        objects.get(8).id = R.drawable.d5;
        objects.get(12).id = R.drawable.d6;  objects.get(16).id = R.drawable.d7;  objects.get(20).id = R.drawable.d8;  objects.get(25).id = R.drawable.d9;
        objects.get(29).id = R.drawable.d10;
        objects.get(50).id = R.drawable.h2; objects.get(2).id = R.drawable.h3;  objects.get(6).id = R.drawable.h4;  objects.get(9).id = R.drawable.h5;
        objects.get(13).id = R.drawable.h6;  objects.get(17).id = R.drawable.h7;  objects.get(21).id = R.drawable.h8;  objects.get(26).id = R.drawable.h9;
        objects.get(30).id = R.drawable.h10;
        objects.get(51).id = R.drawable.s2; objects.get(3).id = R.drawable.s3;  objects.get(6).id = R.drawable.s4;  objects.get(10).id = R.drawable.s5;  objects.get(14).id = R.drawable.s6;
        objects.get(18).id = R.drawable.s7;  objects.get(23).id = R.drawable.s8;
          objects.get(27).id = R.drawable.s9;  objects.get(31).id = R.drawable.s10;  objects.get(39).id = R.drawable.sq;
        objects.get(38).id = R.drawable.hq;  objects.get(37).id = R.drawable.dq;
        objects.get(36).id = R.drawable.cq;  objects.get(35).id = R.drawable.sj;  objects.get(33).id = R.drawable.dj;  objects.get(34).id = R.drawable.hj;
        objects.get(32).id = R.drawable.cj;  objects.get(40).id = R.drawable.ck;
        objects.get(41).id = R.drawable.dk;  objects.get(42).id = R.drawable.hk;  objects.get(43).id = R.drawable.sk;
        objects.get(47).id = R.drawable.sa;  objects.get(45).id = R.drawable.da;  objects.get(46).id = R.drawable.ha;  objects.get(44).id = R.drawable.ca;
        backs = new ArrayList<>(Arrays.asList(backc3, backd3, backh3, backs3, backc4, backd4, backh4, backs4, backc5, backd5, backh5, backs5, backc6, backd6, backh6, backs6,
                backc7, backd7, backh7, backs7, backc8, backd8, backh8, backs8, backc9, backd9, backh9, backs9, backc10, backd10, backh10, backs10, backcj, backdj,
                backhj, backsj, backcq, backdq, backhq, backsq, backck, backdk, backhk, backsk, backca, backda, backha, backsa, backc2, backd2, backh2, backs2));
        Log.d("check", "" + cards.contains(sa));
        clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                cards.get(counter).setVisibility(View.INVISIBLE);
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)  cards.get(counter).getLayoutParams();
                update( cards.get(counter), lParams, false);
                update(backs.get(counter), lParams, true);
            }
        };
        for(int i = 0; i < cards.size(); i++)
        {
            MyOnClickListener myOnClickListener = new MyOnClickListener(cards.get(counter), backs.get(counter), true);
            cards.get(counter).setOnClickListener(myOnClickListener);
            counter++;
        }
        counter = 0;
        for(int i = 0; i < cards.size(); i++)
        {
            MyOnClickListener myOnClickListener = new MyOnClickListener( backs.get(counter), cards.get(counter), false);
            backs.get(counter).setOnClickListener(myOnClickListener);
            counter++;
        }
        counter = 0;
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) d4.getLayoutParams();
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStartGame.getAlpha() == 1f) {
                    Intent i = new Intent(DeckSelection.this, Game.class);
                    i.putIntegerArrayListExtra("Array",  deck);
                    i.putStringArrayListExtra("Names",  names);
                    Log.d("arr", deck.toString());
                    startActivity(i);
                }
            }
        });
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp=new MediaPlayer();
                try{
                    mp.setDataSource("com.example.myapplication.music.sliding");
                    mp.prepare();
                    mp.start();
                }catch(Exception e){e.printStackTrace();}
                Log.d("","cards.size = " + cards.size());
                Thread t0, t1, t2, t3, t_1, t_2, t_3, t_4, t4, t0t, t1t, t2t, t3t, t_1t, t_2t, t_3t, t_4t, t4t;
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
                                t_4 = new Thread(new ThreadRight(lParams_4, cards.get(i % cards.size()), "-4",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())));
                                t_4.start();
                                lParams_4 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                                t_4t = new Thread(new ThreadRight(lParams_4, backs.get(i % backs.size()), "-4",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())));
                                t_4t.start();
                                break;

                        case 0:
                            lParams_3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты", "" + cards.get(i % cards.size()));
                            t_3 = new Thread (new ThreadRight(lParams_3, cards.get(i % cards.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_3.start();
                            lParams_3 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_3t = new Thread (new ThreadRight(lParams_3, backs.get(i % backs.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_3t.start();
                            break;
                        case 1:
                            lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты0", "" + cards.get(i % cards.size()));
                            t_2 = new Thread (new ThreadRight(lParams_2, cards.get(i % cards.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_2.start();
                            lParams_2 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_2t = new Thread (new ThreadRight(lParams_2, backs.get(i % backs.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_2t.start();
                            break;
                        case 2:
                            lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты1", "" + cards.get(i % cards.size()));
                            t_1 = new Thread (new ThreadRight(lParams_1, cards.get(i % cards.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t_1.start();
                            lParams_1 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_1t = new Thread (new ThreadRight(lParams_1, backs.get(i % backs.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t_1t.start();
                            break;
                        case 3:
                            lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты2", "" + cards.get(i % cards.size()));

                            t0 = new Thread (new ThreadRight(lParams0, cards.get(i % cards.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t0.start();
                            lParams0 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t0t = new Thread (new ThreadRight(lParams0, backs.get(i % backs.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t0t.start();
                            break;
                        case 4:
                            lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты3", "" + cards.get(i % cards.size()));
                            t1 = new Thread (new ThreadRight(lParams1, cards.get(i % cards.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t1.start();
                            lParams1 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t1t = new Thread (new ThreadRight(lParams1, backs.get(i % cards.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t1t.start();
                            break;
                        case 5:
                            lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты5", "" + cards.get(i % cards.size()));
                            t2 = new Thread (new ThreadRight(lParams2, cards.get(i % cards.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t2.start();
                            lParams2 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t2t = new Thread (new ThreadRight(lParams2, backs.get(i % backs.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t2t.start();
                            break;
                        case 6:
                            lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты6", "" + cards.get(i % cards.size()));
                            t3 = new Thread (new ThreadRight(lParams3, cards.get(i % cards.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                            t3.start();
                            lParams3 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t3t = new Thread (new ThreadRight(lParams3, backs.get(i % backs.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                            t3t.start();

                             if(counter == 0) counter = 51;
                            else counter--;
                            Log.d("gg", "counter first = " + counter);
                            break;
                        case 7:
                            lParams4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                                Log.d("Карты7", "" + cards.get(i % cards.size()));
                                t4 = new Thread(new ThreadRight(lParams4, cards.get(i % cards.size()), "8",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 900, getResources().getDisplayMetrics())));
                                t4.start();
                            lParams4 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t4t = new Thread(new ThreadRight(lParams4, backs.get(i % backs.size()), "8",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 900, getResources().getDisplayMetrics())));
                            t4t.start();
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
                Thread t0, t1, t2, t3, t_1, t_2, t_3, t_4, t4, t0t, t1t, t2t, t3t, t_1t, t_2t, t_3t, t_4t, t4t;
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
                            lParams_4 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_4t = new Thread (new  ThreadLeft(lParams_4, backs.get(i % cards.size()),"-4",
                                    -cards.get(i % backs.size()).getWidth()));
                            t_4t.start();
                            break;
                        case 0:
                            lParams_3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты", "" + cards.get(i % cards.size()));
                            t_3 = new Thread (new ThreadLeft(lParams_3, cards.get(i % cards.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())));
                            t_3.start();
                            lParams_3 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_3t = new Thread (new ThreadLeft(lParams_3, backs.get(i % backs.size()), "-3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics())));
                            t_3t.start();
                            break;
                        case 1:
                            lParams_2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты0", "" + cards.get(i % cards.size()));
                            t_2 = new Thread (new  ThreadLeft(lParams_2, cards.get(i % cards.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_2.start();
                            lParams_2 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_2t = new Thread (new  ThreadLeft(lParams_2, backs.get(i % backs.size()), "-2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 117, getResources().getDisplayMetrics())));
                            t_2t.start();
                            break;
                        case 2:
                            lParams_1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты1", "" + cards.get(i % cards.size()));
                            t_1 = new Thread (new  ThreadLeft(lParams_1, cards.get(i % cards.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_1.start();
                            lParams_1 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t_1t = new Thread (new  ThreadLeft(lParams_1, backs.get(i % backs.size()), "-1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 231, getResources().getDisplayMetrics())));
                            t_1t.start();
                            break;
                        case 3:
                            lParams0 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты2", "" + cards.get(i % cards.size()));

                            t0 = new Thread (new  ThreadLeft(lParams0, cards.get(i % cards.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t0.start();
                            lParams0 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t0t = new Thread (new  ThreadLeft(lParams0, backs.get(i % backs.size()), "0",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 338, getResources().getDisplayMetrics())));
                            t0t.start();
                            break;
                        case 4:
                            lParams1 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты3", "" + cards.get(i % cards.size()));
                            t1 = new Thread (new  ThreadLeft(lParams1, cards.get(i % cards.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t1.start();
                            lParams1 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t1t = new Thread (new  ThreadLeft(lParams1, backs.get(i % backs.size()), "1",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 450, getResources().getDisplayMetrics())));
                            t1t.start();
                            break;
                        case 5:
                            lParams2 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты5", "" + cards.get(i % cards.size()));
                            t2 = new Thread (new  ThreadLeft(lParams2, cards.get(i % cards.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t2.start();
                            lParams2 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t2t = new Thread (new  ThreadLeft(lParams2, backs.get(i % backs.size()), "2",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 563, getResources().getDisplayMetrics())));
                            t2t.start();
                            break;
                        case 6:
                            lParams3 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                            Log.d("Карты6", "" + cards.get(i % cards.size()));
                            t3 = new Thread (new ThreadLeft(lParams3, cards.get(i % cards.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t3.start();
                            lParams3 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t3 = new Thread (new ThreadLeft(lParams3, backs.get(i % backs.size()), "3",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 673, getResources().getDisplayMetrics())));
                            t3.start();
                            if(counter == 51) counter = 0;
                            else counter++;
                            Log.d("gg", "counter first = " + counter);
                            break;
                        case 7:
                            lParams4 = (RelativeLayout.LayoutParams) cards.get(i % cards.size()).getLayoutParams();
                                Log.d("Карты7", "" + cards.get(i % cards.size()));
                                t4 = new Thread(new  ThreadLeft(lParams4, cards.get(i % cards.size()), "8",
                                        (int) TypedValue.applyDimension(
                                                TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                                t4.start();
                            lParams4 = (RelativeLayout.LayoutParams) backs.get(i % backs.size()).getLayoutParams();
                            t4t = new Thread(new  ThreadLeft(lParams4, backs.get(i % backs.size()), "8",
                                    (int) TypedValue.applyDimension(
                                            TypedValue.COMPLEX_UNIT_DIP, 800, getResources().getDisplayMetrics())));
                            t4t.start();
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
    public void update(String text)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run () {
                cardsRemain.setText(text);
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
                Log.d("*", params.topMargin + " == " + (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics()) + " == " + (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics()));
                if(params.topMargin == (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics()) || params.topMargin == (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics()) ||
                        params.topMargin == 329 || params.topMargin == 32){}
                else if(cards.contains(iv)) {
                    params.topMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics());
                    params.bottomMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                }
                else {
                    params.topMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics());
                    params.bottomMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 251, getResources().getDisplayMetrics());
                }
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

                if(params.topMargin == (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics()) || params.topMargin == (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics()) ||
                        params.topMargin == 329 || params.topMargin == 32){Log.d("*", "Работает");}
                else if(cards.contains(iv)) {
                    params.bottomMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 52, getResources().getDisplayMetrics());
                    params.topMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 219, getResources().getDisplayMetrics());
                    Log.d("*", "Работает-");
                }
                else
                {
                    params.bottomMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 251, getResources().getDisplayMetrics());
                    params.topMargin = (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 21, getResources().getDisplayMetrics());
                    Log.d("*", "Работает3");
                }
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

/* c2.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {


        }
        }); */