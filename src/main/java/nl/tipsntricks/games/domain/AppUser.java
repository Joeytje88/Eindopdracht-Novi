package nl.tipsntricks.games.domain;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.Set;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;
    private String username;
    private String email;
    private String password;


    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    @ManyToMany
    @JoinTable (name = "game_user",
              joinColumns = @JoinColumn (name= "game_id"),
              inverseJoinColumns = @JoinColumn (name= "user_id"))
    private List <Game> currentGames;

    @ManyToMany
    @JoinTable (name = "comment_user",
              joinColumns = @JoinColumn (name= "comment_id"),
              inverseJoinColumns = @JoinColumn (name= "user_id"))
    private List <Comment> comments;


    public AppUser() {
    }

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Game> getCurrentGames() {
        return currentGames;
    }

    public void setCurrentGames(List<Game> currentGames) { this.currentGames = currentGames; }

    public String setRoles() { return ("User"); }

    public List<Comment> getComments() { return comments;}

    public void setCommentlist(List<Comment> comments) { this.comments = comments; }
}

