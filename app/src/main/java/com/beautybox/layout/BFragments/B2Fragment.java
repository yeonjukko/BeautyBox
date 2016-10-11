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

public class B2Fragment extends Fragment {
    private View rootView;
    private final int PAGE_SIZE = 3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_b2, container, false);
            VerticalViewPager viewPager = (VerticalViewPager) rootView.findViewById(R.id.verticalViewPager);
            viewPager.setPageTransformer(true, new StackTransformer());
            viewPager.setAdapter(new PagerAdapter(getChildFragmentManager()));
        }


        return rootView;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private Fragment b21Fragment;
        private Fragment b22Fragment;
        private Fragment b23Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            switch (position) {
                case 0:
                    Log.d("test", "a2");

                    if (b21Fragment == null) {
                        b21Fragment = new B21Fragment();
                    }
                    return b21Fragment;
                case 1:
                    if (b22Fragment == null) {
                        b22Fragment = new B22Fragment();
                    }
                    return b22Fragment;

                case 2:
                    if (b23Fragment == null) {
                        b23Fragment = new B23Fragment();
                    }
                    return b23Fragment;

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}

