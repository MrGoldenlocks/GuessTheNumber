package com.mthree.guessthenumber.controller;
import com.mthree.guessthenumber.serviceLayer.serviceLayer;
import com.mthree.guessthenumber.model.Game;
import com.mthree.guessthenumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    serviceLayer serviceLayer;


    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return serviceLayer.addNewGame();
    }

    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return serviceLayer.makeGuess(round);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return serviceLayer.getAllGames();
    }

    @GetMapping("/game/{game_id}")
    public Game getGameById(@PathVariable("game_id") int gameId) {
        return serviceLayer.getGameById(gameId);
    }

    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") int gameId) {
        return serviceLayer.getRounds(gameId);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
    }


}
