package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
