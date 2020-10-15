package nl.tipsntricks.games.domain;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;;import java.util.Date;

@CrossOrigin(origins = "*",maxAge = 3600)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentid;

    @NotBlank
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Comment() {
    }

    public Comment (String text, String url){
        this.text = text;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setId(long commentid) {
        this.commentid = commentid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AppUser user() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

}
