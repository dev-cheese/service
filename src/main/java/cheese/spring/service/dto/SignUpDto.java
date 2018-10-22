package cheese.spring.service.dto;

import cheese.spring.service.model.Email;
import lombok.Getter;

import javax.validation.Valid;

public class SignUpDto {

    @Getter
    public static class Req {
        @Valid
        private Email email;
    }
}
