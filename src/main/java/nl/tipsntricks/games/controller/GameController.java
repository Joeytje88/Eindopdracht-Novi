package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping(value = "/api/games")
    public List<Game> getCurrentGames() {
        List<Game> game = gameRepository.findAll();
        return game;
    }

    @GetMapping(value = "/api/game/{gameId}")
    public Game getGames(@PathVariable Long id) {
        return gameRepository.findById(id).orElseThrow(
                () -> new GameNotFoundException("Game is niet gevonden"));
    }

    @PostMapping(value = "/api/game")
    public Game addGames(@RequestBody Game newGame) {
        return gameRepository.save(newGame);
    }

    @DeleteMapping(value = "/api/game/{gameid}")
    public String deleteGames(@PathVariable Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            gameRepository.deleteById(id);
            return "Game met id " + game.get().getGameId() + " is verwijderd";
        } else {
            return "Game is niet gevonden";
        }
    }

    //TODO checken of game al bestaat en dan koppelen aan gebruiker
    @PostMapping(value = "/api/game/{id}")
    public Game addGameToUser(@PathVariable Long id, @RequestBody Game newGame) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            List<Game> currentGames = userFromDb.getCurrentGames();

            List<AppUser> owners = new ArrayList<>();
            owners.add(userFromDb);

            newGame.setOwners(owners);

            currentGames.add(newGame);
            userFromDb.setCurrentGames(currentGames);

            return gameRepository.save(newGame);
        }
        throw new UserNotFoundException(id);
    }


    private boolean checkIsValidName(String name) {
        return !name.contains("fuck") && !name.equalsIgnoreCase("");
    }
}
