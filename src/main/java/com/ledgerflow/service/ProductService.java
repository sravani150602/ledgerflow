package com.ledgerflow.service;
import com.ledgerflow.api.ApiDtos.*;
import com.ledgerflow.domain.*;
import com.ledgerflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ProductService {
  private final ProductRepository products; private final InventoryRepository inventory;
  @Transactional @CachePut(value="products", key="#result.id")
  public ProductResponse create(CreateProductRequest request) { if(products.findBySku(request.sku()).isPresent()) throw new IllegalArgumentException("SKU already exists"); var p=products.save(new Product(UUID.randomUUID(),request.sku(),request.name(),request.price())); var inv=inventory.save(new Inventory(p,request.initialQuantity())); return map(p,inv); }
  @Transactional(readOnly=true) @Cacheable(value="products", key="#id")
  public ProductResponse get(UUID id) { var p=products.findById(id).orElseThrow(()->new NoSuchElementException("Product not found")); var inv=inventory.findById(id).orElseThrow(); return map(p,inv); }
  private ProductResponse map(Product p, Inventory i) { return new ProductResponse(p.getId(),p.getSku(),p.getName(),p.getPrice(),i.getAvailableQuantity(),i.getReservedQuantity()); }
}
