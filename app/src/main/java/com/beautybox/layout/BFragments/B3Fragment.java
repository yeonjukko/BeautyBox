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
import com.beautybox.layout.MainActivity;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.StackTransformer;

import static android.content.Context.MODE_PRIVATE;
import static com.beautybox.BeautyBoxApplication.MODE_B;
import static com.beautybox.BeautyBoxApplication.SP_PREVIOUS_MODE;

public class B3Fragment extends Fragment {
    private View rootView;
    private final int PAGE_SIZE = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_b3, container, false);
            VerticalViewPager viewPager = (VerticalViewPager) rootView.findViewById(R.id.verticalViewPager);
            viewPager.setPageTransformer(true, new StackTransformer());
            viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        }

        return rootView;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private Fragment b31Fragment;
        private Fragment b32Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            switch (position) {
                case 0:
                    if (b31Fragment == null) {
                        b31Fragment = new B31Fragment();
                    }
                    return b31Fragment;
                case 1:
                    if (b32Fragment == null) {
                        b32Fragment = new B32Fragment();
                    }
                    return b32Fragment;



            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}
