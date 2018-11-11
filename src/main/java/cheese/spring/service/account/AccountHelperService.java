package cheese.spring.service.account;

import cheese.spring.service.account.exception.EmailDuplicationException;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AccountHelperService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account create(Account account) {
        if (isExistEmail(account.getEmail())) {
            throw new EmailDuplicationException(account.getEmail());
        }
        return accountRepository.save(account);
    }


    public Account findByEmail(Email email) {
        final Optional<Account> account = accountRepository.findByEmail(email);
        return account.get();
    }


    public boolean isExistEmail(final Email email) {
        final Optional<Account> account = accountRepository.findByEmail(email);
        return account.isPresent();
    }

}
