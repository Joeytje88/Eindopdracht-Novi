package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Topic;
import nl.tipsntricks.games.repository.TopicRepository;
import nl.tipsntricks.games.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ITopicService topicService;

    @GetMapping(value = "api/topics")
    public List<Topic> getAllTopics(){
        return topicRepository.findAll();
    }

    @GetMapping(value = "api/topic/{topicid}")
    public Topic getTopicById(@PathVariable long topicid){
        return topicService.getTopicById(topicid);
    }

    @PutMapping (value = "api/topic/{topicid}")
    public Topic updateTopicById(@PathVariable long topicid, @RequestBody Topic updatedTopic){
        return topicService.updateTopicById(topicid, updatedTopic);
    }

    @DeleteMapping(value = "api/topic/{topicid}")
    public String deleteTopic(@PathVariable long topicid){
        return topicService.deleteTopicByid(topicid);
    }
}
