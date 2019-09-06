package io.github.aungkothet.padc.assignment5.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.activities.MainActivity;
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

    List<HouseVo> resultList = new ArrayList<>();
    HouseRecyclerAdapter adapter;
    RecyclerView houseListRV;
    ProgressBar pbLoading;
    HouseItemDelegate houseItemDelegate;
    LinearLayoutManager layoutManager;
    public HouseListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseItemDelegate = (HouseItemDelegate) context;
    }


    private BroadcastReceiver listener = new BroadcastReceiver() {
        @Override
        public void onReceive( Context context, Intent intent ) {
            String query = intent.getStringExtra(MainActivity.IE_SEARCH);
            resultList = HouseModelImpl.getObjInstance().filterHouse(query);
            adapter.setViewData(resultList);
        }
    };

    private BroadcastReceiver layoutListener = new BroadcastReceiver() {
        @Override
        public void onReceive( Context context, Intent intent ) {
            String layout = intent.getStringExtra(MainActivity.IE_LAYOUT);
            if(layout.equals("list"))
            {
                layoutManager = new LinearLayoutManager(context);

            } else {
                layoutManager = new GridLayoutManager(context,2);
            }
            houseListRV.setLayoutManager(layoutManager);
            houseListRV.setAdapter(adapter);
        }
    };

    @Override
    public void onDestroyView() {
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(listener);
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_house_list, container, false);
        LocalBroadcastManager.getInstance(view.getContext()).registerReceiver(listener,
                new IntentFilter("house-search"));

        LocalBroadcastManager.getInstance(view.getContext()).registerReceiver(layoutListener,
                new IntentFilter("house-layout"));

        houseListRV = view.findViewById(R.id.house_list_recyclerview);
        adapter = new HouseRecyclerAdapter(houseItemDelegate);
        layoutManager = new LinearLayoutManager(view.getContext());
        houseListRV.setLayoutManager(layoutManager);
        houseListRV.setAdapter(adapter);

        pbLoading = view.findViewById(R.id.pb_loading);


        HouseModelImpl.getObjInstance().getHouseList(Constants.ACCESS_TOKEN,
                new HouseModel.GetHouseListFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseVo> houseVoList) {
                adapter.setViewData(houseVoList);
                pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String errMessage) {
                houseItemDelegate.showErrorSnack(errMessage);
            }
        });
        return view;
    }
}
