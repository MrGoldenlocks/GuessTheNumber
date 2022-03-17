package com.mthree.guessthenumber.dao;

import com.mthree.guessthenumber.model.Round;

import java.util.List;

public interface RoundDao {

    Round addRound(Round round);
    List<Round> getRoundsByGameId(int gameId);
    Round getRoundById(int roundId);

}
