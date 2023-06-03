package org.necklace.threp.domain;

import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.mapper.OrderDataMapper;
import org.necklace.threp.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.necklace.threp.order.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCreateCommandHandler {

  private final OrderDataMapper orderDataMapper;
  private final OrderCreateHelper orderCreateHelper;
  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

  public OrderCreateCommandHandler(OrderDataMapper orderDataMapper,
      OrderCreateHelper orderCreateHelper,
      OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
    this.orderDataMapper = orderDataMapper;
    this.orderCreateHelper = orderCreateHelper;
    this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
  }

  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
    log.info("Order is created with id: {}",
        orderCreatedEvent.getOrder()
            .getId()
            .getValue());
    orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(),
        "Order created successfully");
  }

}
