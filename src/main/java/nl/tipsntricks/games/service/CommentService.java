package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Game;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.exception.GameNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Autowired
    private AppUserRepository appUserRepository;

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
        checkIsValidCommentMessage(commentMessage);
        return commentRepository.save(newComment);
    }

    @Override
    public Comment updateCommentById(Long commentId, Comment updatedComment) {
        Optional<Comment> commentFromDB = commentRepository.findById(commentId);

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
    public String deleteComment(long commentid) {
        Optional<Comment> comment = commentRepository.findById(commentid);
        if (comment.isPresent()) {
            commentRepository.deleteById(commentid);
            return "Game met id" + comment.get().getId() + "is verwijderd";
        }
        throw new GameNotFoundException("Deze game bestaat niet");
    }
    

    @Override
    public Comment addCommentToUser(Long userid, Comment newComment) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();

            newComment.setAppUser(userFromDb);
            return commentRepository.save(newComment);
        }
        throw new CommentNotFoundException("reactie niet gevonden");
    }


    private boolean checkIsValidCommentMessage(String message) {
        if (message.contains("tering") || message.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
