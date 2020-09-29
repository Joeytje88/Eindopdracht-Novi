package nl.tipsntricks.games.domain;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

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
    private long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size (max = 50)
    private String email;

    @NotBlank
    private String password;


    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List <Role> role;


    @ManyToMany
    @JoinTable (name = "game_user",
              joinColumns = @JoinColumn (name= "game_id"),
              inverseJoinColumns = @JoinColumn (name= "user_id"))
    private List <Game> currentGames;

    @ManyToMany
    @JoinTable (name = "user_comment",
              joinColumns = @JoinColumn (name= "comment_id"),
              inverseJoinColumns = @JoinColumn (name= "user_id"))
    private List <Comment> comment;


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

    public List <Role> getRole() { return role; }

    public void setRoles(List <Role> role) { this.role = role; }

    public List<Game> getCurrentGames() {
        return currentGames;
    }

    public void setCurrentGames(List<Game> currentGames) { this.currentGames = currentGames; }

    public String setRole() { return ("User"); }

    public List<Comment> getComment() { return comment;}

    public void setComment (List<Comment> comments) { this.comment = comments; }
}

