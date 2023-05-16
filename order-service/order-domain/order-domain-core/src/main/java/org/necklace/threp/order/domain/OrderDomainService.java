package org.necklace.threp.order.domain;

import java.util.List;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.necklace.threp.order.domain.event.OrderCancelledEvent;
import org.necklace.threp.order.domain.event.OrderCreatedEvent;
import org.necklace.threp.order.domain.event.OrderPaidEvent;

public interface OrderDomainService {

  OrderCreatedEvent validateAndInitializeOrder(Order order, Restaurant restaurant);

  OrderPaidEvent payOrder(Order order);

  void approveOrder(Order order);

  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

  void cancelOrder(Order order, List<String> failureMessages);

}
