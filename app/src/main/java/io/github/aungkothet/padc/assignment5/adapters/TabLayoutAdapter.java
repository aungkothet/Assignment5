package io.github.aungkothet.padc.assignment5.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import io.github.aungkothet.padc.assignment5.fragments.HouseListFragment;

public class TabLayoutAdapter extends FragmentStatePagerAdapter {

    public TabLayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new HouseListFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0: title = "Top Collection";break;
            case 1: title = "Near Me";break;
            case 2: title = "Low to high price";break;
            default: title = "Recommended"; break;
        }
        return  title;
    }
}
