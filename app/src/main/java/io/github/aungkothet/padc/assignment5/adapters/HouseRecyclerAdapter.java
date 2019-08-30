package io.github.aungkothet.padc.assignment5.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;
import io.github.aungkothet.padc.assignment5.views.holders.HouseViewHolder;

public class HouseRecyclerAdapter extends BaseRecyclerAdapter<HouseViewHolder, HouseVo> {
    HouseItemDelegate delegate;

    public HouseRecyclerAdapter(HouseItemDelegate delegate) {
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_house, viewGroup, false);
        return new HouseViewHolder(view, delegate);
    }

}
