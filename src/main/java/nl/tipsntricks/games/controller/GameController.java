package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.repository.GameRepository;
import nl.tipsntricks.games.service.IGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin (origins = "*", maxAge =3600)
@RestController
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private IGamesService gamesService;

    @GetMapping(value = "/api/game")
    public List<Game>getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping(value = "/api/game/{gameid}")
    public Game getGameById (@PathVariable long gameid){
        return gamesService.getGameById(gameid);
    }

    @PostMapping(value = "/api/game")
    public Game addGame(@RequestBody Game newGame) {
        return gamesService.addGame(newGame);
    }

    @PutMapping(value = "/api/game/user/{userid}")
    public Game addGameToUser (@PathVariable Long userid, @RequestBody Game newGame) {
        return gamesService.addGameToUser(userid, newGame);
    }

    @PutMapping (value = "/api/game/{gameid}")
    public Game updateGameById (@PathVariable long gameid, @RequestBody Game updatedGame){
        return gamesService.updateGameById(gameid, updatedGame);
    }

    @DeleteMapping(value = "/api/game/{gameid}")
    public String deleteGame(@PathVariable Long gameid) {
       return gamesService.deleteGame(gameid);
    }
}

