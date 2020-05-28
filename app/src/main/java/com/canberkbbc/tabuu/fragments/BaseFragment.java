package com.canberkbbc.tabuu.fragments;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.canberkbbc.tabuu.MainActivity;
public abstract class BaseFragment extends Fragment {

    protected MainActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = ((MainActivity) context);
    }

}
