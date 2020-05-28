package com.canberkbbc.tabuu.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.canberkbbc.tabuu.MainActivity;
import com.canberkbbc.tabuu.R;
import com.canberkbbc.tabuu.databinding.CardDesignPreviousGamesItemBinding;
import com.canberkbbc.tabuu.models.Scores;

import java.util.List;

public class PreviousGamesAdapter extends RecyclerView.Adapter<PreviousGamesAdapter.ViewHolderPreviousGames> {
    private MainActivity activity;
    private List<Scores> previousGamesArrayList;
    private CardDesignPreviousGamesItemBinding binding;

    public PreviousGamesAdapter (MainActivity activity, List<Scores> previousGamesArrayList){
        this.activity = activity;
        this.previousGamesArrayList = previousGamesArrayList;
    }

    @NonNull
    @Override
    public ViewHolderPreviousGames onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity),
                R.layout.card_design_previous_games_item,
                parent,
                false);
        return new ViewHolderPreviousGames(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPreviousGames holder, int position) {
        Scores scores=previousGamesArrayList.get(position);
        holder.bind(scores,position);
    }

    @Override
    public int getItemCount() {
        return previousGamesArrayList.size();
    }

    public class ViewHolderPreviousGames extends RecyclerView.ViewHolder {
        private CardDesignPreviousGamesItemBinding rowBinding;
        public ViewHolderPreviousGames(CardDesignPreviousGamesItemBinding rowBinding) {
            super(binding.getRoot());
            this.rowBinding = rowBinding;
        }
        private void bind(final Scores scores, int position){
            rowBinding.txtTeamA.setText(scores.getTeamNameA());
            rowBinding.txtTeamB.setText(scores.getTeamNameB());
            rowBinding.txtTeamAScore.setText(String.valueOf(scores.getPointA()));
            rowBinding.txtTeamBScore.setText(String.valueOf(scores.getPointB()));
        }
    }
}
