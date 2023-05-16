package org.necklace.threp.domain.dto.messages;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.necklace.threp.domain.valueobject.PaymentStatus;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {

  private String id;
  private String sagaId;
  private String orderId;
  private String paymentId;
  private String customerId;
  private BigDecimal price;
  private Instant createdAt;
  private PaymentStatus paymentStatus;
  private List<String> failureMessages;
}
