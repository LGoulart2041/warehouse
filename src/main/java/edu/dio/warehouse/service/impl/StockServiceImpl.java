package edu.dio.warehouse.service.impl;

import edu.dio.warehouse.dto.StockStatusMessage;
import edu.dio.warehouse.entity.StockEntity;
import edu.dio.warehouse.entity.StockStatus;
import edu.dio.warehouse.repository.ProductRepository;
import edu.dio.warehouse.repository.StockRepository;
import edu.dio.warehouse.service.IProductChangeAvailabilityProducer;
import edu.dio.warehouse.service.IProductService;
import edu.dio.warehouse.service.IStockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static edu.dio.warehouse.entity.StockStatus.AVAILABLE;
import static edu.dio.warehouse.entity.StockStatus.UNAVAILABLE;

@Service
@AllArgsConstructor
public class StockServiceImpl implements IStockService {

    private final StockRepository repository;
    private final ProductRepository productRepository;
    private final IProductChangeAvailabilityProducer producer;

    @Override
    public StockEntity save(StockEntity entity) {
        var product = productRepository.findById(entity.getProduct().getId())
                        .orElseThrow();
        entity.setProduct(product);
        return repository.save(entity);
    }

    @Override
    public void release(UUID id) {
        changeStatus(id, AVAILABLE);
    }

    @Override
    public void inactive(UUID id) {
        changeStatus(id, UNAVAILABLE);
    }

    @Override
    public void changeStatus(UUID id, StockStatus status) {
        var entity = repository.findById(id).orElseThrow();
        entity.setStatus(status);
        repository.save(entity);
        producer.notifyStatusChange(new StockStatusMessage(entity.getProduct().getId(), status));
    }
}
