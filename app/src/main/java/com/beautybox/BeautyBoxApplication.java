package com.beautybox;

import android.app.Application;

/**
 * Created by yeonjukko on 2016. 10. 6..
 */

public class BeautyBoxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "assets/montserrat_extralight.otf"))
//                .addBold(Typekit.createFromAsset(this, "assets/montserrat_medium.otf"));
    }
}
