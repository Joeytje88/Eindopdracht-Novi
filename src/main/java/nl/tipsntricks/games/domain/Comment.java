package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentid;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Comment (){
    }

    public Long getCommentid (){
        return commentid;
    }
    public void setId(long commentid) {
        this.commentid = commentid;
    }

    public String  getText() {
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
