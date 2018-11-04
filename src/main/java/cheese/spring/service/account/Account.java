package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import cheese.spring.service.model.Date;
import cheese.spring.service.model.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

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

    @Embedded
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Builder
    private Account(Email email) {
        this.id = new AccountId(UUID.randomUUID().toString());
        this.email = email;
        this.status = AccountStatus.ACTIVE;
        this.date = Date.now();
    }

    public static Account signUp(SignUpDto.Req dto) {
        return Account.builder()
                .email(dto.getEmail())
                .build();
    }

    public Account doInactive() {
        this.status = AccountStatus.INACTIVE;
        return this;
    }
}
