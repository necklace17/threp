package org.necklace.threp.domain.ports.input.service;

import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.dto.track.TrackOrderQuery;
import org.necklace.threp.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {

  CreateOrderResponse createOrder(
//      TODO: @Valid
      CreateOrderCommand createOrderCommand);

  TrackOrderResponse trackOrder(
//      TODO: @Valid
      TrackOrderQuery trackOrderQuery);

}
