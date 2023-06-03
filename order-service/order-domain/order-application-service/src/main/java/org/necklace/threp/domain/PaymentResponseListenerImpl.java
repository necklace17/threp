package org.necklace.threp.domain;

import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.messages.PaymentResponse;
import org.necklace.threp.domain.ports.input.message.listener.payment.PaymentResponseListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Slf4j
public class PaymentResponseListenerImpl implements PaymentResponseListener {

  @Override
  public void paymentCompleted(PaymentResponse paymentResponse) {

  }

  @Override
  public void paymentCancelled(PaymentResponse paymentResponse) {

  }
}
