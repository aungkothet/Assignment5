package io.github.aungkothet.padc.assignment5;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.github.aungkothet.padc.assignment5.activities.BaseActivity;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;
import io.github.aungkothet.padc.assignment5.fragments.HomeFrameFragment;

public class MainActivity extends BaseActivity implements HouseItemDelegate, View.OnClickListener {

    TextView tvForYou,tvShareMe,tvFav,tvDiscover,tvProfile,tvMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,new HomeFrameFragment())
                .commit();

        tvForYou = findViewById(R.id.tv_menu_for_you);
        tvShareMe = findViewById(R.id.tv_menu_share_me);
        tvFav = findViewById(R.id.tv_menu_fav);
        tvDiscover = findViewById(R.id.tv_menu_discover);
        tvProfile = findViewById(R.id.tv_menu_profile);
        tvMore = findViewById(R.id.tv_menu_more);

        tvForYou.setOnClickListener(this);
        tvShareMe.setOnClickListener(this);
        tvFav.setOnClickListener(this);
        tvDiscover.setOnClickListener(this);
        tvProfile.setOnClickListener(this);
        tvMore.setOnClickListener(this);
    }

    @Override
    public void onItemClicked() {
        startActivity(HouseDetailActivity.newIntent(MainActivity.this));
    }

    @Override
    public void showErrorSnack(String errMessage) {
        showSnack(errMessage);
    }

    @Override
    public void onClick(View v) {
        tvForYou.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        tvShareMe.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        tvFav.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        tvDiscover.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        tvProfile.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        tvMore.setTextColor(getResources().getColor(R.color.colorImageIconBg));
        switch (v.getId()){
            case R.id.tv_menu_discover: tvDiscover.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_for_you: tvForYou.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_share_me: tvShareMe.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_fav: tvFav.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_profile: tvProfile.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_more: tvMore.setTextColor(getResources().getColor(R.color.colorAccent));break;
            default : break;
        }

    }
}
