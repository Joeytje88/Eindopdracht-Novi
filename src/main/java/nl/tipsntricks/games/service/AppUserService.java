package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.*;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService implements IAppUserService {


    private final AppUserRepository appUserRepository;

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
    public AppUser addUser(AppUser newUser) {
        String userName = newUser.getUsername();

        if (checkIsValidName(userName)) {
            return appUserRepository.save(newUser);
        }
        throw new UserNotFoundException("Gebruiker bestaat niet");
    }

    @Override
    public AppUser updateUserById(Long id, AppUser updatedUser) {
        Optional<AppUser> userFromDb = appUserRepository.findById(id);

        if (userFromDb.isPresent()) {
            if(checkIsValidName(updatedUser.getUsername())) {
            AppUser appUser = new AppUser();
            appUser.setUsername(updatedUser.getUsername());
            appUser.setEmail(updatedUser.getEmail());
            appUser.setPassword(updatedUser.getPassword());
            return appUserRepository.save(appUser);
        }
    }
    throw new UserNotFoundException("Gebruiker bestaat niet");
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
    public AppUser addGameToUser(Long userid, AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Game> currentGames = userFromDb.getCurrentGames();

            Set<AppUser> gamesList = new HashSet<>();
            gamesList.add(userFromDb);

            newUser.setCurrentGames(currentGames);
            userFromDb.setCurrentGames(currentGames);

            return appUserRepository.save(newUser);
        }
        throw new GameNotFoundException("Game bestaat niet");
    }

    @Override
    public AppUser addCommentToUser(Long userid, AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Comment> comments  = userFromDb.getComments();

            Set<AppUser> comment = new HashSet<>();
            comment.add(userFromDb);

            newUser.setComments(comments);
            userFromDb.setComments(comments);

            return appUserRepository.save(newUser);
        }
        throw new GameNotFoundException("Reactie bestaat niet");
    }

    @Override
    public AppUser addRoleToUser(long userid, AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(userid);

        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Role> roles = userFromDb.getRoles();

            Set <AppUser> appUser = new HashSet<>();
            appUser.add(userFromDb);

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
            if(userFromDb.getRoles().contains(adminRole)) {
                userFromDb.getRoles().remove(adminRole);
                return appUserRepository.save(userFromDb);
            }
        }
        return user.get();
    }

    private boolean checkIsValidName(String name) {
        if(name.contains("fuck") || name.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
