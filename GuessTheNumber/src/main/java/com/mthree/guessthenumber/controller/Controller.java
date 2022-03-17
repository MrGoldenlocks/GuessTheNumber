package com.mthree.guessthenumber.controller;
import com.mthree.guessthenumber.serviceLayer.service;
import com.mthree.guessthenumber.model.Game;
import com.mthree.guessthenumber.model.Round;
import com.mthree.guessthenumber.serviceLayer.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    service service;


    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.addNewGame();
    }

    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return service.makeGuess(round);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    @GetMapping("/game/{game_id}")
    public Game getGameById(@PathVariable("game_id") int gameId) {
        return service.getGameById(gameId);
    }

    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") int gameId) {
        return service.getRounds(gameId);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
    }

    @GetMapping
    public String[] helloWorld() {
        String[] result = {"Hello", "World", "!"};
        return result;
    }


}
