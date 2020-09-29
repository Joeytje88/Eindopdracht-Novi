package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.AppUser;
import nl.tipsntricks.games.domain.Comment;

public interface ICommentService {

    Comment getCommentById (long id);
    Comment addComment (Comment newComment);
    Comment updateCommentById (Long id, Comment updatedComment);
    String deleteComment (long id);
    Comment addCommentToUser (Long id, AppUser updatedUser);
    Comment addTestCommentWithUser();
}
