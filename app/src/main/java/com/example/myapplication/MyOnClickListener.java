package com.example.myapplication;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MyOnClickListener implements View.OnClickListener
{
    DeskSelection ds = new DeskSelection();
    private int numberOfRemainingCards = 36;
    ImageView iv, back;
    public MyOnClickListener(ImageView iv, ImageView back) {
        this.iv = iv;
        this.back = back;
    }


    @Override
    public void onClick(View v)
    {
        iv.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)  iv.getLayoutParams();
        new DeskSelection().update( iv, lParams, false);
        new DeskSelection().update(back, lParams, true);
       /* if(ds.contain(iv))
        {
          new DeskSelection().setNumber(--numberOfRemainingCards);
       }
       else  new DeskSelection().setNumber(++numberOfRemainingCards);
    }*/

    }
}
