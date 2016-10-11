package com.beautybox.manager;

import com.beautybox.R;

/**
 * Created by yeonjukko on 2016. 10. 4..
 */

public class CosmeticModel {
    private String cosmeticName;
    private String cosmeticDescription;
    private boolean isChecked = false;
    private int imagePath;


    public CosmeticModel(String cosmeticName, String cosmeticDescription, boolean isChecked, int imagePath){
        this.cosmeticName = cosmeticName;
        this.cosmeticDescription = cosmeticDescription;
        this.isChecked = isChecked;
        this.imagePath = imagePath;
    }


    public String getCosmeticName() {
        return cosmeticName;
    }

    public String getCosmeticDescription() {
        return cosmeticDescription;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public int getImagePath() {
        return imagePath;
    }

}
