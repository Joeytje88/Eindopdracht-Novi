package nl.tipsntricks.games.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long platformid;
    private String platformName;

    @ManyToMany (mappedBy = "platforms")
    Set<AppUser> user;


    public Platform() {
    }

    public Long getPlatformid() {
        return platformid;
    }

    public void setPlatformid(Long platformid) {
        this.platformid = platformid;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Set<AppUser> getUser() {
        return user;
    }

    public void setUser(Set<AppUser> user) {
        this.user = user;
    }
}




