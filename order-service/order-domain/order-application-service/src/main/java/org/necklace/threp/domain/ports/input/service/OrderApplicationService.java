package org.necklace.threp.domain.ports.input.service;

import javax.validation.Valid;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.dto.track.TrackOrderQuery;
import org.necklace.threp.domain.dto.track.TrackOrderResponse;

public interface OrderApplicationService {

  CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

  TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);

}
