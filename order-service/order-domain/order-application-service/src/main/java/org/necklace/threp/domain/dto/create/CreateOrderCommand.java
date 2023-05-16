package org.necklace.threp.domain.dto.create;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderCommand {

  // TODO: fore add @NotNull
  private final UUID customerId;
  private final UUID restaurantId;
  private final BigDecimal price;
  private final List<OrderItem> orderItems;
  private final OrderAddress address;
}
