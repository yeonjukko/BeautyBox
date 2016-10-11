package com.beautybox.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.beautybox.R;
import com.beautybox.layout.AFragments.A1Fragment;
import com.beautybox.layout.AFragments.A2Fragment;
import com.beautybox.layout.AFragments.A3Fragment;
import com.beautybox.layout.BFragments.B1Fragment;
import com.beautybox.layout.BFragments.B2Fragment;
import com.beautybox.layout.BFragments.B3Fragment;

import static com.beautybox.BeautyBoxApplication.MODE_B;
import static com.beautybox.BeautyBoxApplication.SP_PREVIOUS_MODE;

public class BMainActivity extends AppCompatActivity {
    private static final int PAGE_SIZE = 3;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));



    }


    private class PagerAdapter extends FragmentPagerAdapter {

        private Fragment b1Fragment;
        private Fragment b2Fragment;
        private Fragment b3Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.


            switch (position) {
                case 0:
                    if (b1Fragment == null) {
                        b1Fragment = new B1Fragment();
                    }
                    return b1Fragment;
                case 1:
                    if (b2Fragment == null) {
                        b2Fragment = new B2Fragment();
                    }
                    return b2Fragment;
                case 2:
                    if (b3Fragment == null) {
                        b3Fragment = new B3Fragment();
                    }
                    return b3Fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}
