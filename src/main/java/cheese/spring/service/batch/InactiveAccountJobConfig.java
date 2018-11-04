package cheese.spring.service.batch;

import cheese.spring.service.account.Account;
import cheese.spring.service.account.AccountHelperService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@AllArgsConstructor
@Configuration
public class InactiveAccountJobConfig {

    private final AccountHelperService accountHelperService;


    @Bean
    public Job inactiveAccountJob(JobBuilderFactory jobBuilderFactory, Step inactiveJobStep) {
        return jobBuilderFactory
                .get(BatchName.INACTIVE_ACCOUNT_JOB.name())
                .preventRestart()
                .start(inactiveJobStep)
                .build();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory
                .get(BatchName.INACTIVE_ACCOUNT_STEP.name())
                .<Account, Account>chunk(10)
                .reader(inactiveAccountReader())
                .processor(inactiveAccountProcessor())
                .writer(inactiveAccountWriter())
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<Account> inactiveAccountReader() {
        List<Account> targetAccounts = accountHelperService.findAll();
        return new ListItemReader<>(targetAccounts);
    }

    public ItemProcessor<Account, Account> inactiveAccountProcessor() {
        return Account::doInactive;
    }


    public ItemWriter<Account> inactiveAccountWriter() {
        return (List<? extends Account> users) -> accountHelperService.saveAll(users);
    }


}
