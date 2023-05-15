package org.necklace.threp.domain.valueobject.ids;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {

  protected CustomerId(UUID value) {
    super(value);
  }
}
