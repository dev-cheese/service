package cheese.spring.service.mail;

import cheese.spring.service.model.Email;
import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;

public class MailMessageDto {

    @Getter
    public static class Simple {

        private Email email;
        private String subject;
        private String text;

        private Simple(Email email, String subject, String text) {
            this.email = email;
            this.subject = subject;
            this.text = text;
        }

        public static SimpleMailMessage of(final Email email) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email.getValue());
            message.setSubject("회원가입 축하 블라블라");
            message.setText("회원가입 블라블라..");

            return message;

        }

    }
}
