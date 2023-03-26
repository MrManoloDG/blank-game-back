package com.github.mrmanolodg.blankgame.game;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {
    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final Map<String, String> createdGames = new HashMap<>();

    public String createGame(){
        String newUUID = RandomStringUtils.randomNumeric(6);
        while(this.createdGames.containsKey(newUUID)){
            newUUID = RandomStringUtils.randomNumeric(6);
        }
        this.createdGames.put(newUUID, "");
        logger.info("Created new Game with UUID: {}", newUUID);
        return newUUID;
    }

    public void endGame(UUID uuid){
        this.createdGames.remove(uuid);
        logger.info("Removed game with UUID: {}", uuid);
    }

}
