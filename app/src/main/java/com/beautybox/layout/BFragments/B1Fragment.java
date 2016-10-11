package com.beautybox.layout.BFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beautybox.R;
import com.beautybox.layout.BMainActivity;
import com.beautybox.layout.MainActivity;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.StackTransformer;

import static android.content.Context.MODE_PRIVATE;
import static com.beautybox.BeautyBoxApplication.MODE_A;
import static com.beautybox.BeautyBoxApplication.MODE_B;
import static com.beautybox.BeautyBoxApplication.SP_PREVIOUS_MODE;


public class B1Fragment extends Fragment {
    private View rootView;
    private final int PAGE_SIZE = 3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("test", "a1");

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_b1, container, false);
            VerticalViewPager viewPager = (VerticalViewPager) rootView.findViewById(R.id.verticalViewPager);
            viewPager.setPageTransformer(true, new StackTransformer());
            viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        }

        return rootView;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private Fragment b11Fragment;
        private Fragment b12Fragment;
        private Fragment b13Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.

            switch (position) {
                case 0:
                    if (b11Fragment == null) {
                        b11Fragment = new B11Fragment();
                    }
                    return b11Fragment;
                case 1:
                    if (b12Fragment == null) {
                        b12Fragment = new B12Fragment();
                    }
                    return b12Fragment;
                case 2:
                    if (b13Fragment == null) {
                        b13Fragment = new B13Fragment();
                    }
                    return b13Fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}
