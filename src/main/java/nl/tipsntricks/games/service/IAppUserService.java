package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;

public interface IAppUserService {
    AppUser getUserById(Long id);
    AppUser addUser(AppUser newUser);
    AppUser updateUserById(Long userid, AppUser updatedUser);
    String deleteUser(Long id);
    AppUser demoteUserById(long userid);
    AppUser addRoleToUser(long userid, AppUser newUser);
}

