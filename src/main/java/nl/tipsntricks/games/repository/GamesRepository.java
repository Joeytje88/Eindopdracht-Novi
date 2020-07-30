package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Long> {
}

