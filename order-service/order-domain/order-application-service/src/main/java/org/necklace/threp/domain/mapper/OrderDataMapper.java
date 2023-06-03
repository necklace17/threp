package org.necklace.threp.domain.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.dto.create.OrderAddress;
import org.necklace.threp.domain.valueobject.Money;
import org.necklace.threp.domain.valueobject.ids.CustomerId;
import org.necklace.threp.domain.valueobject.ids.ProductId;
import org.necklace.threp.domain.valueobject.ids.RestaurantId;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.OrderItem;
import org.necklace.threp.order.domain.entity.Product;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.necklace.threp.order.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

  public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
    return Restaurant.Builder.builder()
        .id(new RestaurantId(createOrderCommand.getRestaurantId()))
        .products(
            createOrderCommand.getOrderItems()
                .stream()
                .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                .collect(
                    Collectors.toList()))
        .build();
  }

  public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
    return Order.Builder.builder()
        .customerId(new CustomerId(createOrderCommand.getCustomerId()))
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
        .price(new Money(createOrderCommand.getPrice()))
        .orderItems(orderItemsToOrderItemEntities(createOrderCommand.getOrderItems()))
        .build();
  }

  private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
    return new StreetAddress(
        UUID.randomUUID(),
        address.getStreet(),
        address.getPostalCode(),
        address.getCity());
  }

  private List<OrderItem> orderItemsToOrderItemEntities(
      List<org.necklace.threp.domain.dto.create.OrderItem> orderItems) {
    return orderItems.stream()
        .map(orderItem -> OrderItem.Builder.builder()
            .product(new Product(new ProductId(orderItem.getProductId())))
            .price(new Money(orderItem.getPrice()))
            .quantity(orderItem.getQuantity())
            .subTotal(new Money(orderItem.getSubTotal()))
            .build())
        .collect(Collectors.toList());
  }

  public CreateOrderResponse orderToCreateOrderResponse(Order order) {
    return CreateOrderResponse.builder()
        .orderTrackingId(order.getTrackingId()
            .getValue())
        .orderStatus(order.getOrderStatus())
        .build();
  }
}
