package edu.dio.warehouse.service;

import edu.dio.warehouse.entity.ProductEntity;

import java.util.UUID;

public interface IProductQueryService {
    ProductEntity findById(final UUID id);
}
