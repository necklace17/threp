package org.necklace.threp.domain.ports.output.repository;

import java.util.Optional;
import java.util.UUID;
import org.necklace.threp.order.domain.entity.Customer;

public interface CustomerRepository {

  Optional<Customer> findCustomer(UUID customerId);

}
