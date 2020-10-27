package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Game;

public interface IGamesService {
    Game getGameById(Long gameid);
    Game addGame(Game newGame);
    Game addGameToUser (Long userid, Game newGame);
    Game updateGameById(Long gameid, Game updatedGame);
    String deleteGame(Long gameid);
}

