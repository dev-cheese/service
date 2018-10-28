package cheese.spring.service.account.exception;

import cheese.spring.service.model.Email;

public class EmailDuplicationException extends RuntimeException {

    public EmailDuplicationException(Email email) {
        super(email.getValue());
    }
}
