package com.mthree.guessthenumber.model;

import java.time.LocalDateTime;

public class Round {

    public  int gameId;
    public  int roundId;
    public LocalDateTime time;
    public String guess;
    public String result;

    public  Round(int gameId, String guess){
        this.gameId = gameId;
        this.guess = guess;
    }

    public  Round(int gameId, int roundId, LocalDateTime time, String guess, String result){
        this.gameId = gameId;
        this.roundId = roundId;
        this.time = time;
        this.guess = guess;
        this.result = result;
    }

    public Round() {

    }

    public void  setGameId(int gameId){
        this.gameId = gameId;
    }

    public void  setRoundId(int roundId){
        this.roundId = roundId;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setGuess(String guess) {
        this.guess = Round.this.guess;
    }

    public void setResult(String result) {
        this.result = Round.this.result;
    }

    public int getId(){
        return gameId;
    }

    public int getRoundId(){
        return roundId;
    }

    public LocalDateTime getTime(){
        return time;
    }

    public String getGuess(){
        return guess;
    }
    public String getResult(){
        return result;
    }



}
