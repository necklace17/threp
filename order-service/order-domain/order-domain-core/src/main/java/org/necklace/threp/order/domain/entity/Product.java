package org.necklace.threp.order.domain.entity;

import org.necklace.threp.domain.entity.BaseEntity;
import org.necklace.threp.domain.valueobject.Money;
import org.necklace.threp.domain.valueobject.ids.ProductId;

public class Product extends BaseEntity<ProductId> {

  private String name;
  private Money price;

  public Product(ProductId productId, String name, Money price) {
    super.setId(productId);
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }

  public void updateWithConfirmedNameAndPrice(String name, Money price) {
    this.name = name;
    this.price = price;
  }
}
