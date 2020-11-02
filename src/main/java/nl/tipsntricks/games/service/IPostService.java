package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Post;

public interface IPostService {
    Post getPostById(long postid);
    Post addPost(Post newPost);
    Post updatePostById(long postid, Post updatedPost);
    String deletePostById(long postid);

}
