package nl.tipsntricks.games.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private long postId;

    private String postURL;

    @Column(name = "post_title")
    private String postTitle;

    private String header;

    @Lob
    private String postText;

    @Lob
    private String picture;
    private String categorie;
    private String tags;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser author;

    @OneToMany (mappedBy = "post")
    Set<Comment> postComments;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }

    public Set<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<Comment> postComments) {
        this.postComments = postComments;
    }
}
