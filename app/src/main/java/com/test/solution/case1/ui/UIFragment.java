package com.test.solution.case1.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.solution.FragmentListener;
import com.test.solution.R;
import com.test.solution.case1.adapter.view1.View1Adapter;
import com.test.solution.case1.adapter.view2.Page;
import com.test.solution.case1.adapter.view2.View2Adapter;
import com.test.solution.case1.ui.view2.View2Fragment;
import com.test.solution.case1.viewmodel.UIViewModel;
import com.test.solution.databinding.FragmentCase1Binding;

import java.util.Arrays;
import java.util.List;

/**
 * Main Fragment class for View 2
 *
 * created by fahad on 07/08/2018
 */
public class UIFragment extends Fragment implements View1Adapter.OnItemClickListener{

    private UIViewModel viewModel;
    private FragmentCase1Binding binding;

    public static UIFragment getInstance(){
        return new UIFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create the viewmodel object
        viewModel = ViewModelProviders.of(this).get(UIViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Obtain the reference to the layout using databinding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_1, container, false);

        //connect the layout with viewmodel for databinding
        binding.setViewModel(viewModel);

        //Set the views
        setView1();
        setView2();

        return binding.getRoot();
    }

    //Set the list, layout manager and adapter for View 1 - recycler view
    private void setView1(){
        List<String> list = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5");
        binding.view1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new View1Adapter(list, this));
    }

    //Set the list and pager adapter for View 2 - view pager
    private void setView2(){
        //Random text and colors for the fragments
        List<Page> list = Arrays.asList(
                new Page("Page 1", R.color.md_amber_700),
                new Page("Page 2", R.color.md_red_500),
                new Page("Page 3", R.color.md_teal_500),
                new Page("Page 4", R.color.md_purple_500)
        );

        //Number of fragments are set to 4
        final PagerAdapter pagerAdapter = new View2Adapter(getChildFragmentManager(), 4, list);
        binding.view2.setAdapter(pagerAdapter);
    }

    //View 1 adapter item click callback. Sets the text in View 4
    @Override
    public void onItemClick(String text) {
        viewModel.setView4Text(text);
    }
}
