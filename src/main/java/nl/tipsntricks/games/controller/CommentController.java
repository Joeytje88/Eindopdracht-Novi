package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.CommentRepository;
import nl.tipsntricks.games.service.CommentService;
import nl.tipsntricks.games.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

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
    @PostMapping("/comment/{commentid}/{userId}")
    public Comment addCommentToUser (Long userid, Comment newComment){
        return commentService.addCommentToUser(userid, newComment);
    }

    @PutMapping(value = "/api/comment/{commentid}")
    public Comment updateCommentById (long commentid, Comment updatedComment){
        return commentService.updateCommentById(commentid, updatedComment);
    }

    @DeleteMapping(value = "/api/comment/{commentid}")
    public String deleteComment(Long commentid) {
        return commentService.deleteComment(commentid);
    }



}