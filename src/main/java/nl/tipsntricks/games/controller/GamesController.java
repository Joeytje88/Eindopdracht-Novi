package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Games;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GamesController {
    @Autowired
    private GamesRepository GameRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping(value = "/api/game")
    public List<Games> getGames() {
        List<Games> games = GameRepository.findAll();
        return games;
    }

    @GetMapping(value = "/api/game/{gameId}")
    public Games getGames(@PathVariable Long gameId) {
        Optional<Games> game = GameRepository.findById(gameId);
        if (game.isPresent()) {
            return game.get();
        }
        return null;
    }

    @PostMapping(value = "/api/game")
    public Games saveGames(@RequestBody Games newGames) {
        return GameRepository.save(newGames);
    }

    @DeleteMapping(value = "/api/game/{id}")
    public String deleteGames(@PathVariable Long id) {
        Optional<Games> games = GameRepository.findById(id);
        if (games.isPresent()){
            GameRepository.deleteById(id);
            return "Game met id " + games.get().getGameId() + " is verwijderd";
        }else {
            return "Game is niet gevonden";
        }
    }

    @PostMapping(value = "/api/game/{gameId}")
    public Games addGamesToUser(@PathVariable Long id, @RequestBody Games games) {
        Optional<AppUser> user = appUserRepository.findById(id);
        if (user.isPresent()) {
            games.setUser((List<AppUser>) user.get()); {
                return GameRepository.save(games);}
        }
        return null;
    }



}
