package com.ledgerflow.api;
import com.ledgerflow.api.ApiDtos.*;
import com.ledgerflow.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/orders") @RequiredArgsConstructor
public class OrderController {
  private final OrderService service;
  @PostMapping public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest r) { return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.create(r)); }
  @GetMapping("/{id}") public OrderResponse get(@PathVariable UUID id) { return service.get(id); }
}
