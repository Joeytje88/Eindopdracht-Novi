package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.domain.Tag;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.exception.TagException;
import nl.tipsntricks.games.repository.PostRepository;
import nl.tipsntricks.games.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Autowired
    private PostRepository postRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag getTagById(long tagid) {
        return tagRepository.findById(tagid)
                .orElseThrow(()-> new TagException("Tag niet gevonden"));
    }

    @Override
    public Tag addTagToPost(long postid, Tag newTag) {
       Optional<Post> post = postRepository.findById(postid);
       if (post.isPresent()){
           Post postFromDb = post.get();
           Set<Tag> tags = postFromDb.getTags();

           tags.add(newTag);
           newTag.setPost(postFromDb);
           return tagRepository.save(newTag);
       }
       throw new TagException("tag niet gevonden");
    }

    @Override
    public String deleteTagById(long tagid) {
        Optional<Tag> tag = tagRepository.findById(tagid);
        if (tag.isPresent()){
            tagRepository.deleteById(tagid);
            return "tag met id " +tag.get().getTagid() + " is verwijderd";
        }
        throw new TagException("tag niet gevonden");
    }

    @Override
    public Tag updateTagById(long tagid, Tag updatedTag) {
        return tagRepository.findById(tagid).map(
                tag -> {
                    tag.setTagName(updatedTag.getTagName());
                    return tagRepository.save(tag);
                }).orElseGet(()-> {
                    updatedTag.setTagid(tagid);
                    return tagRepository.save(updatedTag);
                }
        );
    }
}
