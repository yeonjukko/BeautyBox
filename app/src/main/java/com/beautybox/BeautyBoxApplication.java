package com.beautybox;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.tsengvn.typekit.Typekit;

/**
 * Created by yeonjukko on 2016. 10. 6..
 */

public class BeautyBoxApplication extends Application {

    public static final String SP_PREVIOUS_MODE = "PreviousMode";
    public static final String MODE_A = "MODE_A";
    public static final String MODE_B = "MODE_B";


    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance().addNormal(Typekit.createFromAsset(this, "montserrat_extralight.otf"))
                .addBold(Typekit.createFromAsset(this, "montserrat_medium.otf"));

        sharedPreferences = getSharedPreferences(SP_PREVIOUS_MODE,MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editor.putString(SP_PREVIOUS_MODE,MODE_A);

        Log.d("test","app:"+sharedPreferences.getString(SP_PREVIOUS_MODE,null));
        editor.commit();


    }
}
