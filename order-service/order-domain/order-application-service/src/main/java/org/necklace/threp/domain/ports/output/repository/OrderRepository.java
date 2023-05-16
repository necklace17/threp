package org.necklace.threp.domain.ports.output.repository;

import java.util.Optional;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.valueobject.TrackingId;

public interface OrderRepository {

  Order save(Order order);

  Optional<Order> findByTrackingId(TrackingId trackingId);

}
