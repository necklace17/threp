package org.necklace.threp.domain.ports.input.message.listener.payment;

import org.necklace.threp.domain.dto.messages.PaymentResponse;

public interface PaymentResponseListener {

  void paymentCompleted(PaymentResponse paymentResponse);

  void paymentCancelled(PaymentResponse paymentResponse);

}
