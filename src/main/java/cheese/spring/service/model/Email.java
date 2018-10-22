package cheese.spring.service.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Email {


    @org.hibernate.validator.constraints.Email
    @Column(name = "email")
    private String value;

    @Builder
    public Email(String value) {
        this.value = value;
    }

    public String getHost() {
        return value.substring(value.indexOf("@"));
    }

    public String getId() {
        return value.substring(0, value.indexOf("@"));
    }

}
