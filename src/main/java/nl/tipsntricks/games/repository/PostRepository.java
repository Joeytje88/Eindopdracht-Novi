package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
}
