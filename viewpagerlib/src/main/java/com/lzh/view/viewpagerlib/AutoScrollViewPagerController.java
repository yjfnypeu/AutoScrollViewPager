package com.lzh.view.viewpagerlib;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * 控制ViewPager自动滚动.
 * Created by zhihaol on 16/8/29.
 */
public class AutoScrollViewPagerController implements AutoScrollController{
    Handler mainHandler = new Handler(Looper.getMainLooper());
    private int delay;
    private AutoScrollTask autoScrollTask;

    public AutoScrollViewPagerController(ViewPager viewPager, int delay) {
        this.delay = Math.max(delay,1000);
        autoScrollTask = new AutoScrollTask(viewPager);
    }

    @Override
    public void start() {
        checkTaskValid();
        mainHandler.removeCallbacks(autoScrollTask);
        mainHandler.postDelayed(autoScrollTask,delay);
    }

    private void checkTaskValid() {
        if (autoScrollTask == null) {
            throw new IllegalStateException("Auto scroll task is recycled");
        }
    }

    @Override
    public void stop() {
        checkTaskValid();
        mainHandler.removeCallbacks(autoScrollTask);
    }

    @Override
    public void recycle() {
        stop();
        autoScrollTask = null;
    }

    class AutoScrollTask implements Runnable {

        ViewPager viewPager;

        public AutoScrollTask(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void run() {
            PagerAdapter adapter = this.viewPager.getAdapter();
            if (adapter == null) return;

            int count = adapter.getCount();
            int currentItem = this.viewPager.getCurrentItem();
            int nextItem = (currentItem + 1 >= count) ? 0 : currentItem + 1;
            this.viewPager.setCurrentItem(nextItem,true);
            start();
        }
    }
}
