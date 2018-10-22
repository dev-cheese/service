package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("accounts")
public class AccountController {


    @RequestMapping(method = RequestMethod.POST)
    public Account signUp(@Valid @RequestBody SignUpDto.Req dto) {

        return Account.builder()
                .id(AccountId.builder().value(UUID.randomUUID().toString()).build())
                .email(dto.getEmail())
                .build();
    }

}
