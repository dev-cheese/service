package cheese.spring.service.account;

import cheese.spring.service.account.event.SignUpEvent;
import cheese.spring.service.config.RabbitMqConfig;
import cheese.spring.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AccountSignUpService {

    private final AccountHelperService accountHelperService;
    private final AmqpTemplate amqpTemplate;

    @Transactional
    public Account signUp(SignUpDto.Req dto) {
        final Account account = accountHelperService.create(Account.signUp(dto));
        amqpTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, new SignUpEvent(account));
        return account;
    }
}
