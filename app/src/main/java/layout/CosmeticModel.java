package layout;

import com.beautybox.R;

/**
 * Created by yeonjukko on 2016. 10. 4..
 */

public class CosmeticModel {
    private String cosmeticName;
    private String cosmeticDescription;
    private boolean isChecked = false;
    private int imagePath = R.mipmap.ic_launcher;

    CosmeticModel(String cosmeticName, String cosmeticDescription, boolean isChecked){
        this.cosmeticName = cosmeticName;
        this.cosmeticDescription = cosmeticDescription;
        this.isChecked = isChecked;
    }

    CosmeticModel(String cosmeticName, String cosmeticDescription, boolean isChecked, int imagePath){
        this.cosmeticName = cosmeticName;
        this.cosmeticDescription = cosmeticDescription;
        this.isChecked = isChecked;
        this.imagePath = imagePath;
    }


    public String getCosmeticName() {
        return cosmeticName;
    }

    public void setCosmeticName(String cosmeticName) {
        this.cosmeticName = cosmeticName;
    }

    public String getCosmeticDescription() {
        return cosmeticDescription;
    }

    public void setCosmeticDescription(String cosmeticDescription) {
        this.cosmeticDescription = cosmeticDescription;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
