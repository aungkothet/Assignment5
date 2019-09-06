package io.github.aungkothet.padc.assignment5.views.holders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;

public class HouseViewHolder extends BaseViewHolder<HouseVo> {

    @BindView(R.id.img_house)
    ImageView imgHouse;

    @BindView(R.id.tv_house_area)
    TextView tvHouseArea;

    @BindView(R.id.tv_house_price)
    TextView tvHousePrice;

    @BindView(R.id.tv_house_location)
    TextView tvHouseLocation;

    private Context context;

    public HouseViewHolder(@NonNull View itemView, final HouseItemDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onItemClicked(mData.getId());
            }
        });
    }


    @Override
    public void bindData(HouseVo data) {
        mData = data;
        Glide.with(itemView).load(data.getHouseImageUrl()).into(imgHouse);
        tvHouseArea.setText(context.getResources()
                .getString(R.string.str_house_area_template, data.getSquare_feet()));
        tvHousePrice.setText("$ " + data.getPrice());
        tvHouseLocation.setText(data.getAddress());

    }
}
