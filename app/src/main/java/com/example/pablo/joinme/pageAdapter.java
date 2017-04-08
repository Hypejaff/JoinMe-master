package com.example.pablo.joinme;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

// setCurrentTabFragment

public class pageAdapter extends FragmentPagerAdapter {
    final int NB_TAB = 2  ;
    private String tab_title[] = new String[]{"Actuality","Create an event !"} ;
    private Context context ;

    public pageAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context ;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Actuality() ;
            case 1:
                return new Create_event() ;
        }
        return null;
    }


    @Override
    public int getCount() {
        return NB_TAB;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tab_title[position];
    }


}