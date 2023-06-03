package org.necklace.threp.order.domain;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.Product;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.necklace.threp.order.domain.event.OrderCancelledEvent;
import org.necklace.threp.order.domain.event.OrderCreatedEvent;
import org.necklace.threp.order.domain.event.OrderPaidEvent;
import org.necklace.threp.order.domain.exception.OrderDomainException;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  private static final String UTC = "UTC";

  @Override
  public OrderCreatedEvent validateAndInitializeOrder(Order order, Restaurant restaurant) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateOrder();
    order.initializeOrder();
    log.info("Order with id: {} initialized", order.getId());
    return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  private void validateRestaurant(Restaurant restaurant) {
    if (!restaurant.isActive()) {
      throw new OrderDomainException(
          "Restaurant with id: " + restaurant.getId() + " is not active!");
    }
  }

  private void setOrderProductInformation(Order order, Restaurant restaurant) {
    order.getOrderItems()
        .forEach(orderItem -> {
          Product currentProduct = orderItem.getProduct();
          Product restaurantProduct = restaurant.getProduct(currentProduct.getId());
          currentProduct.updateWithConfirmedNameAndPrice(
              restaurantProduct.getName(), restaurantProduct.getPrice());
        });
  }

  @Override
  public OrderPaidEvent payOrder(Order order) {
    order.pay();
    log.info("Order with id: {} is paid", order.getId());
    return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public void approveOrder(Order order) {
    order.approve();
    log.info("Order with id: {} is approved", order.getId());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
    order.initCancel(failureMessages);
    log.info("Order with id: {} is cancelling", order.getId());
    return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
  }

  @Override
  public void cancelOrder(Order order, List<String> failureMessages) {
    order.cancel(failureMessages);
    log.info("Order with id: {} is cancelled", order.getId());
  }
}
