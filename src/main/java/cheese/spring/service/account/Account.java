package cheese.spring.service.account;

import cheese.spring.service.model.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "account")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

    @EmbeddedId
    private AccountId id;

    @Embedded
    @AttributeOverride(
            name = "value",
            column = @Column(name = "email", nullable = false, updatable = false, unique = true)
    )
    private Email email;

    @Builder
    public Account(AccountId id, Email email) {
        this.id = id;
        this.email = email;
    }
}
