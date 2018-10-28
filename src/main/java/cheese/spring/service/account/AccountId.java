package cheese.spring.service.account;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode
public class AccountId implements Serializable {

    @Column(name = "id")
    private String value;

    public AccountId(String value) {
        this.value = value;
    }

}
