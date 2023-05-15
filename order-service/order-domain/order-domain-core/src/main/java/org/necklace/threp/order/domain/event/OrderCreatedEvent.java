package org.necklace.threp.order.domain.event;

import java.time.ZonedDateTime;
import org.necklace.threp.order.domain.entity.Order;

public class OrderCreatedEvent extends OrderEvent {

  public OrderCreatedEvent(Order order,
      ZonedDateTime createdAt) {
    super(order, createdAt);
  }
}
