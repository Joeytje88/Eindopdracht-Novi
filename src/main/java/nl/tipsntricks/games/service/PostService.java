package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPostById(long postid) {
        return postRepository.findById(postid)
                .orElseThrow(() -> new PostNotFoundException("post niet gevonden"));
    }

    @Override
    public Post addPost(Post newPost) {
        String postTitle = newPost.getPostTitle();
        if (checkIsValidName(postTitle)) {
            return postRepository.save(newPost);
        }
        throw new PostNotFoundException("post bestaat niet");

}

    @Override
    public Post updatePostById(long postid, Post updatedPost) {
        return postRepository.findById(postid).map(
                post -> {
                    post.setPostTitle(updatedPost.getPostTitle());
                    post.setPostText(updatedPost.getPostText());
                    post.setPostURL(updatedPost.getPostURL());
                    post.setAuthor(updatedPost.getAuthor());
                    post.setCategorie(updatedPost.getCategorie());
                    post.setHeader(updatedPost.getHeader());
                    return postRepository.save(post);
                }).orElseGet(() -> {
                    updatedPost.setPostId(postid);
                    return postRepository.save(updatedPost);
                });
    }

    @Override
    public String deletePostById(long postid) {
        Optional<Post> post = postRepository.findById(postid);
                if (post.isPresent()){
                     postRepository.deleteById(postid);
                     return "Post met id " +post.get().getPostId() + " is verwijderd";
        }
        throw new PostNotFoundException("Post niet gevonden");
    }


private boolean checkIsValidName(String postTitle) {
    if (postTitle.contains("poep") || postTitle.equalsIgnoreCase("")) {
        return false;
    }
    return true;
}
        }
