package com.test.solution.case1.adapter.view2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.test.solution.case1.ui.view2.View2Fragment;

import java.util.List;

/**
 * Adapter for view pager in View 2.
 *
 * created by fahad on 07/08/2018
 */

public class View2Adapter extends FragmentStatePagerAdapter {

    private int numOfPages; // number of fragments in view pager
    private List<Page> list; // page list

    public View2Adapter(FragmentManager fm, int numOfPages, List<Page> list) {
        super(fm);
        this.numOfPages = numOfPages;
        this.list = list;
    }

    /**
     * Page object and the position is passed to the fragment.
     * @param position - position of the current visible fragment
     */
    @Override
    public Fragment getItem(int position) {
        return View2Fragment.create(list.get(position), position);
    }

    @Override
    public int getCount() {
        return numOfPages;
    }
}
