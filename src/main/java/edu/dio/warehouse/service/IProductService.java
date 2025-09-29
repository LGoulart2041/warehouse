package edu.dio.warehouse.service;

import edu.dio.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    void purchase(final UUID id);
}
