package com.mthree.guessthenumber.model;

import java.util.List;
import java.util.Objects;


public class Game {

    public int gameId;
    public String target;
    public  boolean finished;

    public  Game(String target, boolean finished){
        this.target = target;
        this.finished = finished;
    }

    public  Game(int gameId, String target, boolean finished){
        this.target = target;
        this.finished = finished;
        this.gameId = gameId;
    }

    public Game() {
    }

    public void  setGameId(int gameId){
        this.gameId = gameId;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getId(){
        return gameId;
    }
    public boolean getFinished(){
        return finished;
    }
    public String getTarget(){
        return target;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.target, other.target)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", target=" + target + ", finished=" + finished + '}';
    }

}
