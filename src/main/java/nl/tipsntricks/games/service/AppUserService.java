package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.ERole;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.domain.Role;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class AppUserService implements IAppUserService {


    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService (AppUserRepository appUserRepository){
        this.appUserRepository= appUserRepository;
    }
    @Override
    public AppUser getUserById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public AppUser addUser(AppUser newUser) {
        String userName = newUser.getUsername();

        if (!userName.contains("fuck")) {
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
    public String deleteUser(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        if (user.isPresent()) {
            appUserRepository.deleteById(id);
            return "Gebruiker met id " + user.get().getId() + "is verwijderd";
        }
        throw new UserNotFoundException("Deze gebruiker bestaat niet");
    }

    @Override
    public AppUser addGameToUser(Long id, Game neGame) {
        return null;
    }


    @Override
    public AppUser addTestUserWithGame() {
        return null;
    }

    @Override
    public AppUser demoteUserById(long id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            if(userFromDb.getRole().contains(adminRole)) {
                userFromDb.getRole().remove(adminRole);
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
