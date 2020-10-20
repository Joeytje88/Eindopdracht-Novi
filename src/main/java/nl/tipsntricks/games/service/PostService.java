package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Category;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    @Autowired
    private AppUserRepository appUserRepository;

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
            String postText= newPost.getPostText();
            if (checkIsValidName(postTitle))
                if (checkIsValidName(postText)){
        return postRepository.save(newPost);
    }
    throw new PostNotFoundException("post bestaat niet");

}

    @Override
    public Post addPostToUser(long userid, Post newPost) {
        Optional <AppUser> user = appUserRepository.findById(userid);
        if(user.isPresent()){
            AppUser userFromDb= user.get();
            Set <Post> posts = userFromDb.getPosts();

            posts.add(newPost);
            userFromDb.setPosts(posts);

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
                    return postRepository.save(post);
                }).orElseGet(() -> {
                    updatedPost.setPostId(postid);
                    updatedPost.setAuthor(updatedPost.getAuthor());
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


private boolean checkIsValidName(String name) {
        if(name.contains("fuck")) {
        return false; }
        return true; }
        }
