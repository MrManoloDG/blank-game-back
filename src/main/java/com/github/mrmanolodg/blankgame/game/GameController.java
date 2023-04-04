package com.github.mrmanolodg.blankgame.game;

import com.github.mrmanolodg.blankgame.game.gameDto.JoinClassDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/game")
public class GameController {

    private static final Gson gson = new Gson();

    private final GameService gameService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public GameController(GameService gameService, SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping()
    public ResponseEntity<String> createGame(@RequestParam("user") String user){
        return ResponseEntity.ok(gson.toJson(gameService.createGame(user)));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<String>  getGame(@PathVariable("uuid") String uuid){
        Game game = gameService.getGame(uuid);
        if (game != null){
            return new ResponseEntity<>(gson.toJson(game), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Game not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{uuid}/user/{user}")
    public ResponseEntity<String> getWord(@PathVariable("uuid") String uuid, @PathVariable("user") String user){
        return new ResponseEntity<>(gson.toJson(gameService.getWord(uuid,user)), HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinToGame(@RequestBody JoinClassDto joinInstance){
        try{
            Game game = gameService.joinUserToGame(joinInstance.getUuid(), joinInstance.getUser());
            return ResponseEntity.ok(gson.toJson(game));
        } catch (RuntimeException e){
            return new ResponseEntity<>(gson.toJson("Username is already taken"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{uuid}/start")
    public ResponseEntity<String> startGame(@PathVariable("uuid") String uuid, @RequestParam("user") String user) throws IOException {
        Game game = gameService.getGame(uuid);
        if (game == null){
            return new ResponseEntity<>(gson.toJson("Game not found"), HttpStatus.NOT_FOUND);
        }

        if(game.getCreatedBy().equals(user)){
            game.setStarted(true);
            //TODO Send message websocket and generate first round
            gameService.GenerateWords(game.getUuid());
            simpMessagingTemplate.convertAndSend("/topic/game/"+ game.getUuid(), "Started Game");
            return ResponseEntity.ok(gson.toJson("Game Started"));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

}
