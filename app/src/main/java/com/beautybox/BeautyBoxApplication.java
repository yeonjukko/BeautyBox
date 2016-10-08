package com.beautybox;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by yeonjukko on 2016. 10. 6..
 */

public class BeautyBoxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "montserrat_extralight.otf"))
                .addBold(Typekit.createFromAsset(this, "montserrat_medium.otf"));
    }
}
