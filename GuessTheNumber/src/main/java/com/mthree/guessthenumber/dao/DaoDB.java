package com.mthree.guessthenumber.dao;



import com.mthree.guessthenumber.model.Game;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

@Repository
public class DaoDB implements Dao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    public DaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }




    @Override
    @Transactional
    public Game addGame(Game game) {
        String sql = "INSERT INTO guessNumber(targetNumber) VALUES (?)";

        jdbc.update(sql, game.getTarget());

        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);



        return game;
    }

    @Override
    public Game getGame(int game) {

        final String sql = "SELECT id, targetNumber, finished "
                + "FROM guessNumber WHERE id = ?;";

        return jdbc.queryForObject(sql, new GameMapper(),game);
    }



    @Override
    public List<Game> getAllGames() {

        final String sql = "SELECT id, targetNumber, finished FROM guessNumber;";
        return jdbc.query(sql, new GameMapper());

    }

    @Override
    public void updateGame(Game game) {

        final String sql = "UPDATE guessNumber SET "
                + "finished = ? "
                + "WHERE id = ?;";

        jdbc.update(sql, game.getFinished(), game.getId());

    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("id"));
            game.setTarget(rs.getString("targetNumber"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
