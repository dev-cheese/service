package cheese.spring.service.dto;

import cheese.spring.service.model.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

public class SignUpDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Req {
        @Valid
        private Email email;

        public Req(Email email) {
            this.email = email;
        }
    }
}
