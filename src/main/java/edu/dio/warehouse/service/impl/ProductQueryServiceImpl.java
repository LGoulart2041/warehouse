package edu.dio.warehouse.service.impl;

import edu.dio.warehouse.entity.ProductEntity;
import edu.dio.warehouse.repository.ProductRepository;
import edu.dio.warehouse.service.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements IProductQueryService {

    private final ProductRepository productRepository;

    @Override
    public ProductEntity findById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }
}
