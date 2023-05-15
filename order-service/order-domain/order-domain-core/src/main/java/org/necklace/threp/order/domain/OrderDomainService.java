package org.necklace.threp.order.domain;

import java.util.List;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.necklace.threp.order.domain.event.OrderCancelledEvent;
import org.necklace.threp.order.domain.event.OrderCreatedEvent;
import org.necklace.threp.order.domain.event.OrderPaidEvent;

public interface OrderDomainService {

  OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

  OrderCreatedEvent validateAndInitializeOrder(Order order, Restaurant restaurant);

  OrderPaidEvent payOrder(Order order);

  void approveOrder(Order order);

  void cancelOrder(Order order, List<String> failureMessages);

}
