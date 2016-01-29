package com.matchandfind.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.matchandfind.ui.fragment.MapsFragment;
import com.matchandfind.ui.fragment.PersonFragment;

public class VPFragmentsAdapter extends FragmentPagerAdapter{

    public VPFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0: {
                fragment = PersonFragment.getInstance();
                break;
            }
            case 1: {
                fragment = MapsFragment.getInstance();
                break;
            }
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
