package com.beautybox;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;


/**
 * Created by yeonjukko on 2016. 10. 6..
 */

public class BeautyBoxActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));

    }


}
