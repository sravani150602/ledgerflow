package com.ledgerflow.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity @Table(name="orders") @Getter @Setter @NoArgsConstructor
public class CustomerOrder {
  @Id private UUID id;
  @Column(nullable=false) private UUID customerId;
  @Enumerated(EnumType.STRING) @Column(nullable=false, length=20) private OrderStatus status;
  @Column(nullable=false, precision=14, scale=2) private BigDecimal totalAmount;
  @Column(nullable=false) private Instant createdAt;
  @Column(nullable=false) private Instant updatedAt;
  @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true) private List<OrderItem> items = new ArrayList<>();
  public CustomerOrder(UUID id, UUID customerId) { this.id=id; this.customerId=customerId; status=OrderStatus.PENDING; totalAmount=BigDecimal.ZERO; createdAt=Instant.now(); updatedAt=createdAt; }
  public void addItem(Product product, int quantity) { var item=new OrderItem(UUID.randomUUID(), this, product, quantity, product.getPrice()); items.add(item); totalAmount=totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(quantity))); }
  public void transition(OrderStatus next) { if (status != OrderStatus.PENDING) throw new IllegalStateException("Order already finalized"); status=next; updatedAt=Instant.now(); }
}
