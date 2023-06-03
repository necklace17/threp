package org.necklace.threp.domain;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.CreateOrderResponse;
import org.necklace.threp.domain.mapper.OrderDataMapper;
import org.necklace.threp.domain.ports.output.repository.CustomerRepository;
import org.necklace.threp.domain.ports.output.repository.OrderRepository;
import org.necklace.threp.domain.ports.output.repository.RestaurantRepository;
import org.necklace.threp.order.domain.OrderDomainService;
import org.necklace.threp.order.domain.entity.Customer;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.necklace.threp.order.domain.event.OrderCreatedEvent;
import org.necklace.threp.order.domain.exception.OrderDomainException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateCommandHandler {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;
  private final OrderDataMapper orderDataMapper;

  public OrderCreateCommandHandler(OrderDomainService orderDomainService,
      OrderRepository orderRepository, CustomerRepository customerRepository,
      RestaurantRepository restaurantRepository, OrderDataMapper orderDataMapper) {
    this.orderDomainService = orderDomainService;
    this.orderRepository = orderRepository;
    this.customerRepository = customerRepository;
    this.restaurantRepository = restaurantRepository;
    this.orderDataMapper = orderDataMapper;
  }

  @Transactional
  public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
    checkCustomer(createOrderCommand.getCustomerId());
    Restaurant restaurant = checkRestaurant(createOrderCommand);
    Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitializeOrder(order,
        restaurant);
    Order orderResult = saveOrder(order);
    log.info("Order is created with id: {}", orderResult.getId());
    return orderDataMapper.orderToCreateOrderResponse(order);
  }

  private void checkCustomer(UUID customerId) {
    Optional<Customer> customer = customerRepository.findCustomer(customerId);
    if (customer.isEmpty()) {
      log.warn("Could not find customer with customer id: {}", customerId);
      throw new OrderDomainException("Could not find customer with customer id: " + customer);
    }
  }

  private Restaurant checkRestaurant(CreateOrderCommand restaurantId) {
    Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(
        restaurantId);
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findRestaurantInformation(
        restaurant);
    if (optionalRestaurant.isEmpty()) {
      log.warn("Could not find restaurant with restaurant id: {}", restaurantId);
      throw new OrderDomainException("Could not find restaurant with restaurant id: " + restaurant);
    }
    return optionalRestaurant.get();
  }

  private Order saveOrder(Order order) {
    Order orderResult = this.orderRepository.save(order);
    if (orderResult == null) {
      log.error("Could not save order!");
      throw new OrderDomainException("Could not save order");
    }
    log.info("Order is saved with id: {}", order.getId());
    return orderResult;
  }
}
