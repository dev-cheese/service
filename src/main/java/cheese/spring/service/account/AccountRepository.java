package cheese.spring.service.account;

import cheese.spring.service.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, AccountId> {

    Optional<Account> findByEmail(Email email);

    List<Account> findByUpdateAtBeforeAndStatus(LocalDateTime updateAt, AccountStatus status);

}
