package cheese.spring.service.account;

import cheese.spring.service.account.exception.EmailDuplicationException;
import cheese.spring.service.dto.SignUpDto;
import cheese.spring.service.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AccountHelperServiceTest {

    @InjectMocks
    private AccountHelperService accountHelperService;

    @Mock
    private AccountRepository accountRepository;

    private Email email;
    private Account account;

    @Before
    public void setUp() {
        email = new Email("email@test.com");
        account = Account.signUp(new SignUpDto.Req(email));
    }

    @Test
    public void create() {
        //given
        given(accountRepository.findByEmail(email)).willReturn(getAccount(null));
        given(accountRepository.save(this.account)).willReturn(this.account);

        //when
        final Account account = accountHelperService.create(accountHelperService.create(this.account));

        //then
        assertThat(account.getEmail(), is(this.account.getEmail()));


    }

    @Test(expected = EmailDuplicationException.class)
    public void create_이메일중복시_EmailDuplicationException() {
        //given
        given(accountRepository.findByEmail(email)).willReturn(getAccount(account));

        //when
        accountHelperService.create(account);
    }

    @Test
    public void isExistEmail_존재하는경우_true() {
        //given
        given(accountRepository.findByEmail(email)).willReturn(getAccount(account));

        //when
        final boolean existEmail = accountHelperService.isExistEmail(email);

        //then
        assertThat(existEmail, is(true));
    }

    @Test
    public void isExistEmail_존재하는경우_false() {

        //given
        given(accountRepository.findByEmail(email)).willReturn(getAccount(null));

        //when
        final boolean existEmail = accountHelperService.isExistEmail(email);

        //then
        assertThat(existEmail, is(false));
    }

    private Optional<Account> getAccount(Account account) {
        return Optional.ofNullable(account);
    }
}