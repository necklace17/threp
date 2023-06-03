package org.necklace.threp.domain;

import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.dto.track.TrackOrderQuery;
import org.necklace.threp.domain.dto.track.TrackOrderResponse;
import org.necklace.threp.domain.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

  private final OrderCreateCommandHandler orderCreateCommandHandler;
  private final OrderTrackCommandHandler orderTrackCommandHandler;

  OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
      OrderTrackCommandHandler orderTrackCommandHandler) {
    this.orderCreateCommandHandler = orderCreateCommandHandler;
    this.orderTrackCommandHandler = orderTrackCommandHandler;
  }

  @Override
  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    return orderCreateCommandHandler.createOrder(createOrderCommand);
  }

  @Override
  public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
    return orderTrackCommandHandler.trackOrder(trackOrderQuery);
  }
}
