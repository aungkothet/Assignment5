package io.github.aungkothet.padc.assignment5.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;

public class HouseDetailActivity extends BaseActivity {

    public static String IE_HOUSE = "houseExtra";

    public static Intent newIntent(Context context, int houseID) {
        Intent intent = new Intent(context, HouseDetailActivity.class);
        intent.putExtra(IE_HOUSE, houseID);
        return intent;
    }

    @BindView(R.id.img_back_btn)
    ImageView imgBackBtn;

    @BindView(R.id.fab_location_detail)
    FloatingActionButton fabLocation;

    @BindView(R.id.tv_house_area)
    TextView houseArea;

    @BindView(R.id.tv_house_location)
    TextView houseLocation;

    @BindView(R.id.tv_price_detail)
    TextView housePrice;

    @BindView(R.id.tv_house_desc)
    TextView houseDescription;

    @BindView(R.id.image_house_detail)
    ImageView houseImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_house_detail);
        int houseID = getIntent().getIntExtra(IE_HOUSE, 0);
        ButterKnife.bind(this);
        final HouseVo houseVo = mHouseModel.getHouseById(houseID);

        bindData(houseVo);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fabLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(getString(R.string.str_geo, houseVo.getLatitude(), houseVo.getLongitude()));
//                Uri gmmIntentUri = Uri.parse(getString(R.string.str_geo, 16.812715, 96.131513));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    private void bindData(HouseVo houseVo) {
        Glide.with(getApplicationContext()).load(houseVo.getHouseImageUrl()).into(houseImage);
        houseArea.setText(getString(R.string.str_house_area_template, houseVo.getSquare_feet()));
        houseLocation.setText(houseVo.getAddress());
        housePrice.setText(getResources().getString(R.string.str_price, NumberFormat.getInstance().format(houseVo.getPrice())));
        houseDescription.setText(houseVo.getDescription());
    }
}
