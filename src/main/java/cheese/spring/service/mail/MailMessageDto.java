package cheese.spring.service.mail;

import cheese.spring.service.model.Email;
import org.springframework.mail.SimpleMailMessage;

public class MailMessageDto {

    public static class Simple {

        public static SimpleMailMessage of(final Email email) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getValue());
            message.setSubject("회원가입 제목");
            message.setText(String.format("%s님 안녕하세요 ... 블라블라...회원가입 블라블라..", email.getValue()));
            return message;

        }

    }
}
