package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Platform;
import nl.tipsntricks.games.exception.PlatformNotFoundExecption;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlatformService implements IPlatformService {

    private final PlatformRepository platformRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    public PlatformService(PlatformRepository platformRepository) {
        this.platformRepository = platformRepository;
    }


    @Override
    public Platform getPlatformById(long platformid) {
        return platformRepository.findById(platformid)
                .orElseThrow(()-> new PlatformNotFoundExecption("platform niet gevonden"));
    }

    @Override
    public Platform addPlatform(Platform newPlatform) {
        String platformName = newPlatform.getPlatformName();
        checkIsValidPlatform(platformName);
        return platformRepository.save(newPlatform);
    }

    @Override
    public Platform updatePlatformByID(Long platformid, Platform updatedPlatform) {
        return platformRepository.findById(platformid).map(
                platform -> {
                    platform.setPlatformName(updatedPlatform.getPlatformName());
                    return platformRepository.save(platform);
                }).orElseGet(() -> {
            updatedPlatform.setPlatformid(platformid);
            updatedPlatform.setPlatformName(updatedPlatform.getPlatformName());
            return platformRepository.save(updatedPlatform);
        });
    }

    @Override
    public Platform addPlatformToUser(Long userid, Platform newPlatform) {
        Optional<AppUser> appUser = appUserRepository.findById(userid);
        if (appUser.isPresent()) {
            AppUser userFromDb = appUser.get();
            Set<Platform> platforms = userFromDb.getPlatforms();

            platforms.add(newPlatform);
            userFromDb.setPlatforms(platforms);

            return platformRepository.save(newPlatform);
        }
        throw new PlatformNotFoundExecption("platform niet gevonden");
    }

    @Override
    public String deletePlatformById(Long platformid) {
        Optional<Platform> platform = platformRepository.findById(platformid);
        if (platform.isPresent()) {
            platformRepository.deleteById(platformid);
            return "Platform met id " +platform.get().getPlatformid() + "is verwijderd";
        }
        throw new PlatformNotFoundExecption("Platform bestaat niet");
    }

    @Override
    public boolean checkIsValidPlatform(String platformName) {
        if (platformName.contains("klote") || platformName.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
