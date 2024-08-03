package com.github.mrmanolodg.blankgame.repositories;

import com.github.mrmanolodg.blankgame.models.Word;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WordRepository extends ReactiveMongoRepository<Word, String> {

    @Aggregation({
            "{ $sample:  {size: 1}}"
    })
    Mono<Word> getWordRandomly();
}
