package org.necklace.threp.domain.dto.create;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.necklace.threp.domain.valueobject.OrderStatus;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {

  //TODO: fore add NotNull
  private final UUID orderTrackingId;
  private final OrderStatus orderStatus;
  private final String message;
}
