package com.ledgerflow.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name="products") @Getter @Setter @NoArgsConstructor
public class Product {
  @Id private UUID id;
  @Column(nullable=false, unique=true, length=80) private String sku;
  @Column(nullable=false, length=200) private String name;
  @Column(nullable=false, precision=12, scale=2) private BigDecimal price;
  @Column(nullable=false) private Instant createdAt;
  public Product(UUID id, String sku, String name, BigDecimal price) { this.id=id; this.sku=sku; this.name=name; this.price=price; this.createdAt=Instant.now(); }
}
