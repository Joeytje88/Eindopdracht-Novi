package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;
    private String text;

    @JsonIgnoreProperties ("comment")
    @ManyToMany (fetch = FetchType.EAGER, mappedBy = "comment")
    private List<AppUser> comments;

    public Comment () {
    }

    public Long getcommentId (){
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

    public List<AppUser> getComments() {
        return comments;
    }

    public void setComments(List<AppUser> comments) {
        this.comments = comments;
    }


}
