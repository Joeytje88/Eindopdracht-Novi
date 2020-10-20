package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
