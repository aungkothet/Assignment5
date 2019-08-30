package io.github.aungkothet.padc.assignment5.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.aungkothet.padc.assignment5.R;
import io.github.aungkothet.padc.assignment5.adapters.TabLayoutAdapter;
import io.github.aungkothet.padc.assignment5.delegates.HouseItemDelegate;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrameFragment extends Fragment {

    HouseItemDelegate houseItemDelegate;

    public HomeFrameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        houseItemDelegate = (HouseItemDelegate)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_frame, container, false);
        TabLayoutAdapter tabLayoutAdapter = new TabLayoutAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = view.findViewById(R.id.viewPager);
        pager.setAdapter(tabLayoutAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
        return  view;

    }

}
