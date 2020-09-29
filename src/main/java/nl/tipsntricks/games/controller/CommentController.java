package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @SuppressWarnings("UnnecessaryLocalVariable")
    @GetMapping(value = "api/comments")
    public List<Comment> comments() {
        List<Comment> comment = commentRepository.findAll();
        return comment;
    }

    @GetMapping(value = "/api/comment/{id}")
    public Comment getComments(@PathVariable long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new CommentNotFoundException("Reactie niet gevonden"));
    }

    @PostMapping(value = "/api/comment/")
    public Comment addComment(@RequestBody Comment newComment) {
        return commentRepository.save(newComment);

    }

    @DeleteMapping(value = "/api/comment/{commentid}")
    public String deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.deleteById(id);
            return "Reactie met id" + comment.get().getcommentId() + "is niet gevonden";
        }
        throw new CommentNotFoundException("Reactie niet gevonden");
    }

    @PutMapping(value = "/api/comment/{id}/user")
    public Comment addUserToComment(@PathVariable long id,
                                    @RequestBody Comment newComment) {
        Optional<Comment> reactie = commentRepository.findById(id);

        if (reactie.isPresent()) {
            Comment commentFromDb = reactie.get();
            List<AppUser> comments = commentFromDb.getComments();

            List<Comment> comment = new ArrayList<>();
            comment.add(commentFromDb);

            newComment.setComments(comments);
            commentFromDb.setComments(comments);

            return commentRepository.save(newComment);
        }
        throw new CommentNotFoundException("reactie niet gevonden");
    }
}
