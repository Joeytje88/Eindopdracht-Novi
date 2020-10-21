package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Set;


@Entity
public class Account {
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "account_id")
    private long accountid;

    @ManyToMany (cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("users")
    @JoinTable (name = "user_games",
            joinColumns = @JoinColumn (name= "user_id"),
            inverseJoinColumns = @JoinColumn (name= "game_id"))
    private Set<Game> currentGames;

    private String  accountUrl;

    @Column(columnDefinition = "text")
    private String image;

    @ManyToMany (cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("owners")
    @JoinTable (name= "platform_owners",
            joinColumns = @JoinColumn(name= "user_id"),
            inverseJoinColumns = @JoinColumn (name= "platform_id"))
    private Set <Platform> platforms;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AppUser userAccount;

    public Account() {

    }

    public long getAccountid() {
        return accountid;
    }

    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AppUser getUserAccount() {
        return userAccount;
    }

    public Set<Game> getCurrentGames() {
        return currentGames;
    }

    public void setCurrentGames(Set<Game> currentGames) {
        this.currentGames = currentGames;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setUserAccount(AppUser userAccount) {
        this.userAccount = userAccount;
    }

}
