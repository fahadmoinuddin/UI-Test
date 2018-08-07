package com.test.solution.case1.adapter.view1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.solution.databinding.View1ItemBinding;

import java.util.List;

/**
 * Adapter for recyclerview in View 1. Data binding is used in the adapter.
 *
 * created by fahad on 07/08/2018
 */
public class View1Adapter extends RecyclerView.Adapter<View1Adapter.ViewHolder> {

    //item list containing the text to be shown
    private List<String> list;

    private OnItemClickListener onItemClickListener;

    //Callback interface for item click
    public interface OnItemClickListener {
        void onItemClick(String text);
    }

    public View1Adapter(List<String> list, OnItemClickListener onItemClickListener){
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View1ItemBinding binding = View1ItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final View1ItemBinding itemBinding; //Layout data binding object

        public ViewHolder(View1ItemBinding itemBinding){
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        //Displaying the view with the text
        public void bind(final String item){
            itemBinding.setItem(item);
            itemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //callback to the activity to populate the clicked text in View 4
                    onItemClickListener.onItemClick(item);
                }
            });
            itemBinding.executePendingBindings();
        }
    }
}
