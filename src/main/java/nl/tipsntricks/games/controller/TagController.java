package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Tag;
import nl.tipsntricks.games.repository.TagRepository;
import nl.tipsntricks.games.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ITagService tagService;

    @GetMapping(value = "api/tags")
    List<Tag> getAllTags () {
        return tagRepository.findAll();
    }

    @GetMapping(value = "api/tag/{tagid}")
    Tag getTagById(@PathVariable long tagid){
        return tagService.getTagById(tagid);
    }

    @PutMapping(value = "api/tag/{tagid}")
    Tag updateTagById (@PathVariable long tagid, @RequestBody Tag updatedTag){
        return tagService.updateTagById(tagid, updatedTag);
    }

    @PutMapping(value = "api/tag/post/{postid}")
    Tag addTagToPost (@PathVariable long postid, @RequestBody Tag newTag){
        return tagService.addTagToPost(postid, newTag);
    }

    @DeleteMapping(value = "api/tag/{tagid}")
    String deleteTagById(@PathVariable long tagid){
        return tagService.deleteTagById(tagid);
    }

 }
