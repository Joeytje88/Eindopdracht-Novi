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

    @Lob
    private String postText;

    @OneToMany
    @JoinColumn(name="postCategory_id")
    Set<Category> categories;

    @OneToMany (mappedBy = "post",cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set <Tag> tags;

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private AppUser author;

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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }
}
