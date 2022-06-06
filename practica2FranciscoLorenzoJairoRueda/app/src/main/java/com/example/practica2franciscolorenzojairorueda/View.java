package com.example.practica2franciscolorenzojairorueda;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class View extends RecyclerView.Adapter<View.ViewHolder> {

    private List<Game> data = Collections.emptyList();
    private OnItemClickListener listener;
    private String nickname;

    public View(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setGames(List<Game> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setFilteredGames(List<Game> data){
        List<Game> games = new ArrayList<>(data);
        for(Game game: data){
            if(!game.getNickname().equals(nickname)){
                games.remove(game);
            }
        }
        setGames(games);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        android.view.View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game currentGame = data.get(position);
        holder.id.setText(currentGame.getId() + "");
        holder.nickname.setText(currentGame.getNickname() + "");
        holder.points.setText(currentGame.getPoints() + "");
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        public TextView id;
        public TextView nickname;
        public TextView points;

        public ViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.num);
            nickname = itemView.findViewById(R.id.nick);
            points = itemView.findViewById(R.id.points);
            nickname.setOnClickListener(this);
        }


        @Override
        public void onClick(android.view.View view) {
            listener.onItemClick(data.get(getAdapterPosition()).getNickname());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String nick);
    }

    public void setNickname(String nick){
        this.nickname =  nick;
    }


}