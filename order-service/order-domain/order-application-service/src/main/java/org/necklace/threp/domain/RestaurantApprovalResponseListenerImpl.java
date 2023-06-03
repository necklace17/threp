package org.necklace.threp.domain;

import lombok.extern.slf4j.Slf4j;
import org.necklace.threp.domain.dto.messages.RestaurantApprovalResponse;
import org.necklace.threp.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@Validated
public class RestaurantApprovalResponseListenerImpl implements
    RestaurantApprovalResponseListener {

  @Override
  public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {

  }

  @Override
  public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {

  }
}
