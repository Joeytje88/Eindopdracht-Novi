package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private GameRepository gameRepository;


    @GetMapping(value = "/api/users")
    public List<AppUser> getOwners() {
        List<AppUser> user = appUserRepository.findAll();
        return user;
    }

    @GetMapping(value = "/api/user/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        Optional<AppUser> owner = appUserRepository.findById(id);
        return owner.orElse(null);
    }

    @PostMapping(value = "/api/user/")
    public AppUser addUser(@RequestBody AppUser newAppUser) {
        return appUserRepository.save(newAppUser);
    }

    @DeleteMapping(value = "/api/user/{id}")
    public String deleteUser(@PathVariable long id) {
        Optional<AppUser> owner = appUserRepository.findById(id);

        if (owner.isPresent()) {
            appUserRepository.deleteById(id);
            return "Gebruikersnaam met id " + owner.get().getId() + " is verwijderd";
        }
        return "Gebruikersnaam niet gevonden";
    }

    @PutMapping(value = "/api/user/{id}")
    public AppUser updateUserById(@PathVariable long id, @RequestBody AppUser updatedAppUser) {
        return appUserRepository.findById(id).map(
                user -> {
                    user.setUsername(updatedAppUser.getUsername());
                    user.setEmail(updatedAppUser.getEmail());
                    return appUserRepository.save(user);
                })
                .orElseGet(() -> {
                    updatedAppUser.setId(id);
                    return appUserRepository.save(updatedAppUser);
                });
    }

    @PostMapping(value = "api/user/{id}/game")
    public AppUser addGameToUser(@PathVariable long id,
                                 @RequestBody AppUser newUser) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) {
            Game gameFromDb = game.get();
            List<AppUser> owners = gameFromDb.getOwners();

            List<Game> currentGames = new ArrayList<>();
            currentGames.add(gameFromDb);

            newUser.setCurrentGames(currentGames);
            gameFromDb.setOwners(owners);

            return appUserRepository.save(newUser);
        }
        throw new GameNotFoundException("Game bestaat niet");
    }

    @PreAuthorize("hasRole ('ROLE_ADMIN')")
    @PutMapping(value = "/api/user/{id}/role")
    public AppUser addRoleToUser(@PathVariable long id,
                                 @RequestBody AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            List<Role> roles = userFromDb.getRole();

            List<AppUser> role = new ArrayList<>();
            role.add(userFromDb);

            newUser.setRoles(roles);
            userFromDb.setRoles(roles);

            return appUserRepository.save(newUser);
        }
        throw new UserNotFoundException("gebruiker niet gevonden");
    }

    @PutMapping(value = "/api/user/{id}/comment")
    public AppUser addCommentToUser(@PathVariable long id,
                                    @RequestBody AppUser newUser) {
        Optional<AppUser> user = appUserRepository.findById(id);

        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            List<Comment> comment = userFromDb.getComment();

            List<AppUser> comments = new ArrayList<>();
            comments.add(userFromDb);

            newUser.setComment(comment);
            userFromDb.setComment(comment);

            return appUserRepository.save(newUser);
        }
        throw new UserNotFoundException("gebruiker niet gevonden");
    }

}



