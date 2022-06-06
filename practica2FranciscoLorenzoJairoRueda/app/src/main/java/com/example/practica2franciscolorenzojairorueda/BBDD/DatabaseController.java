package com.example.practica2franciscolorenzojairorueda.BBDD;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.practica2franciscolorenzojairorueda.BBDD.AppDatabase;
import com.example.practica2franciscolorenzojairorueda.Game;
import com.example.practica2franciscolorenzojairorueda.GameDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DatabaseController {
    private GameDao gameDao;
    private LiveData<List<Game>> Games;

    private LiveData<List<Game>> GamesNick;

    private LiveData<List<Game>> NickGames;

    public DatabaseController(Application application, String nickname) {
        AppDatabase db = AppDatabase.getDatabase(application);
        gameDao = db.GameDao();
        Games = gameDao.getAllGames();
        GamesNick = gameDao.getAllGamesByNick();
        NickGames = gameDao.getNickGames(nickname);
    }

    public DatabaseController(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        gameDao = db.GameDao();
        Games = gameDao.getAllGames();
        GamesNick = gameDao.getAllGamesByNick();
    }
    public LiveData<List<Game>> fetchAllNick() {
        return GamesNick;
    }
    public LiveData<List<Game>> fetchAll() {
        return Games;
    }
    public void setGame(Game game) {
        new insertAsyncTask(gameDao).execute(game);
    }

    private static class insertAsyncTask {
        private GameDao asyncDao;
        private Executor executor = Executors.newSingleThreadExecutor();

        insertAsyncTask(GameDao dao) {
            asyncDao = dao;
        }

        public void execute(Game game) {
            this.doInBackground(game);
        }

        private void doInBackground(final Game game) {
            this.executor.execute(() -> asyncDao.insertGame(game));
        }
    }
}
