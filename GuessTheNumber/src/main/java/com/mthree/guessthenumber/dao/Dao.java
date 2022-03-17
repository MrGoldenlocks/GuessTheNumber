package com.mthree.guessthenumber.dao;

import com.mthree.guessthenumber.model.Game;

import java.util.List;

public interface Dao {

    Game addGame(Game game);
    Game getGame(int game);
    List<Game> getAllGames();
    void updateGame(Game round);

}
