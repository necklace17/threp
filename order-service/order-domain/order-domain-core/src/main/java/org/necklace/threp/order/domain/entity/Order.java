package org.necklace.threp.order.domain.entity;

import java.util.List;
import java.util.UUID;
import org.necklace.threp.domain.entity.AggregateRoot;
import org.necklace.threp.domain.valueobject.Money;
import org.necklace.threp.domain.valueobject.OrderStatus;
import org.necklace.threp.domain.valueobject.ids.CustomerId;
import org.necklace.threp.domain.valueobject.ids.OrderId;
import org.necklace.threp.domain.valueobject.ids.RestaurantId;
import org.necklace.threp.order.domain.exception.OrderDomainException;
import org.necklace.threp.order.domain.valueobject.OrderItemId;
import org.necklace.threp.order.domain.valueobject.StreetAddress;
import org.necklace.threp.order.domain.valueobject.TrackingId;

public class Order extends AggregateRoot<OrderId> {

  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress deliveryAddress;
  private final Money price;
  private final List<OrderItem> orderItems;
  private TrackingId trackingId;
  private OrderStatus orderStatus;
  private List<String> failureMessages;

  private Order(Builder builder) {
    super.setId(builder.orderId);
    customerId = builder.customerId;
    restaurantId = builder.restaurantId;
    deliveryAddress = builder.deliveryAddress;
    price = builder.price;
    orderItems = builder.orderItems;
    trackingId = builder.trackingId;
    orderStatus = builder.orderStatus;
    failureMessages = builder.failureMessages;
  }

  public CustomerId getCustomerId() {
    return customerId;
  }

  public RestaurantId getRestaurantId() {
    return restaurantId;
  }

  public StreetAddress getDeliveryAddress() {
    return deliveryAddress;
  }

  public Money getPrice() {
    return price;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public TrackingId getTrackingId() {
    return trackingId;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public List<String> getFailureMessages() {
    return failureMessages;
  }

  public void initializeOrder() {
    setId(new OrderId(UUID.randomUUID()));
    trackingId = new TrackingId(UUID.randomUUID());
    initializeOrderItems();
  }

  private void initializeOrderItems() {
    long itemId = 1;
    for (OrderItem orderItem : orderItems) {
      orderItem.initializeOrderItem(super.getId(), new OrderItemId(itemId++));
    }
  }

  public void validateOrder() {
    validateInitialOrder();
    validateTotalPrice();
    validateItemsPrice();
  }

  private void validateInitialOrder() {
    if (orderStatus != null || getId() != null) {
      throw new OrderDomainException("Order is not in correct state for initialization!");
    }
  }

  private void validateTotalPrice() {
    if (price == null || !price.isGreaterThanZero()) {
      throw new OrderDomainException("Total price must be greater than zero!");
    }
  }

  private void validateItemsPrice() {
    Money orderItemsTotal = orderItems.stream()
        .map(orderItem -> {
          validateItemPrice(orderItem);
          return orderItem.getSubTotal();
        })
        .reduce(Money.ZERO, Money::add);
    if (!price.equals(orderItemsTotal)) {
      throw new OrderDomainException(
          "Total price: " + price.getAmount() + " is not equal to Order items total: "
              + orderItemsTotal.getAmount() + "!");
    }
  }

  private void validateItemPrice(OrderItem orderItem) {
    if (!orderItem.isPriceValid()) {
      throw new OrderDomainException(
          "Order item price: " + orderItem.getPrice().getAmount() + " is not valid for product"
              + orderItem.getProduct().getId() + "!");
    }
  }


  public static final class Builder {

    private OrderId orderId;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress deliveryAddress;
    private Money price;
    private List<OrderItem> orderItems;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Builder() {
    }

    public static Builder builder() {
      return new Builder();
    }


    public Builder orderId(OrderId val) {
      orderId = val;
      return this;
    }

    public Builder customerId(CustomerId val) {
      customerId = val;
      return this;
    }

    public Builder restaurantId(RestaurantId val) {
      restaurantId = val;
      return this;
    }

    public Builder deliveryAddress(StreetAddress val) {
      deliveryAddress = val;
      return this;
    }

    public Builder price(Money val) {
      price = val;
      return this;
    }

    public Builder orderItems(List<OrderItem> val) {
      orderItems = val;
      return this;
    }

    public Builder trackingId(TrackingId val) {
      trackingId = val;
      return this;
    }

    public Builder orderStatus(OrderStatus val) {
      orderStatus = val;
      return this;
    }

    public Builder failureMessages(List<String> val) {
      failureMessages = val;
      return this;
    }

    public Order build() {
      return new Order(this);
    }
  }
}
