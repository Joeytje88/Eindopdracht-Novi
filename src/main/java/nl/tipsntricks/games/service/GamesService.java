package nl.tipsntricks.games.service;

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


