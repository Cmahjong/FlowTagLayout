package com.yj.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import com.yj.library.utils.DensityUtils;


/**
 * desc:
 * time: 2019/10/24
 *
 * @author 银进
 */
public class DefaultTextView extends AppCompatTextView {
    //默认标签内间距
    private static final float DEFAULT_TAG_PADDING = 5;

    public DefaultTextView(Context context) {
        super(context);
        init();
    }

    public DefaultTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
    }

    private void init() {
        int padding = DensityUtils.dp2px(getContext(), DEFAULT_TAG_PADDING);
        setPadding(padding, padding, padding, padding);
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.default_text_view_bg);
    }


}
