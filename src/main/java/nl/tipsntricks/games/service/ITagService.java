package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Tag;

public interface ITagService {
    Tag getTagById (long tagid);
    Tag addTagToPost (long postid, Tag newTag);
    String deleteTagById (long tagid);
    Tag updateTagById (long tagid, Tag updatedTag);
}
