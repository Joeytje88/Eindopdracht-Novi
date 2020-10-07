package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Comment;

public interface ICommentService {

    Comment getCommentById (long commentId);
    Comment addComment (Comment newComment);
    Comment updateCommentById (Long commentid, Comment updatedComment);
    String deleteComment (long commentid);
    Comment addCommentToUser (Long commentid, Comment newComment);
}
