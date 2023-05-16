package org.necklace.threp.domain.dto.messages;

import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.necklace.threp.domain.valueobject.OrderApprovalStatus;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovalResponse {

  private String id;
  private String sagaId;
  private String orderId;
  private String restaurantId;
  private Instant createdAt;
  private OrderApprovalStatus orderApprovalStatus;
  private List<String> failureMessages;
}
