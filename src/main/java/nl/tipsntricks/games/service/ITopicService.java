package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Topic;

public interface ITopicService {
    Topic getTopicById(long topicid);
    Topic updateTopicById(long topicid, Topic updatedTopic);
    String deleteTopicByid(long topicid);
}
