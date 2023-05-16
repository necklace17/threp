package org.necklace.threp.order.domain.entity;

import java.util.List;
import java.util.Map;
import org.necklace.threp.domain.entity.AggregateRoot;
import org.necklace.threp.domain.valueobject.ids.ProductId;
import org.necklace.threp.domain.valueobject.ids.RestaurantId;

public class Restaurant extends AggregateRoot<RestaurantId> {

  public final Map<ProductId, Product> products;
  private boolean active;

  private Restaurant(Builder builder) {
    super.setId(builder.restaurantId);
    products = builder.products;
    active = builder.active;
  }

  public List<Product> getProducts() {
    return products.values().stream().toList();
  }

  public Product getProduct(ProductId productId) {
    return products.get(productId);
  }

  public boolean isActive() {
    return active;
  }

  public static final class Builder {

    private RestaurantId restaurantId;
    private Map<ProductId, Product> products;
    private boolean active;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }

    public Builder id(RestaurantId val) {
      restaurantId = val;
      return this;
    }

    public Builder products(List<Product> val) {
      val.forEach(product -> products.put(product.getId(), product));
      return this;
    }

    public Builder active(boolean val) {
      active = val;
      return this;
    }

    public Restaurant build() {
      return new Restaurant(this);
    }
  }
}
