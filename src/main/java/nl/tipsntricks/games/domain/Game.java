package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "gameId")
@Entity
public class Game {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
private long gameId;

@Column(name = "game_title")
private String name;

@ManyToMany (mappedBy = "currentGames", cascade = CascadeType.ALL)
Set<AppUser> users;


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

