package cheese.spring.service.account;

import cheese.spring.service.model.Email;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class AccountTest {

    private String emailValue;
    private Email email;
    private Account account;

    @Before
    public void setUp() {
        emailValue = "qwe2@test.com";
        email = new Email(emailValue);
        account = Account.builder()
                .email(email)
                .build();
    }

    @Test
    public void account_생성_테스트() {
        assertThat(account.getEmail().getValue(), is(emailValue));
        assertThat(account.getId().getValue(), is(notNullValue()));
        assertThat(account.getStatus(), is(AccountStatus.ACTIVE));
    }

    @Test
    public void account_비활성화테스트() {
        account.doInactive();
        assertThat(account.getStatus(), is(AccountStatus.INACTIVE));
    }
}