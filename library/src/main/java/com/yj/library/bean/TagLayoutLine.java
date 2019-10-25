package com.yj.library.bean;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * desc:  每一行的View
 * time: 2019/10/24
 *
 * @author 银进
 */
public class TagLayoutLine {
    private List<View> viewList = new ArrayList<>();
    private int maxWidth;
    private int lineHeight;
    private int space;


    public TagLayoutLine(int maxWidth, int space) {
        this.maxWidth = maxWidth;
        this.space = space;
        this.lineHeight = 0;
    }

    public List<View> getViewList() {
        return viewList;
    }

    public void addView(View view) {
        if (lineHeight < view.getMeasuredHeight()) {
            lineHeight = view.getMeasuredHeight();
        }
        viewList.add(view);
    }

    public boolean canAdd(View view) {
        int childSumWidth = 0;
        for (View viewChild : viewList) {
            int measuredWidth = viewChild.getMeasuredWidth();
            childSumWidth += measuredWidth;
        }
        childSumWidth += view.getMeasuredWidth();
        if (maxWidth >=(childSumWidth+viewList.size()*space)) return true;
        return false;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
}
