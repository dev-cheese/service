package cheese.spring.service.mail;

import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MailSignUpSender implements MailService {

    private final JavaMailSender mailSender;

    public void sendSimpleMessage(final Email email) {
        final SimpleMailMessage message = MailMessageDto.Simple.of(email);
        mailSender.send(message);
    }


}
