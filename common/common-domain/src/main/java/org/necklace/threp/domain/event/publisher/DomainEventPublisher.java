package org.necklace.threp.domain.event.publisher;

import org.necklace.threp.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

  void publish(T domainEvent);
}
