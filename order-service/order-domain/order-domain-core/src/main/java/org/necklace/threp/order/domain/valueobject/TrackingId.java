package org.necklace.threp.order.domain.valueobject;

import java.util.UUID;
import org.necklace.threp.domain.valueobject.ids.BaseId;

public class TrackingId extends BaseId<UUID> {

  public TrackingId(UUID value) {
    super(value);
  }
}
