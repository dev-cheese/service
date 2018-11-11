package cheese.spring.service.account.event;

import cheese.spring.service.mail.MailSignUpSender;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class AccountEventHandler {

    private final MailSignUpSender mailSignUpSender;

    @TransactionalEventListener
    @Async
    public void signUpHandle(SignUpEvent event) {
        final Email email = event.getAccount().getEmail();
        mailSignUpSender.sendSimpleMessage(email);
    }
}
