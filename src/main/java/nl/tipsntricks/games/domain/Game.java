package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

@Id
@GeneratedValue (strategy = GenerationType.AUTO)
private long gameId;
private String name;
private String developer;
private String publisher;
private int releasedate;
private String platforms;


@JsonIgnoreProperties("currentGames")
@ManyToMany (fetch = FetchType.EAGER, mappedBy = "currentGames")
private List <AppUser> owners;

public long getGameId() { return gameId;}
public void setGameId (long id) {this.gameId= id;}
public String getName() {return name;}
public void setName(String name) {this.name= name;}
public String getDeveloper () {return developer;}
public void setDeveloper (String developer) {this.developer=developer;}
public String getPublisher () {return publisher;}
public void setPublisher (String publisher) {this.publisher=publisher;}
public int getReleasedate () {return releasedate;}
public void setReleasedate (int releasedate){this.releasedate=releasedate;}
public String getPlatforms () {return platforms;}
public void setPlatforms (String platforms) {this.platforms=platforms;}
public List <AppUser> getOwners (){return owners;}
public void setOwners (List <AppUser> owners){this.owners = owners;}
}

