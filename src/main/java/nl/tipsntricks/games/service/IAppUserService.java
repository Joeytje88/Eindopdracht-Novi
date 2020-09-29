package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Game;

public interface IAppUserService {
    AppUser getUserById(Long id);
    AppUser addUser(AppUser newUser);
    AppUser updateUserById(Long id, AppUser updatedUser);
    String deleteUser(Long id);
    AppUser addGameToUser(Long id, Game newGame);
    AppUser addTestUserWithGame();
    AppUser demoteUserById(long id);
}

