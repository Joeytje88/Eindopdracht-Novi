package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.exception.GameException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GamesService implements IGamesService{

    private final GameRepository gameRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    public GamesService(GameRepository gameRepository){
        this.gameRepository= gameRepository;}


    @Override
    public Game getGameById(Long gameid) {
        return gameRepository.findById(gameid)
                .orElseThrow(()-> new GameException("Game met id " +gameid +" niet gevonden"));
    }


    @Override
    public Game addGame(Game newGame) {
        if (!gameRepository.existsByName(newGame.getName())) {
            String gameName = newGame.getName();
            if (checkIsValidName(gameName)) {
                return gameRepository.save(newGame);
            }
            throw new GameException("game bestaat niet");
        } throw new GameException("game alreeds toegevoegd");
    }


    @Override
    public Game updateGameById(Long gameid, Game updatedGame) {
        return gameRepository.findById(gameid).map(
                game -> {
                    game.setName(updatedGame.getName());
                    return gameRepository.save(game);
                }).orElseGet(()->{
            updatedGame.setGameId(gameid);
            return gameRepository.save(updatedGame);
        });
    }

    @Override
    public String deleteGame(Long gameid) {
        Optional<Game> game = gameRepository.findById(gameid);
        if (game.isPresent()) {
            gameRepository.deleteById(gameid);
            return "Game met id " + game.get().getGameId() + " is verwijderd";
        }
        throw new GameException("Deze game bestaat niet");
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

}


