package org.necklace.threp.domain.ports.output.message.publisher.restaurantapproval;

import org.necklace.threp.domain.event.publisher.DomainEventPublisher;
import org.necklace.threp.order.domain.event.OrderPaidEvent;

public interface OrderPaidPaymentRequestMessagePublisher extends
    DomainEventPublisher<OrderPaidEvent> {

}
