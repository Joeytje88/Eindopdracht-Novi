package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GamesService implements IGamesService{


    private final GameRepository gameRepository;

    @Autowired
    public GamesService(GameRepository gameRepository){
        this.gameRepository= gameRepository;}


    @Override
    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(()-> new GameNotFoundException("Game niet gevonden"));
    }


    @Override
    public Game addGame(Game newGame) {
        String gameName = newGame.getName();

        if (!gameName.contains("shit")){
            return gameRepository.save(newGame);
        }
        throw new GameNotFoundException("De naam van de game bevat scheldwoorden dat is niet toegestaan");
          }

    @Override
    public Game updateGameById(Long id, Game updatedGame) {
        Optional<Game> gameFromDB = gameRepository.findById(id);

        if (gameFromDB.isPresent()) {
            if (checkIsValidName(updatedGame.getName())) {
                Game game = new Game();
                game.setName(updatedGame.getName());
                game.setPublisher(updatedGame.getPublisher());
                game.setDeveloper(updatedGame.getDeveloper());
                game.setReleasedate(updatedGame.getReleasedate());
                game.setPlatforms(updatedGame.getPlatforms());
                return gameRepository.save(game);
            }
        }
        throw new GameNotFoundException("De game bestaat niet");
    }

    @Override
    public String deleteGame(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            gameRepository.deleteById(id);
            return "Game met id" + game.get().getGameId() + "is verwijderd";
        }
        throw new GameNotFoundException("Deze game bestaat niet");
    }

    @Override
    public Game addUsertoGame(Long id, AppUser updatedUser) {
        return null;
    }
    /*@Override
    public AppUser addUsertoGame(Long id, AppUser newUser) {
        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            Game gameFromdb = game.get();
            List <AppUser> owners = gameFromdb.getOwners();

            if (newUser.getGame() == null || newUser.getGame().getGameId() != id) {
               newUser.setGame(gameFromdb);
            }

            owners.add(newUser);
            gameFromdb.setOwners(owners);

            return appUserRepository.save(owners);
        }
        throw new GameNotFoundException("Game bestaat niet");
    }*/

    @Override
    public Game addTestGamesWithUser() {
        return null;
    }
    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

}


