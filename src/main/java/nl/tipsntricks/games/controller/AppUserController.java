package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

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

        if(owner.isPresent()) {
            appUserRepository.deleteById(id);
            return "User met id " + owner.get().getId() + " is verwijderd";
        }
        return "User niet gevonden";
    }

    @PutMapping(value = "/api/user/{id}")
    public AppUser updateUserById(@PathVariable long id, @RequestBody AppUser updatedAppUser) {
        return appUserRepository.findById(id).map(
                user -> {
                    user.setUsername(updatedAppUser.getUsername());
                    user.setEmail(updatedAppUser.getEmail());
                    return appUserRepository.save(user);
                })
                // Kan de user niet vinden in database
                .orElseGet(() -> {
                    updatedAppUser.setId(id);
                    return appUserRepository.save(updatedAppUser);
                });
    }
}



