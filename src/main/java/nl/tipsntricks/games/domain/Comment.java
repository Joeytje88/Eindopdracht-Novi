package nl.tipsntricks.games.domain;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@CrossOrigin(origins = "*",maxAge = 3600)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private long commentid;

    @NotBlank
    private String text;

    @Column(columnDefinition = "text")
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AppUser user() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
