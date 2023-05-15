package org.necklace.threp.order.domain.va;

import java.util.UUID;
import org.necklace.threp.domain.valueobject.ids.BaseId;

public class TrackingId extends BaseId<UUID> {

  protected TrackingId(UUID value) {
    super(value);
  }
}
