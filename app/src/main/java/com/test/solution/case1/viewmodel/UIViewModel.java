package com.test.solution.case1.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

/**
 * View model class for the Main UI fragment. This class has observables for databinding with the layout.
 *
 * created by fahad on 07/08/2018
 */
public class UIViewModel extends ViewModel {

    //Observable fields for databinding
    private ObservableField<String> view4Text = new ObservableField<>(); //sets the text in View 4 based on the selection in View 1
    private ObservableField<String> color = new ObservableField<>(); // sets the color of the button in View 5

    public ObservableField<String> getView4Text() {
        return view4Text;
    }

    public void setView4Text(ObservableField<String> view4Text) {
        this.view4Text = view4Text;
    }

    public ObservableField<String> getColor() {
        return color;
    }

    public void setColor(ObservableField<String> color) {
        this.color = color;
    }

    //Called from the fragment on View 1 list item click
    public void setView4Text(String text){
        view4Text.set(text);
    }

    //Called from the layout on View 5 button click
    public void colorButtonClicked(String tag){
        color.set(tag);
    }
}
