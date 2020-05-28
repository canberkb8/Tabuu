package com.canberkbbc.tabuu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.adapters.PreviousGamesAdapter;
import com.canberkbbc.tabuu.databinding.FragmPreviousgamesBinding;
import com.canberkbbc.tabuu.db.DataSource;
import com.canberkbbc.tabuu.models.Scores;

import java.util.ArrayList;
import java.util.Collections;

public class PreviousGames extends BaseFragment {
    private View view;
    private FragmPreviousgamesBinding binding;
    ArrayList<Scores> scoresArrayList;
    DataSource dataSource;
    PreviousGamesAdapter previousGamesAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragm_previousgames, container, false);
        view = binding.getRoot();
        scoresArrayList = new ArrayList<>();

        dataSource=new DataSource(activity);
        dataSource.open();
        scoresArrayList = dataSource.arrayList();
        Collections.reverse(scoresArrayList);
        previousGamesAdapter = new PreviousGamesAdapter(activity,scoresArrayList);
        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setAdapter(previousGamesAdapter);

        dataSource.close();

        return view;
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }
}
