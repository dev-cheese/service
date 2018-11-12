package cheese.spring.service.account.event;

import cheese.spring.service.config.RabbitMqConfig;
import cheese.spring.service.mail.MailSignUpSender;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountEventHandler {

    private final MailSignUpSender mailSignUpSender;

    @RabbitListener(queues = RabbitMqConfig.GENERIC_QUEUE)
    public void signUpHandle(SignUpEvent event) {
        final Email email = event.getAccount().getEmail();
//        mailSignUpSender.sendSimpleMessage(email);
    }
}
