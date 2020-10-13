package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin (origins = "*", maxAge = 3600)
@RestController

public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @SuppressWarnings("UnnecessaryLocalVariable")
    @GetMapping(value = "/api/user")
    public List<AppUser> user() {
        List<AppUser> user = appUserRepository.findAll();
        return user;
    }

    @GetMapping(value = "/api/user/{userid}")
    public AppUser getUserById(@PathVariable Long userid) {
       return appUserService.getUserById(userid);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/api/user")
    public AppUser addUser(AppUser newAppUser) {
        return appUserService.addUser(newAppUser);
    }

    @DeleteMapping(value = "/api/user/{userid}")
    public String deleteUser(@PathVariable long userid){
        return appUserService.deleteUser(userid);
    }

    @PutMapping(value = "/api/user/{userid}")
    public AppUser updateUserById(@PathVariable long userid,@RequestBody AppUser updatedAppUser) {
        return appUserService.updateUserById(userid, updatedAppUser);
    }

    @PreAuthorize("hasRole ('ROLE_ADMIN')")
    @PutMapping(value = "/api/user/{userid}/role")
    public AppUser addRoleToUser(@PathVariable long userid, @RequestBody AppUser newUser) {
        return appUserService.addRoleToUser(userid, newUser);
    }

    @PreAuthorize("hasRole ('ROLE_ADMIN') ")
    @PutMapping (value = "/api/user/role/{userid}")
    public AppUser demoteUserById (@PathVariable long userid){
        return appUserService.demoteUserById(userid);
    }

}



