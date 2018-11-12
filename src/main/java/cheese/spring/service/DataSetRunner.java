package cheese.spring.service;

import cheese.spring.service.account.Account;
import cheese.spring.service.account.AccountRepository;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
@AllArgsConstructor
public class DataSetRunner implements ApplicationRunner {

    private final AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) {
        List<Account> accounts = buildAccounts();
        accountRepository.saveAll(accounts);
    }

    private List<Account> buildAccounts() {
        List<Account> accounts = new ArrayList<>();
        IntStream.range(0, 100).forEach(i -> {
            Email email = new Email("test" + i + "@test.com");
            accounts.add(Account.builder().email(email).build());
        });
        return accounts;
    }
}
