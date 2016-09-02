package com.lzh.view.viewpagerlib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * A auto scroll ViewPager,use {@link AutoScrollViewPagerController} to control viewpager scrolled;<br>
 * Created by lzh on 16/8/30.
 */
public class AutoScrollViewPager extends ViewPager implements AutoScrollController{

    private AutoScrollController autoScrollController;

    public AutoScrollViewPager(Context context) {
        this(context,null);
    }

    public AutoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        autoScrollController = new AutoScrollViewPagerController(this,2000);
    }

    @Override
    public void start() {
        autoScrollController.start();
    }

    @Override
    public void stop() {
        autoScrollController.stop();
    }

    @Override
    public void recycle() {
        autoScrollController.recycle();
        autoScrollController = null;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stop();
                break;
            case MotionEvent.ACTION_UP:
                start();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycle();
    }
}
