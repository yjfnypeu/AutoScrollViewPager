package com.lzh.view.autoscrollviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 16/8/30.
 */
public class TextFragment extends Fragment {

    private String text;

    public static TextFragment newInstance(String text) {
        TextFragment fragment = new TextFragment();
        Bundle data = new Bundle();
        data.putString("key",text);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments().getString("key");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView root = new TextView(getContext());
        root.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
        root.setText(text);
        return root;
    }
}
