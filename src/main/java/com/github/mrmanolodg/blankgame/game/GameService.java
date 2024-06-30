package com.github.mrmanolodg.blankgame.game;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GameService {

    private final WordService wordService;

    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final Map<String, Game> createdGames = new HashMap<>();

    @Autowired
    public GameService(WordService wordService) {
        this.wordService = wordService;
    }

    public String createGame(String user){

        return "newUUID";
    }

    public Game getGame(String uuid){
        return this.createdGames.get(uuid);
    }

    public void endGame(UUID uuid){
        this.createdGames.remove(uuid);
        logger.info("Removed game with UUID: {}", uuid);
    }

    public Game joinUserToGame(String uuid, String user){
        Game game = this.createdGames.get(uuid);
        game.addUserToGame(user);
        return game;
    }

    public void GenerateWords(String uuid) throws IOException {
        //Obtain word
        String word = wordService.getRandomWord();
        Map<String, String> userWordMap = new HashMap<>();

        Game game = this.createdGames.get(uuid);
        List<String> users = game.getUsers();
        // Choose blank users
       // int indexUser = RandomUtils.nextInt(0, users.size() - 1 );
        String blankUser = users.get(0);
        for (String user : users){
            if(user.equals(blankUser))
                userWordMap.put(user, "Blank");
            else
                userWordMap.put(user, word);
        }
        game.setUserWordMap(userWordMap);
    }

    public String getWord(String uuid, String user){
        Game game = this.createdGames.get(uuid);
        if(game == null || game.getUserWordMap() == null)
            return "";
        return game.getUserWordMap().get(user);
    }

}
