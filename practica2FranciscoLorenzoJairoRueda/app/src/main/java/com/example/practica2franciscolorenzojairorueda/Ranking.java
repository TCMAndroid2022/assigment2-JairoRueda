package com.example.practica2franciscolorenzojairorueda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Ranking extends AppCompatActivity implements View.OnItemClickListener{
    private TextView Num;
    private TextView Nick;
    private TextView Points;
    private GameViewModel viewModel;
    private View view;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Nick = findViewById(R.id.nick);
        Num = findViewById(R.id.num);
        Points = findViewById(R.id.points);
        recyclerView = findViewById(R.id.ranking_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        viewModel.AllGamesByNick().observe(this, games -> view.setGames(games));

        view = new View((View.OnItemClickListener) this);
        recyclerView.setAdapter(view);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemClick (String nick){
        Intent intent = new Intent(this, PlayerInfo.class);
        intent.putExtra("nick", nick);
        startActivity(intent);

    }

}