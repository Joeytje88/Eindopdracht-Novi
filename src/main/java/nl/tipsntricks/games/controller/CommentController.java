package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.repository.CommentRepository;
import nl.tipsntricks.games.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @SuppressWarnings("UnnecessaryLocalVariable")
    @GetMapping(value = "api/comments")
    public List<Comment> comments() {
        List<Comment> comment = commentRepository.findAll();
        return comment;
    }

    @GetMapping(value = "/api/comment/{commentid}")
    public Comment getComments(@PathVariable long commentid) {
        return commentService.getCommentById(commentid);
    }

    @PostMapping(value = "/api/comment")
    public Comment addComment(@RequestBody Comment newComment) {
        return commentService.addComment(newComment);
    }

    @PostMapping(value= "/api/comment/{userid}")
    public Comment addCommentToUser (@PathVariable Long userid,@RequestBody Comment newComment){
        return commentService.addCommentToUser(userid, newComment);
    }

    @PutMapping(value = "/api/comment/{commentid}")
    public Comment updateCommentById (@PathVariable long commentid,@RequestBody Comment updatedComment){
        return commentService.updateCommentById(commentid, updatedComment);
    }

    @DeleteMapping(value = "/api/comment/{commentid}")
    public String deleteComment(@PathVariable Long commentid) {
        return commentService.deleteComment(commentid);
    }



}
