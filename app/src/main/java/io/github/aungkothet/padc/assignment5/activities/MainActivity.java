package io.github.aungkothet.padc.assignment5.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.data.models.HouseModelImpl;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;
import io.github.aungkothet.padc.assignment5.fragments.HomeFrameFragment;

public class MainActivity extends BaseActivity implements HouseItemDelegate, View.OnClickListener {

    public static String IE_SEARCH = "query";
    public static String IE_LAYOUT= "layout";

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

    @BindView(R.id.et_search)
    EditText searchBox;

    Intent intent;
    LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intent = new Intent("house-layout");
        intent.putExtra(IE_LAYOUT,"list");
        localBroadcastManager.sendBroadcast(intent);

        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,new HomeFrameFragment())
                .commit();
        tvForYou.setOnClickListener(this);
        tvShareMe.setOnClickListener(this);
        tvFav.setOnClickListener(this);
        tvDiscover.setOnClickListener(this);
        tvProfile.setOnClickListener(this);
        tvMore.setOnClickListener(this);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intent intent = new Intent("house-search");
                intent.putExtra(IE_SEARCH,s.toString());
                localBroadcastManager.sendBroadcast(intent);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onItemClicked(int id) {
        startActivity(HouseDetailActivity.newIntent(MainActivity.this,id));
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
            case R.id.tv_menu_discover: intent.putExtra(IE_LAYOUT,"grid"); tvDiscover.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_for_you: intent.putExtra(IE_LAYOUT,"list"); tvForYou.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_share_me: intent.putExtra(IE_LAYOUT,"list"); tvShareMe.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_fav: intent.putExtra(IE_LAYOUT,"grid"); tvFav.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_profile: intent.putExtra(IE_LAYOUT,"list"); tvProfile.setTextColor(getResources().getColor(R.color.colorAccent));break;
            case R.id.tv_menu_more: intent.putExtra(IE_LAYOUT,"grid"); tvMore.setTextColor(getResources().getColor(R.color.colorAccent));break;
            default : intent.putExtra(IE_LAYOUT,"list"); break;
        }

        localBroadcastManager.sendBroadcast(intent);

    }
}
