package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository <Topic, Long> {
}
