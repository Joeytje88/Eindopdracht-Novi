package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.exception.CommentNotFoundException;
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
            if (checkIsValidName(postTitle)) {
        return postRepository.save(newPost);
    }
    throw new PostNotFoundException("post bestaat niet");

}

    @Override
    public Post addPostToUser(long userid, Post newPost) {
        Optional <AppUser> user = appUserRepository.findById(userid);
        if(user.isPresent()) {
            AppUser userFromDb= user.get();
            Set <Post> posts = userFromDb.getPosts();

            AppUser author = new AppUser();
            author.setPosts(posts);

            posts.add(newPost);
            userFromDb.setPosts(posts);

            return postRepository.save(newPost);
        }
        throw new PostNotFoundException("post bestaat niet");
    }

    @Override
    public Post addCommentToPost(long postid,long commentid, Comment newComment) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isPresent()){
            Post postFromDb = post.get();
            Set<Comment> postComments = postFromDb.getPostComments();

            postComments.add(newComment);
            postFromDb.setPostComments(postComments);

            return postRepository.save(postFromDb);
        }
        throw new PostNotFoundException("post niet gevonden");
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
