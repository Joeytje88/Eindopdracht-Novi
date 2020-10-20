package nl.tipsntricks.games.domain;

import javax.persistence.*;

@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

@Column(name = "category_name")
private String categoryName;

@ManyToOne (fetch = FetchType.EAGER)
@JoinColumn(name = "postCategory_id", referencedColumnName = "post_id")
    private Post postCategory;

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Post getPostCategory() {
        return postCategory;
    }

    public void setPostCategory( Post postCategory) {
        this.postCategory = postCategory;
    }
}
