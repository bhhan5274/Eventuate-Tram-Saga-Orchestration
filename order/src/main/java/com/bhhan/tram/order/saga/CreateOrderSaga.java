package com.bhhan.tram.order.saga;

import com.bhhan.tram.common.Money;
import com.bhhan.tram.customer.api.command.ReserveCreditCommand;
import com.bhhan.tram.customer.api.reply.CustomerCreditLimitExceeded;
import com.bhhan.tram.customer.api.reply.CustomerNotFound;
import com.bhhan.tram.order.common.RejectionReason;
import com.bhhan.tram.order.domain.Order;
import com.bhhan.tram.order.domain.OrderRepository;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.RequiredArgsConstructor;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@RequiredArgsConstructor
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData> {
    private final OrderRepository orderRepository;

    private SagaDefinition<CreateOrderSagaData> sagaDefinition =
            step()
                .invokeLocal(this::create)
                .withCompensation(this::reject)
            .step()
                .invokeParticipant(this::reserveCredit)
                .onReply(CustomerNotFound.class, this::handleCustomerNotFound)
                .onReply(CustomerCreditLimitExceeded.class, this::handleCustomerCreditLimitExceeded)
            .step()
                .invokeLocal(this::approve)
            .build();

    @Override
    public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
        return this.sagaDefinition;
    }

    private CommandWithDestination reserveCredit(CreateOrderSagaData data){
        Long orderId = data.getOrderId();
        Long customerId = data.getOrderDetails().getCustomerId();
        Money orderTotal = data.getOrderDetails().getOrderTotal();

        return send(new ReserveCreditCommand(customerId, orderId, orderTotal))
                .to("customerService")
                .build();
    }

    private void handleCustomerNotFound(CreateOrderSagaData data, CustomerNotFound reply){
        data.setRejectionReason(RejectionReason.UNKNOWN_CUSTOMER);
    }

    private void handleCustomerCreditLimitExceeded(CreateOrderSagaData data, CustomerCreditLimitExceeded reply){
        data.setRejectionReason(RejectionReason.INSUFFICIENT_CREDIT);
    }

    private void approve(CreateOrderSagaData data){
        orderRepository.findById(data.getOrderId()).get().approve();
    }

    private void create(CreateOrderSagaData data){
        Order order = Order.createOrder(data.getOrderDetails());
        orderRepository.save(order);
        data.setOrderId(order.getId());
    }

    private void reject(CreateOrderSagaData data){
        orderRepository.findById(data.getOrderId())
                .get()
                .reject(data.getRejectionReason());
    }
}
