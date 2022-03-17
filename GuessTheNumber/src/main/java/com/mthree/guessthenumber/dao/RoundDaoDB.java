package com.mthree.guessthenumber.dao;

import com.mthree.guessthenumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class RoundDaoDB implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    public RoundDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(game_id, guess, result) VALUES(?,?,?)";
        jdbc.update(sql, round.getId(), round.getGuess(), round.getResult());

        int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newRoundId);
        return getRoundById(newRoundId);
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        try {
            final String sql = "SELECT * FROM round "
                    + "WHERE game_id = ? ORDER BY timeOfGuess";
            List<Round> rounds = jdbc.query(sql, new RoundMapper(), gameId);
            return rounds;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Round getRoundById(int roundId) {
        return null;
    }

    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("id"));
            round.setGameId(rs.getInt("game_id"));
            round.setGuess(rs.getString("guess"));

            Timestamp timestamp = rs.getTimestamp("timeOfGuess");
            round.setTime(timestamp.toLocalDateTime());

            round.setResult(rs.getString("result"));
            return round;
        }
    }

}
