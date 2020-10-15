package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
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
                .orElseThrow(()-> new GameNotFoundException("Game met id " +gameid +" niet gevonden"));
    }

    @Override
    public Game addGame(Game newGame) {
        if (!gameRepository.existsByName(newGame.getName())) {
            String gameName = newGame.getName();
            if (checkIsValidName(gameName)) {
                return gameRepository.save(newGame);
            }
            throw new GameNotFoundException("game bestaat niet");
        } throw new GameNotFoundException("game bestaat al");
    }
    @Override
    public Game addGameToUser(Long userid, Game newGame) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDB = user.get();
            Set<Game> currentGames = userFromDB.getCurrentGames();

            Set<AppUser> users = new HashSet<>();
            currentGames.add(newGame);

            newGame.setUsers(users);
            userFromDB.setCurrentGames(currentGames);

            return gameRepository.save(newGame);
        }
        throw new GameNotFoundException("Game bestaat niet");
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
        throw new GameNotFoundException("Deze game bestaat niet");
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

}


