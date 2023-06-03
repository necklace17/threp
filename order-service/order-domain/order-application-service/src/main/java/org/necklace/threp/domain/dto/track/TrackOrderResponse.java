package org.necklace.threp.domain.dto.track;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.necklace.threp.domain.valueobject.OrderStatus;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {

  @NotNull
  private final UUID orderTrackingId;
  @NotNull
  private final OrderStatus orderStatus;
  private final List<String> failureMessages;

}
