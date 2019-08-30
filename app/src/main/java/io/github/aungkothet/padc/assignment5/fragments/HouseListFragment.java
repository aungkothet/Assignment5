package io.github.aungkothet.padc.assignment5.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.adapters.HouseRecyclerAdapter;
import io.github.aungkothet.padc.assignment5.data.models.HouseModel;
import io.github.aungkothet.padc.assignment5.data.models.HouseModelImpl;
import io.github.aungkothet.padc.assignment5.data.vos.HouseVo;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;
import io.github.aungkothet.padc.assignment5.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HouseListFragment extends Fragment {


    HouseItemDelegate houseItemDelegate;

    public HouseListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseItemDelegate = (HouseItemDelegate) context;
    }

    RecyclerView houseListRV;
    ProgressBar pbLoading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_house_list, container, false);
        houseListRV = view.findViewById(R.id.house_list_recyclerview);
        final HouseRecyclerAdapter adapter = new HouseRecyclerAdapter(houseItemDelegate);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        pbLoading = view.findViewById(R.id.pb_loading);

        houseListRV.setLayoutManager(layoutManager);
        HouseModelImpl.getObjInstance().getHouseList(Constants.ACCESS_TOKEN,
                new HouseModel.GetHouseListFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseVo> houseVoList) {
                adapter.setViewData(houseVoList);
                pbLoading.setVisibility(View.GONE);
                houseListRV.setAdapter(adapter);
            }

            @Override
            public void onFailure(String errMessage) {
                houseItemDelegate.showErrorSnack(errMessage);
            }
        });
        return view;

    }
}
