package nl.tipsntricks.games.domain;

import javax.persistence.*;

@Entity
public class Game {

@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private long gameId;
private String name;


    public Game (String name){
        this.name = name;
    }

    public Game() {

    }

public long getGameId() {
    return gameId;
    }
public void setGameId (long gameId) {
    this.gameId= gameId;
    }
public String getName() {
    return name;
    }
public void setName(String name) {
    this.name= name;
    }

}

