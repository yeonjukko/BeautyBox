package com.beautybox.layout.BFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beautybox.R;
import com.beautybox.manager.CosmeticModel;
import com.beautybox.manager.CosmeticRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class B13Fragment extends Fragment {
    List<CosmeticModel> items;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView ==null) {
            rootView = inflater.inflate(R.layout.fragment_b13, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.cosmeticRecyclerView);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            items = new ArrayList<>();
            items.add(new CosmeticModel("Georgio Armani", "Blushing Fabric \n#01 Petal Coral", false,R.drawable.ic_item_1));
            items.add(new CosmeticModel("Shine Easy Glam", "Eye Shadow Palette 2", false,R.drawable.ic_item_2));
            items.add(new CosmeticModel("ARITAUM", "Micro Mascara \n#01 Noir Black", false,R.drawable.ic_item_3));
            items.add(new CosmeticModel("Pro 8 Chung Dam", "Stay On Gel Eyeliner \nDark Brown", false,R.drawable.ic_item_4));
            items.add(new CosmeticModel("VDL", "Brightening Tone Concealer \n#A201", false,R.drawable.ic_item_5));
            items.add(new CosmeticModel("Missha", "SILKY LASTRING LIP PENCIL \n#ANTIQUE BOX", false,R.drawable.ic_item_6));
            items.add(new CosmeticModel("Estee Lauder", "Double Wear Cushion Foundation \nCool Bone", false,R.drawable.ic_item_7));
            items.add(new CosmeticModel("CLIO", "Virgin Kiss Tinted Lip \n# 13", false,R.drawable.ic_item_8));
            items.add(new CosmeticModel("Make Up Forever", "Aqua Brown \n#15 Blond", false,R.drawable.ic_item_9));
            items.add(new CosmeticModel("MAC", "Mineralize Skin Finish \nLightscapade", false,R.drawable.ic_item_10));

            recyclerView.setAdapter(new CosmeticRecyclerViewAdapter(this, items));

        }
        Log.d("test",this.getActivity().getLocalClassName());

        return rootView;
    }






}
