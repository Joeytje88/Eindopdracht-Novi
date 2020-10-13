package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.*;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public AppUser updateUserById(Long userid, AppUser updatedUser) {
        return appUserRepository.findById(userid).map(
                appUser -> {
                    appUser.setUsername(updatedUser.getUsername());
                    appUser.setEmail(updatedUser.getEmail());
                    appUser.setPassword(updatedUser.getPassword());
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
