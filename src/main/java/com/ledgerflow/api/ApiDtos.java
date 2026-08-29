package com.ledgerflow.api;

import com.ledgerflow.domain.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public final class ApiDtos {
  private ApiDtos() {}
  public record CreateProductRequest(@NotBlank String sku, @NotBlank String name, @NotNull @DecimalMin("0.01") BigDecimal price, @Min(0) int initialQuantity) {}
  public record ProductResponse(UUID id, String sku, String name, BigDecimal price, int availableQuantity, int reservedQuantity) {}
  public record OrderLineRequest(@NotNull UUID productId, @Min(1) int quantity) {}
  public record CreateOrderRequest(@NotNull UUID customerId, @NotEmpty List<@Valid OrderLineRequest> items) {}
  public record OrderLineResponse(UUID productId, String sku, int quantity, BigDecimal unitPrice) {}
  public record OrderResponse(UUID id, UUID customerId, OrderStatus status, BigDecimal totalAmount, Instant createdAt, List<OrderLineResponse> items) {}
  public record ErrorResponse(Instant timestamp, int status, String error, String message, String path) {}
}
