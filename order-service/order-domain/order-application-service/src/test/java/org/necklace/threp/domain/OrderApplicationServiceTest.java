package org.necklace.threp.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.necklace.threp.domain.dto.create.CreateOrderCommand;
import org.necklace.threp.domain.dto.create.OrderAddress;
import org.necklace.threp.domain.dto.create.OrderItem;
import org.necklace.threp.domain.mapper.OrderDataMapper;
import org.necklace.threp.domain.ports.input.service.OrderApplicationService;
import org.necklace.threp.domain.ports.output.repository.CustomerRepository;
import org.necklace.threp.domain.ports.output.repository.OrderRepository;
import org.necklace.threp.domain.ports.output.repository.RestaurantRepository;
import org.necklace.threp.domain.valueobject.Money;
import org.necklace.threp.domain.valueobject.ids.CustomerId;
import org.necklace.threp.domain.valueobject.ids.OrderId;
import org.necklace.threp.domain.valueobject.ids.ProductId;
import org.necklace.threp.domain.valueobject.ids.RestaurantId;
import org.necklace.threp.order.domain.entity.Customer;
import org.necklace.threp.order.domain.entity.Order;
import org.necklace.threp.order.domain.entity.Product;
import org.necklace.threp.order.domain.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {

  private final UUID CUSTOMER_ID = UUID.fromString("123-123");
  private final UUID RESTAURANT_ID = UUID.fromString("345-345");
  private final UUID PRODUCT_ID = UUID.fromString("678-678");
  private final UUID ORDER_ID = UUID.fromString("910-910");
  private final BigDecimal PRICE = new BigDecimal("200.00");
  @Autowired
  private OrderApplicationService orderApplicationService;
  @Autowired
  private OrderDataMapper orderDataMapper;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RestaurantRepository restaurantRepository;
  private CreateOrderCommand createOrderCommand;
  private CreateOrderCommand createOrderCommandWrongPrice;
  private CreateOrderCommand createOrderCommandWrongProductPrice;

  @BeforeAll
  public void init() {
    createOrderCommand = CreateOrderCommand.builder()
        .customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID)
        .address(OrderAddress.builder()
            .street("Street-1")
            .postalCode("100AB")
            .city("Perth")
            .build())
        .price(PRICE)
        .orderItems(List.of(OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(1)
                .price(new BigDecimal("50.00"))
                .subTotal(new BigDecimal("50.00"))
                .build(),
            OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(3)
                .price(new BigDecimal("50.00"))
                .subTotal(new BigDecimal("150.00"))
                .build()))
        .build();
    createOrderCommandWrongPrice = CreateOrderCommand.builder()
        .customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID)
        .address(OrderAddress.builder()
            .street("Street-1")
            .postalCode("100AB")
            .city("Perth")
            .build())
        .price(new BigDecimal("250.00"))
        .orderItems(List.of(OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(1)
                .price(new BigDecimal("50.00"))
                .subTotal(new BigDecimal("50.00"))
                .build(),
            OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(3)
                .price(new BigDecimal("50.00"))
                .subTotal(new BigDecimal("150.00"))
                .build()))
        .build();
    createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
        .customerId(CUSTOMER_ID)
        .restaurantId(RESTAURANT_ID)
        .address(OrderAddress.builder()
            .street("Street-1")
            .postalCode("100AB")
            .city("Perth")
            .build())
        .price(new BigDecimal("210.00"))
        .orderItems(List.of(OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(1)
                .price(new BigDecimal("60.00"))
                .subTotal(new BigDecimal("60.00"))
                .build(),
            OrderItem.builder()
                .productId(PRODUCT_ID)
                .quantity(3)
                .price(new BigDecimal("50.00"))
                .subTotal(new BigDecimal("150.00"))
                .build()))
        .build();

    Customer customer = new Customer();
    customer.setId(new CustomerId(CUSTOMER_ID));
    Restaurant restaurantResponse = Restaurant.Builder.builder()
        .id(new RestaurantId(createOrderCommand.getRestaurantId()))
        .products(List.of(
            new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
            new Product(new ProductId(PRODUCT_ID), "product2", new Money(new BigDecimal("50.00")))))
        .active(true)
        .build();
    Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
    order.setId(new OrderId(ORDER_ID));

    when(customerRepository.findCustomer(CUSTOMER_ID)).thenReturn(Optional.of(customer));
    when(restaurantRepository.findRestaurantInformation(
        orderDataMapper.createOrderCommandToRestaurant(createOrderCommand))).thenReturn(
        Optional.of(restaurantResponse));
    when(orderRepository.save(any(Order.class))).thenReturn(order);

  }
}
