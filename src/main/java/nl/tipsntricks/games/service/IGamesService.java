package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;

public interface IGamesService {
    Game getGameById(Long id);
    Game addGame(Game newGame);
    Game updateGameById(Long id, Game updatedGame);
    String deleteGame(Long id);
    Game addUsertoGame(Long id, AppUser updatedUser);
    Game addTestGamesWithUser();
}

