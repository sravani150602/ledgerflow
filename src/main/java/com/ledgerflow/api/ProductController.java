package com.ledgerflow.api;
import com.ledgerflow.api.ApiDtos.*;
import com.ledgerflow.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController @RequestMapping("/api/v1/products") @RequiredArgsConstructor
public class ProductController {
  private final ProductService service;
  @PostMapping public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductRequest r) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r)); }
  @GetMapping("/{id}") public ProductResponse get(@PathVariable UUID id) { return service.get(id); }
}
