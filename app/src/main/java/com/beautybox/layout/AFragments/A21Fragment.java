package com.beautybox.layout.AFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beautybox.R;
import com.beautybox.layout.MainActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.beautybox.BeautyBoxApplication.MODE_A;
import static com.beautybox.BeautyBoxApplication.SP_PREVIOUS_MODE;


public class A21Fragment extends Fragment {
    View rootView;
    private ImageView mImageMenuButton;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("test", "a21");

        if (rootView == null) {

            rootView = inflater.inflate(R.layout.fragment_a21, container, false);
        }

        mImageMenuButton = (ImageView) rootView.findViewById(R.id.imageMenuButton);
        mImageMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getContext().getSharedPreferences(SP_PREVIOUS_MODE,MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString(SP_PREVIOUS_MODE,MODE_A);
                editor.commit();

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();

                Log.d("test","a main:"+sharedPreferences.getString(SP_PREVIOUS_MODE,null));


            }
        });


        return rootView;

    }

}