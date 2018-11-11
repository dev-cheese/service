package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import cheese.spring.service.model.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;


    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Builder
    private Account(Email email) {
        this.id = new AccountId(UUID.randomUUID().toString());
        this.email = email;
        this.status = AccountStatus.ACTIVE;
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
