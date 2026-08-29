package com.ledgerflow.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name="inventory") @Getter @Setter @NoArgsConstructor
public class Inventory {
  @Id private UUID productId;
  @MapsId @OneToOne(fetch=FetchType.LAZY) @JoinColumn(name="product_id") private Product product;
  @Column(nullable=false) private int availableQuantity;
  @Column(nullable=false) private int reservedQuantity;
  @Column(nullable=false) private Instant updatedAt;
  @Version private long version;
  public Inventory(Product product, int available) { this.product=product; this.productId=product.getId(); this.availableQuantity=available; this.reservedQuantity=0; this.updatedAt=Instant.now(); }
  public void reserve(int quantity) { if (quantity <= 0 || availableQuantity < quantity) throw new IllegalStateException("Insufficient inventory"); availableQuantity -= quantity; reservedQuantity += quantity; updatedAt=Instant.now(); }
}
