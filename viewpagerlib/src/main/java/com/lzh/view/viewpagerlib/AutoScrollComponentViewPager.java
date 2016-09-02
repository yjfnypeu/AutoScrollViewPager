package com.lzh.view.viewpagerlib;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * A UI component to composite both {@link AutoScrollViewPager} and {@link BannerView} <br>
 * Created by lzh on 16/8/31.
 */
public class AutoScrollComponentViewPager extends FrameLayout implements ViewPager.OnPageChangeListener{

    AutoScrollViewPager viewPager;
    BannerView banner;

    public AutoScrollComponentViewPager(Context context) {
        this(context,null);
    }

    public AutoScrollComponentViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoScrollComponentViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.auto_scroll_component_viewpager,this,true);
        viewPager = (AutoScrollViewPager) findViewById(R.id.component_viewpager);
        banner = (BannerView) findViewById(R.id.component_banner);
        viewPager.addOnPageChangeListener(this);
    }

    /**
     * Set adapter to ViewPager and set count to banner view
     * @param adapter {@link ViewPager#setAdapter(PagerAdapter)}
     */
    public void setAdapter (PagerAdapter adapter) {
        if (adapter != null) {
            banner.setCount(adapter.getCount());
            viewPager.setAdapter(adapter);
        }
    }

    public AutoScrollViewPager getViewPager() {
        return viewPager;
    }

    public BannerView getBanner() {
        return banner;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        banner.setCurItem(position,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
