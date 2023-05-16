package org.necklace.threp.domain.dto.track;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.necklace.threp.domain.valueobject.OrderStatus;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {

  //TODO: add NotNull
  private final UUID orderTrackingId;
  //TODO: add NotNull
  private final OrderStatus orderStatus;
  private final List<String> failureMessages;

}
