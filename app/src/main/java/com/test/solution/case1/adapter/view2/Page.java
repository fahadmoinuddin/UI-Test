package com.test.solution.case1.adapter.view2;

/**
 * POJO class for View 2 view pager adapter.
 *
 * created by fahad on 07/08/2018
 */
public class Page {

    private String text; // text to be displayed in fragment
    private int color; // background color for the fragment

    public Page(String text, int color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
