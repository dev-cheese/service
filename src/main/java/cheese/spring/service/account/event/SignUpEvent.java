package cheese.spring.service.account.event;

import cheese.spring.service.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SignUpEvent implements Serializable {

    private Account account;

    public SignUpEvent(Account account) {
        this.account = account;
    }
}
