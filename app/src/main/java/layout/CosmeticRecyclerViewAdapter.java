package layout;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.beautybox.R;

import java.util.List;

/**
 * Created by yeonjukko on 2016. 10. 4..
 */

public class CosmeticRecyclerViewAdapter extends RecyclerView.Adapter<CosmeticRecyclerViewAdapter.CosmeticViewHolder> {
    private Fragment fragment;
    private List<CosmeticModel> items;
    private static boolean isModeA = false;

    public CosmeticRecyclerViewAdapter(Fragment fragment, List<CosmeticModel> items) {
        this.fragment = fragment;
        this.items = items;
        if (fragment instanceof A13Fragment) {
            isModeA = true;
        }
    }

    @Override
    public CosmeticViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CosmeticViewHolder(LayoutInflater.from(fragment.getContext())
                .inflate(R.layout.recycler_view_cosmetic, parent,false));
    }

    @Override
    public void onBindViewHolder(CosmeticViewHolder holder, int position) {
        CosmeticModel cosmeticModel = items.get(position);
        holder.imageView.setImageResource(cosmeticModel.getImagePath());
        holder.textViewName.setText(cosmeticModel.getCosmeticName());
        holder.textViewDescription.setText(cosmeticModel.getCosmeticDescription());
        holder.checkBox.setChecked(cosmeticModel.isChecked());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CosmeticViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;
        CheckBox checkBox;
        ImageView imageView;

        public CosmeticViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.textViewName);
            textViewDescription = (TextView) v.findViewById(R.id.textViewDesc);
            checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            imageView = (ImageView) v.findViewById(R.id.imageView);


        }
    }
}
