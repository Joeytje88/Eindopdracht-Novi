package nl.tipsntricks.games.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Game {

@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private long gameId;
private String name;

@ManyToMany
Set<AppUser> users;


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
    public Set<AppUser> getUsers() {
        return users;
    }
    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

}

