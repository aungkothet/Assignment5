package io.github.aungkothet.padc.assignment5.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aungkothet.padc.assignment5.R;

public class HouseDetailActivity extends BaseActivity {

    public static Intent newIntent(Context context){
        return new Intent(context,HouseDetailActivity.class);
    }

    @BindView(R.id.img_back_btn)
    ImageView imgBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);
        ButterKnife.bind(this);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
