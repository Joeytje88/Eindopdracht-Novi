package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Comment;

public interface ICommentService {

    Comment getCommentById (Long commentid);
    Comment updateCommentById (Long commentid, Comment updatedComment);
    String deleteComment (Long commentid);
}
