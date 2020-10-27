package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.*;

public interface IAppUserService {
    AppUser getUserById(Long userid);
    AppUser updateUserById(Long userid, AppUser updatedUser);
    String deleteUser(Long userid);
    AppUser addTopicToUser (long userid, Topic newTopic);
    AppUser addPlatformToUser(long userid, Platform newPlatform);
    AppUser addGameToUser (long userid, Game newGame);
    AppUser addCommentToUser(long userid, Comment newComment);
    AppUser demoteUserById(long userid);
    AppUser addRoleToUser(long roleid, AppUser newUser);
}

