package com.beautybox.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.beautybox.BeautyBoxActivity;
import com.beautybox.R;

import static com.beautybox.BeautyBoxApplication.MODE_A;
import static com.beautybox.BeautyBoxApplication.MODE_B;
import static com.beautybox.BeautyBoxApplication.SP_PREVIOUS_MODE;

public class MainActivity extends BeautyBoxActivity implements View.OnClickListener {
    LinearLayout linearLayoutSwitchMode;
    ImageView imageView;
    SharedPreferences sharedPreferences;

    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SP_PREVIOUS_MODE,MODE_PRIVATE);
        mode = sharedPreferences.getString(SP_PREVIOUS_MODE, null);

        imageView = (ImageView) findViewById(R.id.imageView);
        setImageView();

        startActivity(new Intent(MainActivity.this,BluetoothActivity.class));

        linearLayoutSwitchMode = (LinearLayout) findViewById(R.id.linearLayoutSwitchMode);
        linearLayoutSwitchMode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayoutSwitchMode:
                if(isModeA()){
                    startActivity(new Intent(MainActivity.this, BMainActivity.class));
                    finish();
                }else if(isModeB()){
                    startActivity(new Intent(MainActivity.this, AMainActivity.class));
                    finish();

                }

                break;
        }
    }

    private boolean isModeA(){
        return mode.equals(MODE_A);
    }

    private boolean isModeB(){
        return mode.equals(MODE_B);
    }


    private void setImageView() {
        if (mode.equals(MODE_A)) {
            imageView.setImageResource(R.drawable.menu);
        } else if (mode.equals(MODE_B)) {
            imageView.setImageResource(R.drawable.menu2);
        }
    }
}
