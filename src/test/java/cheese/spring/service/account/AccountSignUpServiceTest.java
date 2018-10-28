package cheese.spring.service.account;

import cheese.spring.service.account.event.SignUpEvent;
import cheese.spring.service.dto.SignUpDto;
import cheese.spring.service.model.Email;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationEventPublisher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AccountSignUpServiceTest {

    @InjectMocks
    private AccountSignUpService accountSignUpService;

    @Mock
    private AccountHelperService accountHelperService;

    @Mock
    private ApplicationEventPublisher eventPublisher;


    private Email email;
    private Account account;


    @Before
    public void setUp() {
        email = new Email("email@test.com");
        account = Account.signUp(new SignUpDto.Req(email));
    }

    @Test
    public void signUp() {
        //given
        given(accountHelperService.create(any())).willReturn(this.account);

        SignUpDto.Req req = new SignUpDto.Req(email);
        //when
        final Account account = accountSignUpService.signUp(req);

        //then
        assertThat(account.getEmail(), is(email));
        verify(eventPublisher, atLeastOnce()).publishEvent(any(SignUpEvent.class));
    }

}