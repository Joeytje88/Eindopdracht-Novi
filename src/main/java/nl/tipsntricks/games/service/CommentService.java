package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

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
    public Comment getCommentById(Long commentid) {
        return commentRepository.findById(commentid)
                .orElseThrow(() -> new CommentNotFoundException(commentid));
    }
    //geeft een 500 als comment niet bestaat?!

    @Override
    public Comment updateCommentById(Long commentid, Comment updatedComment) {
        return commentRepository.findById(commentid).map(
                comment -> {
                    comment.setText(updatedComment.getText());
                    return commentRepository.save(comment);
                }).orElseGet(()->{
                    updatedComment.setId(commentid);
                    return commentRepository.save(updatedComment);
        });
    }

    @Override
    public String deleteComment(Long commentid) {
        Optional<Comment> comment = commentRepository.findById(commentid);
        if (comment.isPresent()) {
            commentRepository.deleteById(commentid);
            return "Reactie met id " + comment.get().getCommentid() +  " is verwijderd";
        }
        return "Deze reactie bestaat niet";
    }
    

    @Override
    public Comment addCommentToUser(Long userid, Comment newComment) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        if (user.isPresent()) {
            AppUser userFromDb = user.get();
            Set<Comment> comments = userFromDb.getComments();

            comments.add(newComment);
            newComment.setUser(userFromDb);

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
