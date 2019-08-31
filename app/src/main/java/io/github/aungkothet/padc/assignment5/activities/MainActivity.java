package io.github.aungkothet.padc.assignment5.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;
import io.github.aungkothet.padc.assignment5.fragments.HomeFrameFragment;

public class MainActivity extends BaseActivity implements HouseItemDelegate, View.OnClickListener {

    @BindView(R.id.tv_menu_for_you)
    TextView tvForYou;

    @BindView(R.id.tv_menu_share_me)
    TextView tvShareMe;

    @BindView(R.id.tv_menu_fav)
    TextView tvFav;

    @BindView(R.id.tv_menu_discover)
    TextView tvDiscover;

    @BindView(R.id.tv_menu_profile)
    TextView tvProfile;

    @BindView(R.id.tv_menu_more)
    TextView tvMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,new HomeFrameFragment())
                .commit();
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
