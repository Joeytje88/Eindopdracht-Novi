package nl.tipsntricks.games.repository;

import nl.tipsntricks.games.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountName (String accountName);
}
