package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController("/accounts")
public class AccountController {


    @PostMapping
    public Account signUp(@Valid @RequestBody SignUpDto.Req dto) {

        return Account.builder()
                .id(AccountId.builder().value(UUID.randomUUID().toString()).build())
                .email(dto.getEmail())
                .build();
    }

}
