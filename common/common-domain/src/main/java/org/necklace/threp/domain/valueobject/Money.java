package org.necklace.threp.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

  public static final Money ZERO = new Money(BigDecimal.ZERO);
  private final BigDecimal amount;

  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isGreaterThanZero() {
    return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
  }

  public boolean isGreaterThan(Money money) {
    return amount != null && amount.compareTo(money.getAmount()) > 0;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Money add(Money money) {
    return new Money(setScale(amount.add(money.getAmount())));
  }

  private BigDecimal setScale(BigDecimal input) {
    return input.setScale(2, RoundingMode.HALF_EVEN);
  }

  public Money subtract(Money money) {
    return new Money(setScale(amount.subtract(money.getAmount())));
  }

  public Money multiply(int multiplier) {
    return new Money(setScale(amount.multiply(new BigDecimal(multiplier))));
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return Objects.equals(amount, money.amount);
  }
}
