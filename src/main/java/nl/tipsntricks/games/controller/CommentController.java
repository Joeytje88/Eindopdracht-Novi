package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.repository.CommentRepository;
import nl.tipsntricks.games.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)

public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ICommentService commentService;

    @GetMapping(value = "api/comments")
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(value = "/api/comment/{commentid}")
    public Comment getCommentById(@PathVariable Long commentid) {
        return commentService.getCommentById(commentid);
    }

    @PutMapping(value = "/api/comment/{commentid}")
    public Comment updateCommentById (@PathVariable Long commentid, @RequestBody Comment updatedComment){
        return commentService.updateCommentById(commentid, updatedComment);
    }


    @PostMapping(value = "api//comment/{userid}/post/{postid}")
    public Comment addCommentToPost (@PathVariable long userid, @PathVariable long postid, @RequestBody Comment newComment){
        return commentService.addCommentToPost(userid, postid, newComment);
    }


    @DeleteMapping(value = "/api/comment/{commentid}")
    public String deleteComment(@PathVariable Long commentid) {
        return commentService.deleteComment(commentid);
    }

}
