package cheese.spring.service.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountId implements Serializable {

    @Column(name = "id")
    private String value;

    @Builder
    public AccountId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountId)) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(getValue(), accountId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
