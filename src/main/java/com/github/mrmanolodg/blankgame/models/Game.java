package com.github.mrmanolodg.blankgame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collation = "games")
public class Game {

    @Id
    private String id;

    private String uuid;

    private String createdBy;

    private Boolean isEnded;

    private Boolean isStarted;

    private String joinUrl;

    private Long totalBlankNumber;

    private Map<String, String> userWordMap;

    private List<String> users;

}
