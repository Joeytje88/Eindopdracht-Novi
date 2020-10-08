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
                .orElseThrow(()-> new GameNotFoundException("Game niet gevonden"));
    }

    @Override
    public Game addGame(Game newGame) {
        String gameName=  newGame.getName();

        if (checkIsValidName(gameName)){
            return gameRepository.save(newGame);
        } throw new GameNotFoundException("game bestaat niet");
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

        String name = updatedGame.getName();
        if (checkIsValidName(name)) {
            Game game = new Game();
            game.setName(updatedGame.getName());
            return gameRepository.save(game);
        } throw new GameNotFoundException ("game bestaat niet");
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


