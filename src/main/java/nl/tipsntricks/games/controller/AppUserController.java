package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.*;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.service.AppUserService;
import nl.tipsntricks.games.service.IAppUserService;
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
    private IAppUserService appUserService;

    @GetMapping(value = "/api/user")
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @GetMapping(value = "/api/user/{userid}")
    public AppUser getUserById(@PathVariable Long userid) {
       return appUserService.getUserById(userid);
    }

    @DeleteMapping(value = "/api/user/{userid}")
    public String deleteUser(@PathVariable long userid){
        return appUserService.deleteUser(userid);
    }

    @PutMapping(value = "/api/user/{userid}")
    public AppUser updateUserById(@PathVariable long userid,@RequestBody AppUser updatedAppUser) {
        return appUserService.updateUserById(userid, updatedAppUser);
    }

    @PutMapping (value ="api/user/game/{userid}")
    public AppUser addGameToUser(@PathVariable long userid, @RequestBody Game newGame){
        return appUserService.addGameToUser(userid, newGame);
    }

    @PostMapping (value = "api/user/comment/{userid}")
    public AppUser addCommentToUser(@PathVariable long userid, @RequestBody Comment newComment){
        return appUserService.addCommentToUser(userid, newComment);
    }


    @PostMapping (value = "api/user/platform/{userid}")
    public AppUser addPlatformToUser(@PathVariable long userid, @RequestBody Platform newPlatform){
        return appUserService.addPlatformToUser(userid, newPlatform);
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



