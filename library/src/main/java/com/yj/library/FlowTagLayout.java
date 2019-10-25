package com.yj.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.yj.library.bean.TagBean;
import com.yj.library.bean.TagLayoutLine;
import com.yj.library.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * desc:
 * time: 2019/10/24
 *
 * @author 银进
 */
public class FlowTagLayout extends ViewGroup {
    private List<TagBean> tagBeanList;
    //用来做点击事件用的
    private List<View> flagTagVewList;
    private int tagSpace = 20;
    private int lineSpace = 20;
    private List<TagLayoutLine> tagLayoutLineList;
    private ClickItemListener clickItemListener;

    public FlowTagLayout(Context context) {
        super(context);
        init();
    }

    public FlowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public List<TagBean> getData() {
        return tagBeanList;
    }


    /**
     * 初始化变量
     */
    private void init() {
        tagBeanList = new ArrayList<>();
        tagLayoutLineList = new ArrayList<>();
        flagTagVewList = new ArrayList<>();
    }

    public void setTagSpace(int tagSpace) {
        this.tagSpace = DensityUtils.dp2px(getContext(), tagSpace);
    }

    public void setNewData(List<TagBean> tagBeanList) {
        this.tagBeanList.clear();
        this.tagBeanList.addAll(tagBeanList);
        initView();
        requestLayout();
    }


    public void setNewData(TagBean tagBean) {
        this.tagBeanList.clear();
        this.tagBeanList.add(tagBean);
        initView();
        requestLayout();
    }

    public void addData(List<TagBean> tagBeanList) {
        this.tagBeanList.addAll(tagBeanList);
        initView();
        requestLayout();
    }

    public void addData(TagBean tagBean) {
        this.tagBeanList.add(tagBean);
        initView();
        requestLayout();
    }

    public int getTagSpace() {
        return tagSpace;
    }

    public int getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
        addListener();
    }

    private void addListener() {
        if (clickItemListener == null) {
            return;
        }
        if (!tagLayoutLineList.isEmpty()) {
            for (int i = 0; i < tagLayoutLineList.size(); i++) {
                TagLayoutLine tagLayoutLine = tagLayoutLineList.get(i);
                List<View> viewList = tagLayoutLine.getViewList();
                for (final View view : viewList) {
                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int clickPosition = flagTagVewList.indexOf(v);
                            FlowTagLayout.this.clickItemListener.click(clickPosition);
                            TagBean tagBean = tagBeanList.get(clickPosition);
                            tagBean.setSelect(!tagBean.isSelect());
                            view.setSelected(tagBean.isSelect());
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        tagLayoutLineList = null;
        tagBeanList = null;
        flagTagVewList = null;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        addListener();
    }

    /**
     * 將View添加到ViewGroup
     */
    private void initView() {
        for (TagBean tagBean : tagBeanList) {
            DefaultTextView defaultTextView = new DefaultTextView(getContext());
            defaultTextView.setText(tagBean.getTagName());
            defaultTextView.setTextSize(16);
            defaultTextView.setSelected(tagBean.isSelect());
            addView(defaultTextView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tagLayoutLineList.clear();
        flagTagVewList.clear();
        int childCount = getChildCount();
        TagLayoutLine tagLayoutLine = null;
        int layoutHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //测量子view的大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //默认进来是null
            if (tagLayoutLine == null) {
                //第一次需要创建对象
                tagLayoutLine = new TagLayoutLine(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), tagSpace);
                tagLayoutLineList.add(tagLayoutLine);
                layoutHeight += childView.getMeasuredHeight();
            }
            if (tagLayoutLine.canAdd(childView)) {
                tagLayoutLine.addView(childView);
                flagTagVewList.add(childView);
            } else {
                tagLayoutLine = new TagLayoutLine(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), tagSpace);
                tagLayoutLineList.add(tagLayoutLine);
                tagLayoutLine.addView(childView);
                flagTagVewList.add(childView);
                layoutHeight += childView.getMeasuredHeight();
            }

        }
        layoutHeight += (getPaddingTop() + getPaddingBottom() + (tagLayoutLineList.size() - 1) * lineSpace);
        setMeasuredDimension(getMeasuredWidth(), resolveSize(layoutHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int bottom = getPaddingTop();
        for (int i = 0; i < tagLayoutLineList.size(); i++) {
            //获取
            //测量子view的大小
            int right = getPaddingLeft();
            TagLayoutLine tagLayoutLine = tagLayoutLineList.get(i);
            bottom += tagLayoutLine.getLineHeight();
            int top = bottom - tagLayoutLine.getLineHeight();
            if (i != 0) {
                top += lineSpace;
                bottom += lineSpace;
            }
            List<View> viewList = tagLayoutLine.getViewList();
            for (int k = 0; k < viewList.size(); k++) {
                View view = viewList.get(k);
                right += view.getMeasuredWidth();
                viewList.get(k).layout(right - view.getMeasuredWidth(), top, right, bottom);
                right += tagLayoutLine.getSpace();
            }

        }
    }
}
