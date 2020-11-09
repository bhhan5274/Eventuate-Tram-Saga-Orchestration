package com.bhhan.tram.order.config;

import com.bhhan.tram.order.domain.OrderRepository;
import com.bhhan.tram.order.saga.CreateOrderSaga;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;
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
@Import({SagaOrchestratorConfiguration.class, OptimisticLockingDecoratorConfiguration.class,
        TramMessageProducerJdbcConfiguration.class,
        EventuateTramKafkaMessageConsumerConfiguration.class})
public class OrderConfiguration {

    @Bean
    public CreateOrderSaga createOrderSaga(OrderRepository orderRepository){
        return new CreateOrderSaga(orderRepository);
    }
}
