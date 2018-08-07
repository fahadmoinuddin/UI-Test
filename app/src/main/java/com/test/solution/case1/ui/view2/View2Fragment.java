package com.test.solution.case1.ui.view2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.test.solution.R;
import com.test.solution.case1.adapter.view2.Page;

/**
 * Fragment class for the view pager in View 2
 *
 * created by fahad on 07/08/2018
 */


public class View2Fragment extends Fragment {

    public static final String ARG_TEXT = "text"; // text to be displayed
    public static final String ARG_COLOR = "color"; // background color of the fragment
    public static final String ARG_POSITION = "position"; // page number in the view pager

    private String text;
    private int color;
    private int position;

    private OnPagerFragmentClickListener onPagerFragmentClickListener;

    //Callback interface for fragment click listener
    public interface OnPagerFragmentClickListener{
        void onFragmentClicked(int position);
    }

    //Create an instance of the fragment packing the arguments.
    public static View2Fragment create(Page page, int position){
        View2Fragment fragment = new View2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, page.getText());
        args.putInt(ARG_COLOR, page.getColor());
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onPagerFragmentClickListener = (OnPagerFragmentClickListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retrieve the passed arguments
        text = getArguments().getString(ARG_TEXT);
        color = getArguments().getInt(ARG_COLOR);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_case_1_view_2, container, false);
        LinearLayout main = (LinearLayout)view.findViewById(R.id.main);
        TextView pageText = (TextView)view.findViewById(R.id.page_text);

        //set the text
        pageText.setText(text);

        //set the background color
        main.setBackgroundColor(getResources().getColor(color));

        //click listener - control is transferred back to the activity and a toast is displayed
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPagerFragmentClickListener.onFragmentClicked(position + 1);
            }
        });
        return view;


    }
}
