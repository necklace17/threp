package org.necklace.threp.domain.dto.track;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderQuery {

  //TODO add NotNull
  private final UUID orderTrackingId;
}
