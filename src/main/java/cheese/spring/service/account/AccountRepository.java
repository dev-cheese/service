package cheese.spring.service.account;

import cheese.spring.service.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

    Optional<Account> findByEmail(Email email);

    List<Account> findByStatusEquals(AccountStatus status);
}
