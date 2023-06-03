package org.necklace.threp.domain;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.track.TrackOrderQuery;
import org.necklace.threp.domain.dto.track.TrackOrderResponse;
import org.necklace.threp.domain.mapper.OrderDataMapper;
import org.necklace.threp.domain.ports.output.repository.OrderRepository;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.exception.OrderNotFoundException;
import org.necklace.threp.order.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderTrackCommandHandler {

  private final OrderRepository orderRepository;
  private final OrderDataMapper orderDataMapper;

  public OrderTrackCommandHandler(OrderRepository orderRepository,
      OrderDataMapper orderDataMapper) {
    this.orderRepository = orderRepository;
    this.orderDataMapper = orderDataMapper;
  }

  @Transactional(readOnly = true)
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    Optional<Order> orderResult = orderRepository.findByTrackingId(
        new TrackingId(trackOrderQuery.getOrderTrackingId()));
    if (orderResult.isEmpty()) {
      log.warn("Could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
      throw new OrderNotFoundException(
          "Could not find order with tracking id: " + trackOrderQuery.getOrderTrackingId());
    }
    return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
  }
}
