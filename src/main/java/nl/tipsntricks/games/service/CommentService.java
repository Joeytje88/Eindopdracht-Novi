package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Reactie niet gevonden"));
    }

    @Override
    public Comment addComment(Comment newComment) {
        String commentMessage = newComment.getText();
        if (!commentMessage.contains("Tering")) {
            return commentRepository.save(newComment);
        }
        throw new CommentNotFoundException("reactie bestaat niet");
    }

    @Override
    public Comment updateCommentById(Long id, Comment updatedComment) {
        Optional<Comment> commentFromDB = commentRepository.findById(id);

        if (commentFromDB.isPresent()) {
            if (checkIsValidCommentMessage(updatedComment.getText())) {
                Comment comment = new Comment();
                comment.setText(updatedComment.getText());
                return commentRepository.save(comment);
            }
        }
        throw new CommentNotFoundException("Reactie bestaat niet");
    }

    @Override
    public String deleteComment(long id) {
        return null;
    }

    @Override
    public Comment addCommentToUser(Long id, AppUser updatedUser) {
        return null;
    }

    @Override
    public Comment addTestCommentWithUser() {
        return null;
    }
    private boolean checkIsValidCommentMessage(String message) {
        if (message.contains("tering") || message.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
