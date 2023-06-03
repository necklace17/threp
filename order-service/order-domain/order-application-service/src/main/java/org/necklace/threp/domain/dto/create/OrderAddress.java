package org.necklace.threp.domain.dto.create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderAddress {

  @NotNull
  @Max(20)
  @Min(5)
  private final String street;
  @NotNull
  private final String postalCode;
  @NotNull
  private final String city;
}
