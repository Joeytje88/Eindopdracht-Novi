package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.exception.CommentNotFoundException;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.exception.UserNotFoundException;
import nl.tipsntricks.games.repository.AppUserRepository;
import nl.tipsntricks.games.repository.CommentRepository;
import nl.tipsntricks.games.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment getCommentById(Long commentid) {
        return commentRepository.findById(commentid)
                .orElseThrow(() -> new CommentNotFoundException("Reactie met id " +commentid +" niet gevonden"));
    }

    @Override
    public Comment addCommentToPost(long userid,long postid, Comment newComment) {
        Optional<AppUser> user = appUserRepository.findById(userid);
        Optional<Post> post = postRepository.findById(postid);
        if (user.isPresent() && post.isPresent()) {
            AppUser userfromDb = user.get();
            Post postFromDb = post.get();

            newComment.setUser(userfromDb);
            newComment.setPost(postFromDb);

            return commentRepository.save(newComment);
        }
        throw new UserNotFoundException("gebruiker niet gevonden") ;

    }


    @Override
    public Comment updateCommentById(Long commentid, Comment updatedComment) {
        return commentRepository.findById(commentid).map(
                comment -> {
                    comment.setText(updatedComment.getText());
                    comment.setImage(updatedComment.getImage());
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

    private boolean checkIsValidCommentMessage(String message) {
        if (message.contains("tering") || message.equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
