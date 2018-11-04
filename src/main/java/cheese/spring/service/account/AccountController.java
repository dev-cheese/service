package cheese.spring.service.account;

import cheese.spring.service.dto.SignUpDto;
import cheese.spring.service.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountSignUpService accountSignUpService;

    private final AccountHelperService accountHelperService;

    @GetMapping("/{email}")
    public Account getAccount(@PathVariable String email) {
        return accountHelperService.findByEmail(new Email(email));
    }

    @PostMapping
    public Account signUp(@Valid @RequestBody SignUpDto.Req dto) {
        return accountSignUpService.signUp(dto);
    }

}
