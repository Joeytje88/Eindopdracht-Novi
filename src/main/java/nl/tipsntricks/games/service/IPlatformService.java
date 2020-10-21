package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Platform;


public interface IPlatformService {
    Platform getPlatformById(long platformid);
    Platform addPlatform(Platform newPlatform);
    Platform updatePlatformByID(Long platformid, Platform updatedPlatform);
    Platform addPlatformToUser(Long accountid, Platform newPlatform);
    String deletePlatformById (Long platformid);
    boolean checkIsValidPlatform (String platformName);
}
