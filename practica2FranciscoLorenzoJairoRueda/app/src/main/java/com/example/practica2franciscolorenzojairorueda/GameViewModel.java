package com.example.practica2franciscolorenzojairorueda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.practica2franciscolorenzojairorueda.BBDD.DatabaseController;

import java.util.List;

public class GameViewModel extends AndroidViewModel {


    private DatabaseController repository;
    private LiveData<List<Game>> allNickGames;
    private LiveData<List<Game>> allGamesByNick;
    private LiveData<List<Game>> allGames;


    public GameViewModel(@NonNull Application application, String nickname) {
        super(application);
        repository = new DatabaseController(application, nickname);
        allNickGames = repository.fetchAll();
        allGamesByNick = repository.fetchAllNick();
        allGames = repository.fetchAll();
    }

    public GameViewModel(@NonNull Application application) {
        super(application);
        repository = new DatabaseController(application);
        allGames = repository.fetchAll();
        allGamesByNick = repository.fetchAllNick();
    }

    LiveData<List<Game>> AllGames() {
        return allGames;
    }

    LiveData<List<Game>> AllGamesByNick() {
        return allGamesByNick;
    }
    void insert(Game game) {
        repository.setGame(game);
    }


}