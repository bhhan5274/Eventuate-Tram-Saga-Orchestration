package com.bhhan.tram.customer.config;

import com.bhhan.tram.customer.service.CustomerCommandHandler;
import com.bhhan.tram.customer.service.CustomerService;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;
import io.eventuate.tram.spring.consumer.kafka.EventuateTramKafkaMessageConsumerConfiguration;
import io.eventuate.tram.spring.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Configuration
@Import({
        SagaParticipantConfiguration.class,
        OptimisticLockingDecoratorConfiguration.class,
        TramMessageProducerJdbcConfiguration.class,
        EventuateTramKafkaMessageConsumerConfiguration.class
})
public class CustomerConfiguration {
    @Bean
    public CustomerCommandHandler customerCommandHandler(CustomerService customerService){
        return new CustomerCommandHandler(customerService);
    }

    @Bean
    public CommandDispatcher consumerCommandDispatcher(CustomerCommandHandler target,
                                                       SagaCommandDispatcherFactory sagaCommandDispatcherFactory){
        return sagaCommandDispatcherFactory.make("customerCommandDispatcher",
                target.commandHandler());
    }
}
