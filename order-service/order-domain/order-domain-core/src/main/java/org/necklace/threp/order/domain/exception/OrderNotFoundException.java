package org.necklace.threp.order.domain.exception;

import org.necklace.threp.domain.exception.DomainException;

public class OrderNotFoundException extends DomainException {

  public OrderNotFoundException(String message) {
    super(message);
  }

  public OrderNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
