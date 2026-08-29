package com.ledgerflow.service;
import com.ledgerflow.api.ApiDtos.*;
import com.ledgerflow.domain.*;
import com.ledgerflow.event.OrderCreatedEvent;
import com.ledgerflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.*;

@Service @RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orders; private final ProductRepository products; private final ApplicationEventPublisher events;
  @Transactional
  public OrderResponse create(CreateOrderRequest request) {
    var order=new CustomerOrder(UUID.randomUUID(),request.customerId());
    for(var line:request.items()) order.addItem(products.findById(line.productId()).orElseThrow(()->new NoSuchElementException("Product not found: "+line.productId())),line.quantity());
    orders.save(order);
    var event=new OrderCreatedEvent(UUID.randomUUID(),order.getId(),Instant.now(),request.items().stream().map(i->new OrderCreatedEvent.Line(i.productId(),i.quantity())).toList());
    events.publishEvent(event);
    return map(order);
  }
  @Transactional(readOnly=true) public OrderResponse get(UUID id) { return map(orders.findDetailedById(id).orElseThrow(()->new NoSuchElementException("Order not found"))); }
  public OrderResponse map(CustomerOrder o) { return new OrderResponse(o.getId(),o.getCustomerId(),o.getStatus(),o.getTotalAmount(),o.getCreatedAt(),o.getItems().stream().map(i->new OrderLineResponse(i.getProduct().getId(),i.getProduct().getSku(),i.getQuantity(),i.getUnitPrice())).toList()); }
}
