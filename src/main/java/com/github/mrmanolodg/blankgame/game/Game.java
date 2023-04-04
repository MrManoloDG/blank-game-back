package com.github.mrmanolodg.blankgame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game {

    private String joinURL;
    private String uuid;

    private  List<String> users;
    private  List<String> deletedUsers;

    private boolean isStarted;

    private final String createdBy;

    private Map<String, String> userWordMap;

    public Game(String uuid, String createdBy) {
        this.uuid = uuid;
        this.createdBy = createdBy;
        this.users = new ArrayList<>();
        this.deletedUsers = new ArrayList<>();
        this.isStarted = false;
        this.addUserToGame(createdBy);
    }

    public void addUserToGame(String user) throws RuntimeException{
        if(this.users.contains(user)){
            throw new RuntimeException("Username is already on the game");
        }
        this.users.add(user);
    }

    public void clearDeletedUsers(){
        this.deletedUsers = new ArrayList<>();
    }

    public String getJoinURL() {
        return joinURL;
    }

    public void setJoinURL(String joinURL) {
        this.joinURL = joinURL;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getDeletedUsers() {
        return deletedUsers;
    }

    public void setDeletedUsers(List<String> deletedUsers) {
        this.deletedUsers = deletedUsers;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Map<String, String> getUserWordMap() {
        return userWordMap;
    }

    public void setUserWordMap(Map<String, String> userWordMap) {
        this.userWordMap = userWordMap;
    }
}
