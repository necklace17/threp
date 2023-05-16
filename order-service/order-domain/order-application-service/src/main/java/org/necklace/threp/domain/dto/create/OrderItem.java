package org.necklace.threp.domain.dto.create;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {

  // TODO: fore add @NotNull
  private final UUID productId;
  private final Integer quantity;
  private final BigDecimal price;
  private final BigDecimal subTotal;

}
