package com.beautybox;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.beautybox.layout.A1Fragment;
import com.beautybox.layout.A2Fragment;
import com.beautybox.layout.A3Fragment;

public class MainActivity extends BeautyBoxActivity {
    private static final int PAGE_SIZE = 3;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private Fragment a1Fragment;
        private Fragment a2Fragment;
        private Fragment a3Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.


            switch (position) {
                case 0:
                    if (a1Fragment == null) {
                        a1Fragment = new A1Fragment();
                    }
                    return a1Fragment;
                case 1:
                    if (a2Fragment == null) {
                        a2Fragment = new A2Fragment();
                    }
                    return a2Fragment;
                case 2:
                    if (a3Fragment == null) {
                        a3Fragment = new A3Fragment();
                    }
                    return a3Fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}
