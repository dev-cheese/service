package cheese.spring.service.config;

import lombok.Setter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.aopalliance.aop.Advice;

@Setter
@Configuration
public class RabbitMqConfig{

    public static final  String ROUTING_KEY = "routing";
    public static final String EXCHANGE_NAME = "appExchange";
    public static final String GENERIC_QUEUE = "generic.queue";
    public static final String DEAD_LETTER_QUEUE = "dead.letter.queue";

    @Bean
    public DirectExchange appExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable(GENERIC_QUEUE)
                .autoDelete()
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_QUEUE)
                .build();
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(queue()).to(appExchange()).with(ROUTING_KEY);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDefaultRequeueRejected(false);
        factory.setMessageConverter(jackson2JsonMessageConverter());
        factory.setAdviceChain(new Advice[] {
                org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(2).recoverer(new RejectAndDontRequeueRecoverer())
                        .backOffOptions(1000, 2, 5000)
                        .build()
        });
        return factory;
    }

    //message convertor
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}