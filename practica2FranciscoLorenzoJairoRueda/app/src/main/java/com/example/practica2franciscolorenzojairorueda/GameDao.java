package com.example.practica2franciscolorenzojairorueda;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM Game")
    LiveData<List<Game>> getAllGames();

    @Query("SELECT * FROM Game WHERE nickname = :nickname")
    LiveData<List<Game>> getNickGames(String nickname);

    @Query ("SELECT id, nickname, sum(points) as points, cont FROM Game GROUP BY nickname ORDER BY id asc")
    LiveData<List<Game>> getAllGamesByNick();



    @Insert
    void insertGame(Game game);
}
