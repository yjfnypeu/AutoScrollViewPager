package com.lzh.view.autoscrollviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lzh.view.viewpagerlib.AutoScrollComponentViewPager;

public class MainActivity extends FragmentActivity {


    private String[] items = new String[]{
            "This is first page",
            "无图别瞎BB",
            "呀灭嗲~~",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoScrollComponentViewPager viewPager = (AutoScrollComponentViewPager) findViewById(R.id.autoScrollViewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TextFragment.newInstance(items[position]);
        }

        @Override
        public int getCount() {
            return items.length;
        }
    }

}
