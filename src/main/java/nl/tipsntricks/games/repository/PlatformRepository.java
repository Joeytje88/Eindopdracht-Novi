package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform,Long> {
}
