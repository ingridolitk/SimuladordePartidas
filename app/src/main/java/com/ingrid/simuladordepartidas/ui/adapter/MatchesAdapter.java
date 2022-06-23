package com.ingrid.simuladordepartidas.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.ingrid.simuladordepartidas.databinding.MatchItemBinding;
import com.ingrid.simuladordepartidas.domain.Match;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    public List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);
        Context context = holder.itemView.getContext();
        Glide.with(context).load(match.getHomeTeam().getImage()).circleCrop().into(holder.binding.ivHomeTeam);
        Glide.with(context).load(match.getAwayTeam().getImage()).circleCrop().into(holder.binding.ivAwayTeam);

        //adapta os dados da partida (recuperada da api) para o layout
        holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        holder.binding.tvAwayTeamName.setText(match.getAwayTeam().getName());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
