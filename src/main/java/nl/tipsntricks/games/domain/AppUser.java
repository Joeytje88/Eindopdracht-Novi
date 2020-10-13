package nl.tipsntricks.games.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )

    @Column(columnDefinition = "serial")
    private long userid;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size (max = 50)
    private String email;

    @NotBlank
    @JsonIgnoreProperties
    private String password;


    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set <Role> roles;


    @ManyToMany (cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("users")
    @JoinTable (name = "user_games",
            joinColumns = @JoinColumn (name= "user_id"),
            inverseJoinColumns = @JoinColumn (name= "game_id"))
    private Set <Game> currentGames;

    @ManyToMany (cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("owners")
    @JoinTable (name= "platform_owners",
            joinColumns = @JoinColumn(name= "user_id"),
            inverseJoinColumns = @JoinColumn (name= "platform_id"))
    private Set <Platform> platforms;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Comment> comments;


    public AppUser() {
    }

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserId(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set <Role> roles) { this.roles = roles; }

    public Set<Game> getCurrentGames() {
        return currentGames;
    }

    public void setCurrentGames(Set<Game> currentGames) {
        this.currentGames = currentGames;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;


    }
}

