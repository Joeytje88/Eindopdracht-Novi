package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Platform;
import nl.tipsntricks.games.repository.PlatformRepository;
import nl.tipsntricks.games.service.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge=3600)
@RestController
public class PlatformController {

    @Autowired
    private PlatformRepository platformRepository;

    @Autowired
    private IPlatformService platformService;

    @GetMapping(value = "/api/platform/{platformid)")
    Platform getPlatformById(@RequestBody long platformid) {
        return platformService.getPlatformById(platformid);
    }

    @GetMapping(value = "/api/platforms")
    public List<Platform> getAllPlatforms() {
        return platformRepository.findAll();
    }

    @PostMapping(value = "/api/platform")
    Platform addPlatform(@RequestBody Platform newPlatform){
        return platformService.addPlatform(newPlatform);
    }

    @PutMapping(value = "api/platform/{platformid}")
    Platform updatePlatformByID(@PathVariable Long platformid, @RequestBody Platform updatedPlatform){
        return platformService.updatePlatformByID(platformid, updatedPlatform);
    }

    @PutMapping (value = "api/platform/user/{userid}")
    Platform addPlatformToUser(@PathVariable Long userid, @RequestBody Platform newPlatform){
        return platformService.addPlatformToUser(userid, newPlatform);
    }

    @DeleteMapping (value= "api/platform/{platformid}")
    String deletePlatformById (@PathVariable Long platformid){
        return platformService.deletePlatformById(platformid);
    }
}
