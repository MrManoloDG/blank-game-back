package com.github.mrmanolodg.blankgame.repositories;

import com.github.mrmanolodg.blankgame.models.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveMongoRepository<Game, String> {
    Mono<Game> findAllByUuid(String uuid);
}
