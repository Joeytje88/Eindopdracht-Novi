package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    boolean existsByName (String gameName);
}

