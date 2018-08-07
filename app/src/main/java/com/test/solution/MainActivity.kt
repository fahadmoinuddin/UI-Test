package com.test.solution

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.test.solution.case1.ui.UIFragment
import com.test.solution.case1.ui.view2.View2Fragment
import com.test.solution.case2.locations.LocationsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity class which contains a bottom navigation view with 2 tabs - Case 1 and Case 2.
 * Case 1 - 5 views
 * Case 2 - Locations from URL
 *
 * created by fahad on 07/08/2018
 */
class MainActivity : BaseActivity(), FragmentListener, View2Fragment.OnPagerFragmentClickListener {

    //bottom navigation item selected listener. Based on the selection, the appropriate fragment is replaced and the action bar title is set
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_case_1 -> {
                supportActionBar?.title = getString(R.string.title_case_1)
                showFragment(UIFragment.getInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_case_2 -> {
                supportActionBar?.title = getString(R.string.title_case_2)
                showFragment(LocationsFragment.getInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        //select the first tab by default
        navigation.selectedItemId = R.id.navigation_case_1
    }

    //function to replace the fragment in the container
    fun showFragment(fragment : Fragment){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    //show/close progress dialog
    override fun showProgress(show: Boolean) {
        if(show) showProgress() else closeProgress()
    }

    //show toast with message
    override fun showToastInActivity(message: String) {
        showToast(message)
    }

    //show fragment page number on view pager click
    override fun onFragmentClicked(position: Int) {
        showToast(String.format(getString(R.string.view_pager_click), position))
    }
}
