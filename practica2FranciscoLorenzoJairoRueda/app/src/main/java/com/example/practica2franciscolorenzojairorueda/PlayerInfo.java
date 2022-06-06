package com.example.practica2franciscolorenzojairorueda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class PlayerInfo extends AppCompatActivity implements View.OnItemClickListener {

    private TextView nickName;
    private GameViewModel viewModel;
    private View view;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = findViewById(R.id.playerRanking_view);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        view = new View((View.OnItemClickListener) this);
        view.setNickname(getIntent().getStringExtra("nick"));
        recyclerView.setAdapter(view);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        viewModel.AllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> games) {
                view.setFilteredGames(games);
            }
        });



    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick(String nick) {

    }
}