package com.bhhan.tram.order.service;

import com.bhhan.tram.order.common.OrderDetails;
import com.bhhan.tram.order.domain.Order;
import com.bhhan.tram.order.domain.OrderRepository;
import com.bhhan.tram.order.saga.CreateOrderSaga;
import com.bhhan.tram.order.saga.CreateOrderSagaData;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SagaInstanceFactory sagaInstanceFactory;
    private final CreateOrderSaga createOrderSaga;

    @Transactional
    public Order createOrder(OrderDetails orderDetails){
        CreateOrderSagaData data = new CreateOrderSagaData(orderDetails);
        sagaInstanceFactory.create(createOrderSaga, data);
        return orderRepository.findById(data.getOrderId()).get();
    }
}
