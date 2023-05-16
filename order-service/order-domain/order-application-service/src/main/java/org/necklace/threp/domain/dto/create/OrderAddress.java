package org.necklace.threp.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {

  // TODO: fore add NotNull
//  TODO: add MAX
  private final String street;
  private final String postalCode;
  private final String city;
}
