package org.necklace.threp.domain.ports.output.repository;

import java.util.Optional;
import org.necklace.threp.order.domain.entity.Restaurant;

public interface RestaurantRepository {

  Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

}
