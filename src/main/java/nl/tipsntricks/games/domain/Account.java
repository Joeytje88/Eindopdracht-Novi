package nl.tipsntricks.games.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "account_id")
    private long accountid;

    private String accountName;

    @Column(columnDefinition = "text")
    private String image;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AppUser userAccount;

    public Account() {
    }


    public long getAccountid() {
        return accountid;
    }

    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AppUser getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(AppUser userAccount) {
        this.userAccount = userAccount;
    }

}
