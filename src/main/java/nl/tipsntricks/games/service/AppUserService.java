package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.*;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.exception.GameException;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GameRepository;
import nl.tipsntricks.games.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService implements IAppUserService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public GameRepository gameRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public AppUserService (AppUserRepository appUserRepository){
        this.appUserRepository= appUserRepository;
    }

    @Override
    public AppUser getUserById(Long userid) {
        return appUserRepository.findById(userid)
                .orElseThrow(() -> new UserNotFoundException(userid));
    }

    @Override
    public AppUser updateUserById(Long userid, AppUser updatedUser) {
        return appUserRepository.findById(userid).map(
                appUser -> {
                    appUser.setUsername(updatedUser.getUsername());
                    appUser.setEmail(updatedUser.getEmail());
                    appUser.setPassword(updatedUser.getPassword());
                    appUser.setPicture(updatedUser.getPicture());
                    appUser.setUrl(updatedUser.getUrl());
                    return appUserRepository.save(appUser);
                }).orElseGet(() -> {
            updatedUser.setUserId(userid);
            return appUserRepository.save(updatedUser);
        });
    }

    @Override
    public String deleteUser(Long userid) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            appUserRepository.deleteById(userid);
            return "Gebruiker met id " + user.get().getUserid() + "is verwijderd";
        }
        throw new UserNotFoundException("Deze gebruiker bestaat niet");
    }

    @Override
    public AppUser addGameToUser(long userid, Game newGame) {
            Optional<AppUser> appUser = appUserRepository.findById(userid);
            if (!gameRepository.existsByName(newGame.getName())) {
                if (appUser.isPresent()) {
                    AppUser userFromDB = appUser.get();
                    Set<Game> currentGames = userFromDB.getCurrentGames();
                    currentGames.add(newGame);
                    userFromDB.setCurrentGames(currentGames);

                    return appUserRepository.save(userFromDB);
                }
                throw new GameException("Game bestaat niet");
            }
            throw new GameException("game al toegevoegd");
        }

    @Override
    public AppUser addCommentToUser(long userid, Comment newComment) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Comment> comments = userFromDb.getComments();

            comments.add(newComment);
            newComment.setUser(userFromDb);

            return appUserRepository.save(userFromDb);
        }
        throw new CommentNotFoundException("reactie niet gevonden");
    }

    @Override
    public AppUser addPlatformToUser(long userid, Platform newPlatform) {
        Optional<AppUser> appUser = appUserRepository.findById(userid);
        if (appUser.isPresent()) {
            AppUser userFromDb = appUser.get();
            Set<Platform> platforms = userFromDb.getPlatforms();

            platforms.add(newPlatform);
            userFromDb.setPlatforms(platforms);

            return appUserRepository.save(userFromDb);
        }
        throw new UserNotFoundException("gebruiker niet gevonden");
    }

    @Override
    public AppUser addRoleToUser(long roleid, AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(roleid);

        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Role> roles = userFromDb.getRoles();

            newUser.setRoles(roles);
            userFromDb.setRoles(roles);

            return appUserRepository.save(newUser);
        }
        throw new UserNotFoundException("gebruiker niet gevonden");
    }

    @Override
    public AppUser demoteUserById(long userid) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            if (userFromDb.getRoles().contains(adminRole)) {
                userFromDb.getRoles().remove(adminRole);
                return appUserRepository.save(userFromDb);
            }
            return user.get();
        }
        throw new UserNotFoundException("gebruiker niet gevonden");
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
