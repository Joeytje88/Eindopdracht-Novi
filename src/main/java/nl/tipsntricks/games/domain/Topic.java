package nl.tipsntricks.games.domain;

import javax.persistence.*;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long topicid;

    private String topicUrl;
    @Lob
    private String topicImage;

    private String topicText;

    public long getTopicid() {
        return topicid;
    }

    private String Writer;

    public Topic (){

    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl;
    }

    public void setTopicid(long topicid) {
        this.topicid = topicid;
    }

    public String getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(String topicImage) {
        this.topicImage = topicImage;
    }

    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }
}
