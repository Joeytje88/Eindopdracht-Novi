package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.repository.PostRepository;
import nl.tipsntricks.games.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IPostService postService;

    @GetMapping(value = "api/post/{postid}")
    Post getPostById(@PathVariable long postid){
        return postService.getPostById(postid);
    }

    @GetMapping (value = "/api/posts")
    public List<Post> getAllPosts (){
        return postRepository.findAll();
    }

    @PostMapping (value = "api/post")
    public Post addPost(@RequestBody Post newPost){
        return postService.addPost(newPost);
    }

    @PutMapping(value = "api/post/user/{userid}")
    public Post addPostToUser (@PathVariable long userid ,@RequestBody Post newPost){
        return postService.addPostToUser(userid, newPost);
    }

    @PutMapping(value = "api/post/{postid}/comment/{commentid}")
    public Post addCommentToPost (@PathVariable long postid, @PathVariable long commentid, @RequestBody Comment newComment){
        return postService.addCommentToPost(postid, commentid, newComment);
    }

    @PutMapping(value ="api/post/{postid}")
    Post updatePostById(@PathVariable long postid, @RequestBody Post updatedPost){
        return postService.updatePostById(postid,updatedPost);
    }

    @DeleteMapping(value= "api/post/{postid}")
    String deletePostById(@PathVariable long postid){
        return postService.deletePostById(postid);
    }
}
