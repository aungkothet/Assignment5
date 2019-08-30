package io.github.aungkothet.padc.assignment5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import io.github.aungkothet.padc.assignment5.activities.BaseActivity;

public class HouseDetailActivity extends BaseActivity {

    public static Intent newIntent(Context context){
        return new Intent(context,HouseDetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        ImageView imgBackBtn = findViewById(R.id.img_back_btn);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
