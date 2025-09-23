package edu.dio.warehouse.service;


import edu.dio.warehouse.dto.StockStatusMessage;

public interface IProductChangeAvailabilityProducer {

    void notifyStatusChange(final StockStatusMessage message);
}
