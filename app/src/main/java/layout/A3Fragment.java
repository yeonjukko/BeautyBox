package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beautybox.R;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.StackTransformer;

public class A3Fragment extends Fragment {
    private View rootView;
    private final int PAGE_SIZE = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("test", "a2");

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_a3, container, false);
            VerticalViewPager viewPager = (VerticalViewPager) rootView.findViewById(R.id.verticalViewPager);
            viewPager.setPageTransformer(true, new StackTransformer());
            viewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager()));
        }
        return rootView;
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private Fragment a31Fragment;
        private Fragment a32Fragment;
        private Fragment a33Fragment;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            switch (position) {
                case 0:
                    if (a31Fragment == null) {
                        a31Fragment = new A31Fragment();
                    }
                    return a31Fragment;
                case 1:
                    if (a32Fragment == null) {
                        a32Fragment = new A32Fragment();
                    }
                    return a32Fragment;

                case 2:
                    if (a33Fragment == null) {
                        a33Fragment = new A33Fragment();
                    }
                    return a33Fragment;

            }
            return null;
        }

        @Override
        public int getCount() {
            return PAGE_SIZE;
        }

    }

}
