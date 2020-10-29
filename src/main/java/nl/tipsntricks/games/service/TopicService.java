package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.domain.Topic;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.exception.TopicNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TopicService implements ITopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public Topic getTopicById(long topicid) {
        return topicRepository.findById(topicid)
                .orElseThrow(()-> new TopicNotFoundException("Topic niet gevonden"));
    }



    @Override
    public Topic updateTopicById(long topicid, Topic updatedTopic) {
        return topicRepository.findById(topicid).map(
                topic -> {
                    topic.setTopicImage(updatedTopic.getTopicImage());
                    topic.setTopicText(updatedTopic.getTopicText());
                    return topicRepository.save(topic);
                }).orElseGet(()->{
                    updatedTopic.setTopicid(topicid);
                    return topicRepository.save(updatedTopic);
                });
    }

    @Override
    public String deleteTopicByid(long topicid) {
       Optional<Topic> topic = topicRepository.findById(topicid);
       if(topic.isPresent()){
           topicRepository.deleteById(topicid);
           return "Topic met id " +topic.get().getTopicid() + " is verwijderd";
       }
       throw new TopicNotFoundException("Topic niet gevonden");
    }
}
