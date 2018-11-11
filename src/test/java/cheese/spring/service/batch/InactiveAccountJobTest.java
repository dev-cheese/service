package cheese.spring.service.batch;

import cheese.spring.service.account.Account;
import cheese.spring.service.account.AccountRepository;
import cheese.spring.service.account.AccountStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InactiveAccountJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void account_휴면회원으로_전화_테스트() throws Exception {
        final JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertThat(BatchStatus.COMPLETED, is(jobExecution.getStatus()));

        final List<Account> inactiveAccounts = accountRepository.findByUpdateAtBeforeAndStatus(
                LocalDateTime.now().minusNanos(1),
                AccountStatus.ACTIVE
        );
        assertThat(0, is(inactiveAccounts.size()));
    }
}
