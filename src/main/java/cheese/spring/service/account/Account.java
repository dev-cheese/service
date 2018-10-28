package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
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

    @Builder
    private Account(Email email) {
        this.id = new AccountId(UUID.randomUUID().toString());
        this.email = email;
    }

    public static Account signUp(SignUpDto.Req dto) {
        return Account.builder()
                .email(dto.getEmail())
                .build();
    }
}
