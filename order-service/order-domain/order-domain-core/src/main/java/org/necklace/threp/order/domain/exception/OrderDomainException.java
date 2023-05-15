package org.necklace.threp.order.domain.exception;

import org.necklace.threp.domain.exception.DomainException;

public class OrderDomainException extends
    DomainException {

  public OrderDomainException(String message) {
    super(message);
  }

  public OrderDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}
