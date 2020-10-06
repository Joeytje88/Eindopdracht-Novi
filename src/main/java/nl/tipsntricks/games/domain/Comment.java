package nl.tipsntricks.games.domain;

import javax.persistence.*;;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;

    private String text;

    @ManyToOne
    @JoinColumn (name = "user_comments")
    private AppUser appUser;


    public Comment () {
    }


    public Long getId (){
        return commentId;
    }
    public void setId(long commentId) {
        this.commentId = commentId;
    }

    public String  getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
