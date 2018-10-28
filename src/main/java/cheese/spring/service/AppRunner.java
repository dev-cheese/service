package cheese.spring.service;

import cheese.spring.service.mail.MailSignUpSender;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {


    private final MailSignUpSender emailService;

    @Override
    public void run(ApplicationArguments args) {

        Email email = new Email("cheese10yun@gmail.com");
        emailService.sendSimpleMessage(email);

    }
}
