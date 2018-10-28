package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountSignUpService accountSignUpService;

    @RequestMapping(method = RequestMethod.POST)
    public Account signUp(@Valid @RequestBody SignUpDto.Req dto) {
        return accountSignUpService.signUp(dto);
    }

}
