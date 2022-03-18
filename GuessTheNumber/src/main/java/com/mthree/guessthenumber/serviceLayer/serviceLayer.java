package com.mthree.guessthenumber.serviceLayer;

import com.mthree.guessthenumber.dao.Dao;
import com.mthree.guessthenumber.dao.RoundDao;
import com.mthree.guessthenumber.model.Game;
import com.mthree.guessthenumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class serviceLayer {

    @Autowired
    Dao gameDao;

    @Autowired
    RoundDao roundDao;

    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public int addNewGame() {
        Game game = new Game();
        game.setTarget(generateAnswer());

        game = gameDao.addGame(game);

        return game.getId();
    }

    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            if (!game.getFinished()) {
                game.setTarget("****");
            }
        }

        return games;
    }

    public List<Round> getRounds(int gameId) {
        return roundDao.getRoundsByGameId(gameId);
    }

    public Game getGameById(int gameId) {
        Game game = gameDao.getGame(gameId);


        if (!game.getFinished()) {
            game.setTarget("****");
        }

        return game;
    }

    public Round makeGuess(Round round) {
        String answer = gameDao.getGame(round.getId()).getTarget();
        String guess = round.getGuess();
        String result = calculateResult(guess, answer);
        round.setResult(result);

        if (guess.equals(answer)) {
            Game game = getGameById(round.getId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }

        return roundDao.addRound(round);
    }

    public String calculateResult(String guess, String answer) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < guessChars.length; i++) {
            if (answer.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }

        String result = "e:" + exact + ":p:" + partial;

        return result;
    }

    public String generateAnswer() {
        Random rnd = new Random();
        int digit1 = rnd.nextInt(10);

        int digit2 = rnd.nextInt(10);
        while (digit2 == digit1) {
            digit2 = rnd.nextInt(10);
        }

        int digit3 = rnd.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1) {
            digit3 = rnd.nextInt(10);
        }

        int digit4 = rnd.nextInt(10);
        while (digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
            digit4 = rnd.nextInt(10);
        }

        String answer = String.valueOf(digit1) + String.valueOf(digit2)
                + String.valueOf(digit3) + String.valueOf(digit4);

        return answer;
    }



}
