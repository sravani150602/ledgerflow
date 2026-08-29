package com.ledgerflow.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity @Table(name="order_items") @Getter @NoArgsConstructor
public class OrderItem {
  @Id private UUID id;
  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id", nullable=false) private CustomerOrder order;
  @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id", nullable=false) private Product product;
  @Column(nullable=false) private int quantity;
  @Column(nullable=false, precision=12, scale=2) private BigDecimal unitPrice;
  public OrderItem(UUID id, CustomerOrder order, Product product, int quantity, BigDecimal unitPrice) { this.id=id; this.order=order; this.product=product; this.quantity=quantity; this.unitPrice=unitPrice; }
}
