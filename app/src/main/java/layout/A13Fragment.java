package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beautybox.R;

import java.util.ArrayList;
import java.util.List;

public class A13Fragment extends Fragment {
    List<CosmeticModel> items;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView ==null) {
            rootView = inflater.inflate(R.layout.fragment_a13, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.cosmeticRecyclerView);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            items = new ArrayList<>();
            items.add(new CosmeticModel("Georgio Armani", "Blushing Fabric \n#01 Petal Coral", false));
            items.add(new CosmeticModel("Make Up Forever", "Aqua Brown \n#15 Blond", false));
            items.add(new CosmeticModel("Shine Easy Glam", "Eye Shadow Palette 2", false));
            items.add(new CosmeticModel("ARITAUM", "Micro Mascara \n#01 Noir Black", false));
            items.add(new CosmeticModel("Pro 8 Chung Dam", "Stay On Gel Eyeliner \nDark Brown", false));
            items.add(new CosmeticModel("VDL", "Brightening Tone Concealer \n#A201", false));
            items.add(new CosmeticModel("Missha", "SILKY LASTRING LIP PENCIL \n#ANTIQUE BOX", false));
            items.add(new CosmeticModel("Estee Lauder", "Double Wear Cushion Foundation \nCool Bone", false));
            items.add(new CosmeticModel("MAC", "Mineralize Skin Finish \nLightscapade", false));

            recyclerView.setAdapter(new CosmeticRecyclerViewAdapter(this, items));

        }
        Log.d("test",this.getActivity().getLocalClassName());

        return rootView;
    }






}
