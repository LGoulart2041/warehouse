package edu.dio.warehouse.service;

import edu.dio.warehouse.entity.StockEntity;
import edu.dio.warehouse.entity.StockStatus;

import java.util.UUID;

public interface IStockService {

    StockEntity save(final StockEntity entity);

    void release(final UUID id);

    void inactive(final UUID id);

    void changeStatus(final UUID id, final StockStatus status);
}
