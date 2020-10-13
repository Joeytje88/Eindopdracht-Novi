package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Comment;
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

    @PutMapping(value= "/api/comment/user/{userid}")
    public Comment addCommentToUser (@PathVariable Long userid,@RequestBody Comment newComment){
        return commentService.addCommentToUser(userid, newComment);
    }

    @PutMapping(value = "/api/comment/{commentid}")
    public Comment updateCommentById (@PathVariable Long commentid, @RequestBody Comment updatedComment){
        return commentService.updateCommentById(commentid, updatedComment);
    }

    @DeleteMapping(value = "/api/comment/{commentid}")
    public String deleteComment(@PathVariable Long commentid) {
        return commentService.deleteComment(commentid);
    }

}
