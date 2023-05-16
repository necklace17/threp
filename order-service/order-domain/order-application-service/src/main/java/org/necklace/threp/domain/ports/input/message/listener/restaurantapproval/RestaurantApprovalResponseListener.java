package org.necklace.threp.domain.ports.input.message.listener.restaurantapproval;

import org.necklace.threp.domain.dto.messages.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseListener {

  void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

  void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
