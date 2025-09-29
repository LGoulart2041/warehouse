package edu.dio.warehouse.service.impl;

import edu.dio.warehouse.dto.ProductStorefrontSaveDTO;
import edu.dio.warehouse.dto.ProductStorefrontSavedDTO;
import edu.dio.warehouse.entity.ProductEntity;
import edu.dio.warehouse.mapper.IProductMapper;
import edu.dio.warehouse.repository.ProductRepository;
import edu.dio.warehouse.service.IProductQueryService;
import edu.dio.warehouse.service.IProductService;
import edu.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.swing.*;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final IProductQueryService productService;
    private final IStockService stockService;
    private final RestClient storefrontClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        repository.save(entity);
        var dto = mapper.toDTO(entity);
        saveStoreFront(dto);
        return entity;
    }

    @Override
    public void purchase(UUID id) {
        var entity = productService.findById(id);
        var stock = entity.decStock();
        repository.save(entity);
        if(stock.isUnavailable()){
            stockService.changeStatus(stock.getId(), stock.getStatus());
        }
    }

    private void saveStoreFront(ProductStorefrontSaveDTO dto) {
        storefrontClient.post()
                .uri("/products")
                .body(dto)
                .retrieve()
                .body(ProductStorefrontSavedDTO.class);
    }
}
