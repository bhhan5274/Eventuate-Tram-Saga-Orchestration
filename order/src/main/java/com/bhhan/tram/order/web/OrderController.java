package com.bhhan.tram.order.web;

import com.bhhan.tram.order.common.OrderDetails;
import com.bhhan.tram.order.domain.Order;
import com.bhhan.tram.order.domain.OrderRepository;
import com.bhhan.tram.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderService.createOrder(new OrderDetails(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal()));
        return new CreateOrderResponse(order.getId());
    }

    @RequestMapping(value="/orders/{orderId}", method= RequestMethod.GET)
    public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long orderId) {
        return orderRepository
                .findById(orderId)
                .map(o -> new ResponseEntity<>(new GetOrderResponse(o.getId(), o.getState(), o.getRejectionReason()), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value="/orders/customer/{customerId}", method= RequestMethod.GET)
    public ResponseEntity<List<GetOrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId) {
        return new ResponseEntity<>(orderRepository
                .findAllByOrderDetailsCustomerId(customerId)
                .stream()
                .map(o -> new GetOrderResponse(o.getId(), o.getState(), o.getRejectionReason()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
