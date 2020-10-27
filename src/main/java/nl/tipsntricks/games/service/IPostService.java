package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Comment;
import nl.tipsntricks.games.domain.Post;

public interface IPostService {
    Post getPostById(long postid);
    Post addPost(Post newPost);
    Post addPostToUser(long userid, Post newPost);
    Post addCommentToPost (long postid, long commentid, Comment newComment );
    Post updatePostById(long postid, Post updatedPost);
    String deletePostById(long postid);

}
