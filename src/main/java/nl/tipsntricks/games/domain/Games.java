package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Games {

@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private long gameId;
private String name;
private String developer;
private String publisher;
private Date releasedate;
private String platforms;




@ManyToMany
@JoinTable (name = "games_user",
            joinColumns = @JoinColumn (name= "game_id"),
            inverseJoinColumns = @JoinColumn (name= "user_id"))
    private List<AppUser> user;


public long getGameId() { return gameId;}
public void setGameId (long id) {this.gameId= gameId;}
public String getName() {return name;}
public void setName(String name) {this.name= name;}
public String getDeveloper () {return developer;}
public void setDeveloper (String developer) {this.developer=developer;}
public String getPublisher () {return publisher;}
public void setPublisher (String publisher) {this.publisher=publisher;}
public Date getReleasedate () {return releasedate;}
public void setReleasedate (Date releasedate){this.releasedate=releasedate;}
public String getPlatforms () {return platforms;}
public void setPlatforms (String platforms) {this.platforms=platforms;}
public List <AppUser> user (){return user;}
public void setUser (List <AppUser> user){this.user = user;}
}

