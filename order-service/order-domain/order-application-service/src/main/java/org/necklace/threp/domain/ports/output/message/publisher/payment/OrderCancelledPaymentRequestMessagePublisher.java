package org.necklace.threp.domain.ports.output.message.publisher.payment;

import org.necklace.threp.domain.event.publisher.DomainEventPublisher;
import org.necklace.threp.order.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends
    DomainEventPublisher<OrderCancelledEvent> {

}
