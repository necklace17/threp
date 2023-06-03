package org.necklace.threp.domain;

import org.mockito.Mockito;
import org.necklace.threp.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import org.necklace.threp.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import org.necklace.threp.domain.ports.output.message.publisher.restaurantapproval.OrderPaidPaymentRequestMessagePublisher;
import org.necklace.threp.domain.ports.output.repository.CustomerRepository;
import org.necklace.threp.domain.ports.output.repository.OrderRepository;
import org.necklace.threp.domain.ports.output.repository.RestaurantRepository;
import org.necklace.threp.order.domain.OrderDomainService;
import org.necklace.threp.order.domain.OrderDomainServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "org.necklace.threp")
class OrderTestConfiguration {

  @Bean
  public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderPaidPaymentRequestMessagePublisher orderPaidPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderPaidPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderRepository orderRepository() {
    return Mockito.mock(OrderRepository.class);
  }

  @Bean
  public CustomerRepository customerRepository() {
    return Mockito.mock(CustomerRepository.class);
  }

  @Bean
  public RestaurantRepository restaurantRepository() {
    return Mockito.mock(RestaurantRepository.class);
  }

  @Bean
  public OrderDomainService orderDomainService() {
    return new OrderDomainServiceImpl();
  }
}
