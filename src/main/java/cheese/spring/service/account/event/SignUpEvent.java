package cheese.spring.service.account.event;

import cheese.spring.service.account.Account;
import lombok.Getter;

@Getter
public class SignUpEvent {

    private Account account;

    public SignUpEvent(Account account) {
        this.account = account;
    }
}
